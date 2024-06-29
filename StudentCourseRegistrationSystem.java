import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentCourseRegistrationSystem {


    static List<Course> courses = new ArrayList<>();


    static List<Student> students = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        courses.add(new Course("CS102", "Introduction to Computer Science", 40, "Monday 9am"));
        courses.add(new Course("MATH402", "Calculus II", 25, "Tuesday 10am"));
        courses.add(new Course("ENG104", "English Composition", 50, "Wednesday 11am"));


        while (true) {
            System.out.println("\nStudent Course Registration System");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. List Courses");
            System.out.println("4. Register for Course");
            System.out.println("5. Drop Course");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addCourse(scanner);
                    break;
                case 2:
                    addStudent(scanner);
                    break;
                case 3:
                    listCourses();
                    break;
                case 4:
                    registerForCourse(scanner);
                    break;
                case 5:
                    dropCourse(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        students.add(new Student(id, name));
        System.out.println("Student added successfully.");
    }
    private static void addCourse(Scanner scanner) {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter course capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter course schedule: ");
        String schedule = scanner.nextLine();

        courses.add(new Course(code, title, capacity, schedule));
        System.out.println("Course added successfully.");
    }


    private static void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        System.out.println("\nAvailable Courses:");
        System.out.println("Code\tTitle\tCapacity\tSchedule");
        for (Course course : courses) {
            System.out.println(course.code + "\t" + course.title + "\t" + course.capacity + "\t" + course.schedule);
        }
    }


    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = findtheStudent(studentId);
        Course course = findCourse(courseCode);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        if (course.isFull()) {
            System.out.println("Course is full. Registration failed.");
            return;
        }

        student.registerForCourse(course);
        System.out.println("Registration successful.");
    }


    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = findtheStudent(studentId);
        Course course = findCourse(courseCode);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.dropCourse(course);
        System.out.println("Course dropped successfully.");
    }


    private static Student findtheStudent(int id) {
        for (Student student : students) {
            if (student.id == id) {
                return student;
            }
        }
        return null;
    }


    private static Course findCourse(String code) {
        for (Course course : courses) {
            if (course.code.equals(code)) {
                return course;
            }
        }
        return null;
    }
}


class Course {
    String code;
    String title;
    int capacity;
    String schedule;
    List<Student> registeredStudents = new ArrayList<>();

    public Course(String code, String title, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public boolean isFull() {
        return registeredStudents.size() >= capacity;
    }
}


class Student {
    int id;
    String name;
    List<Course> registeredCourses = new ArrayList<>();

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void registerForCourse(Course course) {
        registeredCourses.add(course);
        course.registeredStudents.add(this);
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        course.registeredStudents.remove(this);
    }

}
