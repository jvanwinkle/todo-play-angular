package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Task extends Model {

	private static final long serialVersionUID = 7428501291844515265L;

	@Id
	public Long id;
	public String text;
	public boolean done;

	public static Finder<Long, Task> find = new Finder(Long.class, Task.class);

	public static List<Task> all() {
		return find.all();
	}

	public static Task create(Task task) {
		task.save();
		return task;
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static Task update(Task task) {
		task.update();
		return task;
	}

}