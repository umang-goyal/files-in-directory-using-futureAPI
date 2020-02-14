import java.io.File

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class filesInDirectory {

  def getFiles(pathAsString: String): Unit = {

    val directory = new File(pathAsString)
    val pathAfterDirectory = pathAsString.split("/").reverse(0)

    if (directory.exists() && directory.isDirectory) {
      val filesInDirectory = directory.listFiles.toList

      def fileIterator(fileList: List[File]) {

        for (file <- fileList) {
          if (!file.isDirectory) {
            println(file.toString.split(pathAfterDirectory + "/").reverse(0))
          } else {
              fileIterator(file.listFiles.toList)
          }
        }
      }
      fileIterator(filesInDirectory)
    }
  }
}

object filesInDirectory extends App {
  val obj = new filesInDirectory
  obj.getFiles("/home/knoldus/knoldus-assignments/Folder1")
}