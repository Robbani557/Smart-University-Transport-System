package model;

public class Booking {
    private String bookingID;
    private String date;
    private String route;
    private String time;
    private String seat;
    private String status;

    public Booking(String bookingID, String date, String route, String time, String seat, String status) {
        this.bookingID = bookingID;
        this.date = date;
        this.route = route;
        this.time = time;
        this.seat = seat;
        this.status = status;
    }

    public String getBookingID() { return bookingID; }
    public String getDate() { return date; }
    public String getRoute() { return route; }
    public String getTime() { return time; }
    public String getSeat() { return seat; }
    public String getStatus() { return status; }
}