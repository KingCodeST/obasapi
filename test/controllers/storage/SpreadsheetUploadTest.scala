package controllers.storage

import java.io.{File, FileInputStream}

import org.apache.poi.ss.usermodel.{DataFormatter, WorkbookFactory}
import org.scalatest.FunSuite

class SpreadsheetUploadTest extends FunSuite {
  val file = new File("/home/dit-lab/IdeaProjects/obasapi/test/controllers/storage/Sample_File.xlsx")
  println(file.getAbsolutePath)
  val logo = new FileInputStream(file)
  val baseUrl = "http://localhost:9000"
  val UPLOAD = "upload"
  val SITEID = "SITEID"

  test("Test File Upload ") {
    println(file.getAbsolutePath)




  }

}
