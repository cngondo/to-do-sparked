import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;

public class App {
   
    public static void main(String[] args) {
    	staticFileLocation("/public");
	    String layout = "templates/layout.vtl";

	    //the root route loading the index.vtl
	    get("/", (request, response) -> {
	    	Map<String, Object> model = new HashMap<String, Object>();
	    	//retrieve task session and placing it in the model
	    	model.put("tasks", request.session.attribute("tasks"));	    	
	    	model.put("templates", "templates/index.vtl");
	    	return new ModelAndView(model, layout);  	
	    }, new VelocityTemplateEngine());

	    //the task route
	    /*
		* 
	    */
	    post("/task", (request, response) -> {
	    	Map<String, Object> model = new HashMap<String, Object>();

	    	//save our multiple tasks into an array List
	    	ArrayList<Task> tasks = request.session.attribute("task", newTask);
	    	if(tasks == null){
	    		tasks = new ArrayList<Task>();
	    		request.session.attribute("task", newTask);
	    	}
	    	//save our task object to the user session
	    	String description = request.queryparams("description");
	    	Task newTask = new Task(description);
	    	tasks.add(newTask);

	    	model.put("template","templates/success.vtl");
	    	return new ModelAndView(model, layout);
	    }, new VelocityTemplateEngine());
    }
}
