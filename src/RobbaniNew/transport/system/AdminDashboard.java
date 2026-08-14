package smart.university.transport.system;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {

        setTitle("Smart University Transport System - Dashboard");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout());

        // ================= SIDEBAR =================

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(240, 700));
        sidebar.setBackground(new Color(21, 101, 192));

        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(
                "<html><center>SMART UNIVERSITY<br>TRANSPORT</center></html>"
        );

        logo.setFont(new Font("Arial", Font.BOLD, 18));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(Box.createVerticalStrut(30));
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(40));

        JButton dashboardBtn = menuButton("Dashboard");
        JButton routesBtn = menuButton("Manage Routes");
        JButton busesBtn = menuButton("Manage Buses");
        JButton allocationBtn = menuButton("Bus Allocation");
        JButton studentsBtn = menuButton("Manage Students");
        JButton logoutBtn = menuButton("Logout");

        sidebar.add(dashboardBtn);
        sidebar.add(routesBtn);
        sidebar.add(busesBtn);
        sidebar.add(allocationBtn);
        sidebar.add(studentsBtn);

        sidebar.add(Box.createVerticalGlue());

        sidebar.add(logoutBtn);
        sidebar.add(Box.createVerticalStrut(30));

        // ================= CONTENT =================

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(245, 247, 250));

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel heading = new JLabel("Admin Dashboard");
        heading.setFont(new Font("Arial", Font.BOLD, 28));

        top.add(heading, BorderLayout.WEST);

        content.add(top, BorderLayout.NORTH);

        // ================= CARDS =================

        JPanel cards = new JPanel(new GridLayout(2, 2, 20, 20));
        cards.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        cards.setBackground(new Color(245, 247, 250));

        cards.add(createCard("TOTAL ROUTES", "12"));
        cards.add(createCard("TOTAL BUSES", "25"));
        cards.add(createCard("STUDENTS", "850"));
        cards.add(createCard("ALLOCATIONS", "18"));

        content.add(cards, BorderLayout.CENTER);

        // ================= BUTTON ACTION =================

        routesBtn.addActionListener(e -> {
            new ManageRoutes(this).setVisible(true);
        });

        busesBtn.addActionListener(e -> {
            new ManageBuses(this).setVisible(true);
        });

        allocationBtn.addActionListener(e -> {
            new BusAllocation(this).setVisible(true);
        });

        studentsBtn.addActionListener(e -> {
            new ManageStudents(this).setVisible(true);
        });

        dashboardBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "You are already on Dashboard."
            );
        });

        logoutBtn.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {

                dispose();

                new AdminLogin().setVisible(true);
            }
        });

        main.add(sidebar, BorderLayout.WEST);
        main.add(content, BorderLayout.CENTER);

        add(main);
    }

    private JButton menuButton(String text) {

        JButton button = new JButton(text);

        button.setMaximumSize(new Dimension(220, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setBackground(new Color(21, 101, 192));
        button.setForeground(Color.WHITE);

        button.setFont(new Font("Arial", Font.BOLD, 14));

        button.setBorderPainted(false);
        button.setFocusPainted(false);

        return button;
    }

    private JPanel createCard(String title, String value) {

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 30, 30, 30
                )
        );

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 35));
        valueLabel.setForeground(new Color(21, 101, 192));

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(valueLabel);

        return panel;
    }
}