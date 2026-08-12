package transport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransportDashboard extends JFrame {

    private final Color BLUE = new Color(30, 105, 190);
    private final Color LIGHT_BLUE = new Color(235, 242, 250);

    public TransportDashboard() {

        setTitle("Smart University Transport System");
        setSize(1360, 760);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();
    }

    private void createUI() {

        setLayout(new BorderLayout());

       

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 25, 20, 25
                )
        );

        JLabel title = new JLabel("Transport Dashboard");

        title.setFont(
                new Font("Arial", Font.BOLD, 25)
        );

        JLabel manager = new JLabel("Transport Manager");

        manager.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        JPanel rightHeader = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        25,
                        0
                )
        );

        rightHeader.setOpaque(false);

        JLabel notification =
                new JLabel("Notifications");

        notification.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        rightHeader.add(notification);
        rightHeader.add(manager);

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                rightHeader,
                BorderLayout.EAST
        );

        add(
                header,
                BorderLayout.NORTH
        );

        
        JPanel sidebar = new JPanel();

        sidebar.setPreferredSize(
                new Dimension(220, 0)
        );

        sidebar.setBackground(BLUE);

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel busTitle =
                new JLabel("BUS");

        busTitle.setForeground(Color.WHITE);

        busTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        busTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel smartTransport =
                new JLabel("Smart Transport");

        smartTransport.setForeground(Color.WHITE);

        smartTransport.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        smartTransport.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        sidebar.add(Box.createVerticalStrut(35));
        sidebar.add(busTitle);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(smartTransport);
        sidebar.add(Box.createVerticalStrut(55));

        JButton dashboardButton =
                createSidebarButton("Dashboard");

        JButton allocationButton =
                createSidebarButton("Bus Allocation");

        JButton studentsButton =
                createSidebarButton("Students");

        JButton routesButton =
                createSidebarButton("Routes");

        JButton bookingsButton =
                createSidebarButton("Bookings");

        JButton reportsButton =
                createSidebarButton("Reports");

        JButton settingsButton =
                createSidebarButton("Settings");

        JButton logoutButton =
                createSidebarButton("Logout");

        sidebar.add(dashboardButton);
        sidebar.add(allocationButton);
        sidebar.add(studentsButton);
        sidebar.add(routesButton);
        sidebar.add(bookingsButton);
        sidebar.add(reportsButton);
        sidebar.add(settingsButton);

        sidebar.add(Box.createVerticalGlue());

        sidebar.add(logoutButton);

        sidebar.add(Box.createVerticalStrut(25));

        add(
                sidebar,
                BorderLayout.WEST
        );

        

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        mainPanel.setBackground(
                new Color(245, 245, 245)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );

       
        JPanel cardsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                20,
                                0
                        )
                );

        cardsPanel.setOpaque(false);

        cardsPanel.add(
                createCard(
                        "26",
                        "Total Buses"
                )
        );

        cardsPanel.add(
                createCard(
                        "8",
                        "Total Routes"
                )
        );

        cardsPanel.add(
                createCard(
                        "421",
                        "Today's Bookings"
                )
        );

        cardsPanel.add(
                createCard(
                        "24",
                        "Active Drivers"
                )
        );

        mainPanel.add(
                cardsPanel,
                BorderLayout.NORTH
        );

      

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );

        centerPanel.setOpaque(false);

        

        JPanel routePanel =
                new JPanel(
                        new BorderLayout()
                );

        routePanel.setBackground(Color.WHITE);

        routePanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220)
                )
        );

        JLabel routeTitle =
                new JLabel(
                        "Today's Route Summary"
                );

        routeTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        routeTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        10,
                        15
                )
        );

        routePanel.add(
                routeTitle,
                BorderLayout.NORTH
        );

        String[] columns = {
            "Route",
            "Students",
            "Required Buses",
            "Allocated",
            "Status"
        };

        Object[][] data = {
            {"Mirpur", 245, 5, 4, "Need 1 Bus"},
            {"Dhanmondi", 331, 7, 7, "Perfect"},
            {"Uttara", 148, 3, 3, "Perfect"},
            {"Mohammadpur", 87, 2, 2, "Perfect"},
            {"Badda", 193, 4, 3, "Need 1 Bus"}
        };

        DefaultTableModel model =
                new DefaultTableModel(
                        data,
                        columns
                ) {
                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        JTable table =
                new JTable(model);

        table.setRowHeight(38);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        table.getTableHeader().setBackground(BLUE);

        table.getTableHeader().setForeground(
                Color.WHITE
        );

        table.setGridColor(
                new Color(220, 220, 220)
        );

        routePanel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        centerPanel.add(
                routePanel,
                BorderLayout.CENTER
        );

      

        JPanel activityPanel =
                new JPanel(
                        new BorderLayout()
                );

        activityPanel.setPreferredSize(
                new Dimension(250, 0)
        );

        activityPanel.setBackground(Color.WHITE);

        activityPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220)
                )
        );

        JLabel activityTitle =
                new JLabel(
                        "Recent Activity"
                );

        activityTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        activityTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        activityPanel.add(
                activityTitle,
                BorderLayout.NORTH
        );

        JPanel activityList =
                new JPanel();

        activityList.setBackground(Color.WHITE);

        activityList.setLayout(
                new BoxLayout(
                        activityList,
                        BoxLayout.Y_AXIS
                )
        );

        addActivity(
                activityList,
                "Bus 12 assigned to Mirpur"
        );

        addActivity(
                activityList,
                "Dhanmondi allocation completed"
        );

        addActivity(
                activityList,
                "Bus 7 returned to campus"
        );

        addActivity(
                activityList,
                "Uttara schedule updated"
        );

        addActivity(
                activityList,
                "Driver assigned to Bus 18"
        );

        activityPanel.add(
                activityList,
                BorderLayout.CENTER
        );

        centerPanel.add(
                activityPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

       
        dashboardButton.addActionListener(
                e -> {
                    // Already on dashboard
                }
        );

        allocationButton.addActionListener(
                e -> {

                    BusAllocation allocation =
                            new BusAllocation();

                    allocation.setLocationRelativeTo(
                            this
                    );

                    allocation.setVisible(true);
                }
        );

        studentsButton.addActionListener(
                e -> {

                    transport.ui.StudentPanel panel =
                            new transport.ui.StudentPanel();

                    panel.setVisible(true);
                }
        );

        routesButton.addActionListener(
                e -> {

                    transport.ui.RoutePanel panel =
                            new transport.ui.RoutePanel();

                    panel.setVisible(true);
                }
        );

        bookingsButton.addActionListener(
                e -> {

                    transport.ui.BookingPanel panel =
                            new transport.ui.BookingPanel();

                    panel.setVisible(true);
                }
        );

        reportsButton.addActionListener(
                e -> JOptionPane.showMessageDialog(
                        this,
                        "Reports module coming soon."
                )
        );

        settingsButton.addActionListener(
                e -> JOptionPane.showMessageDialog(
                        this,
                        "Settings module coming soon."
                )
        );

        logoutButton.addActionListener(
                e -> {

                    int choice =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to exit?",
                                    "Logout",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (choice ==
                            JOptionPane.YES_OPTION) {

                        dispose();
                    }
                }
        );
    }

    private JButton createSidebarButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setMaximumSize(
                new Dimension(
                        190,
                        55
                )
        );

        button.setPreferredSize(
                new Dimension(
                        190,
                        55
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(BLUE);

        button.setBorderPainted(false);

        button.setFocusPainted(false);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        35,
                        0,
                        0
                )
        );

        return button;
    }

    private JPanel createCard(
            String number,
            String label
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220)
                )
        );

        JLabel numberLabel =
                new JLabel(number);

        numberLabel.setForeground(BLUE);

        numberLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        numberLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        0,
                        20
                )
        );

        JLabel labelText =
                new JLabel(label);

        labelText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        labelText.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        15,
                        20
                )
        );

        card.add(
                numberLabel,
                BorderLayout.CENTER
        );

        card.add(
                labelText,
                BorderLayout.SOUTH
        );

        return card;
    }

    private void addActivity(
            JPanel panel,
            String text
    ) {

        JLabel activity =
                new JLabel(
                        "• " + text
                );

        activity.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        activity.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        15,
                        12,
                        10
                )
        );

        panel.add(activity);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TransportDashboard dashboard =
                    new TransportDashboard();

            dashboard.setVisible(true);
        });
    }
}