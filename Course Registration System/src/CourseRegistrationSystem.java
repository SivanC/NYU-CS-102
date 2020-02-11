import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Runs the Course Registration System. User is prompted to log in with their credentials and can then perform a variety of operations
 * @author Sivan Cooperman
 * @version 0.1
 */
public class CourseRegistrationSystem {
	public static void main(String[] args) throws IOException {
		// Loading and reading the csv file containing all the courses
		System.out.println("Loading courses from csv...");
		// Change this variable to alter the path to the csv containing the courses
		String filepath = "C:\\Users\\Sivan Cooperman\\Desktop\\Code\\Java\\CS_102\\Course Registration System\\MyUniversityCourses.csv";
		// ArrayList containing all courses
		ArrayList<Course> courses = new ArrayList<Course>();
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
						null, lineSplit[5], Integer.parseInt(lineSplit[6]), lineSplit[7]);
				courses.add(course);
			} else {
				cont = false;
			}
		}
		System.out.println("Loading complete!");
		
		// Creating the admin user and instantiating the list of students. Only an admin can create new students to be put in the student list.
		Admin admin = new Admin("Admin", "Admin001");
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("sc7443", "sivan", "Sivan", "Cooperman"));
		
		//
		// Asking the user for credentials and logging them in
		//
		Scanner scn = new Scanner(System.in);
		System.out.println("Please enter your username: ");
		String username = scn.nextLine();
		System.out.println("Please enter your password: ");
		String password = scn.nextLine();
		
		// Checking for a student/admin with those credentials
		if (username.equals("Admin") && password.equals("Admin001")) {
			admin.options();
		} else {
			for (Student student : students) {
				if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
					student.options();
				} else {
					System.out.println("Oops! We could not find a user with those credentials.");
				}
			}
		}
		
		//
		// Processing and executing the user's command
		//
		
		admin.options();
	}
}
