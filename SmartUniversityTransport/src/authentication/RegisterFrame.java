package authentication;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import components.RoundedButton;
import components.RoundedTextField;
import components.RoundedPasswordField;

public class RegisterFrame extends JFrame {

    private static final Color PRIMARY = new Color(21, 101, 192);
    private static final Color PRIMARY_DARK = new Color(13, 71, 161);
    private static final Color TEXT_DARK = new Color(35, 45, 60);
    private static final Color TEXT_MUTED = new Color(105, 115, 130);

    private JTextField txtStudentId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtDepartment;
    private JTextField txtBatch;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;

    private JButton btnRegister;

    public RegisterFrame() {
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

        JPanel brandPanel = createBrandPanel();
        JPanel formPanel = createFormPanel();

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

        root.add(brandPanel, left);
        root.add(formPanel, right);

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
        g.weighty = 0.10;
        g.fill = GridBagConstraints.NONE;
        panel.add(new ImageLabel("/images/logo.png", 125, 125), g);

        JLabel title = createLabel("JOIN US", 30, Font.BOLD, Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 1;
        g.weighty = 0.0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(5, 0, 2, 0);
        panel.add(title, g);

        JLabel line1 = createLabel("Create your", 21, Font.PLAIN, Color.WHITE);
        line1.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 2;
        g.insets = new Insets(2, 0, 0, 0);
        panel.add(line1, g);

        JLabel line2 = createLabel("transport account", 21, Font.PLAIN, Color.WHITE);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 3;
        panel.add(line2, g);

        JLabel tagline = createLabel("Travel smarter with us.", 14, Font.PLAIN,
                new Color(220, 235, 255));
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 4;
        g.insets = new Insets(8, 0, 5, 0);
        panel.add(tagline, g);

        g.gridy = 5;
        g.weighty = 0.90;
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
        g.insets = new Insets(0, 0, 4, 0);
        form.add(logo, g);

        JLabel title = createLabel("CREATE ACCOUNT", 28, Font.BOLD, PRIMARY);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(0, 0, 3, 0);
        form.add(title, g);

        JLabel subtitle = createLabel(
                "Create your student transport account",
                13, Font.PLAIN, TEXT_MUTED
        );
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 2;
        g.insets = new Insets(0, 0, 15, 0);
        form.add(subtitle, g);

        // Row 1
        addField(form, g, 3, 0, "Student ID",
                txtStudentId = new RoundedTextField("Enter your Student ID"));
        addField(form, g, 3, 1, "Full Name",
                txtName = new RoundedTextField("Enter your full name"));

        // Row 2
        addField(form, g, 5, 0, "Email",
                txtEmail = new RoundedTextField("Enter your email"));
        addField(form, g, 5, 1, "Phone",
                txtPhone = new RoundedTextField("Enter your phone number"));

        // Row 3
        addField(form, g, 7, 0, "Department",
                txtDepartment = new RoundedTextField("Enter your department"));
        addField(form, g, 7, 1, "Batch",
                txtBatch = new RoundedTextField("Enter your batch"));

        // Row 4
        addPasswordField(form, g, 9, 0, "Password",
                txtPassword = new RoundedPasswordField("Enter your password"));
        addPasswordField(form, g, 9, 1, "Confirm Password",
                txtConfirmPassword = new RoundedPasswordField("Confirm your password"));

        btnRegister = new RoundedButton("REGISTER");
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnRegister.setBackground(PRIMARY);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegister.setPreferredSize(new Dimension(250, 44));
        btnRegister.addActionListener(e -> handleRegistration());

        g.gridx = 0;
        g.gridy = 11;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.CENTER;
        g.insets = new Insets(14, 0, 10, 0);
        form.add(btnRegister, g);

        btnRegister.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnRegister.setBackground(PRIMARY_DARK);
            }

            public void mouseExited(MouseEvent e) {
                btnRegister.setBackground(PRIMARY);
            }
        });

        JLabel loginLink = createLinkLabel("Already have an account? Login");
        loginLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });

        g.gridy = 12;
        g.insets = new Insets(0, 0, 0, 0);
        form.add(loginLink, g);

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

    private void addField(JPanel form, GridBagConstraints base,
                          int row, int column, String labelText,
                          JTextField field) {
        GridBagConstraints labelG = (GridBagConstraints) base.clone();
        labelG.gridx = column;
        labelG.gridy = row;
        labelG.gridwidth = 1;
        labelG.fill = GridBagConstraints.HORIZONTAL;
        labelG.insets = new Insets(0, column == 0 ? 0 : 8, 5, column == 0 ? 8 : 0);

        JLabel label = createLabel(labelText, 13, Font.BOLD, TEXT_DARK);
        form.add(label, labelG);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(230, 40));

        GridBagConstraints fieldG = (GridBagConstraints) base.clone();
        fieldG.gridx = column;
        fieldG.gridy = row + 1;
        fieldG.gridwidth = 1;
        fieldG.fill = GridBagConstraints.HORIZONTAL;
        fieldG.insets = new Insets(0, column == 0 ? 0 : 8, 9, column == 0 ? 8 : 0);

        form.add(field, fieldG);
    }

    private void addPasswordField(JPanel form, GridBagConstraints base,
                                  int row, int column, String labelText,
                                  JPasswordField field) {
        addField(form, base, row, column, labelText, field);
    }

    private JLabel createLabel(String text, int size, int style, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", style, size));
        label.setForeground(color);
        return label;
    }

    private JLabel createLinkLabel(String text) {
        JLabel label = createLabel(text, 13, Font.PLAIN, PRIMARY);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

    private void handleRegistration() {
        String studentId = txtStudentId.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String department = txtDepartment.getText().trim();
        String batch = txtBatch.getText().trim();

        String password = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirmPassword.getPassword());

        if (studentId.isEmpty() || name.isEmpty() || email.isEmpty()
                || phone.isEmpty() || department.isEmpty() || batch.isEmpty()
                || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all fields.",
                    "Registration",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Registration",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Backend/account creation can be connected here later.
        new ProfileSetupFrame().setVisible(true);
        dispose();
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
                java.net.URL url = RegisterFrame.class.getResource(resource);
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
                    availableHeight / (double) image.getHeight(null)
            );

            int width = Math.max(1,
                    (int) (image.getWidth(null) * scale));
            int height = Math.max(1,
                    (int) (image.getHeight(null) * scale));

            int x = (getWidth() - width) / 2;
            int y = (getHeight() - height) / 2;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR
            );
            g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY
            );

            g2.drawImage(image, x, y, width, height, this);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new RegisterFrame().setVisible(true)
        );
    }
}