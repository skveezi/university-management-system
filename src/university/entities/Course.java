package university.entities;

public class Course {
    private int id;
    private String name;
    private int credits;
    private Teacher teacher;

    public Course(int id, String name, int credits, Teacher teacher) {
        if (credits < 1) throw new IllegalArgumentException("Кількість кредитів має бути > 0");
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.teacher = teacher;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCredits() { return credits; }
    public Teacher getTeacher() { return teacher; }

    @Override
    public String toString() {
        return String.format("[ID:%d] %s | Кредити: %d | Викладач: %s", id, name, credits, teacher.getName());
    }
}