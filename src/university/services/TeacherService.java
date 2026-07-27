package university.services;
import university.entities.Teacher;
import university.enums.TeacherPosition;
import java.util.Arrays;

public class TeacherService {
    private Teacher[] teachers = new Teacher[2];
    private int count = 0;
    private int idCounter = 1;

    public Teacher add(String name, String email, TeacherPosition position) {
        if (count == teachers.length) {
            teachers = Arrays.copyOf(teachers, teachers.length * 2);
        }
        Teacher t = new Teacher(idCounter++, name, email, position);
        teachers[count++] = t;
        return t;
    }
    public Teacher[] getTeachers() {
        return Arrays.copyOf(teachers, count);
    }
    public Teacher findById(int id) {
        for (int i = 0; i < count; i++) {
            if (teachers[i].getId() == id) return teachers[i];
        }
        throw new IllegalArgumentException("Викладача не знайдено");
    }
}