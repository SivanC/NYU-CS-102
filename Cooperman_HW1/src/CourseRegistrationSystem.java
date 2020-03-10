import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.*;

/**
 * Runs the Course Registration System. User is prompted to log in with their credentials and can then perform a variety of operations
 * @author Sivan Cooperman
 * @version 1.0
 */
public class CourseRegistrationSystem {
	public static void main(String[] args) throws IOException {
		// ArrayList for courses and students
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<Student> students = new ArrayList<Student>();
		
		// if a file named 'courses.ser' doesn't exist in the local directory, read everything from csv
		File courseFile = new File("courses.ser");
		if (!courseFile.exists()) {
			// Loading and reading the csv file containing all the courses
			System.out.println("Loading courses from csv...");
			// Change this variable to alter the path to the csv containing the courses
			String filepath = "MyUniversityCourses.csv";
			// Setting up file reader
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			// Cycling through a line to skip headers
			reader.readLine();
			// Reading each course from csv and adding it to the course list
			boolean cont = true;
			while (cont) {
				String line = reader.readLine();
				if (line != null) {
					String[] lineSplit = line.split(",");
					Course course = new Course(lineSplit[0], lineSplit[1],
							Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3]), 
							new ArrayList<String>(), lineSplit[5], Integer.parseInt(lineSplit[6]), lineSplit[7]);
					courses.add(course);
				} else
					cont = false;
			} reader.close();
		// Otherwise use values stored in courses.ser and students.ser
		} else {
			System.out.println("Loading serialized data...");
			courses = (ArrayList<Course>) Data.load("courses.ser");
			students = (ArrayList<Student>) Data.load("students.ser");
		} System.out.println("Loading complete!\n");
		
		// Creating the admin user and instantiating the list of students. Only an admin can create new students to be put in the student list.
		Admin admin = new Admin("Admin", "Admin001");
		
		//
		// Asking the user for credentials and logging them in
		//
		Scanner scn = new Scanner(System.in);
		System.out.println("Please enter your username: ");
		String username = scn.nextLine();
		System.out.println("Please enter your password: ");
		String password = scn.nextLine();
		// if admin user
		if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
			System.out.print("\nWelcome, Administrator. ");
			boolean running = true;
			while(running) {
				// get their command
				int[] options = admin.options(scn);
				// Course Management
				if (options[0] == 1) {
					switch(options[1]) {
						// Create a course
						case 1:
							admin.createCourse(courses, scn);
							break;
						// Delete a course
						case 2:
							admin.deleteCourse(courses, scn);
							break;
						// Edit a course
						case 3:
							admin.editCourseInfo(courses, scn);	
							break;
						// Display course info
						case 4:
							admin.displayCourse(courses, scn);
							break;
						// Register a student
						case 5:
							admin.registerStudent(students, scn);
							break;
						case 6:
							continue;
						default:
							System.out.println("Oops! That command was invalid. Please try again.\n");
					}
				// Reports
				} else if (options[0] == 2) {
					switch(options[1]) {
						// View all courses
						case 1:
							for (Course course : courses)
								System.out.println(course);
							break;
						// View all FULL courses
						case 2:
							for (Course course : admin.getFullCourses(courses))
								System.out.println(course);
							break;
						// Write full courses to file
						case 3:
							admin.writeFullCourses(courses, scn);
							break;
						// View all students registered for a course
						case 4:
							ArrayList<Student> registeredStudents = admin.viewRegisteredStudents(courses, students, scn);
							if (registeredStudents.size() == 0)
								System.out.println("Oops! We couldn't find anyone registered for that class. Please try again.\n");
							else {
								for (Student student : registeredStudents)
									System.out.println(student);
							}
							break;
						// View a list of courses that a given student is registered in
						case 5:
							ArrayList<Course> registered = admin.viewRegisteredCourses(students, scn);
							if (registered != null) {
								for (Course course : registered)
									System.out.println(course);
							} break;
						// Sort courses based on number of registered students
						case 6:
							System.out.println("Sorting courses...");
							Collections.sort(courses);
							System.out.println("Courses sorted by number of registered students!");
							break;
						case 7:
							continue;
						default:
							System.out.println("Oops! That command was invalid. Please try again.\n");
					}
				// Exit
				} else if (options[0] == -1 && options[1] == -1)
					running = false;
			}
		}
		boolean studentLogin = false;
		Student student = null;
		for (Student s : students) {
			if (username.equals(s.getUsername()))
				if (password.equals(s.getPassword())) {
					studentLogin = true;
					student = s;
				}
		} if (studentLogin) {
			System.out.println("\nWelcome, " + student.getFullName());
			boolean running = true;
			while(running) {
				int option = student.options(scn)[0];
				
				switch(option) {
					// View all courses
					case 1:
						for (Course course : courses)
							System.out.println(course);
						break;
					// View all courses that are not full
					case 2:
						for (Course course : student.getAvailableCourses(courses))
							System.out.println(course);
						break;
					// Register for a course
					case 3:
						student.register(courses, scn);
						break;
					// Withdraw from a course
					case 4:
						student.withdraw(courses, scn);
						break;
					// View all currently registered courses
					case 5:
						if (student.getRegistered() != null) {
							for (Course course : student.getRegistered())
								System.out.println(course);
						} break;
					// Exit
					case 6:
						running = false;
						break;
					default:
						System.out.println("Oops! That command was invalid. Please try again.\n");
				}
			}
		}
		// Storing altered data
		Data.store(courses, "courses.ser");
		Data.store(students, "students.ser");
	}
}