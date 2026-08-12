package transport.model;

import java.util.ArrayList;
import java.util.List;

public class AllocationSummary {

    private List<BusAllocationResult> results;

    public AllocationSummary() {
        results = new ArrayList<>();
    }

    public void addResult(BusAllocationResult result) {
        results.add(result);
    }

    public List<BusAllocationResult> getResults() {
        return results;
    }

    public int getTotalStudents() {

        int total = 0;

        for (BusAllocationResult result : results) {
            total += result.getStudentCount();
        }

        return total;
    }

    public int getTotalBusesRequired() {

        int total = 0;

        for (BusAllocationResult result : results) {
            total += result.getBusesRequired();
        }

        return total;
    }

    public int getTotalBusesAllocated() {

        int total = 0;

        for (BusAllocationResult result : results) {
            total += result.getBusesAllocated();
        }

        return total;
    }

    public boolean isFullyAllocated() {
        return getTotalBusesAllocated() >= getTotalBusesRequired();
    }

    public String getStatus() {

        if (isFullyAllocated()) {
            return "All Buses Allocated";
        }

        return "More Buses Required";
    }

    public void clear() {
        results.clear();
    }
}