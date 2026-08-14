package transport;

import data.TransportData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import model.BusAllocationResult;
import controller.AllocationUIController;

import transport.ui.TimePanel;
import transport.ui.BusDetailsPanel;

public class TransportDashboard extends JFrame {

    private final Color BLUE = new Color(30, 105, 190);

    private DefaultTableModel routeTableModel;
    private JPanel activityList;

    private TransportData transportData;

    private JLabel totalBusesLabel;
    private JLabel totalRoutesLabel;
    private JLabel totalBookingsLabel;
    private JLabel activeDriversLabel;

    public TransportDashboard() {

        transportData = new TransportData();

        setTitle("Smart University Transport System");
        setSize(1360, 760);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();

        addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
                refreshDashboard();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                // Nothing needed
            }
        });
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

        JLabel title
                = new JLabel("Transport Dashboard");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        JLabel manager
                = new JLabel("Transport Manager");

        manager.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JPanel rightHeader
                = new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                25,
                                0
                        )
                );

        rightHeader.setOpaque(false);


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

        JLabel busTitle
                = new JLabel("BUS");

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

        JLabel smartTransport
                = new JLabel("Smart Transport");

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

        sidebar.add(
                Box.createVerticalStrut(35)
        );

        sidebar.add(busTitle);

        sidebar.add(
                Box.createVerticalStrut(10)
        );

        sidebar.add(smartTransport);

        sidebar.add(
                Box.createVerticalStrut(55)
        );

        JButton dashboardButton
                = createSidebarButton("Dashboard");

        JButton allocationButton
                = createSidebarButton("Bus Allocation");

        JButton timeButton
                = createSidebarButton("Schedule");

        JButton busDetailsButton
                = createSidebarButton("Bus Details");

        
        JButton routesButton
                = createSidebarButton("Routes");

       
        JButton reportsButton
                = createSidebarButton("Reports");

        

        JButton logoutButton
                = createSidebarButton("Logout");

        sidebar.add(dashboardButton);
        sidebar.add(allocationButton);
        sidebar.add(timeButton);
        sidebar.add(busDetailsButton);
       
        sidebar.add(routesButton);
       
        sidebar.add(reportsButton);
       

        sidebar.add(
                Box.createVerticalGlue()
        );

        sidebar.add(logoutButton);

        sidebar.add(
                Box.createVerticalStrut(25)
        );

        add(
                sidebar,
                BorderLayout.WEST
        );

        
        JPanel mainPanel
                = new JPanel(
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

        
        JPanel cardsPanel
                = new JPanel(
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
                        String.valueOf(
                                transportData.getBuses().size()
                        ),
                        "Total Buses"
                )
        );

        cardsPanel.add(
                createCard(
                        String.valueOf(
                                transportData.getRoutes().size()
                        ),
                        "Total Routes"
                )
        );

        cardsPanel.add(
                createCard(
                        String.valueOf(
                                transportData.getBookings().size()
                        ),
                        "Today's Bookings"
                )
        );

        cardsPanel.add(
                createCard(
                        String.valueOf(
                                transportData.getBuses().size()
                        ),
                        "Active Drivers"
                )
        );

        mainPanel.add(
                cardsPanel,
                BorderLayout.NORTH
        );

       
        JPanel centerPanel
                = new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );

        centerPanel.setOpaque(false);

        
        JPanel routePanel
                = new JPanel(
                        new BorderLayout()
                );

        routePanel.setBackground(Color.WHITE);

        routePanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220)
                )
        );

        JLabel routeTitle
                = new JLabel(
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

        routeTableModel
                = new DefaultTableModel(
                        new Object[][]{},
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

        JTable table
                = new JTable(routeTableModel);

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

        table.getTableHeader()
                .setBackground(BLUE);

        table.getTableHeader()
                .setForeground(Color.WHITE);

        table.setGridColor(
                new Color(220, 220, 220)
        );

        table.setAutoCreateRowSorter(true);

        routePanel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        centerPanel.add(
                routePanel,
                BorderLayout.CENTER
        );

       
        JPanel activityPanel
                = new JPanel(
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

        JLabel activityTitle
                = new JLabel(
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

        activityList
                = new JPanel();

        activityList.setBackground(
                Color.WHITE
        );

        activityList.setLayout(
                new BoxLayout(
                        activityList,
                        BoxLayout.Y_AXIS
                )
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
                e -> refreshDashboard()
        );

        allocationButton.addActionListener(
                e -> {

                    BusAllocation allocation
                    = new BusAllocation();

                    allocation.setLocationRelativeTo(
                            this
                    );

                    allocation.setVisible(true);
                }
        );
        timeButton.addActionListener(
        e -> new TimePanel(transportData).setVisible(true)
);

       busDetailsButton.addActionListener(
        e -> new BusDetailsPanel(transportData).setVisible(true)
);

       

        routesButton.addActionListener(
                e -> {

                    transport.ui.RoutePanel panel
                    = new transport.ui.RoutePanel();

                    panel.setVisible(true);
                }
        );

        

        reportsButton.addActionListener(
                e -> showAllocationReport()
        );

        
        logoutButton.addActionListener(
                e -> {

                    int choice
                    = JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to Logout?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (choice
                    == JOptionPane.YES_OPTION) {

                        dispose();
                    }
                }
        );

        refreshDashboard();
    }

   
    private void refreshDashboard() {

        refreshCards();

        refreshRouteSummary();

        refreshRecentActivity();
    }

    private void refreshCards() {

        totalBusesLabel.setText(
                String.valueOf(
                        transportData.getBuses().size()
                )
        );

        totalRoutesLabel.setText(
                String.valueOf(
                        transportData.getRoutes().size()
                )
        );

        totalBookingsLabel.setText(
                String.valueOf(
                        transportData.getBookings().size()
                )
        );

        activeDriversLabel.setText(
                String.valueOf(
                        transportData.getBuses().size()
                )
        );
    }

    
    private void refreshRouteSummary() {

        routeTableModel.setRowCount(0);

        AllocationUIController controller
                = new AllocationUIController();

        for (BusAllocationResult result
                : controller.getSummary().getResults()) {

            routeTableModel.addRow(
                    new Object[]{
                        result.getRouteName(),
                        result.getStudentCount(),
                        result.getBusesRequired(),
                        result.getBusesAllocated(),
                        result.getStatus()
                    }
            );
        }
    }

   
    private void refreshRecentActivity() {

        activityList.removeAll();

        AllocationUIController controller
                = new AllocationUIController();

        if (controller.getSummary()
                .getResults()
                .isEmpty()) {

            addActivity(
                    activityList,
                    "No bus allocation activity yet"
            );

        } else {

            for (BusAllocationResult result
                    : controller.getSummary().getResults()) {

                String activity
                        = result.getRouteName()
                        + " allocation: "
                        + result.getBusesAllocated()
                        + "/"
                        + result.getBusesRequired()
                        + " buses allocated";

                addActivity(
                        activityList,
                        activity
                );
            }
        }

        activityList.revalidate();
        activityList.repaint();
    }

  
    private void showAllocationReport() {

        AllocationUIController controller
                = new AllocationUIController();

        if (controller.getSummary()
                .getResults()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No bus allocation has been completed yet.\n"
                    + "Please allocate buses first.",
                    "Reports",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        String report
                = controller.generateReport();

        JTextArea reportArea
                = new JTextArea(report);

        reportArea.setEditable(false);

        reportArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scrollPane
                = new JScrollPane(reportArea);

        scrollPane.setPreferredSize(
                new Dimension(
                        750,
                        500
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Bus Allocation Report",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

   
    private JButton createSidebarButton(
            String text
    ) {

        JButton button
                = new JButton(text);

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

        button.setForeground(Color.WHITE);

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
            String label) {

        JPanel card
                = new JPanel(
                        new BorderLayout()
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220)
                )
        );

        JLabel numberLabel
                = new JLabel(number);

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

        JLabel labelText
                = new JLabel(label);

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

        if (label.equals("Total Buses")) {
            totalBusesLabel = numberLabel;
        }

        if (label.equals("Total Routes")) {
            totalRoutesLabel = numberLabel;
        }

        if (label.equals("Today's Bookings")) {
            totalBookingsLabel = numberLabel;
        }

        if (label.equals("Active Drivers")) {
            activeDriversLabel = numberLabel;
        }

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

        JLabel activity
                = new JLabel(
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

            TransportDashboard dashboard
                    = new TransportDashboard();

            dashboard.setVisible(true);
        });
    }
}
