package controller;

import controller.AllocationController;
import model.BusAllocationResult;

public class AllocationUIController {

    private AllocationController controller;

    public AllocationUIController() {
        controller = new AllocationController();
    }

    public BusAllocationResult allocate(
            String route,
            String travelTime) {

        return controller.allocateBus(
                route,
                travelTime
        );
    }

    public void reset() {
        controller.resetAllocation();
    }

    public AllocationController getController() {
        return controller;
    }
}