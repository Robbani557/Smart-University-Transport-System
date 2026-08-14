package authentication;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import components.RoundedButton;
import components.RoundedTextField;
import components.RoundedPasswordField;

public class ForgotPasswordFrame extends JFrame {

    private static final Color PRIMARY = new Color(21, 101, 192);
    private static final Color PRIMARY_DARK = new Color(13, 71, 161);
    private static final Color TEXT_DARK = new Color(35, 45, 60);
    private static final Color TEXT_MUTED = new Color(105, 115, 130);

    private JTextField txtStudentId;
    private JTextField txtEmail;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;

    private JButton btnVerify;
    private JButton btnReset;

    private boolean verified = false;

    public ForgotPasswordFrame() {
        initializeFrame();
        buildInterface();
    }

    private void initializeFrame() {
        setTitle("Smart University Transport System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(850, 600));
        setSize(1050, 680);
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
        left.weightx = 0.40;
        left.weighty = 1.0;
        left.fill = GridBagConstraints.BOTH;

        GridBagConstraints right = new GridBagConstraints();
        right.gridx = 1;
        right.gridy = 0;
        right.weightx = 0.60;
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

        JLabel title = label("RESET", 30, Font.BOLD, Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 1;
        g.weighty = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(4, 0, 2, 0);
        panel.add(title, g);

        JLabel line1 = label("Forgot your", 21, Font.PLAIN, Color.WHITE);
        line1.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 2;
        g.insets = new Insets(2, 0, 0, 0);
        panel.add(line1, g);

        JLabel line2 = label("password?", 21, Font.PLAIN, Color.WHITE);
        line2.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 3;
        panel.add(line2, g);

        JLabel tagline = label("Let's get you back on track.", 14, Font.PLAIN,
                new Color(220, 235, 255));
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 4;
        g.insets = new Insets(8, 0, 5, 0);
        panel.add(tagline, g);

        g.gridy = 5;
        g.weighty = 0.88;
        g.fill = GridBagConstraints.BOTH;
        g.insets = new Insets(8, 0, 0, 0);
        panel.add(new ImageLabel("/images/bus.png", 420, 260), g);

        return panel;
    }

    private JPanel createFormPanel() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(Color.WHITE);
        outer.setBorder(BorderFactory.createEmptyBorder(35, 45, 30, 45));

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridy = 0;
        g.fill = GridBagConstraints.NONE;
        g.insets = new Insets(0, 0, 6, 0);
        form.add(new ImageLabel("/images/logo.png", 60, 60), g);

        JLabel title = label("FORGOT PASSWORD", 27, Font.BOLD, PRIMARY);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(0, 0, 4, 0);
        form.add(title, g);

        JLabel subtitle = label(
                "Verify your account and create a new password",
                13, Font.PLAIN, TEXT_MUTED
        );
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 2;
        g.insets = new Insets(0, 0, 20, 0);
        form.add(subtitle, g);

        g.gridy = 3;
        g.insets = new Insets(0, 0, 5, 0);
        form.add(fieldLabel("Student ID"), g);

        txtStudentId = new RoundedTextField("Enter your Student ID");
        txtStudentId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtStudentId.setPreferredSize(new Dimension(340, 42));
        g.gridy = 4;
        g.insets = new Insets(0, 0, 12, 0);
        form.add(txtStudentId, g);

        g.gridy = 5;
        g.insets = new Insets(0, 0, 5, 0);
        form.add(fieldLabel("Email"), g);

        txtEmail = new RoundedTextField("Enter your email");
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEmail.setPreferredSize(new Dimension(340, 42));
        g.gridy = 6;
        g.insets = new Insets(0, 0, 14, 0);
        form.add(txtEmail, g);

        btnVerify = new RoundedButton("VERIFY");
        btnVerify.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVerify.setBackground(PRIMARY);
        btnVerify.setForeground(Color.WHITE);
        btnVerify.setFocusPainted(false);
        btnVerify.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerify.setPreferredSize(new Dimension(180, 42));
        btnVerify.addActionListener(e -> verifyAccount());

        g.gridy = 7;
        g.fill = GridBagConstraints.NONE;
        g.insets = new Insets(0, 0, 18, 0);
        form.add(btnVerify, g);

        g.gridy = 8;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(0, 0, 5, 0);
        form.add(fieldLabel("New Password"), g);

        txtNewPassword = new RoundedPasswordField("Enter new password");
        txtNewPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNewPassword.setPreferredSize(new Dimension(340, 42));
        txtNewPassword.setEnabled(false);
        g.gridy = 9;
        g.insets = new Insets(0, 0, 12, 0);
        form.add(txtNewPassword, g);

        g.gridy = 10;
        g.insets = new Insets(0, 0, 5, 0);
        form.add(fieldLabel("Confirm Password"), g);

        txtConfirmPassword = new RoundedPasswordField("Confirm your new password");
        txtConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtConfirmPassword.setPreferredSize(new Dimension(340, 42));
        txtConfirmPassword.setEnabled(false);
        g.gridy = 11;
        g.insets = new Insets(0, 0, 14, 0);
        form.add(txtConfirmPassword, g);

        btnReset = new RoundedButton("RESET PASSWORD");
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnReset.setBackground(PRIMARY);
        btnReset.setForeground(Color.WHITE);
        btnReset.setFocusPainted(false);
        btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReset.setPreferredSize(new Dimension(190, 42));
        btnReset.setEnabled(false);
        btnReset.addActionListener(e -> resetPassword());

        g.gridy = 12;
        g.fill = GridBagConstraints.NONE;
        g.insets = new Insets(0, 0, 14, 0);
        form.add(btnReset, g);

        btnReset.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (btnReset.isEnabled()) btnReset.setBackground(PRIMARY_DARK);
            }

            public void mouseExited(MouseEvent e) {
                if (btnReset.isEnabled()) btnReset.setBackground(PRIMARY);
            }
        });

        JLabel back = link("Back to Login");
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });

        g.gridy = 13;
        g.insets = new Insets(0, 0, 0, 0);
        form.add(back, g);

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

    private void verifyAccount() {
        String studentId = txtStudentId.getText().trim();
        String email = txtEmail.getText().trim();

        if (studentId.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both Student ID and email.",
                    "Verification",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        verified = true;

        txtNewPassword.setEnabled(true);
        txtConfirmPassword.setEnabled(true);
        btnReset.setEnabled(true);

        JOptionPane.showMessageDialog(
                this,
                "Verification successful! You can now create a new password.",
                "Verification",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void resetPassword() {
        if (!verified) {
            return;
        }

        String newPassword = new String(txtNewPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter and confirm your new password.",
                    "Password Reset",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Password Reset",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Password reset successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        new LoginFrame().setVisible(true);
        dispose();
    }

    private JLabel fieldLabel(String text) {
        return label(text, 14, Font.BOLD, TEXT_DARK);
    }

    private JLabel link(String text) {
        JLabel l = label(text, 13, Font.PLAIN, PRIMARY);
        l.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return l;
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
                        ForgotPasswordFrame.class.getResource(resource);

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

            int width = Math.max(
                    1, (int) (image.getWidth(null) * scale));

            int height = Math.max(
                    1, (int) (image.getHeight(null) * scale));

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
                () -> new ForgotPasswordFrame().setVisible(true)
        );
    }
}