package service;

import model.Bus;
import model.BusAllocationResult;
import model.Route;
import data.TransportData;

import java.util.List;
public class AllocationService {

    private TransportData transportData;

    public AllocationService(TransportData transportData) {
        this.transportData = transportData;
    }

    public BusAllocationResult allocate(
            String routeName,
            String travelTime) {

        int studentCount =
                transportData.getBookingManager()
                        .getBookingsForRouteAndTime(
                                routeName,
                                travelTime
                        );

        Route route = transportData.findRoute(routeName);

        if (route == null) {
            return new BusAllocationResult(
                    routeName,
                    travelTime,
                    0,
                    0,
                    0,
                    new java.util.ArrayList<>()
            );
        }

        int busesRequired =
                transportData.getBusAllocationManager()
                        .calculateRequiredBuses(
                                studentCount,
                                route.getBusCapacity()
                        );

        List<Bus> allocatedBuses =
                transportData.getBusAllocationManager()
                        .allocateBuses(busesRequired);

        return new BusAllocationResult(
                routeName,
                travelTime,
                studentCount,
                busesRequired,
                allocatedBuses.size(),
                allocatedBuses
        );
    }

    public void releaseAllBuses() {
        transportData.getBusAllocationManager()
                .releaseAllBuses();
    }
}