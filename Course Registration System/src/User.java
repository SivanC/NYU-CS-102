import java.util.ArrayList;
import java.util.Scanner;

/**
 * The parent class of the Admin and Student classes covers basic functionality such as logging in, viewing courses, and exiting the program.
 * @author Sivan Cooperman
 * @version 0.1
 */
public abstract class User {
	
	// private static final long seritalVersionUID = 1L;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private boolean exitBool = false;
	
	/**
	 * Instantiates a user without a first or last name (for use when creating admin users)
	 * @param username The user's username
	 * @param password The user's password
	 * @param firstName The user's first name
	 * @param lastName The user's surname
	 */
	public User(String username, String password, String firstname, String lastname) {
		this.setUsername(username);
		this.setPassword(password);
		this.setFirstName(firstname);
		this.setLastName(lastname);
	}
	
	/**
	 * Instantiates a user without a first or last name (for use when creating admin users)
	 * @param username The admin username
	 * @param password The admin password
	 */
	public User(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
	
	/**
	 * Checks whether a student's username is unique among a list of students
	 * @param username The student's username
	 * @param students A list of students within which the student's username should be unique
	 * @return Whether or not the student's username is unique
	 */
	public static boolean isUniqueUsername(String username, ArrayList<Student> students) {
		for (Student student : students) {
			if (username.equals(student.getUsername())) {
					return false;
			}
		} return true;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isExitBool() {
		return exitBool;
	}

	public void setExitBool(boolean exitBool) {
		this.exitBool = exitBool;
	}

	/**
	 * Takes a list of courses and assembles a print statement containing their relevant information
	 * @param courses A list of courses
	 * @return A print statement containing relevant information about each course
	 */
	public String viewCourses(ArrayList<Course> courses) {
		String toPrint = "";
		for (Course course : courses) {
			toPrint += course.toString();
		} return toPrint;
	}
	
	/**
	 * View all courses that a student has registered for
	 * @param courses An ArrayList of courses to check a student's registration in
	 * @param firstname The student's first name
	 * @param lastname The student's last name
	 */
	public ArrayList<Course> viewRegisteredCourses(ArrayList<Course> courses, String firstname, String lastname) {
		ArrayList<Course> registeredCourses = new ArrayList<Course>();
		for (Course course : courses) {
			for (String name : course.getStudentNames()) {
				if ((firstname + " " + lastname).equals(name)) {
					registeredCourses.add(course);
				}
			}
		} return registeredCourses;
	}
	
	/**
	 * Tells the program to exit
	 */
	public void exit() {this.exitBool = true;}
	
	/**
	 * Tells the program to display the options menu for a user. Options differ based on whether the user is an administrator or a student
	 */
	public abstract int options(Scanner scn);
}