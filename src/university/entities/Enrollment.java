package university.entities;
import university.enums.Grade;
import university.interfaces.Payable;

public class Enrollment implements Payable {
    private int id;
    private Student student;
    private Course course;
    private String semester;
    private Grade grade;
    private boolean paid;

    public Enrollment(int id, Student student, Course course, String semester) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = Grade.NA;
        this.paid = false;
    }
    public int getId() { return id; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public String getSemester() { return semester; }
    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; }

    @Override
    public void pay() { this.paid = true; }
    @Override
    public boolean isPaid() { return paid; }

    @Override
    public String toString() {
        return String.format("[Зарахування ID:%d] %s -> %s | Оцінка: %s | Оплачено: %b",
                id, student.getName(), course.getName(), grade, paid);
    }
}