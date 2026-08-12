package transport.model;

import java.util.List;

public class AllocationReport {

    private AllocationSummary summary;

    public AllocationReport(AllocationSummary summary) {
        this.summary = summary;
    }

    public AllocationSummary getSummary() {
        return summary;
    }

    public String generateReport() {

        StringBuilder report = new StringBuilder();

        report.append("========================================\n");
        report.append("       BUS ALLOCATION REPORT\n");
        report.append("========================================\n\n");

        List<BusAllocationResult> results =
                summary.getResults();

        for (BusAllocationResult result : results) {

            report.append("Route: ")
                    .append(result.getRouteName())
                    .append("\n");

            report.append("Travel Time: ")
                    .append(result.getTravelTime())
                    .append("\n");

            report.append("Students: ")
                    .append(result.getStudentCount())
                    .append("\n");

            report.append("Buses Required: ")
                    .append(result.getBusesRequired())
                    .append("\n");

            report.append("Buses Allocated: ")
                    .append(result.getBusesAllocated())
                    .append("\n");

            report.append("Status: ")
                    .append(result.getStatus())
                    .append("\n");

            report.append("----------------------------------------\n");
        }

        report.append("\nTOTAL STUDENTS: ")
                .append(summary.getTotalStudents())
                .append("\n");

        report.append("TOTAL BUSES REQUIRED: ")
                .append(summary.getTotalBusesRequired())
                .append("\n");

        report.append("TOTAL BUSES ALLOCATED: ")
                .append(summary.getTotalBusesAllocated())
                .append("\n");

        report.append("OVERALL STATUS: ")
                .append(summary.getStatus())
                .append("\n");

        report.append("========================================\n");

        return report.toString();
    }
}