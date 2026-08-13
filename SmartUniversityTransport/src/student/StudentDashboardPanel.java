package student;

import model.Booking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDashboardPanel extends JPanel {

    // Theme Color Palette
    private final Color COLOR_BG = new Color(244, 246, 251);
    private final Color COLOR_TEXT_DARK = new Color(30, 41, 59);
    private final Color COLOR_CARD_BG = Color.WHITE;
    private final Color COLOR_PRIMARY = new Color(24, 119, 242);
    private final Color COLOR_BORDER = new Color(226, 232, 240);

    public StudentDashboardPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(COLOR_BG);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // ================= 1. TOP HEADER =================
        JPanel topHeader = new JPanel(new BorderLayout());
        topHeader.setOpaque(false);

        JLabel lblTitle = new JLabel("Student Dashboard");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_TEXT_DARK);
        topHeader.add(lblTitle, BorderLayout.WEST);

        // Date & User Profile Right Side
        JPanel userProfilePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        userProfilePanel.setOpaque(false);

        JLabel lblDate = new JLabel("May 20, 2024  |  10:30 AM  ");
        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDate.setForeground(new Color(100, 116, 139));
        userProfilePanel.add(lblDate);

        JLabel lblUserText = new JLabel("<html><div style='text-align: right;'><b>Ahmed Rahman</b><br><font color='#64748B' size='2'>ID: 20210115</font></div></html>");
        lblUserText.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JLabel lblAvatar = loadScaledLabel("/images/profile.png", 36, 36);
        if (lblAvatar == null) {
            lblAvatar = createFallbackAvatar();
        }

        userProfilePanel.add(lblUserText);
        userProfilePanel.add(lblAvatar);

        topHeader.add(userProfilePanel, BorderLayout.EAST);
        add(topHeader, BorderLayout.NORTH);

        // ================= 2. MAIN CENTER CONTENT =================
        JPanel centerContent = new JPanel();
        centerContent.setLayout(new BoxLayout(centerContent, BoxLayout.Y_AXIS));
        centerContent.setOpaque(false);

        // ----- A. 4 TOP METRIC CARDS -----
        JPanel cardsGrid = new JPanel(new GridLayout(1, 4, 12, 0));
        cardsGrid.setOpaque(false);
        cardsGrid.setMaximumSize(new Dimension(2000, 90));

        cardsGrid.add(createMetricCard("Upcoming Trip", "1", new Color(229, 240, 255), COLOR_PRIMARY));
        cardsGrid.add(createMetricCard("Available Seats", "23", new Color(228, 248, 235), new Color(34, 197, 94)));
        cardsGrid.add(createMetricCard("Available Routes", "8", new Color(243, 230, 255), new Color(168, 85, 247)));
        cardsGrid.add(createMetricCard("My Bookings", "2", new Color(254, 237, 222), new Color(249, 115, 22)));

        centerContent.add(cardsGrid);
        centerContent.add(Box.createVerticalStrut(15));

        // ----- B. MIDDLE SECTION (NEXT TRIP & ANNOUNCEMENTS) -----
        JPanel middleGrid = new JPanel(new GridLayout(1, 2, 15, 0));
        middleGrid.setOpaque(false);
        middleGrid.setMaximumSize(new Dimension(2000, 220));

        // 1. Next Trip Card Section
        JPanel nextTripCard = createStyledPanel();
        nextTripCard.setLayout(new BorderLayout(8, 8));

        JLabel lblNextTripTitle = new JLabel("Next Trip");
        lblNextTripTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNextTripTitle.setForeground(COLOR_TEXT_DARK);
        nextTripCard.add(lblNextTripTitle, BorderLayout.NORTH);

        JPanel tripDetails = new JPanel(new GridLayout(5, 2, 5, 4));
        tripDetails.setOpaque(false);

        addDetailRow(tripDetails, "Route:", "Mirpur 10 to University");
        addDetailRow(tripDetails, "Date:", "May 21, 2024 (Tuesday)");
        addDetailRow(tripDetails, "Time:", "07:30 AM");
        addDetailRow(tripDetails, "Pick-up Point:", "Kazipara Bus Stand");
        addDetailRow(tripDetails, "Bus / Seat:", "BUS-12 / Seat 23");

        nextTripCard.add(tripDetails, BorderLayout.CENTER);

        // Interactive View Ticket Button
        JButton btnViewTicket = new JButton("View Ticket");
        btnViewTicket.setContentAreaFilled(false);
        btnViewTicket.setOpaque(true);
        btnViewTicket.setBackground(COLOR_PRIMARY);
        btnViewTicket.setForeground(Color.WHITE);
        btnViewTicket.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnViewTicket.setFocusPainted(false);
        btnViewTicket.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btnViewTicket.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnViewTicket.setPreferredSize(new Dimension(0, 36));

        // Action Listener to open the Ticket details dialog
        btnViewTicket.addActionListener(e -> showTicketDialog());

        nextTripCard.add(btnViewTicket, BorderLayout.SOUTH);

        // 2. Announcements Card Section
        JPanel announcementsCard = createStyledPanel();
        announcementsCard.setLayout(new BorderLayout(5, 5));

        JLabel lblAnnounceTitle = new JLabel("Announcements");
        lblAnnounceTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblAnnounceTitle.setForeground(COLOR_TEXT_DARK);
        announcementsCard.add(lblAnnounceTitle, BorderLayout.NORTH);

        JPanel announceList = new JPanel();
        announceList.setLayout(new BoxLayout(announceList, BoxLayout.Y_AXIS));
        announceList.setOpaque(false);

        announceList.add(createNoticeItem("Seat booking for next week is open", "May 19, 2024"));
        announceList.add(Box.createVerticalStrut(10));
        announceList.add(createNoticeItem("New Route Added: Uttara Sector 15", "May 18, 2024"));
        announceList.add(Box.createVerticalStrut(10));
        announceList.add(createNoticeItem("Transport Schedule Updated", "May 17, 2024"));

        announcementsCard.add(announceList, BorderLayout.CENTER);

        middleGrid.add(nextTripCard);
        middleGrid.add(announcementsCard);

        centerContent.add(middleGrid);
        centerContent.add(Box.createVerticalStrut(15));

        // ----- C. BOTTOM SECTION (RECENT BOOKINGS TABLE) -----
        JPanel tableCardPanel = createStyledPanel();
        tableCardPanel.setLayout(new BorderLayout(10, 10));

        JLabel tableTitle = new JLabel("Recent Bookings");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        tableTitle.setForeground(COLOR_TEXT_DARK);
        tableCardPanel.add(tableTitle, BorderLayout.NORTH);

        String[] columns = {"Date", "Route", "Time", "Pick-up Point", "Seat", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        List<Booking> list = new ArrayList<>();
//        list.add(new Booking("1", "May 21, 2024", "Mirpur 10 to University", "07:30 AM", "23", "Confirmed"));
//        list.add(new Booking("2", "May 16, 2024", "Dhanmondi to University", "01:30 PM", "15", "Completed"));

//        for (Booking b : list) {
//            String pickup = b.getRoute().contains("Mirpur") ? "Kazipara Bus Stand" : "Dhanmondi 27";
//            model.addRow(new Object[]{b.getDate(), b.getRoute(), b.getTime(), pickup, b.getSeat(), b.getStatus()});
//        }

        JTable table = new JTable(model);
        table.setRowHeight(36);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(248, 250, 252));
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(241, 245, 249));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount() - 1; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Color Status Badge
        table.getColumnModel().getColumn(5).setCellRenderer(new StatusBadgeRenderer());

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.getViewport().setBackground(Color.WHITE);
        tableCardPanel.add(tableScroll, BorderLayout.CENTER);

        centerContent.add(tableCardPanel);

        add(centerContent, BorderLayout.CENTER);
    }

    // Modal Ticket Dialog Viewer
    private void showTicketDialog() {
        JDialog ticketDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Bus E-Ticket", true);
        ticketDialog.setSize(360, 420);
        ticketDialog.setLocationRelativeTo(this);
        ticketDialog.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 25, 20, 25));

        // Header
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        JLabel title = new JLabel("Smart University Transport");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(COLOR_PRIMARY);

        JLabel sub = new JLabel("Official Bus Reservation Pass");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        sub.setForeground(new Color(100, 116, 139));

        header.add(title);
        header.add(sub);
        panel.add(header, BorderLayout.NORTH);

        // Details Grid
        JPanel body = new JPanel(new GridLayout(6, 2, 8, 10));
        body.setOpaque(false);
        body.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));

        addDetailRow(body, "Passenger:", "Ahmed Rahman");
        addDetailRow(body, "Student ID:", "20210115");
        addDetailRow(body, "Route:", "Mirpur 10 to University");
        addDetailRow(body, "Date & Time:", "May 21, 2024 | 07:30 AM");
        addDetailRow(body, "Pick-up Point:", "Kazipara Bus Stand");
        addDetailRow(body, "Bus / Seat:", "BUS-12 / Seat 23");

        panel.add(body, BorderLayout.CENTER);

        // Close Button
        JButton btnClose = new JButton("Close Ticket");
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnClose.setBackground(COLOR_PRIMARY);
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setOpaque(true);
        btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClose.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btnClose.addActionListener(e -> ticketDialog.dispose());

        panel.add(btnClose, BorderLayout.SOUTH);

        ticketDialog.add(panel);
        ticketDialog.setVisible(true);
    }

    // Helper UI Methods
    private JPanel createStyledPanel() {
        JPanel p = new JPanel();
        p.setBackground(COLOR_CARD_BG);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        return p;
    }

    private JPanel createMetricCard(String title, String val, Color bgColor, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout(5, 2));
        card.setBackground(bgColor);
        card.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTitle.setForeground(new Color(71, 85, 105));

        JLabel lblVal = new JLabel(val);
        lblVal.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblVal.setForeground(accentColor);

        JLabel lblLink = new JLabel("View Details >");
        lblLink.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblLink.setForeground(accentColor);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblVal, BorderLayout.CENTER);
        card.add(lblLink, BorderLayout.SOUTH);

        return card;
    }

    private void addDetailRow(JPanel parent, String label, String value) {
        JLabel lblKey = new JLabel(label);
        lblKey.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblKey.setForeground(new Color(100, 116, 139));

        JLabel lblVal = new JLabel(value);
        lblVal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblVal.setForeground(COLOR_TEXT_DARK);

        parent.add(lblKey);
        parent.add(lblVal);
    }

    private JPanel createNoticeItem(String text, String date) {
        JPanel item = new JPanel(new BorderLayout());
        item.setOpaque(false);

        JLabel lblText = new JLabel("• " + text);
        lblText.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JLabel lblDate = new JLabel(date);
        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblDate.setForeground(new Color(148, 163, 184));

        item.add(lblText, BorderLayout.WEST);
        item.add(lblDate, BorderLayout.EAST);
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

    private JLabel createFallbackAvatar() {
        JLabel avatar = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 235, 252));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.setColor(COLOR_PRIMARY);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 14));
                FontMetrics fm = g2.getFontMetrics();
                String text = "AR";
                g2.drawString(text, (getWidth() - fm.stringWidth(text)) / 2, (getHeight() + fm.getAscent() / 2) / 2);
                g2.dispose();
            }
        };
        avatar.setPreferredSize(new Dimension(36, 36));
        return avatar;
    }

    // Status Badge Color Renderer
    private static class StatusBadgeRenderer extends JPanel implements TableCellRenderer {
        private final JLabel lblStatus = new JLabel();

        public StatusBadgeRenderer() {
            setLayout(new GridBagLayout());
            setOpaque(false);
            lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 11));
            lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
            lblStatus.setOpaque(true);
            lblStatus.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
            add(lblStatus);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            String status = (value != null) ? value.toString() : "";
            lblStatus.setText(status);

            if ("Confirmed".equalsIgnoreCase(status)) {
                lblStatus.setBackground(new Color(220, 252, 231));
                lblStatus.setForeground(new Color(22, 101, 52));
            } else if ("Completed".equalsIgnoreCase(status)) {
                lblStatus.setBackground(new Color(224, 242, 254));
                lblStatus.setForeground(new Color(3, 105, 161));
            } else {
                lblStatus.setBackground(new Color(241, 245, 249));
                lblStatus.setForeground(new Color(71, 85, 105));
            }
            return this;
        }
    }
}