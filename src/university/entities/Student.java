package university.entities;
import university.enums.StudentStatus;

public class Student extends Person {
    private StudentStatus status;
    private int year;

    public Student(int id, String name, String email, StudentStatus status, int year) {
        super(id, name, email);
        if (year < 1 || year > 6) throw new IllegalArgumentException("Курс має бути від 1 до 6");
        this.status = status;
        this.year = year;
    }
    public StudentStatus getStatus() { return status; }
    public void setStatus(StudentStatus status) { this.status = status; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return String.format("[ID:%d] %s | %s | Статус: %s | Курс: %d", id, name, email, status, year);
    }
}