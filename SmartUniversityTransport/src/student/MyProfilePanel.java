package student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyProfilePanel extends JPanel {

    // Theme Color Palette
    private final Color COLOR_BG = new Color(245, 247, 250);
    private final Color COLOR_PRIMARY = new Color(24, 119, 242);
    private final Color COLOR_TEXT_DARK = new Color(30, 41, 59);
    private final Color COLOR_TEXT_MUTED = new Color(100, 116, 139);
    private final Color COLOR_BORDER = new Color(226, 232, 240);

    public MyProfilePanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(COLOR_BG);
        setBorder(new EmptyBorder(20, 25, 20, 25));

        // ================= 1. TOP HEADER =================
        JPanel topHeader = new JPanel();
        topHeader.setLayout(new BoxLayout(topHeader, BoxLayout.Y_AXIS));
        topHeader.setOpaque(false);

        JLabel lblTitle = new JLabel("My Profile");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_TEXT_DARK);

        JLabel lblSubTitle = new JLabel("View and update your profile information");
        lblSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubTitle.setForeground(COLOR_TEXT_MUTED);

        topHeader.add(lblTitle);
        topHeader.add(Box.createVerticalStrut(3));
        topHeader.add(lblSubTitle);

        add(topHeader, BorderLayout.NORTH);

        // ================= 2. MAIN CARD CONTAINER =================
        JPanel cardContainer = new JPanel(new BorderLayout(25, 0));
        cardContainer.setBackground(Color.WHITE);
        cardContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(25, 25, 25, 25)
        ));

        // ----- LEFT COLUMN: PROFILE AVATAR ICON -----
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(160, 0));

        // Circular Profile Avatar with Icon
        JPanel avatarPanel = createAvatarPanel();
        avatarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(avatarPanel);

        cardContainer.add(leftPanel, BorderLayout.WEST);

        // ----- RIGHT COLUMN: FORM FIELDS & BUTTON -----
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 10, 6, 10);

        // Form Fields
        addFormField(formPanel, gbc, 0, "Student ID", new JTextField("20210115"));
        addFormField(formPanel, gbc, 1, "Full Name", new JTextField("Ahmed Rahman"));
        addFormField(formPanel, gbc, 2, "Email", new JTextField("ahmed.rahman@iub.edu.bd"));
        addFormField(formPanel, gbc, 3, "Phone Number", new JTextField("017XXXXXXXX"));
        addFormField(formPanel, gbc, 4, "Department", new JTextField("Computer Science & Engineering"));
        addFormField(formPanel, gbc, 5, "Batch / Year", new JTextField("2021"));
        addFormField(formPanel, gbc, 6, "Emergency Contact", new JTextField("018XXXXXXXX (Father)"));

        // Preferred Route Dropdown
        JComboBox<String> cbRoute = new JComboBox<>(new String[]{
                "Mirpur 10 to University",
                "Dhanmondi to University",
                "Uttara to University",
                "Farmgate to University",
                "ECB Chattar to University"
        });
        cbRoute.setBackground(Color.WHITE);
        addFormField(formPanel, gbc, 7, "Preferred Route", cbRoute);

        // Preferred Pick-up Dropdown
        JComboBox<String> cbPickup = new JComboBox<>(new String[]{
                "Kazipara Bus Stand",
                "Dhanmondi 27",
                "House Building Uttara",
                "Farmgate Metro Station"
        });
        cbPickup.setBackground(Color.WHITE);
        addFormField(formPanel, gbc, 8, "Preferred Pick-up Point", cbPickup);

        // Update Profile Button
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.insets = new Insets(18, 10, 0, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;

        JButton btnUpdate = new JButton("Update Profile");
        btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnUpdate.setBackground(COLOR_PRIMARY);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.setOpaque(true);
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUpdate.setPreferredSize(new Dimension(150, 38));
        btnUpdate.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        btnUpdate.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE)
        );

        formPanel.add(btnUpdate, gbc);

        cardContainer.add(formPanel, BorderLayout.CENTER);

        add(cardContainer, BorderLayout.CENTER);
    }

    // Helper: Add Form Label and Input Component
    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent inputComponent) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.35;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(COLOR_TEXT_MUTED);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.65;
        inputComponent.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        inputComponent.setPreferredSize(new Dimension(0, 32));

        if (inputComponent instanceof JTextField) {
            JTextField tf = (JTextField) inputComponent;
            tf.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(COLOR_BORDER, 1),
                    BorderFactory.createEmptyBorder(0, 8, 0, 8)
            ));
        }

        panel.add(inputComponent, gbc);
    }

    // Helper: Profile Avatar Builder with Vector User Icon
    private JPanel createAvatarPanel() {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int size = Math.min(getWidth(), getHeight()) - 10;
                int x = (getWidth() - size) / 2;
                int y = (getHeight() - size) / 2;

                // Circular background
                g2.setColor(new Color(220, 235, 252));
                g2.fillOval(x, y, size, size);

                // Circular border
                g2.setColor(COLOR_PRIMARY);
                g2.setStroke(new BasicStroke(2));
                g2.drawOval(x, y, size, size);

                // Clip drawing to keep shoulders inside circle bounds
                Shape oldClip = g2.getClip();
                g2.setClip(new Ellipse2D.Float(x, y, size, size));

                // Draw Vector Profile Icon (Head + Torso/Shoulders)
                g2.setColor(COLOR_PRIMARY);

                int cx = x + size / 2;

                // Head
                int headRadius = (int) (size * 0.18);
                int headY = y + (int) (size * 0.38);
                g2.fillOval(cx - headRadius, headY - headRadius, headRadius * 2, headRadius * 2);

                // Shoulders / Torso
                int torsoWidth = (int) (size * 0.65);
                int torsoHeight = (int) (size * 0.50);
                int torsoY = y + (int) (size * 0.62);
                g2.fillArc(cx - torsoWidth / 2, torsoY, torsoWidth, torsoHeight, 0, 180);

                // Restore original clip
                g2.setClip(oldClip);
                g2.dispose();
            }
        };
        p.setPreferredSize(new Dimension(120, 120));
        p.setMaximumSize(new Dimension(120, 120));
        p.setOpaque(false);
        return p;
    }
}