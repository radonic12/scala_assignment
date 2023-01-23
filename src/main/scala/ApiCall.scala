import sttp.client._
import sttp.model.Uri
import com.github.nscala_time.time.Imports._
import javax.management.InvalidAttributeValueException

object ApiCall {
    def call_api(url:Uri)={
        val api_key = sys.env("api_key")
        val request = basicRequest
            .header("X-Gravitee-Api-Key", "72287cf4-5270-42fa-90c0-69008a20f903")
            .get(url)
        implicit val backend = HttpURLConnectionBackend()
        val response = request.send()
        val response_body = response.body
        val response_text: String = response_body.fold(l => l.toString(), r => r.toString)
        val response_code:Int = response.code.code

        if (response_code != 200){
            throw new RuntimeException("http call to url: " + url + " not successful. Threw status code " + response_code + " and error: " + response_text) 
        }
        
        response_text
    }

    def construct_url(base_date:String, increment_value:Int, increment_unit:String)={
        val start_date:DateTime = DateTime.parse(base_date)
        var date_increment:org.joda.time.Period = (increment_value.months) 
        if (increment_unit=="month") {
            date_increment = (increment_value.months)}
        else if(increment_unit=="days") {
            date_increment = (increment_value.days)} 
        else if(increment_unit=="hours") {
            date_increment = (increment_value.hours)} 
        else if(increment_unit=="minutes"){ 
            date_increment = (increment_value.minutes) } 
        else if(increment_unit=="seconds"){ 
            date_increment = (increment_value.seconds)}
        else{
            throw new Exception("value " + increment_unit + " is not allowed for environment variable increment_value") 
        }
        val end_date:DateTime = start_date + date_increment
        val url_date_param:String = start_date.toString()+"/"+end_date.toString()
        val url_string = "https://dmigw.govcloud.dk/v2/metObs/collections/observation/items?datetime="+url_date_param
        
        url_string
    }
}