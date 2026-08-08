package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BookSeatPanel extends JPanel {

    // Theme Color Palette
    private final Color COLOR_BG = new Color(244, 246, 251);
    private final Color COLOR_TEXT_DARK = new Color(30, 41, 59);
    private final Color COLOR_PRIMARY = new Color(24, 119, 242);
    private final Color COLOR_SEAT_GREEN = new Color(34, 197, 94);
    private final Color COLOR_CARD_BG = Color.WHITE;

    private JLabel lblSeatCount;
    private int availableSeats = 23;

    private JComboBox<String> cmbRoute;
    private JComboBox<String> cmbDate;
    private JComboBox<String> cmbTime;
    private JComboBox<String> cmbPickup;

    public BookSeatPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(COLOR_BG);
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // ================= 1. TOP HEADER =================
        JPanel topHeader = new JPanel(new BorderLayout());
        topHeader.setOpaque(false);

        JLabel lblTitle = new JLabel("Book a Seat");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_TEXT_DARK);

        JLabel lblSubTitle = new JLabel("Fill in the details to book your seat");
        lblSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubTitle.setForeground(new Color(100, 116, 139));

        JPanel titleContainer = new JPanel();
        titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
        titleContainer.setOpaque(false);
        titleContainer.add(lblTitle);
        titleContainer.add(Box.createVerticalStrut(3));
        titleContainer.add(lblSubTitle);

        topHeader.add(titleContainer, BorderLayout.WEST);
        add(topHeader, BorderLayout.NORTH);

        // ================= 2. MAIN CONTENT AREA =================
        JPanel mainContent = new JPanel(new GridLayout(1, 2, 20, 0));
        mainContent.setOpaque(false);

        // ----- LEFT PANEL: FORM INPUTS & AVAILABLE SEATS -----
        JPanel formCard = createStyledPanel();
        formCard.setLayout(new BorderLayout(15, 15));

        JPanel formFields = new JPanel(new GridBagLayout());
        formFields.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.weightx = 1.0;

        // Select Route Dropdown
        String[] routes = {
            "Mirpur 10 to University",
            "Dhanmondi to University",
            "Uttara to University",
            "Farmgate to University",
            "ECB Chattar to University"
        };
        cmbRoute = addFormRow(formFields, gbc, 0, "Select Route", routes);

        // Select Date
        String[] dates = {
            "May 21, 2024 (Tuesday)",
            "May 22, 2024 (Wednesday)",
            "May 23, 2024 (Thursday)"
        };
        cmbDate = addFormRow(formFields, gbc, 1, "Select Date", dates);

        // Select Time
        String[] times = {
            "07:30 AM",
            "01:30 PM",
            "04:30 PM"
        };
        cmbTime = addFormRow(formFields, gbc, 2, "Select Time", times);

        // Pick-up Point
        String[] pickups = {
            "Kazipara Bus Stand",
            "Dhanmondi 27",
            "House Building Uttara",
            "Farmgate Metro Station"
        };
        cmbPickup = addFormRow(formFields, gbc, 3, "Pick-up Point", pickups);

        formCard.add(formFields, BorderLayout.NORTH);

        // Available Seats Indicator
        JPanel seatContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        seatContainer.setOpaque(false);

        JLabel lblAvailableText = new JLabel("Available Seats");
        lblAvailableText.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblAvailableText.setForeground(COLOR_TEXT_DARK);

        lblSeatCount = new JLabel(String.valueOf(availableSeats));
        lblSeatCount.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblSeatCount.setForeground(COLOR_SEAT_GREEN);

        seatContainer.add(lblAvailableText);
        seatContainer.add(lblSeatCount);

        formCard.add(seatContainer, BorderLayout.CENTER);

        // Interactive Check Availability Button
        JButton btnCheck = new JButton("Check Availability");
        btnCheck.setContentAreaFilled(false);
        btnCheck.setOpaque(true);
        btnCheck.setBackground(COLOR_PRIMARY);
        btnCheck.setForeground(Color.WHITE);
        btnCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCheck.setFocusPainted(false);
        btnCheck.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnCheck.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCheck.setPreferredSize(new Dimension(0, 42));

        // Action listener attached
        btnCheck.addActionListener(e -> openSeatSelectionDialog());

        formCard.add(btnCheck, BorderLayout.SOUTH);

        mainContent.add(formCard);

        // ----- RIGHT PANEL: IMPORTANT NOTES & GRAPHIC -----
        JPanel rightPanel = new JPanel(new BorderLayout(15, 15));
        rightPanel.setOpaque(false);

        // Important Notes Card
        JPanel notesCard = createStyledPanel();
        notesCard.setLayout(new BorderLayout(10, 10));

        JLabel lblNotesTitle = new JLabel("Important Notes");
        lblNotesTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNotesTitle.setForeground(COLOR_TEXT_DARK);
        notesCard.add(lblNotesTitle, BorderLayout.NORTH);

        JPanel notesList = new JPanel();
        notesList.setLayout(new BoxLayout(notesList, BoxLayout.Y_AXIS));
        notesList.setOpaque(false);

        notesList.add(createNoteBullet("Booking is allowed up to 10:00 PM of the previous day."));
        notesList.add(Box.createVerticalStrut(10));
        notesList.add(createNoteBullet("Please be present at the pick-up point 10 minutes before departure."));
        notesList.add(Box.createVerticalStrut(10));
        notesList.add(createNoteBullet("Cancellation is allowed up to 1 hour before departure."));

        notesCard.add(notesList, BorderLayout.CENTER);

        rightPanel.add(notesCard, BorderLayout.NORTH);

        // Bottom Bus Illustrative Image
        JLabel busGraphic = loadScaledLabel("/images/bus_sidebar.png", 320, 150);
        if (busGraphic != null) {
            JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            imagePanel.setOpaque(false);
            imagePanel.add(busGraphic);
            rightPanel.add(imagePanel, BorderLayout.CENTER);
        }

        mainContent.add(rightPanel);

        add(mainContent, BorderLayout.CENTER);
    }

    // Modal Seat Selection Grid Dialog
    private void openSeatSelectionDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Select Bus Seat", true);
        dialog.setSize(420, 520);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(15, 20, 15, 20));

        // Header Information
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        JLabel lblBus = new JLabel("Bus-12 (" + cmbRoute.getSelectedItem() + ")");
        lblBus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblBus.setForeground(COLOR_PRIMARY);

        JLabel lblTimeInfo = new JLabel(cmbDate.getSelectedItem() + " at " + cmbTime.getSelectedItem());
        lblTimeInfo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTimeInfo.setForeground(new Color(100, 116, 139));

        header.add(lblBus);
        header.add(lblTimeInfo);
        root.add(header, BorderLayout.NORTH);

        // Interactive Seat Grid (5 rows of 4 seats = 20 seats)
        JPanel busGrid = new JPanel(new GridLayout(6, 5, 8, 8));
        busGrid.setOpaque(false);

        // Driver Indicator Header Row
        JLabel lblDriver = new JLabel("Driver 🚘");
        lblDriver.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDriver.setForeground(new Color(148, 163, 184));
        busGrid.add(lblDriver);
        busGrid.add(new JLabel("")); // Spacer
        busGrid.add(new JLabel("")); // Aisle Spacer
        busGrid.add(new JLabel("")); // Spacer
        busGrid.add(new JLabel("")); // Spacer

        // Java 8 Compatible Occupied Seats Set Initialization
        Set<Integer> occupiedSeats = new HashSet<>(Arrays.asList(2, 5, 8, 11, 14, 18));
        final int[] selectedSeat = {-1};
        JButton[] seatButtons = new JButton[21];

        for (int i = 1; i <= 20; i++) {
            int seatNo = i;
            JButton btnSeat = new JButton(String.valueOf(seatNo));
            btnSeat.setFont(new Font("Segoe UI", Font.BOLD, 11));
            btnSeat.setFocusPainted(false);
            btnSeat.setCursor(new Cursor(Cursor.HAND_CURSOR));

            if (occupiedSeats.contains(seatNo)) {
                btnSeat.setBackground(new Color(226, 232, 240));
                btnSeat.setForeground(new Color(148, 163, 184));
                btnSeat.setEnabled(false);
            } else {
                btnSeat.setBackground(new Color(220, 252, 231));
                btnSeat.setForeground(new Color(22, 101, 52));

                btnSeat.addActionListener(e -> {
                    selectedSeat[0] = seatNo;
                    for (int s = 1; s <= 20; s++) {
                        if (seatButtons[s] != null && seatButtons[s].isEnabled()) {
                            if (s == seatNo) {
                                seatButtons[s].setBackground(COLOR_PRIMARY);
                                seatButtons[s].setForeground(Color.WHITE);
                            } else {
                                seatButtons[s].setBackground(new Color(220, 252, 231));
                                seatButtons[s].setForeground(new Color(22, 101, 52));
                            }
                        }
                    }
                });
            }

            seatButtons[i] = btnSeat;
            busGrid.add(btnSeat);

            // Insert Aisle spacer after every 2 seats
            if (i % 2 == 0 && i % 4 != 0) {
                JLabel aisle = new JLabel("");
                busGrid.add(aisle);
            }
        }

        root.add(busGrid, BorderLayout.CENTER);

        // Bottom Action Panel
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setOpaque(false);

        // Legend
        JPanel legend = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        legend.setOpaque(false);
        legend.add(createLegendItem("Available", new Color(220, 252, 231)));
        legend.add(createLegendItem("Occupied", new Color(226, 232, 240)));
        legend.add(createLegendItem("Selected", COLOR_PRIMARY));
        bottomPanel.add(legend, BorderLayout.NORTH);

        JButton btnConfirm = new JButton("Confirm Booking");
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnConfirm.setBackground(COLOR_SEAT_GREEN);
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.setContentAreaFilled(false);
        btnConfirm.setOpaque(true);
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirm.setPreferredSize(new Dimension(0, 38));

        btnConfirm.addActionListener(e -> {
            if (selectedSeat[0] == -1) {
                JOptionPane.showMessageDialog(dialog, "Please select an available seat first!", "Selection Required", JOptionPane.WARNING_MESSAGE);
            } else {
                availableSeats--;
                lblSeatCount.setText(String.valueOf(availableSeats));
                dialog.dispose();
                JOptionPane.showMessageDialog(this,
                        "Successfully booked Seat " + selectedSeat[0] + " for " + cmbRoute.getSelectedItem(),
                        "Booking Confirmed",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        bottomPanel.add(btnConfirm, BorderLayout.SOUTH);
        root.add(bottomPanel, BorderLayout.SOUTH);

        dialog.add(root);
        dialog.setVisible(true);
    }

    private JPanel createLegendItem(String text, Color color) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        p.setOpaque(false);

        JPanel box = new JPanel();
        box.setPreferredSize(new Dimension(12, 12));
        box.setBackground(color);
        box.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));

        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));

        p.add(box);
        p.add(lbl);
        return p;
    }

    // Helper UI Methods
    private JPanel createStyledPanel() {
        JPanel p = new JPanel();
        p.setBackground(COLOR_CARD_BG);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(15, 18, 15, 18)
        ));
        return p;
    }

    private JComboBox<String> addFormRow(JPanel parent, GridBagConstraints gbc, int row, String labelText, String[] options) {
        gbc.gridy = row * 2;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(100, 116, 139));
        parent.add(label, gbc);

        gbc.gridy = (row * 2) + 1;
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboBox.setBackground(Color.WHITE);
        comboBox.setPreferredSize(new Dimension(0, 35));
        parent.add(comboBox, gbc);

        return comboBox;
    }

    private JPanel createNoteBullet(String text) {
        JPanel item = new JPanel(new BorderLayout(8, 0));
        item.setOpaque(false);

        JLabel lblBullet = new JLabel("•");
        lblBullet.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblBullet.setForeground(COLOR_PRIMARY);

        JLabel lblText = new JLabel("<html><body style='width: 230px;'>" + text + "</body></html>");
        lblText.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblText.setForeground(new Color(71, 85, 105));

        item.add(lblBullet, BorderLayout.WEST);
        item.add(lblText, BorderLayout.CENTER);

        return item;
    }

    private JLabel loadScaledLabel(String path, int width, int height) {
        try {
            java.net.URL url = getClass().getResource(path);
            if (url != null) {
                ImageIcon icon = new ImageIcon(url);
                Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new JLabel(new ImageIcon(img));
            }
        } catch (Exception ignored) {}
        return null;
    }
}