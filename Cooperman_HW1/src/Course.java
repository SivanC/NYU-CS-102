import java.util.ArrayList;

/**
 * An instance of a course section which implements Serializable and Comparable. A course with the ability to be printed and sorted, registered for, and more.
 * @author Sivan Cooperman
 * @version 1.0
 */
public class Course implements Comparable<Course>, java.io.Serializable {
	private static final long serialVersionUID = -7554455446396412246L; // Unique ID for serialization
	private String name; // Name of the course
	private String id; // Course ID (e.g. ABCD-XY.1111)
	private int capacity; // Number of students that can be enrolled in the section
	private int numStudents; // Number of students currently enrolled in the section
	private ArrayList<String> studentNames; // List of names of students registered in the section
	private String instructor; // Instructor name
	private int section; // Section number
	private String loc; // Location of the section
	
	/**
	 * Creates a new Course object, which represents a section of a course.
	 * @param name The name of the course in plain English
	 * @param id The name of the class within the school's identification system
	 * @param capacity The number of students who can be enrolled in the class at one time
	 * @param numStudents The number of students currently enrolled in the class
	 * @param studentNames A list of full names of students currently enrolled in the class
	 * @param instructor The full name of the course's instructor
	 * @param section The section number of this section of the course
	 * @param loc The location of the class
	 * @return The created course
	 */
	public Course(String name, String id, int capacity, int numStudents, ArrayList<String> studentNames, String instructor,
			int section, String loc) {
		this.setName(name);
		this.setID(id);
		this.setCapacity(capacity);
		this.setNumStudents(numStudents);
		this.setStudentNames(studentNames);
		this.setInstructor(instructor);
		this.setSection(section);
		this.setLoc(loc);
	}
	
	// Name getter and setter
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	// ID getter and setter
	public void setID(String id) {
		this.id = id;
	}
	public String getID() {
		return id;
	}

	// Capacity getter and setter
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// numStudent getter and setter
	public int getNumStudents() {
		return numStudents;
	}
	public void setNumStudents(int numStudents) {
		if (numStudents <= this.getCapacity())
			this.numStudents = numStudents;
		else
			System.out.println("Number of students must be less than or equal to section capacity.");
	}

	// Student names getter and setter
	public ArrayList<String> getStudentNames() {
		return studentNames;
	}
	public void setStudentNames(ArrayList<String> studentNames) {
		this.studentNames = studentNames;
	}

	// Instructor getter and setter
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	// Section no. getter and setter
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}

	// Location getter and setter
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	/**
	 * Represents the Course object as a string. Includes name, ID, capacity, number of students, student names, instructor, section number, and location.
	 * @return A print statement containing information about the course.
	 */
	public String toString() {
		String toPrint = String.format("\nCourse Name: %s\nCourse ID: %s\n"
				+ "Course Capacity: %s\nCurrent Number of Students: %s\n"
				+ "Student Names: %s\nInstructor: %s\nSection Number: %s"
				+ "\nLocation: %s", this.name, this.id, Integer.toString(this.capacity), Integer.toString(this.numStudents),
						this.studentNames.toString(), this.instructor, Integer.toString(this.section), this.loc);		
		return toPrint;
	}
	
	/**
	 * Compares courses by number of registered students
	 * @param course The course to compare
	 * @return 1 if the method-calling course has more students, -1 if less, 0 if equal.
	 */
	public int compareTo(Course course) {
		return this.getNumStudents() > course.getNumStudents() ? 1 : -1;
	}
}
