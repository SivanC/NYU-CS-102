import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements StudentInterface {
	
	private String fullname;
	
	/**
	 * Instantiate a Student object with a username, password, first name, and last name
	 * @param username The student's username. Must be unique
	 * @param password
	 * @param firstname
	 * @param lastname
	 */
	public Student(String username, String password, String firstname, String lastname) {
		super(username, password, firstname, lastname);
		this.setFullName(this.getFirstName() + " " + this.getLastName());
	}
	
	public String getFullName() {
		return fullname;
	}

	public void setFullName(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * Checks whether a student is in a course
	 * @param A Course object
	 * @return True if the student is in the course, false if not
	 */
	public boolean inCourse(Course course) {
		String fullname = this.getFirstName() + this.getLastName();
		if (course.getStudentNames().contains(fullname)) return true;
		return false;
	}
	
	/**
	 * Views all courses that the user is not currently in and that are not full
	 * @param A list of courses to check
	 * @return A list of available courses
	 */
	public ArrayList<Course> viewAvailableCourses(ArrayList<Course> courses) {
		ArrayList<Course> availableCourses = new ArrayList<Course>();
		for (Course course : courses) {
			if (!this.inCourse(course) && (course.getCapacity() != course.getNumStudents())) {
				availableCourses.add(course);
			}
		} return availableCourses;
	}
	
	/**
	 * Registers a student for a course by adding their name to the list of students and increasing the number of students by one
	 * @param course The course to be registered for
	 */
	public void register(Course course) {
		if (course.getNumStudents() != course.getCapacity()) {
			course.getStudentNames().add(this.getFirstName() + " " + this.getLastName());
			course.setNumStudents(course.getNumStudents() + 1);
		}
	}
	
	/**
	 * Withdraws a student from the course by removing their name from the roster and decrementing the student count. Prints an error message if the user was not in the course
	 * @param course The course from which to be withdrawn
	 */
	public void withdraw(Course course) {
		if (this.inCourse(course)) {
			course.getStudentNames().remove(this.getFullName());
			course.setNumStudents(course.getNumStudents() - 1);
		} else {
			System.out.println("Oops! Looks like you weren't in this course in the first place.");
		}
	}
	
	public String options(String selection) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Welcome, " + this.getFullName() + ". Please enter the number of the option you wish to select: \n"
				+ "1) View all courses \n"
				+ "2) View all courses that are not full \n"
				+ "3) Register in a course \n"
				+ "4) Withdraw from a course \n"
				+ "5) View all courses you are registered for \n"
				+ "6) Exit");
		String selection = scn.nextLine();
		if ("123456".contains(selection)) {
			scn.close();
			return selection;
		} else {
			System.out.println("Invalid selection. Restarting...");
			this.options();
		} 
		scn.close();
		return null;
	}
}
