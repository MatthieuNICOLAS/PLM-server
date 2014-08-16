package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

@Entity
public class Course extends Model {
	
	@Id
	public String name;
	
	public String displayName;
	
	public String programmingLanguage;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	public List<Student> students = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	public List<Teacher> teachers = new ArrayList<>();
	
	public static Finder<String, Course> find = new Finder<String, Course>(String.class, Course.class);

	public Course(String name) {
		this.name = name;
	}

    public static int count() {
        return find.findRowCount();
    }
	
	public static List<Course> all() {
	  return find.all();
	}

	public static void create(Course course) {
		System.out.println("Course :\n"
				+ "name : " + course.name);
		course.save();
		course.saveManyToManyAssociations("students");
		course.saveManyToManyAssociations("teachers");
	}
	
	public static void addTeacher(Course course, Teacher teacher) {
		course.teachers.add(teacher);
		course.save();
		course.saveManyToManyAssociations("students");
		course.saveManyToManyAssociations("teachers");

	}
	
	public static void delete(String name, String s) {
		find.byId(name).delete();
	}
	
}