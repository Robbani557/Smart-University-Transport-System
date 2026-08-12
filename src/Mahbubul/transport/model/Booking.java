package transport.model;

public class Booking {

    
    private Student student;
    private Route route;
    private String travelDate;
    private String travelTime;
    private int seatNumber;
    private String status;

    
    public Booking(Student student,
                   Route route,
                   String travelDate,
                   String travelTime,
                   int seatNumber) {

        this.student = student;
        this.route = route;
        this.travelDate = travelDate;
        this.travelTime = travelTime;
        this.seatNumber = seatNumber;
        this.status = "Confirmed";
    }

    

    public Student getStudent() {
        return student;
    }

    public Route getRoute() {
        return route;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getStatus() {
        return status;
    }

    

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public void cancelBooking() {
        this.status = "Cancelled";
    }

   

    public boolean isConfirmed() {
        return status.equals("Confirmed");
    }

 

    @Override
    public String toString() {

        return "Student: "
                + student.getName()
                + " | Route: "
                + route.getRouteName()
                + " | Date: "
                + travelDate
                + " | Time: "
                + travelTime
                + " | Seat: "
                + seatNumber
                + " | Status: "
                + status;
    }
}