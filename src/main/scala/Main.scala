import sttp.client._
import sttp.model.Uri
import upickle.default._
import scala.util.Try

object Main extends App {
  def call_main(){
    //initialize all varibles
    val base_date = sys.env("base_date")
    val increment_unit = sys.env("increment_unit")
    val increment_value = sys.env("increment_value").toInt
    val output_folder = sys.env("output_folder")
    var output_path = output_folder+"/json_response_0.json.gz"
    
    //add period to url
    val url_string:String = ApiCall.construct_url(base_date=base_date, increment_value=increment_value, increment_unit=increment_unit)
    var url_parsed = Uri.parse(url_string)
    var url:Uri = url_parsed.right.get
    
    //call api and save response as gzipx
    var response_text:String = ApiCall.call_api(url=url)
    JsonFunctions.validate_json(url=url.toString(), response_text=response_text)
    FileManipulation.zip_file(output_path=output_path, response_text=response_text)

    //loop until there is no more pages to download
    var bool = true
    var counter = 1
    while( bool == true ){
      val json:ujson.Value.Value = ujson.read(response_text)
      var next_page:String = Try(json("links")(1)("href").toString().replaceAll("^\"|\"$", "")).getOrElse("no next")

      if (next_page == "no next"){
        bool = false
      }
      else{
        url_parsed = Uri.parse(next_page)
        url = url_parsed.right.get

        response_text = ApiCall.call_api(url=url)
        JsonFunctions.validate_json(url=url.toString(), response_text=response_text)
        
        var output_path = output_folder+"/json_response_"+counter+".json.gz"
        FileManipulation.zip_file(output_path=output_path, response_text=response_text)
        counter += 1
      }
    }
  }

  call_main()
}