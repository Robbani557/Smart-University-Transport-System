package model;

import java.util.List;

public class BusAllocationResult {

    private String routeName;
    private String travelTime;
    private int studentCount;
    private int busesRequired;
    private int busesAllocated;
    private List<Bus> allocatedBuses;

    public BusAllocationResult(
            String routeName,
            String travelTime,
            int studentCount,
            int busesRequired,
            int busesAllocated,
            List<Bus> allocatedBuses) {

        this.routeName = routeName;
        this.travelTime = travelTime;
        this.studentCount = studentCount;
        this.busesRequired = busesRequired;
        this.busesAllocated = busesAllocated;
        this.allocatedBuses = allocatedBuses;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public int getBusesRequired() {
        return busesRequired;
    }

    public int getBusesAllocated() {
        return busesAllocated;
    }

    public List<Bus> getAllocatedBuses() {
        return allocatedBuses;
    }

    public boolean isFullyAllocated() {
        return busesAllocated >= busesRequired;
    }

    public String getStatus() {

        if (busesAllocated >= busesRequired) {
            return "Fully Allocated";
        }

        return "Insufficient Buses";
    }

    @Override
    public String toString() {

        return "Route: " + routeName
                + " | Time: " + travelTime
                + " | Students: " + studentCount
                + " | Required: " + busesRequired
                + " | Allocated: " + busesAllocated
                + " | Status: " + getStatus();
    }
}