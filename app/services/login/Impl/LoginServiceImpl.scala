package services.login.Impl

import com.typesafe.config.ConfigFactory
import domain.login.{ChangePassword, Login, LoginToken, Register}
import domain.mail.{EmailMessage, MessageResponse}
import domain.security.ResetToken
import domain.users.{User, UserPassword, UserRole}
import play.api.{Logger, Logging}
import play.api.mvc.Request
import services.login.{LoginService, LoginTokenService}
import services.mail.{EmailCreationMessageService, MailService}
import services.security.{ApiKeysService, AuthenticationService, ResetTokenService, TokenCreationService}
import services.users.{UserChangePasswordService, UserPasswordService, UserRoleService, UserService}
import util.{APPKeys, HelperUtil}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LoginServiceImpl extends LoginService with Logging {
  def className = getClass.getCanonicalName
  def isSecurityEnabled: Boolean = ConfigFactory.load().getBoolean("token-security.enabled")

  override val logger: Logger = Logger(className)

  override def isUserRegistered(user: Register): Future[Boolean] = {
    UserService.apply.isUserAvailable(user.email)
  }

  private def getResetToken(isRegistered: Boolean, resetToken: ResetToken): Future[Option[ResetToken]] = {
    if (isRegistered) Future.successful(None)
    else {
      ResetTokenService.apply.saveEntity(resetToken)
    }
  }

  private def sendResetPasswordMail(resetToken: Option[ResetToken], resetMessage: EmailMessage): Future[MessageResponse] = {
    if (resetToken.isDefined) MailService.sendGrid.sendMail(resetMessage)
    else Future.successful(MessageResponse(500, Map(), ""))
  }

  override def forgotPassword(register: Register): Future[Option[ResetToken]] = {
    val resetURL = HelperUtil.getSiteURL + "/login/passwordreset"
    val user = User(email = register.email)
    val resetKey = ApiKeysService.apply.generateResetToken()
    val resetToken = ResetToken(resetKey, register.email)
    val resetMessage = EmailCreationMessageService.apply.forgetPasswordLinkMessage(user, resetKey, resetURL)
    logger.info("Forgot Password Message: "+ resetMessage)
    for {
      isRegistered <- isUserRegistered(register) if isRegistered
      resetToken <- getResetToken(isRegistered, resetToken)
      _ <- sendResetPasswordMail(resetToken, resetMessage)
    } yield resetToken
  }

  override def register(register: Register): Future[Boolean] = {
    val tempPass = AuthenticationService.apply.generateRandomPassword() // generated password
    val hashedTempPass = AuthenticationService.apply.getHashedPassword(tempPass) // hash passwrd
    lazy val user = User(register.email)
    lazy val userRole = UserRole(user.email, APPKeys.STUDENTROLE)
    lazy val userPassword = UserPassword(user.email, hashedTempPass)
    lazy val emailMessage = EmailCreationMessageService.apply.createNewAccountMessage(user, tempPass) // get Email Message
    println(emailMessage)
    for {
      isRegistered <- isUserRegistered(register) if !isRegistered//check if user is available
      savedUser <- UserService.apply.saveEntity(user) if savedUser.isDefined // save the user
      savedUserPasswd <- UserPasswordService.apply.saveEntity(userPassword) if savedUserPasswd.isDefined //save hashed
      savedUserRole <- UserRoleService.roach.saveEntity(userRole) if savedUserRole.isDefined //save the role
      sendEmail <- MailService.sendGrid.sendMail(emailMessage)
    } yield {
      sendEmail.statusCode == 202
    }
  }

  private def authenticateUser(password: String, actualPass: String): Future[Boolean] = {
    Future.successful(AuthenticationService.apply.checkPassword(password, actualPass)) // compare
  }

  private def saveLoginToken(email: String, token: String): Future[Option[LoginToken]] = {
    val loginToken = LoginToken(email, token)
    for {
      _ <- LoginTokenService.apply.saveEntity(loginToken)
    } yield Some(loginToken)
  }

  private def getSecureChangePasswordObj(changePassword: ChangePassword, userPassword: UserPassword, newHashedPassword: String): ChangePassword = {
    val retdata = changePassword.copy(oldPassword = userPassword.password, newPassword = newHashedPassword)
    retdata
  }

  private def buildUpdatedUserPassword(changePassword: ChangePassword, newHashedPassword: String): UserPassword = {
    UserPassword(changePassword.email, newHashedPassword)
  }

  override def changePassword(changePassword: ChangePassword): Future[Option[LoginToken]] = {
    val newHashedPassword = AuthenticationService.apply.getHashedPassword(changePassword.newPassword)
    for {
      userPassword <- UserPasswordService.apply.getEntity(changePassword.email) //if userPassword.isDefined
      checkPassword <- authenticateUser(changePassword.oldPassword, userPassword.get.password) if checkPassword
      _ <- UserPasswordService.apply.saveEntity(buildUpdatedUserPassword(changePassword, newHashedPassword))
      _ <- UserChangePasswordService.apply.saveEntity(getSecureChangePasswordObj(changePassword, userPassword.get, newHashedPassword))
     loginToken <- getLoginToken(Login(changePassword.email, changePassword.newPassword))
    } yield loginToken

  }

  override def getLoginToken(login: Login): Future[Option[LoginToken]] = {
    for {
      user <- UserService.apply.getEntity(login.email) //if user.isDefined
      userPassword <- UserPasswordService.apply.getEntity(login.email) //if userPassword.isDefined
      userRole <- UserRoleService.roach.getEntity(login.email) //if userRole.isDefined
      checkPasswd <- authenticateUser(login.password, userPassword.get.password) if checkPasswd
      token <- TokenCreationService.apply.generateLoginToken(user.get, userRole.get.roleId)
      loginToken <- saveLoginToken(login.email, token)
    } yield {
      loginToken
    }
  }

  override def resetPasswordRequest(resetKey: String): Future[Boolean] = {
    for {
      resetToken <- ResetTokenService.apply.getEntity(resetKey) if resetToken.isDefined && resetToken.get.status.equals(APPKeys.ACTIVE)
      user <- UserService.apply.getEntity(resetToken.get.email) if user.isDefined
      send <- resetAccount(user) if send
      _ <- ResetTokenService.apply.saveEntity(resetToken.get.copy(status = APPKeys.INACTIVE))
    } yield send
  }

  private def resetAccount(user: Option[User]): Future[Boolean] = {
    val generatedPassword = AuthenticationService.apply.generateRandomPassword()
    val newHashedPassword = AuthenticationService.apply.getHashedPassword(generatedPassword)
    val userPassword = UserPassword(user.get.email, newHashedPassword)
    val emailMessage = EmailCreationMessageService.apply.passwordResetMessage(user.get, generatedPassword)
    for {
      _ <- UserPasswordService.apply.saveEntity(userPassword)
      emailResponse <- MailService.sendGrid.sendMail(emailMessage)
    } yield {
      emailResponse.statusCode == 202
    }
  }

  override def checkLoginStatus[A](request: Request[A]): Future[Boolean] = {
    println("Request: ", request.headers.get(APPKeys.AUTHORIZATION))
    val tokenWithBearer = request.headers.get(APPKeys.AUTHORIZATION).getOrElse("")
    val token = if (tokenWithBearer.contains("Bearer "))
      tokenWithBearer.replace("Bearer ", "") else tokenWithBearer
    val email = LoginTokenService.apply.getUserEmail(token)
    logger.info("Token: " + token)
    println("Token: " + token)
    logger.info("Email: " + email)
    println("Email: " + email)
    if (isSecurityEnabled) {
      if (LoginTokenService.apply.isTokenValid(token).isRight) {
        for {
          token <- LoginTokenService.apply.getEntity(email)
          // Might need to create a cache if Speed become an Issue
        } yield token.isDefined
      } else Future.successful(false)
    } else Future.successful(true)
  }

  override def logOut(register: Register): Future[Boolean] = {
    val emailToken = LoginToken(register.email, "")
    LoginTokenService.apply.deleteEntity(emailToken)
  }

  override def checkFileSize(size: Long): Future[Boolean] = {
    Future.successful(size < 10000000)
  }
}
