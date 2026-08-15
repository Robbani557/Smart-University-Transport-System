package student;

import authentication.StudentSession;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    private StudentMainFrame mainFrame;
    private JButton activeButton;

    // Color Palette
    private final Color COLOR_SIDEBAR = new Color(11, 58, 126);
    private final Color COLOR_ACTIVE = new Color(24, 119, 242);
    private final Color COLOR_TEXT_UNSELECTED = new Color(220, 230, 245);

    public SidebarPanel(StudentMainFrame frame) {
        this.mainFrame = frame;
        setPreferredSize(new Dimension(230, 0));
        setBackground(COLOR_SIDEBAR);
        setLayout(new BorderLayout());

        // --- 1. Top Logo Header Section ---
        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setBorder(BorderFactory.createEmptyBorder(22, 12, 20, 12));

        JLabel logoLabel = createScaledLabel("/images/logo.png", 64, 64);
        if (logoLabel != null) {
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            logoPanel.add(logoLabel);
        } else {
            JLabel txtLogo = new JLabel("Smart University");
            txtLogo.setForeground(Color.WHITE);
            txtLogo.setFont(new Font("Segoe UI", Font.BOLD, 16));
            txtLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
            logoPanel.add(txtLogo);
        }

        JLabel appTitle = new JLabel("Transport System");
        appTitle.setForeground(new Color(175, 195, 230));
        appTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        appTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(Box.createVerticalStrut(5));
        logoPanel.add(appTitle);

        add(logoPanel, BorderLayout.NORTH);

        // --- 2. Middle Navigation Menu ---
        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JButton btnDash = createNavButton("Dashboard", "Dashboard", "/images/ic_dashboard.png");
        JButton btnBook = createNavButton("Book Seat", "BookSeat", "/images/ic_book.png");
        JButton btnHistory = createNavButton("My Bookings", "BookingHistory", "/images/ic_history.png");
        JButton btnProfile = createNavButton("My Profile", "Profile", "/images/ic_profile.png");

        setActiveButton(btnDash);

        menuPanel.add(btnDash);
        menuPanel.add(Box.createVerticalStrut(12));
        menuPanel.add(btnBook);
        menuPanel.add(Box.createVerticalStrut(12));
        menuPanel.add(btnHistory);
        menuPanel.add(Box.createVerticalStrut(12));
        menuPanel.add(btnProfile);

        menuPanel.add(Box.createVerticalGlue());

        // Logout Button
        JButton btnLogout = createNavButton("Logout", "Logout", "/images/ic_logout.png");
        menuPanel.add(btnLogout);

        add(menuPanel, BorderLayout.CENTER);

        // --- 3. Bottom Bus Banner ---
        JLabel busGraphic = createScaledLabel("/images/bus_sidebar.png", 198, 84);
        if (busGraphic != null) {
            JPanel busPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            busPanel.setOpaque(false);
            busPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 5));
            busPanel.add(busGraphic);
            add(busPanel, BorderLayout.SOUTH);
        }
    }

    private JButton createNavButton(String text, String screenKey, String iconPath) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(206, 44));
        btn.setPreferredSize(new Dimension(206, 44));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBackground(COLOR_SIDEBAR);
        btn.setForeground(COLOR_TEXT_UNSELECTED);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 8));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon icon = loadScaledIcon(iconPath, 18, 18);
        if (icon != null) {
            btn.setIcon(icon);
            btn.setIconTextGap(14);
        }

        btn.addActionListener(e -> {
            if ("Logout".equals(screenKey)) {
                int res = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    StudentSession.clear();

                    new authentication.LoginFrame().setVisible(true);
                    mainFrame.dispose();
                }
            } else {
                setActiveButton(btn);
                mainFrame.showScreen(screenKey);
            }
        });

        return btn;
    }

    private void setActiveButton(JButton button) {
        if (activeButton != null) {
            activeButton.setBackground(COLOR_SIDEBAR);
            activeButton.setForeground(COLOR_TEXT_UNSELECTED);
        }
        activeButton = button;
        activeButton.setBackground(COLOR_ACTIVE);
        activeButton.setForeground(Color.WHITE);
    }

    private ImageIcon loadScaledIcon(String path, int width, int height) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                ImageIcon original = new ImageIcon(imgURL);
                Image resized = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(resized);
            }
        } catch (Exception ignored) {}
        return null;
    }

    private JLabel createScaledLabel(String path, int width, int height) {
        ImageIcon icon = loadScaledIcon(path, width, height);
        if (icon != null) {
            return new JLabel(icon);
        }
        return null;
    }
}