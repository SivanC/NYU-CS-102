import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

/**
 * A child of the User class which implements the AdminInterface interface. A user with the ability to edit, create, and delete courses,
 *  as well as register students and a number of other functions. Refer to AdminInterface.java and User.java for documentation of implemented methods.
 * @author Sivan Cooperman
 * @version 1.0
 */
public class Admin extends User implements AdminInterface {

	/**
	 * Constructor that takes a username and a password.
	 * @param username The admin's username
	 * @param password The admin's password
	 */
	public Admin(String username, String password) {
		// Call user constructor
		super(username, password);
	}
	
	public Student findStudent(String username, ArrayList<Student> students) {
		// Look for the student in a list of students
		for (Student student : students) {
			if (username.equals(student.getUsername()))
				return student;
		// If the student isn't found, return null
		} return null;
	}
	
	public void displayCourse(ArrayList<Course> courses, Scanner scn) {
		System.out.println("Please enter the ID of the course you wish to display");
		String id = scn.nextLine();
		
		ArrayList<Course> sections = null;
		sections = Data.findCourseByID(id, courses);
		if (sections.size() == 0) { // If there are no sections of the course then the course does not exist
			System.out.println("Oops! We couldn't find that course. Please try again.");
			return;
		} // Print all courses
		for (Course course : sections)
			System.out.println(course);
	}
	
	public void createCourse(ArrayList<Course> courses, Scanner scn) {
		System.out.println("Enter a name for the course: ");
		String name = scn.nextLine(); // Name
		System.out.println("Enter a course ID: ");
		String id = scn.nextLine(); // Id
		System.out.println("Enter the maximum capacity for this section: ");
		int capacity = Data.assertInt(scn.nextLine(), scn); // Maximum section capacity
		System.out.println("Enter an instructor name: ");
		String instructor = scn.nextLine(); // Instructor
		System.out.println("Enter a course section number: ");
		int section = Data.assertInt(scn.nextLine(), scn); // Section number (each class section must be created separately)
		System.out.println("Enter a section location: ");
		String loc = scn.nextLine(); // Section location
		
		System.out.println("Creating course...");
		Course newCourse = new Course(name, id, capacity, 0, new ArrayList<String>(), instructor, section, loc); // Passing arguments to Course constructor (currently enrolled students set to 0)
		courses.add(newCourse); // Adding course to course list
	}
	
	public void deleteCourse(ArrayList<Course> courses, Scanner scn) {
		System.out.println("Please enter the course ID of the course you wish to delete: ");
		String id = scn.nextLine();
		System.out.println("Please enter the section you wish to delete. Press 0 to delete all sections: ");
		int section = Data.assertInt(scn.nextLine(), scn);
		
		System.out.println("Deleting course...");
		
		if (section == 0) { // If 0 pressed
			if (courses.removeIf(course -> (course.getID().equals(id)))) // Remove all sections with given course ID
				System.out.println("Course deleted!");
			else
				System.out.println("Oops! We couldn't find that course. Please try again.\n");
		} else 
			if (!courses.removeIf(course -> (course.getID().equals(id) && course.getSection() == section))) // if we don't remove any courses with the specified section id
				System.out.print("Oops! We couldn't find that section. Please try again.\n.");
	}
	
	public void editCourseInfo(ArrayList<Course> courses, Scanner scn) {
		System.out.println("Please enter the Course ID of the course you wish to edit: ");
		String id = scn.nextLine();
		System.out.println("Please enter the section number of the section you wish to edit: ");
		int section = Data.assertInt(scn.nextLine(), scn);
		
		Course course = Data.findCourseByID(id, section, courses);
		
		if (course != null) {
			System.out.println("Please enter the attribute you wish to edit"
					+ " (capacity, number of students, section, instructor, location, student names): ");
			String field = scn.nextLine();
			switch (field) { // The cases are grouped by data type (e.g. capacity numStudents and section are all integer values, so they call the same function)
				case "capacity":
				case "number of students":
				case "section":
					System.out.println("Please enter the integer value you wish to set " + field + " to: ");
					int intval = Data.assertInt(scn.nextLine(), scn);
					course = editCourse(course, field, intval); // TODO: It is potentially not necessary to assign editCourse to a value
					break;
				case "instructor":
				case "location":
					System.out.println("Please enter the value you wish to set " + field + " to: ");
					String strval = scn.nextLine();
					editCourse(course, field, strval);
					break;
				case "student names":
					System.out.println("Would you like to append or remove names from the list? ");
					String selection = scn.nextLine();
					
					if (selection.equals("append")) {
						System.out.println("Please enter a comma-separated list of names that you wish to append: ");
						ArrayList<String> n = new ArrayList<String>(Arrays.asList(scn.nextLine().split(",")));
						ArrayList<String> names = course.getStudentNames();
						names.addAll(n);
						course.setStudentNames(names);
						editCourse(course, field, names);
					} else if (selection.equals("remove")) {
						System.out.println("Please enter a comma-separated list of names that you wish to remove: ");
						ArrayList<String> n = new ArrayList<String>(Arrays.asList(scn.nextLine().split(",")));
						ArrayList<String> names = course.getStudentNames();
						names.removeAll(n);
						course.setStudentNames(names);
						editCourse(course, field, names);
					} else
						System.out.println("Oops! You must select 'append' or 'remove'. Please try again.\n");
					break;
				default:
					System.out.println("Oops! We couldn't find that field. Please try again.\n");
			}
		} else
			System.out.println("Oops! We couldn't find that course. Please try again.\n");
	}
	
	public Course editCourse(Course course, String field, int value) {
		switch(field) {
			case "capacity":
				course.setCapacity(value);
				break;
			case "number of students":
				course.setNumStudents(value);
				break;
			case "section":
				course.setSection(value);
				break;
		} return course;
	}
	
	public Course editCourse(Course course, String field, String value) {
		switch(field) {
			case "instructor":
				course.setInstructor(value);
				break;
			case "location":
				course.setLoc(value);
				break;
		} return course;
	}
	
	public Course editCourse(Course course, String field, ArrayList<String> value) {
		switch(field) {
			case "student names":
				course.setStudentNames(value);
		} return course;
	}
	
	public void registerStudent(ArrayList<Student> studentList, Scanner scn) {
		System.out.println("Please enter the student's first name: ");
		String first = scn.nextLine();
		System.out.println("Please enter the student's last name: ");
		String last = scn.nextLine();
		System.out.println("Please enter the student's username: ");
		String username = scn.nextLine();
		System.out.println("Please enter the student's password: ");
		String password = scn.nextLine();
		
		for (Student student : studentList) {
			if (student.getUsername().equals(username)) {
				System.out.println("Oops! This username is already taken. Please try again.");
				return;
			}
		}
		Student student = new Student(username, password, first, last);
		studentList.add(student);
	}
	
	public ArrayList<Course> getFullCourses(ArrayList<Course> courses) {
		ArrayList<Course> fullCourses = (ArrayList<Course>) courses.clone();
		fullCourses.removeIf(course -> (course.getCapacity() != course.getNumStudents())); // Removing courses whose capacity is not equal to their current number of students
		return fullCourses;
	}
	
	public void writeFullCourses(ArrayList<Course> courses, Scanner scn) throws IOException {
		System.out.println("Please enter the name of the file you wish to write to (if it does not exist it will be created in the local directory): ");
		String filepath = scn.nextLine();
		
		ArrayList<Course> fullCourses = this.getFullCourses(courses);
		try { // Writing full courses to file
			BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
			System.out.println("Writing...");
			for (Course course : fullCourses) {
				writer.write(course.toString());
				writer.write("\n\n");
			} writer.close();
			System.out.println("Writing complete!");
		} catch (IOException e) { // If something is wrong
			System.out.println("Oops! There was an error writing to the file. Please try again.\n");
		}
	}
	
	public ArrayList<Student> viewRegisteredStudents(ArrayList<Course> courses, ArrayList<Student> students, Scanner scn) {
		System.out.println("Please enter the ID of the course: ");
		String courseID = scn.nextLine();
		
		ArrayList<Student> registeredStudents = new ArrayList<Student>();
		ArrayList<Course> sections = Data.findCourseByID(courseID, courses);
		if (sections != null) {
			for (Course section : sections) { // for every section of the course, compare each student to each name and see if they match
				for (String name : section.getStudentNames()) {
					for (Student student : students) {
						if (name.equals(student.getFullName()))
							registeredStudents.add(student);
					}
				}
			}
		} return registeredStudents;
	}
	
	public ArrayList<Course> viewRegisteredCourses(ArrayList<Student> students, Scanner scn) {
		System.out.println("Please enter the username of the desired student: ");
		String fullname = scn.nextLine();
		
		Student student = this.findStudent(fullname, students);
		if (student == null) { // If we can't find the student
			System.out.println("Oops! We couldn't find that student. Please try again.\n");
			return null;
		} return student.getRegistered();
	}

	/**
	 * Prints to the console a list of options the admin user can execute based on the user's input.
	 * @param scn A Scanner object for user input.
	 * @return The selection and subselection made by the user.
	 */
	public int[] options(Scanner scn) {
		System.out.println("\n1) Course Management \n"
				+ "2) Reports \n"
				+ "3) Exit \n"
				+ "\nPlease enter the number that corresponds to the command you wish to perform: ");
		int selection = Data.assertInt(scn.nextLine(), scn);
		// If Course Management
		if (selection == 1) {
			System.out.println("\nCourse Management: \n"
				+ "1) Create a new course \n"
				+ "2) Delete a course \n"
				+ "3) Edit a course \n"
				+ "4) Display information for a course \n"
				+ "5) Register a student in the Course Registration System \n"
				+ "6) Back");
			// Loop until the user selects a valid option
			int subselection;
			do {
				System.out.println("\nPlease enter the number that corresponds to the command you wish to perform: ");
				subselection = Data.assertInt(scn.nextLine(), scn);
			} while (!(1 <= subselection && 6 >= subselection)); // While the user hasn't selected an option between 1 and 6
			return new int[] {selection, subselection};
		// If Reports
		} else if (selection == 2) {	
			System.out.println("\nReports: \n"
					+ "1) View all courses \n"
					+ "2) View all FULL courses \n"
					+ "3) Write all FULL courses to a file \n"
					+ "4) View all students registered for a specific course \n"
					+ "5) View all courses a student has registered for \n"
					+ "6) Sort all courses by number of students registered \n"
					+ "7) Back");
			// Loop until the user selects a valid option
			int subselection;
			do {
				System.out.println("\nPlease enter the number that corresponds to the command you wish to perform: ");
				subselection = Data.assertInt(scn.nextLine(), scn);
			} while (!(1 <= subselection && 7 >= subselection)); // While the user hasn't selected an option between 1 and 7
			return new int[] {selection, subselection};
		} return new int[] {-1, -1};
	}
}