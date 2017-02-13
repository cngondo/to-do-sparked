import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
   
    public static void main(String[] args) {
    	staticFileLocation("/public");
	    String layout = "templates/layout.vtl";

	    //the root route loading the index.vtl
	    get("/", (request, response) -> {
	    	Map<String, Object> model = new HashMap<String, Object>();
	    	model.put("templates", "templates/index.vtl");
	    	return new ModelAndView(model, layout);  	
	    }, new VelocityTemplateEngine());
    }
}
