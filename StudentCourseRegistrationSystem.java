
import java.util.*;

class Course {
    String code, title, description, schedule;
    int capacity, enrolled;

    Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    boolean hasSlot() {
        return enrolled < capacity;
    }

    void enroll() {
        enrolled++;
    }

    void drop() {
        enrolled--;
    }

    String details() {
        return String.format("%s: %s\nDescription: %s\nSchedule: %s\nCapacity: %d/%d",
                code, title, description, schedule, enrolled, capacity);
    }
}

class Student {
    String id, name;
    List<Course> courses;

    Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    void register(Course course) {
        if (course.hasSlot()) {
            course.enroll();
            courses.add(course);
            System.out.println("Registered for " + course.title);
        } else {
            System.out.println("Course full.");
        }
    }

    void drop(Course course) {
        if (courses.remove(course)) {
            course.drop();
            System.out.println("Dropped " + course.title);
        } else {
            System.out.println("Not registered for this course.");
        }
    }

    void showCourses() {
        if (courses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            for (Course course : courses) {
                System.out.println(course.title);
            }
        }
    }
}

class CourseRegistrationSystem {
    Map<String, Course> courses;
    Map<String, Student> students;

    CourseRegistrationSystem() {
        courses = new HashMap<>();
        students = new HashMap<>();
    }

    void addCourse(Course course) {
        courses.put(course.code, course);
    }

    void addStudent(Student student) {
        students.put(student.id, student);
    }

    void listCourses() {
        for (Course course : courses.values()) {
            System.out.println(course.details());
        }
    }

    void register(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);
        if (student != null && course != null) {
            student.register(course);
        } else {
            System.out.println("Invalid ID or code.");
        }
    }

    void drop(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);
        if (student != null && course != null) {
            student.drop(course);
        } else {
            System.out.println("Invalid ID or code.");
        }
    }

    void showStudentCourses(String studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            student.showCourses();
        } else {
            System.out.println("Invalid student ID.");
        }
    }
}

public class StudentCourseRegistrationSystem{
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        system.addCourse(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 30, "Mon-Wed-Fri 10:00 AM - 11:00 AM"));
        system.addCourse(new Course("MATH201", "Calculus I", "Introduction to calculus", 25, "Tue-Thu 9:00 AM - 10:30 AM"));
        system.addCourse(new Course("ENG202", "English Literature", "Study of classic English literature", 20, "Mon-Wed 2:00 PM - 3:30 PM"));

        system.addStudent(new Student("S001", "John Doe"));
        system.addStudent(new Student("S002", "Jane Smith"));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. List courses\n2. Register\n3. Drop\n4. Show registered\n5. Exit");
            System.out.print("Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    system.listCourses();
                    break;
                case 2:
                    System.out.print("Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Course code: ");
                    String courseCode = scanner.nextLine();
                    system.register(studentId, courseCode);
                    break;
                case 3:
                    System.out.print("Student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Course code: ");
                    courseCode = scanner.nextLine();
                    system.drop(studentId, courseCode);
                    break;
                case 4:
                    System.out.print("Student ID: ");
                    studentId = scanner.nextLine();
                    system.showStudentCourses(studentId);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
