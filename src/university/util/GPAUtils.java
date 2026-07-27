package university.util;
import university.entities.Enrollment;
import university.entities.Student;
import university.enums.Grade;

public class GPAUtils {
    public static double calculateGPA(Student student, Enrollment[] enrollments) {
        double totalPoints = 0;
        int totalCredits = 0;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId() == student.getId() && enrollment.getGrade() != Grade.NA) {
                totalPoints += enrollment.getGrade().getPoints() * enrollment.getCourse().getCredits();
                totalCredits += enrollment.getCourse().getCredits();
            }
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    public static void bubbleSortStudentsByName(Student[] students) {
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if (students[j].getName().compareToIgnoreCase(students[j + 1].getName()) > 0) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }
}