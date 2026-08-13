package data;

import java.util.ArrayList;
import java.util.List;
import model.Bus;
import model.Booking;
import model.Route;
import model.Student;

import service.BookingManager;
import service.BusAllocationManager;

public class TransportData {

    private List<Route> routes;
    private List<Bus> buses;
    private List<Student> students;
    private List<Booking> bookings;

    private BookingManager bookingManager;
    private BusAllocationManager busAllocationManager;

    public TransportData() {

        routes = new ArrayList<>();
        buses = new ArrayList<>();
        students = new ArrayList<>();
        bookings = new ArrayList<>();

        bookingManager = new BookingManager();
        busAllocationManager = new BusAllocationManager();

        loadRoutes();
        loadBuses();
    }

    private void loadRoutes() {

        routes.add(new Route(
                "Mirpur",
                245,
                50,
                5
        ));

        routes.add(new Route(
                "Dhanmondi",
                331,
                50,
                7
        ));

        routes.add(new Route(
                "Uttara",
                148,
                50,
                3
        ));

        routes.add(new Route(
                "Mohammadpur",
                87,
                50,
                2
        ));

        routes.add(new Route(
                "Badda",
                193,
                50,
                4
        ));

        routes.add(new Route(
                "Jatrabari",
                112,
                50,
                3
        ));

        routes.add(new Route(
                "Gulshan",
                176,
                50,
                4
        ));

        routes.add(new Route(
                "Rampura",
                129,
                50,
                3
        ));
    }

    private void loadBuses() {

        buses.add(new Bus(
                "B001",
                "DHAKA-01",
                50,
                "Driver 1"
        ));

        buses.add(new Bus(
                "B002",
                "DHAKA-02",
                50,
                "Driver 2"
        ));

        buses.add(new Bus(
                "B003",
                "DHAKA-03",
                50,
                "Driver 3"
        ));

        buses.add(new Bus(
                "B004",
                "DHAKA-04",
                50,
                "Driver 4"
        ));

        buses.add(new Bus(
                "B005",
                "DHAKA-05",
                50,
                "Driver 5"
        ));

        buses.add(new Bus(
                "B006",
                "DHAKA-06",
                50,
                "Driver 6"
        ));

        buses.add(new Bus(
                "B007",
                "DHAKA-07",
                50,
                "Driver 7"
        ));

        buses.add(new Bus(
                "B008",
                "DHAKA-08",
                50,
                "Driver 8"
        ));

        buses.add(new Bus(
                "B009",
                "DHAKA-09",
                50,
                "Driver 9"
        ));

        buses.add(new Bus(
                "B010",
                "DHAKA-10",
                50,
                "Driver 10"
        ));

        for (Bus bus : buses) {
            busAllocationManager.addBus(bus);
        }
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public BookingManager getBookingManager() {
        return bookingManager;
    }

    public BusAllocationManager getBusAllocationManager() {
        return busAllocationManager;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addBooking(Booking booking) {

        bookings.add(booking);

        bookingManager.addBooking(booking);
    }

    public Route findRoute(String routeName) {

        for (Route route : routes) {

            if (route.getRouteName()
                    .equalsIgnoreCase(routeName)) {

                return route;
            }
        }

        return null;
    }

    public int getRequiredBuses(
            String routeName,
            String travelTime) {

        Route route = findRoute(routeName);

        if (route == null) {
            return 0;
        }

        int studentsTraveling =
                bookingManager.getBookingsForRouteAndTime(
                        routeName,
                        travelTime
                );

        return busAllocationManager.calculateRequiredBuses(
                studentsTraveling,
                route.getBusCapacity()
        );
    }
}