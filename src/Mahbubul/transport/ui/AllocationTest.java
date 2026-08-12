package transport.ui;

import transport.model.AllocationController;
import transport.model.Bus;
import transport.model.BusAllocationResult;

public class AllocationTest {

    public static void main(String[] args) {

        AllocationController controller =
                new AllocationController();

        BusAllocationResult result =
                controller.allocateBus(
                        "Mirpur",
                        "Morning"
                );

        System.out.println(
                "===== BUS ALLOCATION TEST ====="
        );

        System.out.println(
                "Route: "
                + result.getRouteName()
        );

        System.out.println(
                "Travel Time: "
                + result.getTravelTime()
        );

        System.out.println(
                "Students: "
                + result.getStudentCount()
        );

        System.out.println(
                "Buses Required: "
                + result.getBusesRequired()
        );

        System.out.println(
                "Buses Allocated: "
                + result.getBusesAllocated()
        );

        System.out.println(
                "Status: "
                + result.getStatus()
        );

        System.out.println(
                "\nAllocated Buses:"
        );

        for (Bus bus : result.getAllocatedBuses()) {
            System.out.println(bus);
        }
    }
}