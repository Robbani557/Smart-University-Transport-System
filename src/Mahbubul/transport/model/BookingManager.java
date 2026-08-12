package transport.model;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {

    private List<Booking> bookings;

    public BookingManager() {
        bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void cancelBooking(Booking booking) {
        booking.cancelBooking();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public int getTotalBookings() {
        return bookings.size();
    }

    public int getConfirmedBookings() {

        int count = 0;

        for (Booking booking : bookings) {

            if (booking.isConfirmed()) {
                count++;
            }
        }

        return count;
    }

    public int getBookingsForRoute(String routeName) {

        int count = 0;

        for (Booking booking : bookings) {

            if (booking.isConfirmed()
                    && booking.getRoute()
                    .getRouteName()
                    .equalsIgnoreCase(routeName)) {

                count++;
            }
        }

        return count;
    }

    public int getBookingsForRouteAndTime(
            String routeName,
            String travelTime) {

        int count = 0;

        for (Booking booking : bookings) {

            if (booking.isConfirmed()
                    && booking.getRoute()
                    .getRouteName()
                    .equalsIgnoreCase(routeName)
                    && booking.getTravelTime()
                    .equalsIgnoreCase(travelTime)) {

                count++;
            }
        }

        return count;
    }

    public int getBookingsForDateAndRoute(
            String travelDate,
            String routeName) {

        int count = 0;

        for (Booking booking : bookings) {

            if (booking.isConfirmed()
                    && booking.getTravelDate()
                    .equals(travelDate)
                    && booking.getRoute()
                    .getRouteName()
                    .equalsIgnoreCase(routeName)) {

                count++;
            }
        }

        return count;
    }
}