package controller;

import data.TransportData;
import service.AllocationService;
import model.BusAllocationResult;


public class AllocationController {

    private TransportData transportData;
    private AllocationService allocationService;

    public AllocationController() {

        transportData = new TransportData();

        allocationService =
                new AllocationService(transportData);
    }

    public BusAllocationResult allocateBus(
            String routeName,
            String travelTime) {

        return allocationService.allocate(
                routeName,
                travelTime
        );
    }

    public TransportData getTransportData() {
        return transportData;
    }

    public AllocationService getAllocationService() {
        return allocationService;
    }

    public void resetAllocation() {

        allocationService.releaseAllBuses();
    }
}