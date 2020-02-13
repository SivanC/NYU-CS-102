import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
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
	
	public void createCourse(ArrayList<Course> courses, Scanner scn) {
		// get the info about the new course
		System.out.println("Enter a name for the course: ");
		String name = scn.next();
		System.out.println("Enter a course ID: ");
		String id = scn.next();
		System.out.println("Enter the maximum capacity for this section: ");
		int capacity = scn.nextInt();
		System.out.println("Enter an instructor name: ");
		String instructor = scn.next();
		System.out.println("Enter a course section number: ");
		int section = scn.nextInt();
		System.out.println("Enter a course location: ");
		String loc = scn.next();
		// creating and adding the new course to the list
		System.out.println("Creating course...");
		Course newCourse = new Course(name, id, capacity, 0, new ArrayList<String>(), instructor, section, loc);
		courses.add(newCourse);
	}
	
	public void deleteCourse(ArrayList<Course> courses, Scanner scn) {
		System.out.println("Please enter the course ID of the course you wish to delete: ");
		String id = scn.next();
		System.out.println("Please enter the section you wish to delete. Press 0 to delete all sections: ");
		int section = scn.nextInt();
		System.out.println("Deleting course...");
		if (section == 0) 
			courses.removeIf(course -> (course.getID().equals(id)));
		else 
			courses.removeIf(course -> (course.getID().equals(id) && course.getSection() == section));
	}
	
	public void editCourseInfo(ArrayList<Course> courses, Scanner scn) {
		// Get course ID, section, field to edit, value to edit to
		Course course = null;
		System.out.println("Please enter the Course ID of the course you wish to edit: ");
		String id = scn.next();
		System.out.println("Please enter the section number of the section you wish to edit: ");
		int section = scn.nextInt();
		System.out.println("Please enter the attribute you wish to edit"
				+ " (capacity, number of students, section, instructor, location, student names): ");
		String field = scn.next();
		// finding the course
		boolean courseFound = false;
		for (Course c : courses) {
			if (c.getID().equals(id) && c.getSection() == section) {
				course = c;
				int courseIndex = courses.indexOf(course);
				courseFound = true;
			}
		} // if we find a course in courses
		if (courseFound) {
			// different actions depending on input type
			switch (field) {
				case "capacity":
				case "number of students":
				case "section":
					System.out.println("Please enter the integer value you wish to set " + field + " to: ");
					int val1 = scn.nextInt();
					Course course1 = editCourse(course, field, val1);
					break;
				case "instructor":
				case "location":
					System.out.println("Please enter the value you wish to set " + field + " to: ");
					String val2 = scn.next();
					Course course2 = editCourse(course, field, val2);
					break;
				case "student names":
					System.out.println("Would you like to append or delete names from the list? ");
					String selection = scn.next();
					System.out.println("Please enter a comma-separated list of names that you wish to " + selection + ": ");
					ArrayList<String> n = new ArrayList<String>(Arrays.asList(scn.next().split(",")));
					ArrayList<String> names = course.getStudentNames();
					if (selection.equals("append")) {
						names.addAll(n);
						course.setStudentNames(names);
						Course course3 = editCourse(course, field, names);
					} else if (selection.equals("remove")) {
						names.removeAll(n);
						course.setStudentNames(names);
						Course course4 = editCourse(course, field, names);
					}
			}
		}
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
				if (courses.get(i - 1).compareTo(courses.get(i)) == -1) { 
					Course temp = courses.get(i-1);
					courses.set(i-1, courses.get(i));
					courses.set(i, temp);
				}
			}
		} return courses;
	}

	/**
	 * Prints to the console a list of options the admin user can execute based on the user's input
	 */
	public int options(Scanner scn) {
		// Opening message
		System.out.println("Welcome, Administrator. Please enter the number that corresponds to the command you wish to perform: \n"
				+ "1) Course Management \n"
				+ "2) Reports");
		int selection = scn.nextInt();
		// If Course Management
		if (selection == 1) {
			System.out.println("Selected Course Management. \n"
				+ "Please enter the number that corresponds to the command you wish to perform: \n"
				+ "1) Create a new course \n"
				+ "2) Delete a course \n"
				+ "3) Edit a course \n"
				+ "4) Display information for a course \n"
				+ "5) Register a student in the Course Registration System \n"
				+ "6) Exit");
			// Loop until the user selects a valid option
			int subselection;
			do {
				subselection = Integer.parseInt(scn.next());
				return subselection;
			} while (!(1 <= subselection && 6 >= subselection));
		// If Reports
		} else if (selection == 2) {	
			System.out.println("Selected Reports. \n"
					+ "Please enter the number that corresponds to the command you wish to perform: \n"
					+ "1) View all courses \n"
					+ "2) View all FULL courses \n"
					+ "3) Write all FULL courses to a file \n"
					+ "4) View all students registered for a specific course \n"
					+ "5) View all courses a student has registered for \n"
					+ "6) Sort all courses by number of students enrolled \n"
					+ "7) Exit");
			// Loop until the user selects a valid option
			int subselection;
			do {
				subselection = scn.nextInt();
				return subselection;
			} while (!(1 <= subselection && 7 >= subselection));
		} return -1;
	}
}