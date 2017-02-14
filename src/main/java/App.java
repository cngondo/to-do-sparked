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
	    	//retrieve task session and placing it in the model
	    	model.put("task", request.session.attribute("task"));	    	
	    	model.put("templates", "templates/index.vtl");
	    	return new ModelAndView(model, layout);  	
	    }, new VelocityTemplateEngine());

	    //the task route
	    /*
		* 
	    */
	    post("/task", (request, response) -> {
	    	Map<String, Object> model = new HashMap<String, Object>();

	    	String description = request.queryparams("description");
	    	//save our task object to the user session
	    	Task newTask = new Task(description);
	    	request.session.attribute("task", newTask);

	    	model.put("template","templates/success.vtl");
	    	return new ModelAndView(model, layout);
	    }, new VelocityTemplateEngine());
    }
}
