package university.services;
import university.entities.*;
import university.enums.Grade;
import java.util.Arrays;

public class EnrollmentService {
    private Enrollment[] enrollments = new Enrollment[2];
    private int count = 0;
    private int idCounter = 1;

    public void enroll(Student student, Course course, String semester) {
        if (count == enrollments.length) {
            enrollments = Arrays.copyOf(enrollments, enrollments.length * 2);
        }
        enrollments[count++] = new Enrollment(idCounter++, student, course, semester);
    }
    public void gradeAndPay(int enrollId, Grade grade, boolean pay) {
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getId() == enrollId) {
                enrollments[i].setGrade(grade);
                if (pay) enrollments[i].pay();
                return;
            }
        }
        throw new IllegalArgumentException("Зарахування не знайдено");
    }
    public Enrollment[] getEnrollments() {
        return Arrays.copyOf(enrollments, count);
    }
    public Enrollment[] getStudentEnrollments(int studentId) {
        Enrollment[] studentEnrs = new Enrollment[count];
        int sCount = 0;
        for (int i = 0; i < count; i++) {
            if (enrollments[i].getStudent().getId() == studentId) {
                studentEnrs[sCount++] = enrollments[i];
            }
        }
        return Arrays.copyOf(studentEnrs, sCount);
    }
}