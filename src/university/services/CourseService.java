package university.services;
import university.entities.Course;
import university.entities.Teacher;
import java.util.Arrays;

public class CourseService {
    private Course[] courses = new Course[2];
    private int count = 0;
    private int idCounter = 1;

    public Course add(String name, int credits, Teacher teacher) {
        if (count == courses.length) {
            courses = Arrays.copyOf(courses, courses.length * 2);
        }
        Course c = new Course(idCounter++, name, credits, teacher);
        courses[count++] = c;
        return c;
    }
    public Course[] getCourses() {
        return Arrays.copyOf(courses, count);
    }
    public Course findById(int id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId() == id) return courses[i];
        }
        throw new IllegalArgumentException("Курс не знайдено");
    }
}