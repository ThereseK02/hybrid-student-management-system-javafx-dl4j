package studentManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class ManagementSystem {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(String id, String name, double gpa) {
        students.add(new Student(id, name, gpa));
    }

    public boolean removeStudent(String id) {
        return students.removeIf(student -> student.getId().equals(id));
    }

    public List<Student> getStudents() {
        return students;
    }
}