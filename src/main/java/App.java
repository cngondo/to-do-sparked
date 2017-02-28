import java.util.Map;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

public class App {
   
    public static void main(String[] args) {
    	staticFileLocation("/public");
	    String layout = "templates/layout.vtl";

	    //the root route loading the index.vtl
	    get("/", (request, response) -> {
	    	Map<String, Object> model = new HashMap<String, Object>();
	    	//retrieve task session and placing it in the model
	    	model.put("tasks", request.session().attribute("tasks"));	    	
	    	model.put("template", "templates/index.vtl");
	    	return new ModelAndView(model, layout);  	
	    }, new VelocityTemplateEngine());

	    //the tasks route
	    /*
	    */
	    post("/tasks", (request, response) -> {
	    	Map<String, Object> model = new HashMap<String, Object>();

	    	//save our multiple tasks into an array List
	    	ArrayList<Task> tasks = request.session().attribute("tasks");
	    	if(tasks == null){
	    		tasks = new ArrayList<Task>();
	    		request.session().attribute("tasks", tasks);
	    	}

	    	//save our task object to the user session	    	
	    	String description = request.queryParams("description");
	    	Task newTask = new Task(description);

	    	tasks.add(newTask);

	    	model.put("template","templates/success.vtl");
	    	return new ModelAndView(model, layout);
	    }, new VelocityTemplateEngine());
    }
}
