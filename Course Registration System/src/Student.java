import java.util.ArrayList;
import java.util.Scanner;

/**
 * A child of the User class which implements the StudentInterface interface. A user with the ability to register for, withdraw from, and view courses.
 * Refer to StudentInterface.java and User.java for documentation of implemented methods.
 * @author Sivan Cooperman
 * @version 1.0
 */
public class Student extends User implements StudentInterface, java.io.Serializable {
	private static final long serialVersionUID = -3938274226533774976L;
	private String fullname;
	private ArrayList<Course> registered;
	
	/**
	 * Instantiate a Student object with a username, password, first name, and last name
	 * @param username The student's username. Must be unique
	 * @param password
	 * @param firstname
	 * @param lastname
	 */
	public Student(String username, String password, String firstname, String lastname) {
		// Call User constructor
		super(username, password, firstname, lastname);
		this.setFullName(this.getFirstName() + " " + this.getLastName());
		this.registered = new ArrayList<Course>();
	}
	
	// Full name getter and setter
	public String getFullName() {
		return this.fullname;
	}
	public void setFullName(String fullname) {
		this.fullname = fullname;
	}
	
	// Registered courses getter and setter
	public ArrayList<Course> getRegistered() {
		return this.registered;
	}
	public void setRegistered(ArrayList<Course> registered) {
		this.registered = registered;
	}

	public boolean inCourse(Course course) {
		if (course.getStudentNames().contains(this.getFullName())) return true;
		return false;
	}
	
	public ArrayList<Course> viewAvailableCourses(ArrayList<Course> courses) {
		ArrayList<Course> availableCourses = new ArrayList<Course>();
		for (Course course : courses) {
			if (!this.inCourse(course) && (course.getCapacity() != course.getNumStudents())) { // If the student is not currently enrolled and the course isn't full
				availableCourses.add(course);
			}
		} return availableCourses;
	}
	
	public void register(ArrayList<Course> courses, Scanner scn) {
		System.out.println("Please enter the name of the course you wish to register for: ");
		String name = scn.nextLine();
		System.out.println("Please enter the section number of the course you wish to register for: ");
		int section = Data.assertInt(scn.nextLine(), scn);
		
		ArrayList<Course> sections = Data.findCourseByName(name, courses);
		sections.removeIf(c -> (c.getSection() != section));
		boolean courseFound  = sections.size() == 0 ? false : true; // Course not found if there are no sections left over from the removeIf()
		if (courseFound) {
			Course course = sections.get(0);
			if (course.getStudentNames().contains(this.getFullName()))
				System.out.println("Oops! You are already enrolled in this course. Please try again.\n");
			else if (course.getNumStudents() < course.getCapacity()) { // If there's enough room
				course.getStudentNames().add(this.getFullName());
				course.setNumStudents(course.getNumStudents() + 1);
				this.getRegistered().add(course);
			} else
				System.out.println("Oops! This course is full. Please try again.\n");
		} else
			System.out.println("Oops! We couldn't find that course. Please try again.\n");
	}
	
	public void withdraw(ArrayList<Course> courses, Scanner scn) {
		System.out.println("Please enter the name of the course you wish to withdraw from: ");
		String name = scn.nextLine();
		
		ArrayList<Course> sections = Data.findCourseByName(name, courses);
		if (sections.size() == 0) {
			System.out.println("Oops! We couldn't find that course. Please try again.\n");
		}
		Course course = null;
		for (Course section : sections) {
			for (String studentName : section.getStudentNames()) {
				if (this.getFullName().equals(studentName)) {
					course = section;
				} else {
					System.out.println("Oops! Looks like you weren't in this course in the first place. Please try again.\n");
					return;
				}
			} 
			course.getStudentNames().remove(this.getFullName());
			course.setNumStudents(course.getNumStudents() - 1);
			ArrayList<Course> registered = this.getRegistered();
			registered.remove(course);
			this.setRegistered(registered);
		}
	}
	
	public ArrayList<Course> getAvailableCourses(ArrayList<Course> courses) {
		ArrayList<Course> availableCourses = (ArrayList<Course>) courses.clone();
		availableCourses.removeIf(course -> (course.getCapacity() == course.getNumStudents()));
		return availableCourses;
	}
	
	/**
	 * Prints to the console a list of options the student user can execute based on the user's input.
	 * @param scn A Scanner object for user input.
	 * @return The selection made by the user.
	 */
	public int[] options(Scanner scn) {
		System.out.println("1) View all courses\n"
				+ "2) View all courses that are not full\n"
				+ "3) Register for a course\n"
				+ "4) Withdraw from a course\n"
				+ "5) View all currently registered courses\n"
				+ "6) Exit \n"
				+ "\nPlease enter the number that corresponds to the command you wish to perform: \n");
		int selection = Data.assertInt(scn.nextLine(), scn);
		return new int[] {selection};
	}
}
