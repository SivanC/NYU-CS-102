import java.util.ArrayList;

public class Course {
	private String name;
	private String id;
	private int capacity;
	private int numStudents;
	private ArrayList<String> studentNames;
	private String instructor;
	private int section;
	private String loc;
	
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
		this.name = name;
		this.id = id;
		this.capacity = capacity;
		this.numStudents = numStudents;
		this.studentNames = studentNames;
		this.instructor = instructor;
		this.section = section;
		this.loc = loc;
	}
	
	public String getName() {
		return name;
	}

	public String getID() {
		return id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getNumStudents() {
		return numStudents;
	}

	public void setNumStudents(int numStudents) {
		this.numStudents = numStudents;
	}

	public ArrayList<String> getStudentNames() {
		return studentNames;
	}

	public void setStudentNames(ArrayList<String> studentNames) {
		this.studentNames = studentNames;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

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
		String toPrint = "Course Name: %s\nCourse ID: %s\n"
				+ "Course Capacity: %s\nCurrent Number of Students: %s\n"
				+ "Student Names: %s\nInstructor: %s\nSection Number: %s"
				+ "\nLocation: %s".format(this.name, this.id, Integer.toString(this.capacity), Integer.toString(this.numStudents),
						this.studentNames.toString(), this.instructor, Integer.toString(this.section), this.loc);		
		return toPrint;
	}
}
