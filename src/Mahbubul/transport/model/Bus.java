package transport.model;

public class Bus {

    private String busId;
    private String busNumber;
    private int capacity;
    private String driverName;
    private String status;

    public Bus(String busId, String busNumber, int capacity, String driverName) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.capacity = capacity;
        this.driverName = driverName;
        this.status = "Available";
    }

    public String getBusId() {
        return busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getStatus() {
        return status;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void assign() {
        status = "Assigned";
    }

    public void release() {
        status = "Available";
    }

    public boolean isAvailable() {
        return status.equals("Available");
    }

    @Override
    public String toString() {
        return busNumber
                + " - Capacity: "
                + capacity
                + " - Driver: "
                + driverName
                + " - Status: "
                + status;
    }
}