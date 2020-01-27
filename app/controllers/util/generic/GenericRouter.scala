package controllers.util.generic

import javax.inject.Inject
import play.api.routing.sird._
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter

class GenericRouter @Inject()(genericStatusController: GenericStatusController)
  extends SimpleRouter {
  override def routes: Routes = {

    // STATUS
    case GET(p"/status/all") =>
      genericStatusController.gatAll
    case GET(p"/status/get/$statusId") =>
      genericStatusController.read(statusId)
    case GET(p"/status/incomplete") =>
      genericStatusController.getIncompleteStatus
    case GET(p"/status/complete") =>
      genericStatusController.getCompleteStatus
    case POST(p"/status/create") =>
      genericStatusController.create
    case POST(p"/status/update") =>
      genericStatusController.update
    case POST(p"/status/delete") =>
      genericStatusController.delete

  }
}
