package dao;

import db.DatabaseManager;
import model.Booking;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookingDAO {

    public static final int MAX_BUS_SEATS = 40;
    
    public static final List<String> TO_VARSITY_SLOTS = Arrays.asList("07:00 AM", "08:30 AM", "10:00 AM", "12:00 PM");
    public static final List<String> FROM_VARSITY_SLOTS = Arrays.asList("11:00 AM", "01:00 PM", "04:00 PM", "05:30 PM");

    public boolean isValidTimeSlot(String tripType, String timeSlot) {
        if ("To Varsity".equalsIgnoreCase(tripType)) {
            return TO_VARSITY_SLOTS.contains(timeSlot.toUpperCase().trim());
        } else if ("From Varsity".equalsIgnoreCase(tripType)) {
            return FROM_VARSITY_SLOTS.contains(timeSlot.toUpperCase().trim());
        }
        return false;
    }

    public synchronized String createBooking(Booking booking) {
        autoUpdateCompletedTrips();

        if (!isValidTimeSlot(booking.getTripType(), booking.getTime())) {
            return "FAILED: Invalid departure time slot for " + booking.getTripType() + ".";
        }

        if (hasActiveBooking(booking.getStudentId())) {
            return "FAILED: You already have an active booking. You must cancel your previous booking before creating a new one.";
        }

        if (booking.getSeatNumber() < 1 || booking.getSeatNumber() > MAX_BUS_SEATS) {
            return "FAILED: Invalid seat number. Seats must be between 1 and " + MAX_BUS_SEATS + ".";
        }

        if (isSeatOccupied(booking.getRoute(), booking.getTripType(), booking.getDate(), booking.getTime(), booking.getSeatNumber())) {
            return "FAILED: Seat " + booking.getSeatNumber() + " is already booked for this trip slot.";
        }

        ensureUserExists(booking.getStudentId());

        String sql = "INSERT INTO bookings (student_id, route_name, trip_type, booking_date, time_slot, pickup_point, seat_number, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, 'Confirmed')";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, booking.getStudentId());
            pstmt.setString(2, booking.getRoute());
            pstmt.setString(3, booking.getTripType());
            pstmt.setString(4, booking.getDate());
            pstmt.setString(5, booking.getTime());
            pstmt.setString(6, booking.getPickupPoint());
            pstmt.setInt(7, booking.getSeatNumber());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                return "SUCCESS: Seat " + booking.getSeatNumber() + " successfully booked!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED: Database error while processing booking.";
    }

    private void ensureUserExists(String studentId) {
        String sql = "INSERT OR IGNORE INTO users (user_id, full_name, email) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentId);
            pstmt.setString(2, "Student " + studentId);
            pstmt.setString(3, studentId + "@univ.edu");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized String cancelBooking(int bookingId) {
        Booking booking = getBookingById(bookingId);
        if (booking == null) {
            return "FAILED: Booking record not found.";
        }

        if (!"Confirmed".equalsIgnoreCase(booking.getStatus())) {
            return "FAILED: Booking is already " + booking.getStatus().toLowerCase() + ".";
        }

        long minutesUntilDeparture = getMinutesUntilDeparture(booking.getDate(), booking.getTime());
        if (minutesUntilDeparture <= 30) {
            return "FAILED: Cannot cancel within 30 minutes of departure. (" + minutesUntilDeparture + " mins remaining)";
        }

        String sql = "UPDATE bookings SET status = 'Cancelled' WHERE booking_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookingId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                return "SUCCESS: Booking #" + bookingId + " has been successfully cancelled.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAILED: Database error while cancelling booking.";
    }

    public synchronized void autoUpdateCompletedTrips() {
        String sql = "SELECT booking_id, booking_date, time_slot FROM bookings WHERE status = 'Confirmed'";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("booking_id");
                String date = rs.getString("booking_date");
                String time = rs.getString("time_slot");

                if (getMinutesUntilDeparture(date, time) < 0) {
                    markBookingAsCompleted(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void markBookingAsCompleted(int bookingId) {
        String sql = "UPDATE bookings SET status = 'Completed' WHERE booking_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasActiveBooking(String studentId) {
        String sql = "SELECT COUNT(*) FROM bookings WHERE student_id = ? AND status = 'Confirmed'";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Booking getActiveBooking(String studentId) {
        autoUpdateCompletedTrips();
        String sql = "SELECT * FROM bookings WHERE student_id = ? AND status = 'Confirmed' LIMIT 1";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBooking(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Booking> getBookingHistory(String studentId) {
        autoUpdateCompletedTrips();
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE student_id = ? AND status IN ('Cancelled', 'Completed') ORDER BY booking_id DESC";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isSeatOccupied(String route, String tripType, String date, String time, int seatNumber) {
        String sql = "SELECT COUNT(*) FROM bookings " +
                     "WHERE route_name = ? AND trip_type = ? AND booking_date = ? AND time_slot = ? AND seat_number = ? AND status = 'Confirmed'";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, route);
            pstmt.setString(2, tripType);
            pstmt.setString(3, date);
            pstmt.setString(4, time);
            pstmt.setInt(5, seatNumber);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Integer> getOccupiedSeats(String route, String tripType, String date, String time) {
        List<Integer> occupiedSeats = new ArrayList<>();
        String sql = "SELECT seat_number FROM bookings " +
                     "WHERE route_name = ? AND trip_type = ? AND booking_date = ? AND time_slot = ? AND status = 'Confirmed'";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, route);
            pstmt.setString(2, tripType);
            pstmt.setString(3, date);
            pstmt.setString(4, time);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                occupiedSeats.add(rs.getInt("seat_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return occupiedSeats;
    }

    public int getAvailableSeatCount(String route, String tripType, String date, String time) {
        return MAX_BUS_SEATS - getOccupiedSeats(route, tripType, date, time).size();
    }

    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookingId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBooking(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateTicketSummary(Booking booking) {
        return "========================================\n" +
               "         UNIVERSITY TRANSPORT TICKET\n" +
               "========================================\n" +
               "Booking ID  : #" + booking.getBookingId() + "\n" +
               "Student ID  : " + booking.getStudentId() + "\n" +
               "Route       : " + booking.getRoute() + "\n" +
               "Direction   : " + booking.getTripType() + "\n" +
               "Date        : " + booking.getDate() + "\n" +
               "Departure   : " + booking.getTime() + "\n" +
               "Pickup Point: " + booking.getPickupPoint() + "\n" +
               "Seat Number : Seat " + booking.getSeatNumber() + "\n" +
               "Status      : " + booking.getStatus() + "\n" +
               "========================================\n";
    }

    private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
        return new Booking(
            rs.getInt("booking_id"),
            rs.getString("student_id"),
            rs.getString("route_name"),
            rs.getString("trip_type"),
            rs.getString("booking_date"),
            rs.getString("time_slot"),
            rs.getString("pickup_point"),
            rs.getInt("seat_number"),
            rs.getString("status")
        );
    }

    public long getMinutesUntilDeparture(String dateStr, String timeStr) {
        try {
            String combinedString = dateStr + " " + timeStr.trim().toUpperCase();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
            LocalDateTime departureDateTime = LocalDateTime.parse(combinedString, formatter);
            LocalDateTime now = LocalDateTime.now();

            return Duration.between(now, departureDateTime).toMinutes();
        } catch (Exception e) {
            return -1;
        }
    }
}