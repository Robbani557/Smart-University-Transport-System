package transport.model;

public class AllocationDataGenerator {

    public static void generateSampleBookings(TransportData data) {

        int[] studentCounts = {
            245, 331, 148, 87, 193, 120, 175, 210
        };

        String[] times = {
            "Morning",
            "Noon",
            "Afternoon",
            "Evening"
        };

        int studentId = 1;
        int routeIndex = 0;

        for (Route route : data.getRoutes()) {

            int numberOfStudents =
                    studentCounts[routeIndex % studentCounts.length];

            for (int i = 0; i < numberOfStudents; i++) {

                Student student = new Student(
                        "S" + studentId,
                        "Student " + studentId,
                        "student" + studentId + "@university.edu",
                        route
                );

                data.addStudent(student);

                Booking booking = new Booking(
                        student,
                        route,
                        "2026-08-13",
                        times[i % times.length],
                        (i % 50) + 1
                );

                data.addBooking(booking);

                studentId++;
            }

            routeIndex++;
        }
    }
}