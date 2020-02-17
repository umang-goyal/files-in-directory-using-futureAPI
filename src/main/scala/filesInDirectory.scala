import java.io.File

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class filesInDirectory {

  def getFiles(directoriesList: List[File]): List[File]={
    @scala.annotation.tailrec
    def innerFindAllFiles(fileList: List[File], folderList: List[File]): List[File]={
      folderList match {
        case head :: tail => innerFindAllFiles(fileList::: head.listFiles.filter(_.isFile).toList,
          tail:::head.listFiles.filter(_.isDirectory).toList)
        case Nil => fileList
      }
    }
    innerFindAllFiles(List(), directoriesList)
  }
}

object filesInDirectory extends App{
  //val log = Logger.getLogger{this.getClass}
  val obj = new filesInDirectory
  val path = "/home/knoldus/knoldus-assignments/Folder1"
  val futureListOfFiles: Future[List[File]] = Future{
    obj.getFiles(List(new File(path)))
  }
  futureListOfFiles.onComplete{
      case Success(value)=> println(value)
      case Failure(exception)=> println(exception.getMessage )
    }
  Thread.sleep(7000)
}

