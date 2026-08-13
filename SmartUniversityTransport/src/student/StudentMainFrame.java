package student;

import javax.swing.*;
import java.awt.*;

public class StudentMainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentArea;

    public StudentMainFrame() {
        setTitle("Smart University Transport System");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        SidebarPanel sidebar = new SidebarPanel(this);
        add(sidebar, BorderLayout.WEST);

        // Content Area with CardLayout
        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);

        // Register All 4 Screens
        contentArea.add(new StudentDashboardPanel(), "Dashboard");
        contentArea.add(new BookSeatPanel(), "BookSeat");
        contentArea.add(new MyBookingHistoryPanel(), "BookingHistory");
        contentArea.add(new MyProfilePanel(), "Profile");

        add(contentArea, BorderLayout.CENTER);
        showScreen("Dashboard");
    }

    public void showScreen(String screenName) {
        cardLayout.show(contentArea, screenName);
    }
}