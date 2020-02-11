import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * A child of the user class which implements the AdminInterface interface. A user with the ability to edit, create, and delete courses,
 *  as well as register students and a number of other functions. Refer to AdminInterface.java for documentation of implemented methods.
 * @author Sivan Cooperman
 * @version 1.0
 */
public class Admin extends User implements AdminInterface {
	
	/**
	 * Constructor for the Admin class. Takes a username and a password
	 * @param username The admin's username
	 * @param password The admin's password
	 */
	public Admin(String username, String password) {
		super(username, password);
	}
	
	public Course createCourse(String name, String id, int capacity, int numStudents, ArrayList<String> studentNames, String instructor,
			int section, String loc) {
		return new Course(name, id, capacity, numStudents, studentNames, instructor, section, loc);
	}
	
	public ArrayList<Course> deleteCourse(ArrayList<Course> courses, String id) {
		courses.removeIf(course -> (course.getID().equals(id)));
		return courses;
	}
	
	public Course editCourse(Course course, String field, int value) {
		switch(field) {
			case "capacity":
				course.setCapacity(value);
				break;
			case "numStudents":
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
	
	public ArrayList<Student> registerStudent(ArrayList<Student> studentList, Student student) {
		studentList.add(student);
		return studentList;
	}
	
	public ArrayList<Course> getFullCourses(ArrayList<Course> courses) {
		ArrayList<Course> fullCourses = (ArrayList<Course>) courses.clone();
		fullCourses.removeIf(course -> (course.getCapacity() != course.getNumStudents()));
		return fullCourses;
	}
	
	public void writeFullCourses(ArrayList<Course> courses, String filepath) throws IOException {
		ArrayList<Course> fullCourses = this.getFullCourses(courses);
		BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
		for (Course course : fullCourses) {
			writer.write(course.toString());
			writer.write("\n");
		} writer.close();
	}
	
	public ArrayList<Student> viewRegisteredStudents(Course course, ArrayList<Student> students) {
		ArrayList<Student> registeredStudents = new ArrayList<Student>();
		for (String name : course.getStudentNames()) {
			String firstname = name.split(" ")[0];
			String lastname = name.split(" ")[1];
			
			for (Student student : students) {
				if (student.getFirstName().equals(firstname) && student.getLastName().equals(lastname)) {
					registeredStudents.add(student);
				}
			}
		} return registeredStudents;
	}
	
	private static boolean isSorted(ArrayList<Course> courses) {
		for (int i = 1; i < courses.size(); i++) {
			if (courses.get(i - 1).getNumStudents() < courses.get(i).getNumStudents()) {
				return false;
			}
		} return true;
	}
	
	public ArrayList<Course> sortCourses(ArrayList<Course> courses) {
		while (!Admin.isSorted(courses)) {
			for (int i = 1; i < courses.size(); i++) {
				if (courses.get(i - 1).getNumStudents() < courses.get(i).getNumStudents()) { 
					Course temp = courses.get(i-1);
					courses.set(i-1, courses.get(i));
					courses.set(i, temp);
				}
			}
		} return courses;
	}

	/**
	 * Prints to the console a list of options the admin user can execute
	 * @return The final option selection by the user. If the user has selected an invalid option, returns null and calls the function again.
	 */
	public String options() {
		Scanner scn = new Scanner(System.in);
		System.out.println("Welcome, Administrator. Please enter the number of the option you wish to select: \n"
				+ "1) Course Management \n"
				+ "2) Reports");
		String selection1 = scn.nextLine();
		if (selection1.equals("1")) {
			System.out.println("Selected Course Management. \n"
				+ "Please enter the number of the option you wish to select: "
				+ "1) Create a new course \n"
				+ "2) Delete a course \n"
				+ "3) Edit a course \n"
				+ "4) Display information for a course \n"
				+ "5) Register a student in the Course Registration System \n"
				+ "6) Exit");
			String selection2 = scn.nextLine();
			if ("123456".contains(selection2)) {
				scn.close();
				return selection2;
			} else {
				scn.close();
				System.out.println("Invalid selection. Restarting...");
				this.options();
			}
			
		} else if (selection1.equals("2")) {	
			System.out.println("Selected Reports. \n"
					+ "Please enter the number of the option you wish to select: \n"
					+ "1) View all courses \n"
					+ "2) View all FULL courses \n"
					+ "3) Write all FULL courses to a file \n"
					+ "4) View all students registered for a specific course \n"
					+ "5) View all courses a student has registered for \n"
					+ "6) Sort all courses by number of students enrolled \n"
					+ "7) Exit");
			String selection2 = scn.nextLine();
			if ("1234567".contains(selection2)) {
				scn.close();
				return selection2;
			} else {
				scn.close();
				System.out.println("Invalid selection. Restarting...");
				this.options();
			}
		} else {
			System.out.println("Invalid selection. Restarting...");
			this.options();
		}
	scn.close();
	return null;
	}
}