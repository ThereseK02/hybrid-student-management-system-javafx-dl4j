
package studentManagementSystem;

public class Course {
	private String courseName;
	private int creditHours;

	// 1. Existing Constructor (The "Required" one)
	public Course(String courseName, int creditHours) {
		this.courseName = courseName;
		this.creditHours = creditHours;
	}

	// 2. NEW Constructor (Fixes the "Found: String" error)
	// This allows the GUI to work by just providing a name.
	public Course(String courseName) {
		this.courseName = courseName;
		this.creditHours = 3; // Default value
	}

	// Getters and Setters
	public String getCourseName() { return courseName; }
	public void setCourseName(String courseName) { this.courseName = courseName; }

	public int getCreditHours() { return creditHours; }
	public void setCreditHours(int creditHours) { this.creditHours = creditHours; }

	@Override
	public String toString() {
		return courseName + " (" + creditHours + " hrs)";
	}
}