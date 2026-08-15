package data;

/**
 * Shared application data.
 *
 * Every screen should use this single TransportData instance instead of
 * creating its own TransportData object. This keeps students, bookings,
 * routes, and buses synchronized across the application.
 */
public final class AppData {

    private static final TransportData TRANSPORT_DATA = new TransportData();

    private AppData() {
        // Utility class; do not instantiate.
    }

    public static TransportData getTransportData() {
        return TRANSPORT_DATA;
    }
}
