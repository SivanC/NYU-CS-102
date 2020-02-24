import java.util.Scanner;

/**
 * The parent class of the Admin and Student classes covers basic functionality such as logging in, viewing courses, and exiting the program.
 * @author Sivan Cooperman
 * @version 1.0
 */
public abstract class User implements java.io.Serializable {

	private static final long serialVersionUID = 3292649860699175832L; // Unique ID for serialization
	private String username; // The user's unique username
	private String password; // The user's password
	private String firstname; // The user's (student's) first name
	private String lastname; // The user's (student's) last name
	
	/**
	 * Instantiates a User child (likely an Admin) with a username and a password.
	 * @param username A unique username.
	 * @param password A password.
	 */
	public User(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
	
	/**
	 * Instantiates a User child (likely a Student) with a username, password, and first and last name.
	 * @param username A unique username.
	 * @param password A password.
	 * @param firstname A first name.
	 * @param lastname A last name.
	 */
	public User(String username, String password, String firstname, String lastname) {
		this(username, password);
		this.setFirstName(firstname);
		this.setLastName(lastname);
	}
	
	// Username getter and setter
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	// Password getter and setter
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// First name getter and setter
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	// Last name getter and setter
	public String getLastName() {
		return lastname;
	}
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}
	
	/**
	 * Tells the program to display the options menu for a user. Options differ based on whether the user is an administrator or a student.
	 * @param scn A Scanner object for user input.
	 * @return The choice(s) the user has selected.
	 */
	public abstract int[] options(Scanner scn);
}