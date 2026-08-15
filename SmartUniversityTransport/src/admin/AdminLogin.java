package admin;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class AdminLogin extends JFrame {

    private static final Color PRIMARY = new Color(21, 101, 192);
    private static final Color PRIMARY_DARK = new Color(13, 71, 161);
    private static final Color LIGHT_BG = new Color(245, 247, 250);
    private static final Color TEXT_DARK = new Color(35, 45, 60);
    private static final Color TEXT_MUTED = new Color(105, 115, 130);

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public AdminLogin() {
        initializeFrame();
        buildInterface();
    }

    private void initializeFrame() {
        setTitle("Smart University Transport System - Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(850, 560));
        setSize(1050, 680);
        setLocationRelativeTo(null);
    }

    private void buildInterface() {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(LIGHT_BG);

        JPanel brandPanel = createBrandPanel();
        JPanel formPanel = createFormPanel();

        GridBagConstraints left = new GridBagConstraints();
        left.gridx = 0;
        left.gridy = 0;
        left.weightx = 0.43;
        left.weighty = 1.0;
        left.fill = GridBagConstraints.BOTH;

        GridBagConstraints right = new GridBagConstraints();
        right.gridx = 1;
        right.gridy = 0;
        right.weightx = 0.57;
        right.weighty = 1.0;
        right.fill = GridBagConstraints.BOTH;

        root.add(brandPanel, left);
        root.add(formPanel, right);

        setContentPane(root);
    }

    private JPanel createBrandPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PRIMARY);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.CENTER;

        ImageLabel logo = new ImageLabel("/images/logo.png", 125, 125);
        g.gridy = 0;
        g.weighty = 0.12;
        g.fill = GridBagConstraints.NONE;
        panel.add(logo, g);

        JLabel title = createLabel(
                "SMART UNIVERSITY",
                28,
                Font.BOLD,
                Color.WHITE
        );
        title.setHorizontalAlignment(SwingConstants.CENTER);

        g.gridy = 1;
        g.weighty = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(6, 0, 0, 0);
        panel.add(title, g);

        JLabel title2 = createLabel(
                "TRANSPORT SYSTEM",
                28,
                Font.BOLD,
                Color.WHITE
        );
        title2.setHorizontalAlignment(SwingConstants.CENTER);

        g.gridy = 2;
        g.insets = new Insets(0, 0, 4, 0);
        panel.add(title2, g);

        JLabel subtitle = createLabel(
                "Administration Portal",
                16,
                Font.PLAIN,
                new Color(220, 235, 255)
        );
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        g.gridy = 3;
        g.insets = new Insets(4, 0, 8, 0);
        panel.add(subtitle, g);

        g.gridy = 4;
        g.weighty = 0.88;
        g.fill = GridBagConstraints.BOTH;
        g.insets = new Insets(8, 0, 0, 0);
        panel.add(new ImageLabel("/images/bus.png", 430, 260), g);

        JLabel footer = createLabel(
                "Manage university transportation with ease.",
                12,
                Font.PLAIN,
                new Color(210, 230, 250)
        );
        footer.setHorizontalAlignment(SwingConstants.CENTER);

        g.gridy = 5;
        g.weighty = 0.0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(8, 0, 0, 0);
        panel.add(footer, g);

        return panel;
    }

    private JPanel createFormPanel() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(LIGHT_BG);
        outer.setBorder(BorderFactory.createEmptyBorder(30, 45, 30, 45));

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 229, 235)),
                BorderFactory.createEmptyBorder(32, 38, 32, 38)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.HORIZONTAL;

        ImageLabel smallLogo = new ImageLabel("/images/logo.png", 58, 58);
        g.gridy = 0;
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.CENTER;
        g.insets = new Insets(0, 0, 7, 0);
        card.add(smallLogo, g);

        JLabel title = createLabel(
                "ADMIN LOGIN",
                27,
                Font.BOLD,
                PRIMARY
        );
        title.setHorizontalAlignment(SwingConstants.CENTER);

        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(0, 0, 4, 0);
        card.add(title, g);

        JLabel subtitle = createLabel(
                "Sign in to manage university transport",
                13,
                Font.PLAIN,
                TEXT_MUTED
        );
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        g.gridy = 2;
        g.insets = new Insets(0, 0, 24, 0);
        card.add(subtitle, g);

        JLabel usernameLabel = createLabel(
                "Username",
                13,
                Font.BOLD,
                TEXT_DARK
        );

        g.gridy = 3;
        g.insets = new Insets(0, 0, 6, 0);
        card.add(usernameLabel, g);

        usernameField = createTextField("Enter admin username");

        g.gridy = 4;
        g.insets = new Insets(0, 0, 16, 0);
        card.add(usernameField, g);

        JLabel passwordLabel = createLabel(
                "Password",
                13,
                Font.BOLD,
                TEXT_DARK
        );

        g.gridy = 5;
        g.insets = new Insets(0, 0, 6, 0);
        card.add(passwordLabel, g);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(330, 43));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 223)),
                BorderFactory.createEmptyBorder(8, 11, 8, 11)
        ));

        g.gridy = 6;
        g.insets = new Insets(0, 0, 23, 0);
        card.add(passwordField, g);

        loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(PRIMARY);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(330, 45));
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        getRootPane().setDefaultButton(loginButton);

        loginButton.addActionListener(e -> handleLogin());

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(PRIMARY_DARK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(PRIMARY);
            }
        });

        g.gridy = 7;
        g.insets = new Insets(0, 0, 15, 0);
        card.add(loginButton, g);

        JLabel note = createLabel(
                "Authorized administrators only",
                12,
                Font.PLAIN,
                TEXT_MUTED
        );
        note.setHorizontalAlignment(SwingConstants.CENTER);

        g.gridy = 8;
        g.insets = new Insets(0, 0, 0, 0);
        card.add(note, g);

        GridBagConstraints outerG = new GridBagConstraints();
        outerG.gridx = 0;
        outerG.gridy = 0;
        outerG.weightx = 1.0;
        outerG.weighty = 1.0;
        outerG.fill = GridBagConstraints.HORIZONTAL;
        outerG.anchor = GridBagConstraints.CENTER;

        outer.add(card, outerG);

        return outer;
    }

    private JTextField createTextField(String placeholder) {
        JTextField field = new JTextField();

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(330, 43));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 223)),
                BorderFactory.createEmptyBorder(8, 11, 8, 11)
        ));

        field.setToolTipText(placeholder);

        return field;
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both username and password.",
                    "Admin Login",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        /*
         * Keep this section ready for the project's real authentication
         * service. For frontend testing, any non-empty credentials open
         * the existing AdminDashboard.
         */
        openAdminDashboard();
    }

    private void openAdminDashboard() {
        /*
         * AdminLogin is Swing while AdminDashboard is JavaFX.
         * The dashboard must therefore be created on the JavaFX
         * Application Thread. Platform.runLater() is used here
         * because the project already starts JavaFX elsewhere.
         */
        try {
            javafx.application.Platform.runLater(() -> {
                try {
                    javafx.stage.Stage stage =
                            new javafx.stage.Stage();

                    stage.setOnCloseRequest(event -> {
                        // Keep the Swing login closed after successful login.
                    });

                    new AdminDashboard().start(stage);
                    stage.toFront();
                    stage.requestFocus();

                    dispose();

                } catch (Exception ex) {
                    ex.printStackTrace();

                    SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(
                                    AdminLogin.this,
                                    "Unable to open Admin Dashboard:\n"
                                            + ex.getMessage(),
                                    "Admin Login",
                                    JOptionPane.ERROR_MESSAGE
                            )
                    );
                }
            });
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "The JavaFX dashboard is not available. "
                            + "Please restart the application.",
                    "Admin Login",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private JLabel createLabel(
            String text,
            int size,
            int style,
            Color color) {

        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", style, size));
        label.setForeground(color);

        return label;
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
                        AdminLogin.class.getResource(resource);

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
                    1,
                    (int) (image.getWidth(null) * scale)
            );

            int height = Math.max(
                    1,
                    (int) (image.getHeight(null) * scale)
            );

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
        EventQueue.invokeLater(() ->
                new AdminLogin().setVisible(true)
        );
    }
}