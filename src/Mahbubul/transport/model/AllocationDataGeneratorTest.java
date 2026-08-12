package transport.model;

public class AllocationDataGeneratorTest {

    public static void main(String[] args) {

        TransportData data = new TransportData();

        AllocationDataGenerator.generateSampleBookings(data);

        AllocationController controller =
                new AllocationController();

        System.out.println("Routes: "
                + data.getRoutes().size());

        System.out.println("Students: "
                + data.getStudents().size());

        System.out.println("Bookings: "
                + data.getBookings().size());

        System.out.println("Buses: "
                + data.getBuses().size());

        BusAllocationResult result =
                controller.allocateBus(
                        "Mirpur",
                        "Morning"
                );

        System.out.println();
        System.out.println("===== ALLOCATION TEST =====");
        System.out.println(result);

        System.out.println();
        System.out.println("Allocated Buses:");

        for (Bus bus : result.getAllocatedBuses()) {
            System.out.println(bus);
        }
    }
}