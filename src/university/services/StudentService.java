package university.services;
import university.entities.Student;
import university.enums.StudentStatus;
import university.util.GPAUtils;
import java.util.Arrays;

public class StudentService {
    private Student[] students = new Student[2];
    private int count = 0;
    private int idCounter = 1;

    public Student add(String name, String email, StudentStatus status, int year) {
        if (count == students.length) {
            students = Arrays.copyOf(students, students.length * 2);
        }
        Student s = new Student(idCounter++, name, email, status, year);
        students[count++] = s;
        return s;
    }
    public Student[] getStudents() {
        return Arrays.copyOf(students, count);
    }
    public Student findById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) return students[i];
        }
        throw new IllegalArgumentException("Студента не знайдено");
    }
    public void delete(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId() == id) {
                students[i] = students[count - 1];
                students[count - 1] = null;
                count--;
                return;
            }
        }
        throw new IllegalArgumentException("Студента не знайдено");
    }
    public Student[] getSortedByName() {
        Student[] copy = getStudents();
        GPAUtils.bubbleSortStudentsByName(copy);
        return copy;
    }
}