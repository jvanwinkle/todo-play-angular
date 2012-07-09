package controllers;

import static play.libs.Json.fromJson;
import static play.libs.Json.toJson;
import models.Task;

import org.codehaus.jackson.JsonNode;

import play.Routes;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result tasks() {
		return ok(toJson(Task.all()));
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result newTask() {
		JsonNode taskNode = request().body().asJson();
		return ok(toJson(Task.create(fromJson(taskNode, Task.class))));
	}

	public static Result deleteTask(Long id) {
		Task.delete(id);
		return ok();
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result updateTask() {
		JsonNode taskNode = request().body().asJson();
		return ok(toJson(Task.update(fromJson(taskNode, Task.class))));
	}

    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
            Routes.javascriptRouter("jsRoutes",
                controllers.routes.javascript.Application.tasks(),
                controllers.routes.javascript.Application.updateTask(),
                controllers.routes.javascript.Application.newTask(),
                controllers.routes.javascript.Application.deleteTask()
            )
        );
    }
}