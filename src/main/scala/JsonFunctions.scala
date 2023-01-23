import upickle.default._
import java.io.UnsupportedEncodingException

object JsonFunctions {

  def validate_json(url:String,response_text:String)={
    try {
        val json = ujson.read(response_text)
    } catch {
        case e: UnsupportedEncodingException => println("Response body from url: " + url + " could not be parsed as JSON ")
    }
  }

}