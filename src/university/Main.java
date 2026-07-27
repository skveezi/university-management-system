package university;
import java.util.Scanner;
import university.entities.*;
import university.enums.*;
import university.services.*;
import university.util.GPAUtils;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService = new StudentService();
    private static TeacherService teacherService = new TeacherService();
    private static CourseService courseService = new CourseService();
    private static EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        initData();
        boolean running = true;
        while (running) {
            System.out.println("\n=============================================");
            System.out.println("   UNIVERSITY PORTAL by Vladyslava Troianova ");
            System.out.println("=============================================");
            System.out.println("[1] Управління Студентами");
            System.out.println("[2] Управління Викладачами");
            System.out.println("[3] Управління Курсами");
            System.out.println("[4] Зарахування та Оцінки");
            System.out.println("[5] Аналітика та Звіти");
            System.out.println("[0] Завершити роботу");
            System.out.print("Оберіть розділ: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: manageStudents(); break;
                    case 2: manageTeachers(); break;
                    case 3: manageCourses(); break;
                    case 4: manageEnrollments(); break;
                    case 5: reports(); break;
                    case 0: running = false; break;
                    default: System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: Введіть число.");
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }

    private static void initData() {
        Student vlada = studentService.add("Vladyslava Troianova", "v.troianova@university.pt", StudentStatus.ACTIVE, 3);
        Student alex = studentService.add("Alex Koval", "akoval@university.pt", StudentStatus.ACTIVE, 2);
        Teacher prof = teacherService.add("Dr. Silva", "silva@university.pt", TeacherPosition.PROFESSOR);
        Course java = courseService.add("Advanced Java", 6, prof);
        Course db = courseService.add("Databases", 5, prof);
        
        enrollmentService.enroll(vlada, java, "Fall 2026");
        enrollmentService.enroll(vlada, db, "Fall 2026");
        enrollmentService.enroll(alex, java, "Fall 2026");
    }

    private static void manageStudents() {
        System.out.println("\n--- СТУДЕНТИ ---");
        System.out.println("1.Додати 2.Всі студенти 3.Відсортовані за ПІБ 4.Видалити");
        int opt = Integer.parseInt(scanner.nextLine());
        if (opt == 1) {
            System.out.print("ПІБ: "); String name = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            System.out.print("Курс: "); int year = Integer.parseInt(scanner.nextLine());
            studentService.add(name, email, StudentStatus.ACTIVE, year);
        } else if (opt == 2) {
            for (Student s : studentService.getStudents()) System.out.println(s);
        } else if (opt == 3) {
            for (Student s : studentService.getSortedByName()) System.out.println(s);
        } else if (opt == 4) {
            System.out.print("Введіть ID для видалення: ");
            studentService.delete(Integer.parseInt(scanner.nextLine()));
        }
    }

    private static void manageTeachers() {
        System.out.println("\n--- ВИКЛАДАЧІ ---");
        System.out.println("1.Додати 2.Всі викладачі");
        int opt = Integer.parseInt(scanner.nextLine());
        if (opt == 1) {
            System.out.print("ПІБ: "); String name = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            teacherService.add(name, email, TeacherPosition.LECTURER);
        } else if (opt == 2) {
            for (Teacher t : teacherService.getTeachers()) System.out.println(t);
        }
    }

    private static void manageCourses() {
        System.out.println("\n--- КУРСИ ---");
        System.out.println("1.Додати 2.Всі курси");
        int opt = Integer.parseInt(scanner.nextLine());
        if (opt == 1) {
            System.out.print("Назва: "); String name = scanner.nextLine();
            System.out.print("Кредити: "); int credits = Integer.parseInt(scanner.nextLine());
            System.out.print("ID Викладача: "); Teacher t = teacherService.findById(Integer.parseInt(scanner.nextLine()));
            courseService.add(name, credits, t);
        } else if (opt == 2) {
            for (Course c : courseService.getCourses()) System.out.println(c);
        }
    }

    private static void manageEnrollments() {
        System.out.println("\n--- ЗАРАХУВАННЯ ---");
        System.out.println("1.Зарахувати студента 2.Поставити оцінку/Оплатити 3.Транскрипт");
        int opt = Integer.parseInt(scanner.nextLine());
        if (opt == 1) {
            System.out.print("ID Студента: "); Student s = studentService.findById(Integer.parseInt(scanner.nextLine()));
            System.out.print("ID Курсу: "); Course c = courseService.findById(Integer.parseInt(scanner.nextLine()));
            System.out.print("Семестр: "); String sem = scanner.nextLine();
            enrollmentService.enroll(s, c, sem);
        } else if (opt == 2) {
            System.out.print("ID Зарахування: "); int id = Integer.parseInt(scanner.nextLine());
            enrollmentService.gradeAndPay(id, Grade.A, true);
        } else if (opt == 3) {
            System.out.print("ID Студента: "); Student s = studentService.findById(Integer.parseInt(scanner.nextLine()));
            Enrollment[] enrs = enrollmentService.getStudentEnrollments(s.getId());
            for (Enrollment e : enrs) System.out.println(e);
            System.out.printf("Поточний GPA: %.2f\n", GPAUtils.calculateGPA(s, enrollmentService.getEnrollments()));
        }
    }

    private static void reports() {
        System.out.println("\n--- ЗВІТИ ---");
        System.out.println("1.Пошук студента 2.Боржники (неоплачені курси) 3.Топ студенти (GPA)");
        int opt = Integer.parseInt(scanner.nextLine());
        if (opt == 1) {
            System.out.print("Введіть ПІБ або Email: "); String q = scanner.nextLine().toLowerCase();
            for (Student s : studentService.getStudents()) {
                if (s.getName().toLowerCase().contains(q) || s.getEmail().toLowerCase().contains(q)) System.out.println(s);
            }
        } else if (opt == 2) {
            for (Enrollment e : enrollmentService.getEnrollments()) {
                if (!e.isPaid()) System.out.println(e);
            }
        } else if (opt == 3) {
            Student[] arr = studentService.getStudents();
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    double g1 = GPAUtils.calculateGPA(arr[j], enrollmentService.getEnrollments());
                    double g2 = GPAUtils.calculateGPA(arr[j + 1], enrollmentService.getEnrollments());
                    if (g1 < g2) { Student temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = temp; }
                }
            }
            for (int i = 0; i < Math.min(arr.length, 3); i++) {
                System.out.printf("%s - GPA: %.2f\n", arr[i].getName(), GPAUtils.calculateGPA(arr[i], enrollmentService.getEnrollments()));
            }
        }
    }
}