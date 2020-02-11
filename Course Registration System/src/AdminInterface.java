import java.io.IOException;
import java.util.ArrayList;

/**
 * An interface implemented by the Admin class, which lays out most of the functionality of the Administrator within the Course Registration System
 * @author Sivan Cooperman
 * @version 1.0
 */
public interface AdminInterface {
	/**
	 * Creates a new Course object, which represents a section of a course.
	 * @param name The name of the course in plain English
	 * @param id The name of the class within the school's identification system
	 * @param capacity The number of students who can be enrolled in the class at one time
	 * @param numStudents The number of students currently enrolled in the class
	 * @param studentNames A list of full names of students currently enrolled in the class
	 * @param instructor The full name of the course's instructor
	 * @param section The section number of this section of the course
	 * @param loc The location of the class
	 * @return The created course
	 */
	public Course createCourse(String name, String id, int capacity, int numStudents, ArrayList<String> studentNames, String instructor,
			int section, String loc);
	/**
	 * Deletes a course from the given course list, provided a course id.
	 * @param courses A list of courses from which the selected course should be removed
	 * @param id The ID of the course to be removed
	 * @return The list of courses sans the removed course
	 */
	public ArrayList<Course> deleteCourse(ArrayList<Course> courses, String id);
	/**
	 * Takes a course, Integer field to be changed, and a value, and edits the course's selected attribute
	 * @param course The course to be edited
	 * @param field The field to be edited ("capacity", "numStudents", or "section")
	 * @param value The new value of the field
	 * @return The edited course
	 */
	public Course editCourse(Course course, String field, int value);
	/**
	 * Takes a course, String field to be changed, and a value, and edits the course's selected attribute
	 * @param course The course to be edited
	 * @param field The field to be edited ("instructor" or "location")
	 * @param value The new value of the field
	 * @return The edited course
	 */
	public Course editCourse(Course course, String field, String value);
	/**
	 * Takes a course, ArrayList<String> field to be changed, and a value, and edits the course's selected attribute
	 * @param course The course to be edited
	 * @param field The field to be edited ("student names")
	 * @param value The new value of the field
	 * @return The edited course
	 */
	public Course editCourse(Course course, String field, ArrayList<String> value);
	/**
	 * Takes an ArrayList of Students and adds a new Student object to it
	 * @param studentList An ArrayList of Students to which the new Student should be added
	 * @param student The Student object to be added
	 * @return The updated Student list
	 */
	public ArrayList<Student> registerStudent(ArrayList<Student> studentList, Student student);
	/**
	 * Returns a list of all Courses for which the capacity attribute is equal to the numStudents attribute (i.e. The class is full)
	 * @param courses The list of courses to be iterated through
	 * @return An ArrayList of full courses
	 */
	public ArrayList<Course> getFullCourses(ArrayList<Course> courses);
	/**
	 * Takes a list of courses and a file path, and writes all full courses to the file
	 * @param courses A list of courses
	 * @param filepath The file path (including the file) to which the full courses should be written
	 */
	public void writeFullCourses(ArrayList<Course> courses, String filepath) throws IOException;
	/**
	 * Takes a course and a list of students, and returns a list of all Student enrolled in the given course
	 * @param course The course to be checked for
	 * @param students A list of Students
	 * @return A list of Students who have enrolled in the given course
	 */
	public ArrayList<Student> viewRegisteredStudents(Course course, ArrayList<Student> students);
	/**
	 * Sorts a list of courses by the number of students registered, from most to fewest
	 * @param courses A list of courses
	 * @return The sorted ArrayList of courses
	 */
	public ArrayList<Course> sortCourses(ArrayList<Course> courses);
}
