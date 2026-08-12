package transport.model;

public class AllocationController {

    private TransportData transportData;
    private AllocationService allocationService;

    public AllocationController() {

        transportData = new TransportData();

        AllocationDataGenerator.generateSampleBookings(
                transportData
        );

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