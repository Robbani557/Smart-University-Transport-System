package service;

import java.util.ArrayList;
import java.util.List;

import model.Bus;

public class BusAllocationManager {

    private List<Bus> buses;

    public BusAllocationManager() {
        buses = new ArrayList<>();
    }

    public void addBus(Bus bus) {
        buses.add(bus);
    }

    public void removeBus(String busId) {

        buses.removeIf(bus ->
                bus.getBusId().equals(busId)
        );
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public int getTotalBuses() {
        return buses.size();
    }

    public int getAvailableBuses() {

        int count = 0;

        for (Bus bus : buses) {

            if (bus.isAvailable()) {
                count++;
            }
        }

        return count;
    }

    public int calculateRequiredBuses(
            int students,
            int busCapacity) {

        if (students <= 0 || busCapacity <= 0) {
            return 0;
        }

        return (students + busCapacity - 1)
                / busCapacity;
    }

    public List<Bus> allocateBuses(int requiredBuses) {

        List<Bus> allocatedBuses = new ArrayList<>();

        for (Bus bus : buses) {

            if (bus.isAvailable()) {

                bus.assign();

                allocatedBuses.add(bus);

                if (allocatedBuses.size() == requiredBuses) {
                    break;
                }
            }
        }

        return allocatedBuses;
    }

    public void releaseAllBuses() {

        for (Bus bus : buses) {
            bus.release();
        }
    }
}