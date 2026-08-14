package authentication;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import components.RoundedButton;
import components.RoundedTextField;

public class ProfileSetupFrame extends JFrame {

    private static final Color PRIMARY = new Color(21, 101, 192);
    private static final Color PRIMARY_DARK = new Color(13, 71, 161);
    private static final Color TEXT_DARK = new Color(35, 45, 60);
    private static final Color TEXT_MUTED = new Color(105, 115, 130);
    private static final Color FIELD_BG = new Color(250, 251, 253);

    private JTextField txtStudentId;
    private JTextField txtName;
    private JTextField txtDepartment;
    private JTextField txtPhone;
    private JTextField txtEmergency;

    private JComboBox<String> cmbRoute;
    private JComboBox<String> cmbPickup;

    private JButton btnSave;

    public ProfileSetupFrame() {
        initializeFrame();
        buildInterface();
    }

    private void initializeFrame() {
        setTitle("Smart University Transport System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 620));
        setSize(1100, 700);
        setLocationRelativeTo(null);
    }

    private void buildInterface() {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(Color.WHITE);

        JPanel brand = createBrandPanel();
        JPanel form = createFormPanel();

        GridBagConstraints left = new GridBagConstraints();
        left.gridx = 0;
        left.gridy = 0;
        left.weightx = 0.38;
        left.weighty = 1.0;
        left.fill = GridBagConstraints.BOTH;

        GridBagConstraints right = new GridBagConstraints();
        right.gridx = 1;
        right.gridy = 0;
        right.weightx = 0.62;
        right.weighty = 1.0;
        right.fill = GridBagConstraints.BOTH;

        root.add(brand, left);
        root.add(form, right);

        setContentPane(root);
    }

    private JPanel createBrandPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PRIMARY);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.CENTER;

        g.gridy = 0;
        g.weighty = 0.12;
        g.fill = GridBagConstraints.NONE;
        panel.add(new ImageLabel("/images/logo.png", 130, 130), g);

        JLabel title = label("YOUR PROFILE", 29, Font.BOLD, Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 1;
        g.weighty = 0.0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(5, 0, 2, 0);
        panel.add(title, g);

        JLabel line1 = label("Complete your", 21, Font.PLAIN, Color.WHITE);
        line1.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 2;
        g.insets = new Insets(2, 0, 0, 0);
        panel.add(line1, g);

        JLabel line2 = label("transport profile", 21, Font.PLAIN, Color.WHITE);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 3;
        panel.add(line2, g);

        JLabel tagline = label("This helps us plan your trips.", 14, Font.PLAIN,
                new Color(220, 235, 255));
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 4;
        g.insets = new Insets(8, 0, 5, 0);
        panel.add(tagline, g);

        g.gridy = 5;
        g.weighty = 0.88;
        g.fill = GridBagConstraints.BOTH;
        g.insets = new Insets(8, 0, 0, 0);
        panel.add(new ImageLabel("/images/bus.png", 430, 270), g);

        return panel;
    }

    private JPanel createFormPanel() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(Color.WHITE);
        outer.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);

        GridBagConstraints g = new GridBagConstraints();
        g.weightx = 1.0;
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel logo = new ImageLabel("/images/logo.png", 58, 58);
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.CENTER;
        g.insets = new Insets(0, 0, 3, 0);
        form.add(logo, g);

        JLabel title = label("PROFILE SETUP", 28, Font.BOLD, PRIMARY);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(0, 0, 2, 0);
        form.add(title, g);

        JLabel subtitle = label(
                "Complete your details for a better transport experience",
                13, Font.PLAIN, TEXT_MUTED);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 2;
        g.insets = new Insets(0, 0, 15, 0);
        form.add(subtitle, g);

        addTextField(form, 3, 0, "Student ID",
                txtStudentId = new RoundedTextField("Enter your Student ID"));
        addTextField(form, 3, 1, "Full Name",
                txtName = new RoundedTextField("Enter your full name"));

        addTextField(form, 5, 0, "Department",
                txtDepartment = new RoundedTextField("Enter your department"));
        addTextField(form, 5, 1, "Phone",
                txtPhone = new RoundedTextField("Enter your phone number"));

        addTextField(form, 7, 0, "Emergency Contact",
                txtEmergency = new RoundedTextField("Emergency contact number"));
        addComboField(form, 7, 1, "Preferred Route",
                cmbRoute = new JComboBox<String>(new String[] {
                    "Select Route", "Mirpur", "Dhanmondi", "Uttara",
                    "Mohammadpur", "Badda", "Rampura", "Jatrabari"
                }));

        addComboField(form, 9, 0, "Pickup Point",
                cmbPickup = new JComboBox<String>(new String[] {
                    "Select Pickup Point", "Mirpur 10", "Mirpur 1",
                    "Dhanmondi 27", "Dhanmondi 32", "Uttara",
                    "Mohammadpur", "Badda"
                }));

        btnSave = new RoundedButton("SAVE PROFILE");
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnSave.setBackground(PRIMARY);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setPreferredSize(new Dimension(220, 44));
        btnSave.addActionListener(e -> saveProfile());

        btnSave.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnSave.setBackground(PRIMARY_DARK);
            }

            public void mouseExited(MouseEvent e) {
                btnSave.setBackground(PRIMARY);
            }
        });

        g.gridx = 0;
        g.gridy = 11;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.CENTER;
        g.insets = new Insets(12, 0, 0, 0);
        form.add(btnSave, g);

        GridBagConstraints outerG = new GridBagConstraints();
        outerG.gridx = 0;
        outerG.gridy = 0;
        outerG.weightx = 1.0;
        outerG.weighty = 1.0;
        outerG.fill = GridBagConstraints.HORIZONTAL;
        outerG.anchor = GridBagConstraints.CENTER;

        outer.add(form, outerG);
        return outer;
    }

    private void addTextField(JPanel form, int row, int column,
                              String labelText, JTextField field) {
        GridBagConstraints labelG = new GridBagConstraints();
        labelG.gridx = column;
        labelG.gridy = row;
        labelG.weightx = 1.0;
        labelG.fill = GridBagConstraints.HORIZONTAL;
        labelG.insets = new Insets(0, column == 0 ? 0 : 8, 4,
                column == 0 ? 8 : 0);

        form.add(label(labelText, 13, Font.BOLD, TEXT_DARK), labelG);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(230, 40));

        GridBagConstraints fieldG = new GridBagConstraints();
        fieldG.gridx = column;
        fieldG.gridy = row + 1;
        fieldG.weightx = 1.0;
        fieldG.fill = GridBagConstraints.HORIZONTAL;
        fieldG.insets = new Insets(0, column == 0 ? 0 : 8, 8,
                column == 0 ? 8 : 0);

        form.add(field, fieldG);
    }

    private void addComboField(JPanel form, int row, int column,
                               String labelText, JComboBox<String> combo) {
        GridBagConstraints labelG = new GridBagConstraints();
        labelG.gridx = column;
        labelG.gridy = row;
        labelG.weightx = 1.0;
        labelG.fill = GridBagConstraints.HORIZONTAL;
        labelG.insets = new Insets(0, column == 0 ? 0 : 8, 4,
                column == 0 ? 8 : 0);

        form.add(label(labelText, 13, Font.BOLD, TEXT_DARK), labelG);

        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setPreferredSize(new Dimension(230, 40));
        combo.setBackground(FIELD_BG);
        combo.setForeground(TEXT_DARK);
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints comboG = new GridBagConstraints();
        comboG.gridx = column;
        comboG.gridy = row + 1;
        comboG.weightx = 1.0;
        comboG.fill = GridBagConstraints.HORIZONTAL;
        comboG.insets = new Insets(0, column == 0 ? 0 : 8, 8,
                column == 0 ? 8 : 0);

        form.add(combo, comboG);
    }

    private void saveProfile() {
        if (isEmpty(txtStudentId) || isEmpty(txtName)
                || isEmpty(txtDepartment) || isEmpty(txtPhone)
                || isEmpty(txtEmergency)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all required fields.",
                    "Profile Setup",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (cmbRoute.getSelectedIndex() == 0
                || cmbPickup.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a preferred route and pickup point.",
                    "Profile Setup",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Profile saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean isEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    private JLabel label(String text, int size, int style, Color color) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", style, size));
        l.setForeground(color);
        return l;
    }

    private static class ImageLabel extends JLabel {
        private final Image image;

        ImageLabel(String resource, int width, int height) {
            setPreferredSize(new Dimension(width, height));
            setMinimumSize(new Dimension(40, 40));
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);

            Image loaded = null;
            try {
                java.net.URL url =
                        ProfileSetupFrame.class.getResource(resource);
                if (url != null) {
                    loaded = ImageIO.read(url);
                }
            } catch (IOException ignored) {
            }
            image = loaded;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (image == null) {
                return;
            }

            int availableWidth = Math.max(1, getWidth() - 10);
            int availableHeight = Math.max(1, getHeight() - 10);

            double scale = Math.min(
                    availableWidth / (double) image.getWidth(null),
                    availableHeight / (double) image.getHeight(null));

            int width = Math.max(1,
                    (int) (image.getWidth(null) * scale));
            int height = Math.max(1,
                    (int) (image.getHeight(null) * scale));

            int x = (getWidth() - width) / 2;
            int y = (getHeight() - height) / 2;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.drawImage(image, x, y, width, height, this);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new ProfileSetupFrame().setVisible(true));
    }
}