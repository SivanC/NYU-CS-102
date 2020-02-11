import java.util.ArrayList;

public interface StudentInterface {
	public ArrayList<Course> viewAvailableCourses(ArrayList<Course> courses);
	public void register(Course course);
	public void withdraw(Course course);
	public boolean inCourse(Course course);
}
