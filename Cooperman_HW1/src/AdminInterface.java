import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An interface implemented by the Admin class, which lays out most of the functionality of the Administrator within the Course Registration System
 * @author Sivan Cooperman
 * @version 1.0
 */
public interface AdminInterface {
	/**
	 * Finds a student a list of students.
	 * @param username A String containing the student's username.
	 * @param students An ArrayList of Students.
	 * @return A Student object whose username matches the given username.
	 */
	public Student findStudent(String username, ArrayList<Student> students);
	
	/**
	 * Asks the user for a course id and displays information about that course.
	 * @param courses An ArrayList of Courses.
	 * @param scn A Scanner object for user input.
	 */
	public void displayCourse(ArrayList<Course> courses, Scanner scn);
	
	/**
	 * Asks the user for input and then creates a course and appends it to a list of courses.
	 * @param courses An ArrayList of Courses.
	 * @param scn A Scanner object for user input.
	 */
	public void createCourse(ArrayList<Course> courses, Scanner scn);
	
	/**
	 * Asks the user for a course id and section number and then removes it from a list of courses.
	 * @param courses An ArrayList of Courses.
	 * @param scn A Scanner object for user input.
	 */
	public void deleteCourse(ArrayList<Course> courses, Scanner scn);
	
	/**
	 * Asks the user for input and then calls the appropriate function to edit a course.
	 * @param courses An ArrayList of Courses.
	 * @param scn A Scanner object for user input.
	 */
	public void editCourseInfo(ArrayList<Course> courses, Scanner scn);
	
	/**
	 * Edits one of a course's integer attributes.
	 * @param course A Course object.
	 * @param field A String field to be edited ("capacity", "numStudents", or "section").
	 * @param value The new Integer value of the field.
	 * @return The edited course.
	 */
	public Course editCourse(Course course, String field, int value);
	
	/**
	 * Edits one of a course's string attributes.
	 * @param course A Course object.
	 * @param field A String field to be edited ("instructor" or "location").
	 * @param value The new String value of the field.
	 * @return The edited course.
	 */
	public Course editCourse(Course course, String field, String value);
	
	/**
	 * Edits one of a course's string array attributes.
	 * @param course A course object.
	 * @param field A String[] field to be edited ("student names").
	 * @param value The new String[] value of the field.
	 * @return The edited course.
	 */
	public Course editCourse(Course course, String field, ArrayList<String> value);
	
	/**
	 * Adds a new student to a list of students.
	 * @param studentList An ArrayList of Students
	 * @param student A Student object.
	 */
	public void registerStudent(ArrayList<Student> studentList, Scanner scn);
	
	/**
	 * Returns a list of all Courses for which the capacity attribute is equal to the numStudents attribute (i.e. The class is full).
	 * @param courses An ArrayList of Courses.
	 * @return An ArrayList of full courses
	 */
	public ArrayList<Course> getFullCourses(ArrayList<Course> courses);
	
	/**
	 * Asks the user for a file path, then writes all full courses from a list of courses to said file. If the file does not exist, it will be created. 
	 * If the path is not given, the file will be written in the local directory.
	 * @param courses A list of courses
	 * @param scn The Scanner the file path is gathered from
	 */
	public void writeFullCourses(ArrayList<Course> courses, Scanner scn) throws IOException;
	
	/**
	 * Displays all students registered for a course.
	 * @param courses An ArrayList of Courses.
	 * @param students An ArrayList of Students.
	 * @param scn A Scanner object for user input.
	 * @return A list of Students who have registered for the given course.
	 */
	public ArrayList<Student> viewRegisteredStudents(ArrayList<Course> courses, ArrayList<Student> students, Scanner scn);
	
	/**
	 * Displays all courses a student is registered for.
	 * @param students An ArrayList of Students.
	 * @param scn A Scanner object for user input.
	 * @return An ArrayList of Courses.
	 */
	public ArrayList<Course> viewRegisteredCourses(ArrayList<Student> students, Scanner scn);	
}
