package authentication;

import model.Student;

/**
 * Holds the student who is currently signed in.
 *
 * The session is intentionally small so existing StudentMainFrame and
 * student panels can continue using their current constructors.
 */
public final class StudentSession {

    private static Student currentStudent;

    private StudentSession() {
        // Utility class; do not instantiate.
    }

    public static Student getCurrentStudent() {
        return currentStudent;
    }

    public static void setCurrentStudent(Student student) {
        currentStudent = student;
    }

    public static boolean isLoggedIn() {
        return currentStudent != null;
    }

    public static void clear() {
        currentStudent = null;
    }
}
