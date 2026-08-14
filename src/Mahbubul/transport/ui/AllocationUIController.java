package transport.ui;

import transport.model.AllocationController;
import transport.model.AllocationReport;
import transport.model.AllocationSummary;
import transport.model.BusAllocationResult;
import transport.model.TransportData;

public class AllocationUIController {

    private static final AllocationController controller =
            new AllocationController();

    private static final AllocationSummary summary =
            new AllocationSummary();
    private static final TransportData transportData =
        new TransportData();

    public AllocationUIController() {
    }

    public BusAllocationResult allocate(
            String route,
            String travelTime) {

        BusAllocationResult result =
                controller.allocateBus(
                        route,
                        travelTime
                );

        summary.addResult(result);

        return result;
    }

    public AllocationSummary getSummary() {
        return summary;
    }

    public AllocationReport getReport() {
        return new AllocationReport(summary);
    }

    public String generateReport() {

        AllocationReport report =
                new AllocationReport(summary);

        return report.generateReport();
    }

  
    public void reset() {

        controller.resetAllocation();
    }

   
    public void clearHistory() {

        controller.resetAllocation();
        summary.clear();
    }
    
    public TransportData getTransportData() {
    return transportData;
}

    public AllocationController getController() {
        return controller;
    }
}