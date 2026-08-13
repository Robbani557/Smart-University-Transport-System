package model;

public class Route {

    

    private String routeName;
    private int totalStudents;
    private int busCapacity;
    private int allocatedBuses;



    public Route(String routeName, int totalStudents,
                 int busCapacity, int allocatedBuses) {

        this.routeName = routeName;
        this.totalStudents = totalStudents;
        this.busCapacity = busCapacity;
        this.allocatedBuses = allocatedBuses;
    }

  

    public String getRouteName() {
        return routeName;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public int getBusCapacity() {
        return busCapacity;
    }

    public int getAllocatedBuses() {
        return allocatedBuses;
    }

 

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public void setBusCapacity(int busCapacity) {
        this.busCapacity = busCapacity;
    }

    public void setAllocatedBuses(int allocatedBuses) {
        this.allocatedBuses = allocatedBuses;
    }



    public int getRequiredBuses() {

        if (busCapacity <= 0) {
            return 0;
        }

        return (int) Math.ceil(
                (double) totalStudents / busCapacity
        );
    }



    public int getBusShortage() {

        int required = getRequiredBuses();

        if (required > allocatedBuses) {
            return required - allocatedBuses;
        }

        return 0;
    }


    public int getExtraBuses() {

        int required = getRequiredBuses();

        if (allocatedBuses > required) {
            return allocatedBuses - required;
        }

        return 0;
    }

   

    public String getStatus() {

        if (allocatedBuses < getRequiredBuses()) {

            return "SHORTAGE: " + getBusShortage();

        } else if (allocatedBuses > getRequiredBuses()) {

            return "EXTRA: " + getExtraBuses();

        } else {

            return "OK";
        }
    }

 

    @Override
    public String toString() {

        return routeName
                + " - Students: "
                + totalStudents
                + ", Required Buses: "
                + getRequiredBuses()
                + ", Allocated: "
                + allocatedBuses
                + ", Status: "
                + getStatus();
    }
}