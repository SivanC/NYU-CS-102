import java.util.ArrayList;
import java.util.Scanner;

public interface StudentInterface {
	/**
	 * Displays all courses that the student is not currently registered for and that are not full.
	 * @param courses An ArrayList of Courses.
	 * @return An ArrayList of available Courses.
	 */
	public ArrayList<Course> viewAvailableCourses(ArrayList<Course> courses);
	
	/**
	 * Registers a student in a course.
	 * @param courses An ArrayList of Courses.
	 * @param scn A Scanner object for user input.
	 */
	public void register(ArrayList<Course> courses, Scanner scn);
	
	/**
	 * Withdraws a student from a course.
	 * @param courses An ArrayList of courses.
	 * @param scn A Scanner object for user input.
	 */
	public void withdraw(ArrayList<Course> courses, Scanner scn);
	
	/**
	 * Checks whether a student is registered in a course.
	 * @param A Course object.
	 * @return True if the student is in the course, false if not.
	 */
	public boolean inCourse(Course course);
}
