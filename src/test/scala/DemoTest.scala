import org.scalatest._
import java.nio.file.{Paths, Files}

class DemoTest extends FlatSpec with Matchers {
    "Main" should "product zip file" in {
        val output_folder:String = "test_output"
        setEnv(key="base_date",value="2018-02-12T00:00:00Z")
        setEnv(key="increment_unit",value="seconds")
        setEnv(key="increment_value",value="2")
        setEnv(key="output_folder",value=output_folder)
        setEnv(key="api_key",value=<api_key>)
        
        Main.call_main()

        var output_path = output_folder+"/json_response_0.json.gz"
        assert(Files.exists(Paths.get(output_path))==true)

        output_path = output_folder+"/json_response_1.json.gz"
        assert(Files.exists(Paths.get(output_path))==true)
        
        output_path = output_folder+"/json_response_2.json.gz"
        assert(Files.exists(Paths.get(output_path))==true)

    }
    def setEnv(key: String, value: String) = {
        val field = System.getenv().getClass.getDeclaredField("m")
        field.setAccessible(true)
        val map = field.get(System.getenv()).asInstanceOf[java.util.Map[java.lang.String, java.lang.String]]
        map.put(key, value)
    }
}