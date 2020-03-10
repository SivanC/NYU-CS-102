import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * A helper class for the Course Registration System. Implements the Serializable interface.
 * Allows for the program to load and store serialized objects in files, find courses based on ID and course name, and allows for validation of user input.
 * @author Sivan Cooperman
 * @version 1.0
 */
public class Data implements Serializable {
	/**
	 * Writes an ArrayList to a file.
	 * @param data An ArrayList.
	 * @param filename The file to write the ArrayList to.
	 */
	public static void store(ArrayList<?> data, String filename) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
		out.writeObject(data);
		out.close();
	}
	
	/**
	 * Reads an ArrayList from a file.
	 * @param filename The file to read the ArrayList from.
	 * @return An ArrayList. Returns null if the object does not exist.
	 */
	public static ArrayList<?> load(String filename) throws IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
		try {
			ArrayList<?> data = (ArrayList<?>) in.readObject();
			in.close();
			return data;
		} catch (ClassNotFoundException e) {
			in.close();
			return null;
		}
	}
	
	/**
	 * Returns all Course objects with a specified ID from a list of courses.
	 * @param courseID The id of the course to be found.
	 * @param courses The list of courses to search.
	 * @return A list of all courses with the specified ID.
	 */
	public static ArrayList<Course> findCourseByID(String courseID, ArrayList<Course> courses) {
		ArrayList<Course> sections = new ArrayList<Course>();
		for (Course course : courses) {
			// if the course id matches the given id
			if (course.getID().equals(courseID))
				sections.add(course);
		} return sections;
	}
	
	/**
	 * Takes a course id and section number and returns a section of the course.
	 * @param courseID A String course id.
	 * @param sectionNumber An integer section number.
	 * @param courses An ArrayList of Courses.
	 * @return A section of the Course. Returns null if the course cannot be found.
	 */
	public static Course findCourseByID(String courseID, int sectionNumber, ArrayList<Course> courses) {
		ArrayList<Course> sections = new ArrayList<Course>();
		for (Course course : courses) {
			// if the course id matches the given id
			if (course.getID().equals(courseID))
				sections.add(course);
		} 
		for (Course section : sections) {
			if (section.getSection() == sectionNumber)
				return section;
		} return null;
	}
	
	/**
	 * Returns all Course objects with a specified course name from a list of courses.
	 * @param courseName The name of the course to be found.
	 * @param courses The list of courses to search.
	 * @return A list of all courses with the specified name.
	 */
	public static ArrayList<Course> findCourseByName(String courseName, ArrayList<Course> courses) {
		ArrayList<Course> specifiedCourses = new ArrayList<Course>();
		for (Course course : courses) {
			// if the course id matches the given id
			if (course.getName().equals(courseName))
				specifiedCourses.add(course);
		} return specifiedCourses;
	}
	
	/**
	 * Requires the user to enter an integer 
	 * @param s
	 * @param scn
	 * @return
	 */
	public static int assertInt(String s, Scanner scn) {
		try {
			int n = Integer.parseInt(s);
			if (n >= 0) return n; // If the integer is nonnegative
		} catch (NumberFormatException e) { // If s is not an integer try again
			System.out.println("Please enter an positive integer.");
			return assertInt(scn.nextLine(), scn);
		} System.out.println("Please enter an positive integer."); // If s is negative try again
		return assertInt(scn.nextLine(), scn);
	}
}
