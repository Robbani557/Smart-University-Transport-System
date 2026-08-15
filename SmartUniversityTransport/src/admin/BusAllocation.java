package admin;

import data.TransportData;
import model.AllocationController;
import model.Bus;
import model.BusAllocationResult;
import model.Route;

import data.AppData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BusAllocation extends JFrame {

    private static final Color BLUE =
            new Color(21, 101, 192);

    private static final Color LIGHT_BLUE =
            new Color(232, 240, 254);

    private static final Color LIGHT =
            new Color(245, 247, 250);

    private static final Color BORDER =
            new Color(224, 224, 224);

    private static final Color DARK =
            new Color(33, 33, 33);

    private static final Color MUTED =
            new Color(102, 112, 133);

    private JComboBox<String> routeBox;
    private JComboBox<String> timeBox;

    private JLabel studentsLabel;
    private JLabel requiredLabel;
    private JLabel allocatedLabel;
    private JLabel statusLabel;
    private JLabel availableLabel;

    private JTable busTable;
    private DefaultTableModel busTableModel;

    private JTextArea resultArea;

    private TransportData transportData;
    private AllocationController controller;

    /*
     * Standalone constructor.
     *
     * For the final AdminDashboard integration, use:
     *
     * new BusAllocation(existingTransportData);
     *
     * so buses, routes and bookings are shared with the rest
     * of the application.
     */
    public BusAllocation() {
        this(AppData.getTransportData());
    }

    public BusAllocation(
            TransportData transportData
    ) {
        this.transportData =
                transportData == null
                        ? new TransportData()
                        : transportData;

        /*
         * This constructor of AllocationController uses the
         * supplied TransportData and therefore does not create
         * another unrelated transport dataset.
         */
        this.controller =
                new AllocationController(
                        this.transportData
                );

        initializeFrame();
        createUI();
        loadRoutes();
        refreshBusTable();
    }

    private void initializeFrame() {

        setTitle(
                "Smart University Transport System - Bus Allocation"
        );

        setSize(
                1200,
                760
        );

        setMinimumSize(
                new Dimension(
                        950,
                        600
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        getContentPane().setBackground(
                LIGHT
        );
    }

    private void createUI() {

        setLayout(
                new BorderLayout()
        );

        add(
                createSidebar(),
                BorderLayout.WEST
        );

        add(
                createMainPanel(),
                BorderLayout.CENTER
        );
    }

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(
                        220,
                        0
                )
        );

        sidebar.setBackground(
                BLUE
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        JPanel brand =
                new JPanel();

        brand.setOpaque(false);

        brand.setBorder(
                new EmptyBorder(
                        22,
                        18,
                        18,
                        18
                )
        );

        brand.setLayout(
                new BoxLayout(
                        brand,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel logo =
                new JLabel("◈");

        logo.setForeground(
                Color.WHITE
        );

        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        34
                )
        );

        JLabel university =
                new JLabel(
                        "Smart University"
                );

        university.setForeground(
                Color.WHITE
        );

        university.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        JLabel system =
                new JLabel(
                        "Transport System"
                );

        system.setForeground(
                new Color(
                        220,
                        235,
                        255
                )
        );

        system.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        JLabel admin =
                new JLabel(
                        "ADMIN PANEL"
                );

        admin.setForeground(
                new Color(
                        187,
                        214,
                        247
                )
        );

        admin.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        10
                )
        );

        brand.add(logo);
        brand.add(university);
        brand.add(system);
        brand.add(
                Box.createVerticalStrut(5)
        );
        brand.add(admin);

        sidebar.add(brand);

        String[] menuItems = {
                "⌂   Dashboard",
                "♙   Manage Students",
                "▣   Manage Buses",
                "⌁   Manage Routes",
                "⇄   Bus Allocation",
                "◷   Schedules",
                "▤   Bookings",
                "▥   Reports",
                "⚙   Settings"
        };

        for (String text : menuItems) {

            JButton button =
                    createMenuButton(
                            text
                    );

            if (text.contains(
                    "Bus Allocation"
            )) {
                button.setBackground(
                        new Color(
                                25,
                                118,
                                210
                        )
                );
            }

            sidebar.add(button);
        }

        sidebar.add(
                Box.createVerticalGlue()
        );

        JButton logout =
                createMenuButton(
                        "⇥   Logout"
                );

        logout.addActionListener(
                e -> dispose()
        );

        sidebar.add(logout);

        sidebar.add(
                Box.createVerticalStrut(18)
        );

        return sidebar;
    }

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        44
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        16,
                        10,
                        10
                )
        );

        button.setFocusPainted(
                false
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                BLUE
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        button.setOpaque(
                true
        );

        button.setBorderPainted(
                false
        );

        return button;
    }

    private JPanel createMainPanel() {

        JPanel main =
                new JPanel(
                        new BorderLayout(
                                0,
                                0
                        )
                );

        main.setBackground(
                LIGHT
        );

        main.add(
                createTopBar(),
                BorderLayout.NORTH
        );

        main.add(
                createContent(),
                BorderLayout.CENTER
        );

        return main;
    }

    private JPanel createTopBar() {

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setBackground(
                Color.WHITE
        );

        top.setBorder(
                BorderFactory.createMatteBorder(
                        0,
                        0,
                        1,
                        0,
                        BORDER
                )
        );

        top.setPreferredSize(
                new Dimension(
                        0,
                        62
                )
        );

        JLabel title =
                new JLabel(
                        "  ⇄   Bus Allocation"
                );

        title.setForeground(
                DARK
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        JPanel user =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        user.setOpaque(
                false
        );

        JLabel admin =
                new JLabel(
                        "Admin User"
                );

        admin.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        admin.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        JLabel role =
                new JLabel(
                        "Super Admin"
                );

        role.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        role.setForeground(
                MUTED
        );

        role.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        10
                )
        );

        user.add(admin);
        user.add(role);

        user.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        0,
                        20
                )
        );

        top.add(
                title,
                BorderLayout.WEST
        );

        top.add(
                user,
                BorderLayout.EAST
        );

        return top;
    }

    private JPanel createContent() {

        JPanel content =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        content.setBackground(
                LIGHT
        );

        content.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        content.add(
                createControlPanel(),
                BorderLayout.NORTH
        );

        content.add(
                createCenterPanel(),
                BorderLayout.CENTER
        );

        return content;
    }

    private JPanel createControlPanel() {

        JPanel wrapper =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        wrapper.setBackground(
                Color.WHITE
        );

        wrapper.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JPanel fields =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        fields.setOpaque(
                false
        );

        JLabel routeLabel =
                new JLabel(
                        "Route"
                );

        routeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        routeBox =
                new JComboBox<>();

        routeBox.setPreferredSize(
                new Dimension(
                        180,
                        36
                )
        );

        JLabel timeLabel =
                new JLabel(
                        "Travel Time"
                );

        timeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        timeBox =
                new JComboBox<>(
                        new String[]{
                                "Morning",
                                "Noon",
                                "Afternoon",
                                "Evening"
                        }
                );

        timeBox.setPreferredSize(
                new Dimension(
                        140,
                        36
                )
        );

        JButton allocate =
                createPrimaryButton(
                        "Allocate Buses"
                );

        allocate.addActionListener(
                e -> allocateBuses()
        );

        JButton reset =
                createSecondaryButton(
                        "Reset Allocation"
                );

        reset.addActionListener(
                e -> resetAllocation()
        );

        fields.add(routeLabel);
        fields.add(routeBox);
        fields.add(timeLabel);
        fields.add(timeBox);
        fields.add(allocate);
        fields.add(reset);

        wrapper.add(
                fields,
                BorderLayout.CENTER
        );

        return wrapper;
    }

    private JPanel createCenterPanel() {

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        center.setOpaque(
                false
        );

        center.add(
                createMetricPanel(),
                BorderLayout.NORTH
        );

        JSplitPane split =
                new JSplitPane(
                        JSplitPane.VERTICAL_SPLIT,
                        createBusTablePanel(),
                        createResultPanel()
                );

        split.setResizeWeight(
                0.68
        );

        split.setBorder(
                null
        );

        center.add(
                split,
                BorderLayout.CENTER
        );

        return center;
    }

    private JPanel createMetricPanel() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                12,
                                0
                        )
                );

        panel.setOpaque(
                false
        );

        studentsLabel =
                createMetricCard(
                        "Students",
                        "0"
                );

        requiredLabel =
                createMetricCard(
                        "Required Buses",
                        "0"
                );

        allocatedLabel =
                createMetricCard(
                        "Allocated",
                        "0"
                );

        statusLabel =
                createMetricCard(
                        "Status",
                        "Waiting"
                );

        availableLabel =
                createMetricCard(
                        "Available Buses",
                        String.valueOf(
                                getAvailableBuses()
                        )
                );

        panel.add(
                createCardWrapper(
                        studentsLabel
                )
        );

        panel.add(
                createCardWrapper(
                        requiredLabel
                )
        );

        panel.add(
                createCardWrapper(
                        allocatedLabel
                )
        );

        panel.add(
                createCardWrapper(
                        statusLabel
                )
        );

        panel.add(
                createCardWrapper(
                        availableLabel
                )
        );

        return panel;
    }

    private JLabel createMetricCard(
            String title,
            String value
    ) {

        JLabel label =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                        + "<span style='font-size:11px;color:#667085;'>"
                        + title
                        + "</span><br>"
                        + "<span style='font-size:19px;font-weight:bold;'>"
                        + value
                        + "</span></div></html>",
                        SwingConstants.CENTER
                );

        label.setForeground(
                DARK
        );

        return label;
    }

    private JPanel createCardWrapper(
            JLabel label
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                10,
                                5,
                                10,
                                5
                        )
                )
        );

        card.add(
                label,
                BorderLayout.CENTER
        );

        return card;
    }

    private JPanel createBusTablePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "Bus Allocation Status"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        title.setForeground(
                DARK
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        busTableModel =
                new DefaultTableModel(
                        new String[]{
                                "Bus ID",
                                "Bus Number",
                                "Capacity",
                                "Driver",
                                "Status"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        busTable =
                new JTable(
                        busTableModel
                );

        styleTable(
                busTable
        );

        panel.add(
                new JScrollPane(
                        busTable
                ),
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel createResultPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                8
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "Allocation Result"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        title.setForeground(
                DARK
        );

        resultArea =
                new JTextArea();

        resultArea.setEditable(
                false
        );

        resultArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        12
                )
        );

        resultArea.setForeground(
                DARK
        );

        resultArea.setBackground(
                new Color(
                        250,
                        251,
                        253
                )
        );

        resultArea.setBorder(
                new EmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        resultArea.setText(
                "Select a route and travel time, then click "
                + "\"Allocate Buses\"."
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        panel.add(
                new JScrollPane(
                        resultArea
                ),
                BorderLayout.CENTER
        );

        return panel;
    }

    private void styleTable(
            JTable table
    ) {

        table.setRowHeight(
                38
        );

        table.setShowGrid(
                false
        );

        table.setIntercellSpacing(
                new Dimension(
                        0,
                        0
                )
        );

        table.setSelectionBackground(
                LIGHT_BLUE
        );

        table.setSelectionForeground(
                DARK
        );

        table.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                11
                        )
                );

        table.getTableHeader()
                .setBackground(
                        new Color(
                                248,
                                249,
                                251
                        )
                );

        table.getTableHeader()
                .setForeground(
                        DARK
                );

        table.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                34
                        )
                );

        DefaultTableCellRenderer center =
                new DefaultTableCellRenderer();

        center.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0;
             i < table.getColumnCount();
             i++) {

            if (i != 3) {
                table.getColumnModel()
                        .getColumn(i)
                        .setCellRenderer(
                                center
                        );
            }
        }
    }

    private JButton createPrimaryButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        145,
                        36
                )
        );

        button.setBackground(
                BLUE
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        return button;
    }

    private JButton createSecondaryButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        145,
                        36
                )
        );

        button.setBackground(
                Color.WHITE
        );

        button.setForeground(
                BLUE
        );

        button.setFocusPainted(
                false
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        return button;
    }

    private void loadRoutes() {

        routeBox.removeAllItems();

        for (Route route :
                transportData.getRoutes()) {

            routeBox.addItem(
                    route.getRouteName()
            );
        }
    }

    private void allocateBuses() {

        String routeName =
                (String)
                        routeBox.getSelectedItem();

        String travelTime =
                (String)
                        timeBox.getSelectedItem();

        if (routeName == null ||
                routeName.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a route.",
                    "Allocation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        /*
         * Release the previous allocation first.
         * The current BusAllocationManager is designed around
         * one active allocation state and provides releaseAllBuses().
         */
        controller.resetAllocation();

        BusAllocationResult result =
                controller.allocateBus(
                        routeName,
                        travelTime
                );

        int allocated =
                result.getBusesAllocated();

        Route route =
                transportData.findRoute(
                        routeName
                );

        if (route != null) {

            route.setAllocatedBuses(
                    allocated
            );
        }

        updateMetrics(
                result
        );

        showResult(
                result
        );

        refreshBusTable();
    }

    private void resetAllocation() {

        controller.resetAllocation();

        for (Route route :
                transportData.getRoutes()) {

            route.setAllocatedBuses(
                    0
            );
        }

        studentsLabel.setText(
                metricHtml(
                        "Students",
                        "0"
                )
        );

        requiredLabel.setText(
                metricHtml(
                        "Required Buses",
                        "0"
                )
        );

        allocatedLabel.setText(
                metricHtml(
                        "Allocated",
                        "0"
                )
        );

        statusLabel.setText(
                metricHtml(
                        "Status",
                        "Waiting"
                )
        );

        availableLabel.setText(
                metricHtml(
                        "Available Buses",
                        String.valueOf(
                                getAvailableBuses()
                        )
                )
        );

        resultArea.setText(
                "Allocation reset. All buses are available again."
        );

        refreshBusTable();
    }

    private void updateMetrics(
            BusAllocationResult result
    ) {

        studentsLabel.setText(
                metricHtml(
                        "Students",
                        String.valueOf(
                                result.getStudentCount()
                        )
                )
        );

        requiredLabel.setText(
                metricHtml(
                        "Required Buses",
                        String.valueOf(
                                result.getBusesRequired()
                        )
                )
        );

        allocatedLabel.setText(
                metricHtml(
                        "Allocated",
                        String.valueOf(
                                result.getBusesAllocated()
                        )
                )
        );

        statusLabel.setText(
                metricHtml(
                        "Status",
                        result.getStatus()
                )
        );

        availableLabel.setText(
                metricHtml(
                        "Available Buses",
                        String.valueOf(
                                getAvailableBuses()
                        )
                )
        );
    }

    private String metricHtml(
            String title,
            String value
    ) {

        return "<html><div style='text-align:center;'>"
                + "<span style='font-size:11px;color:#667085;'>"
                + title
                + "</span><br>"
                + "<span style='font-size:19px;font-weight:bold;'>"
                + value
                + "</span></div></html>";
    }

    private void showResult(
            BusAllocationResult result
    ) {

        StringBuilder text =
                new StringBuilder();

        text.append(
                "BUS ALLOCATION RESULT\n"
        );

        text.append(
                "====================================\n\n"
        );

        text.append(
                "Route: "
        );

        text.append(
                result.getRouteName()
        );

        text.append("\n");

        text.append(
                "Travel Time: "
        );

        text.append(
                result.getTravelTime()
        );

        text.append("\n");

        text.append(
                "Students: "
        );

        text.append(
                result.getStudentCount()
        );

        text.append("\n");

        text.append(
                "Buses Required: "
        );

        text.append(
                result.getBusesRequired()
        );

        text.append("\n");

        text.append(
                "Buses Allocated: "
        );

        text.append(
                result.getBusesAllocated()
        );

        text.append("\n");

        text.append(
                "Status: "
        );

        text.append(
                result.getStatus()
        );

        text.append("\n\n");

        text.append(
                "ALLOCATED BUSES\n"
        );

        text.append(
                "------------------------------------\n"
        );

        List<Bus> allocated =
                result.getAllocatedBuses();

        if (allocated.isEmpty()) {

            text.append(
                    "No buses were allocated."
            );

        } else {

            for (Bus bus :
                    allocated) {

                text.append(
                        bus.toString()
                );

                text.append("\n");
            }
        }

        resultArea.setText(
                text.toString()
        );

        resultArea.setCaretPosition(
                0
        );
    }

    private void refreshBusTable() {

        if (busTableModel == null) {
            return;
        }

        busTableModel.setRowCount(
                0
        );

        for (Bus bus :
                transportData.getBuses()) {

            busTableModel.addRow(
                    new Object[]{
                            bus.getBusId(),
                            bus.getBusNumber(),
                            bus.getCapacity(),
                            bus.getDriverName(),
                            bus.isAvailable() ? "Available" : "Assigned"
                    }
            );
        }

        if (availableLabel != null) {

            availableLabel.setText(
                    metricHtml(
                            "Available Buses",
                            String.valueOf(
                                    getAvailableBuses()
                            )
                    )
            );
        }
    }

    private int getAvailableBuses() {

        int count = 0;

        for (Bus bus :
                transportData.getBuses()) {

            if (bus.isAvailable()) {
                count++;
            }
        }

        return count;
    }

    public TransportData getTransportData() {
        return transportData;
    }

    public AllocationController getController() {
        return controller;
    }

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    BusAllocation frame =
                            new BusAllocation();

                    frame.setVisible(
                            true
                    );
                }
        );
    }
}
