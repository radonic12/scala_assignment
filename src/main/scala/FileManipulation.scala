import scala.io.Source
import java.io.FileOutputStream
import java.util.zip.GZIPOutputStream
import java.nio.file.Files
import java.nio.file.Paths

object FileManipulation {

  def zip_file(output_path:String,response_text:String)={
    try{
      val file_gzOut = new GZIPOutputStream(new FileOutputStream(output_path))
      val byteArray = response_text.getBytes
      file_gzOut.write(byteArray)
      file_gzOut.close
    } catch {
      case e: RuntimeException => println("File could not be compressed")
    }
  }

}