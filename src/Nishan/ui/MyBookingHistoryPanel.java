package ui;

import model.Booking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MyBookingHistoryPanel extends JPanel {

    // Theme Colors
    private final Color COLOR_BG = new Color(245, 247, 250);
    private final Color COLOR_PRIMARY = new Color(24, 119, 242);
    private final Color COLOR_TEXT_DARK = new Color(30, 41, 59);
    private final Color COLOR_TEXT_MUTED = new Color(100, 116, 139);
    private final Color COLOR_BORDER = new Color(226, 232, 240);

    // Table & Pagination State
    private DefaultTableModel tableModel;
    private JTable table;
    private String activeFilter = "All";

    private List<Booking> masterBookingList;
    private JPanel paginationPanel;
    private int currentPage = 1;
    private final int PAGE_SIZE = 4; // Rows per page

    public MyBookingHistoryPanel() {
        setLayout(new BorderLayout(0, 15));
        setBackground(COLOR_BG);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        // Load Master Data
        masterBookingList = getSampleBookingData();

        // ================= 1. HEADER SECTION =================
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("My Booking History");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_TEXT_DARK);

        JLabel lblSubTitle = new JLabel("View all your past and upcoming bookings");
        lblSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubTitle.setForeground(COLOR_TEXT_MUTED);

        headerPanel.add(lblTitle);
        headerPanel.add(Box.createVerticalStrut(4));
        headerPanel.add(lblSubTitle);

        add(headerPanel, BorderLayout.NORTH);

        // ================= 2. CARD CONTAINER =================
        JPanel cardPanel = new JPanel(new BorderLayout(0, 15));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(15, 20, 15, 20)
        ));

        // ----- TAB FILTER BAR -----
        JPanel filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 0));
        filterBar.setOpaque(false);
        filterBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_BORDER));

        String[] tabs = {"All", "Upcoming", "Completed", "Cancelled"};
        for (String tabName : tabs) {
            filterBar.add(createTabButton(tabName));
        }

        cardPanel.add(filterBar, BorderLayout.NORTH);

        // ----- TABLE SETUP -----
        String[] columns = {"Booking ID", "Date", "Route", "Time", "Seat", "Status", "Action"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Only Action column is clickable
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(42);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setForeground(COLOR_TEXT_DARK);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setForeground(COLOR_TEXT_MUTED);
        table.getTableHeader().setBackground(new Color(248, 250, 252));
        table.getTableHeader().setPreferredSize(new Dimension(0, 35));
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(241, 245, 249));

        // Center alignments
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 5; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Custom renderers for Status Badge & View Button
        table.getColumnModel().getColumn(5).setCellRenderer(new StatusBadgeRenderer());
        table.getColumnModel().getColumn(6).setCellRenderer(new ViewButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ViewButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        cardPanel.add(scrollPane, BorderLayout.CENTER);

        // ----- PAGINATION BAR CONTAINER -----
        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 10));
        paginationPanel.setOpaque(false);
        cardPanel.add(paginationPanel, BorderLayout.SOUTH);

        add(cardPanel, BorderLayout.CENTER);

        // Initial Data & Pagination Load
        refreshTableData();
    }

    // Dynamic Filter & Pagination Engine
    private void refreshTableData() {
        // 1. Filter Master Data according to Active Tab
        List<Booking> filteredList = new ArrayList<>();
        for (Booking b : masterBookingList) {
            if ("All".equalsIgnoreCase(activeFilter)) {
                filteredList.add(b);
            } else if ("Upcoming".equalsIgnoreCase(activeFilter) && "Confirmed".equalsIgnoreCase(b.getStatus())) {
                filteredList.add(b);
            } else if (b.getStatus().equalsIgnoreCase(activeFilter)) {
                filteredList.add(b);
            }
        }

        // 2. Calculate Page Limits
        int totalPages = (int) Math.ceil((double) filteredList.size() / PAGE_SIZE);
        if (totalPages < 1) totalPages = 1;

        if (currentPage > totalPages) currentPage = totalPages;
        if (currentPage < 1) currentPage = 1;

        // 3. Clear and Slice Data for Current Page
        tableModel.setRowCount(0);
        int startIndex = (currentPage - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, filteredList.size());

        for (int i = startIndex; i < endIndex; i++) {
            Booking b = filteredList.get(i);
            tableModel.addRow(new Object[]{
                    b.getBookingID(),
                    b.getDate(),
                    b.getRoute(),
                    b.getTime(),
                    b.getSeat(),
                    b.getStatus(),
                    "View"
            });
        }

        // 4. Rebuild Pagination Buttons
        updatePaginationControls(totalPages);
    }

    // Rebuilds Pagination Bar Controls Dynamically
    private void updatePaginationControls(int totalPages) {
        paginationPanel.removeAll();

        // "< Previous" Button
        JButton btnPrev = createPaginationButton("< Previous", false, false);
        btnPrev.setEnabled(currentPage > 1);
        btnPrev.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                refreshTableData();
            }
        });
        paginationPanel.add(btnPrev);

        // Numeric Page Buttons (1, 2, 3...)
        for (int i = 1; i <= totalPages; i++) {
            int pageNum = i;
            boolean isActive = (i == currentPage);
            JButton btnPage = createPaginationButton(String.valueOf(i), isActive, true);
            btnPage.addActionListener(e -> {
                currentPage = pageNum;
                refreshTableData();
            });
            paginationPanel.add(btnPage);
        }

        // "Next >" Button
        JButton btnNext = createPaginationButton("Next >", false, false);
        btnNext.setEnabled(currentPage < totalPages);
        btnNext.addActionListener(e -> {
            if (currentPage < totalPages) {
                currentPage++;
                refreshTableData();
            }
        });
        paginationPanel.add(btnNext);

        paginationPanel.revalidate();
        paginationPanel.repaint();
    }

    // Tab Button Builder
    private JLabel createTabButton(String text) {
        JLabel tab = new JLabel(text);
        tab.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tab.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (text.equals(activeFilter)) {
            tab.setForeground(COLOR_PRIMARY);
            tab.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_PRIMARY));
        } else {
            tab.setForeground(COLOR_TEXT_MUTED);
            tab.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        }

        tab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                activeFilter = text;
                currentPage = 1; // Reset to page 1 on tab switch

                Container parent = tab.getParent();
                for (Component c : parent.getComponents()) {
                    if (c instanceof JLabel) {
                        JLabel l = (JLabel) c;
                        if (l.getText().equals(activeFilter)) {
                            l.setForeground(COLOR_PRIMARY);
                            l.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_PRIMARY));
                        } else {
                            l.setForeground(COLOR_TEXT_MUTED);
                            l.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
                        }
                    }
                }
                refreshTableData();
            }
        });

        return tab;
    }

    // Pagination Button Visual Helper
    private JButton createPaginationButton(String text, boolean isActive, boolean isNumber) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (isActive) {
            btn.setBackground(COLOR_PRIMARY);
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        } else {
            btn.setBackground(Color.WHITE);
            btn.setForeground(COLOR_TEXT_MUTED);
            btn.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
            btn.setPreferredSize(isNumber ? new Dimension(32, 28) : new Dimension(85, 28));
        }

        return btn;
    }

    // Expanded Sample Data Builder (10 Items for multi-page demonstration)
    private List<Booking> getSampleBookingData() {
        List<Booking> list = new ArrayList<>();
        list.add(new Booking("BK240521001", "May 21, 2024", "Mirpur 10 -> University", "07:30 AM", "23", "Confirmed"));
        list.add(new Booking("BK240520015", "May 20, 2024", "Dhanmondi -> University", "07:30 AM", "05", "Confirmed"));
        list.add(new Booking("BK240519008", "May 19, 2024", "Uttara -> University", "01:30 PM", "14", "Confirmed"));
        list.add(new Booking("BK240516032", "May 16, 2024", "Dhanmondi -> University", "01:30 PM", "15", "Completed"));
        list.add(new Booking("BK240514018", "May 14, 2024", "Uttara -> University", "07:30 AM", "12", "Completed"));
        list.add(new Booking("BK240512003", "May 12, 2024", "Farmgate -> University", "04:30 PM", "08", "Completed"));
        list.add(new Booking("BK240510011", "May 10, 2024", "ECB Chattar -> University", "07:30 AM", "19", "Completed"));
        list.add(new Booking("BK240509007", "May 9, 2024", "Mirpur 10 -> University", "07:30 AM", "09", "Cancelled"));
        list.add(new Booking("BK240507022", "May 7, 2024", "Banani -> University", "01:30 PM", "18", "Completed"));
        list.add(new Booking("BK240505019", "May 5, 2024", "Mirpur 10 -> University", "04:30 PM", "02", "Cancelled"));
        return list;
    }

    // ================= CUSTOM RENDERERS =================

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

            switch (status) {
                case "Confirmed":
                    lblStatus.setBackground(new Color(220, 252, 231));
                    lblStatus.setForeground(new Color(22, 101, 52));
                    break;
                case "Completed":
                    lblStatus.setBackground(new Color(224, 242, 254));
                    lblStatus.setForeground(new Color(3, 105, 161));
                    break;
                case "Cancelled":
                    lblStatus.setBackground(new Color(254, 226, 226));
                    lblStatus.setForeground(new Color(153, 27, 27));
                    break;
                default:
                    lblStatus.setBackground(new Color(241, 245, 249));
                    lblStatus.setForeground(new Color(71, 85, 105));
                    break;
            }
            return this;
        }
    }

    private class ViewButtonRenderer extends JPanel implements TableCellRenderer {
        private final JButton btnView = new JButton("View");

        public ViewButtonRenderer() {
            setLayout(new GridBagLayout());
            setOpaque(false);
            btnView.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            btnView.setBackground(Color.WHITE);
            btnView.setForeground(COLOR_TEXT_DARK);
            btnView.setFocusPainted(false);
            btnView.setContentAreaFilled(false);
            btnView.setOpaque(true);
            btnView.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COLOR_BORDER, 1, true),
                    BorderFactory.createEmptyBorder(2, 10, 2, 10)
            ));
            add(btnView);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    private class ViewButtonEditor extends DefaultCellEditor {
        private final JPanel panel = new JPanel(new GridBagLayout());
        private final JButton btnView = new JButton("View");
        private String bookingId = "";

        public ViewButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel.setOpaque(false);
            btnView.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            btnView.setBackground(Color.WHITE);
            btnView.setForeground(COLOR_TEXT_DARK);
            btnView.setFocusPainted(false);
            btnView.setContentAreaFilled(false);
            btnView.setOpaque(true);
            btnView.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COLOR_BORDER, 1, true),
                    BorderFactory.createEmptyBorder(2, 10, 2, 10)
            ));
            btnView.addActionListener(e -> fireEditingStopped());
            panel.add(btnView);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            bookingId = tableModel.getValueAt(row, 0).toString();
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            JOptionPane.showMessageDialog(this.getComponent(),
                    "Viewing ticket details for Booking ID: " + bookingId,
                    "Ticket Details", JOptionPane.INFORMATION_MESSAGE);
            return "View";
        }
    }
}