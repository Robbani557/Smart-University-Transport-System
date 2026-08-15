package authentication;

import data.AppData;
import data.TransportData;
import model.Route;
import model.Student;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import components.RoundedButton;
import components.RoundedPasswordField;
import components.RoundedTextField;

public class LoginFrame extends JFrame {

    private static final Color PRIMARY = new Color(21, 101, 192);
    private static final Color PRIMARY_DARK = new Color(13, 71, 161);
    private static final Color TEXT_DARK = new Color(35, 45, 60);
    private static final Color TEXT_MUTED = new Color(105, 115, 130);

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chkShowPassword;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Smart University Transport System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(850, 560));
        setSize(1100, 700);
        setLocationRelativeTo(null);
        buildInterface();
    }

    private void buildInterface() {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(Color.WHITE);

        JPanel brand = createBrandPanel();
        JPanel login = createLoginPanel();

        GridBagConstraints a = new GridBagConstraints();
        a.gridx = 0; a.gridy = 0;
        a.weightx = 0.42; a.weighty = 1;
        a.fill = GridBagConstraints.BOTH;

        GridBagConstraints b = new GridBagConstraints();
        b.gridx = 1; b.gridy = 0;
        b.weightx = 0.58; b.weighty = 1;
        b.fill = GridBagConstraints.BOTH;

        root.add(brand, a);
        root.add(login, b);
        setContentPane(root);
    }

    private JPanel createBrandPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(PRIMARY);
        p.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0; g.weightx = 1; g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.CENTER;

        g.gridy = 0; g.weighty = 0.15;
        p.add(new ImageLabel("/images/logo.png", 150, 150), g);

        JLabel welcome = label("WELCOME", 30, Font.BOLD, Color.WHITE);
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 1; g.weighty = 0;
        p.add(welcome, g);

        JLabel t1 = label("Book Your Seat", 21, Font.PLAIN, Color.WHITE);
        t1.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 2;
        p.add(t1, g);

        JLabel t2 = label("Before Travelling", 21, Font.PLAIN, Color.WHITE);
        t2.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 3;
        p.add(t2, g);

        JLabel tag = label("Smart travel starts here.", 14, Font.PLAIN,
                new Color(220, 235, 255));
        tag.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 4;
        g.insets = new Insets(8, 0, 5, 0);
        p.add(tag, g);

        g.gridy = 5; g.weighty = 0.85;
        g.fill = GridBagConstraints.BOTH;
        g.insets = new Insets(8, 0, 0, 0);
        p.add(new ImageLabel("/images/bus.png", 430, 240), g);

        return p;
    }

    private JPanel createLoginPanel() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(Color.WHITE);
        outer.setBorder(BorderFactory.createEmptyBorder(35, 45, 35, 45));

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0; g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridy = 0; g.fill = GridBagConstraints.NONE;
        g.insets = new Insets(0, 0, 7, 0);
        form.add(new ImageLabel("/images/logo.png", 72, 72), g);

        JLabel title = label("LOGIN", 30, Font.BOLD, PRIMARY);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 1; g.insets = new Insets(0, 0, 4, 0);
        g.fill = GridBagConstraints.HORIZONTAL;
        form.add(title, g);

        JLabel sub = label("Sign in to manage your university transport",
                14, Font.PLAIN, TEXT_MUTED);
        sub.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 2; g.insets = new Insets(0, 0, 22, 0);
        form.add(sub, g);

        g.gridy = 3; g.insets = new Insets(0, 0, 6, 0);
        form.add(fieldLabel("Username"), g);

        txtUsername = new RoundedTextField("Enter your username");
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtUsername.setPreferredSize(new Dimension(340, 44));
        g.gridy = 4; g.insets = new Insets(0, 0, 15, 0);
        form.add(txtUsername, g);

        g.gridy = 5; g.insets = new Insets(0, 0, 6, 0);
        form.add(fieldLabel("Password"), g);

        txtPassword = new RoundedPasswordField("Enter your password");
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtPassword.setPreferredSize(new Dimension(340, 44));
        g.gridy = 6; g.insets = new Insets(0, 0, 7, 0);
        form.add(txtPassword, g);

        JPanel options = new JPanel(new BorderLayout());
        options.setOpaque(false);

        chkShowPassword = new JCheckBox("Show Password");
        chkShowPassword.setOpaque(false);
        chkShowPassword.setForeground(TEXT_MUTED);
        chkShowPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        chkShowPassword.setFocusPainted(false);
        chkShowPassword.addActionListener(e -> togglePassword());

        JLabel forgot = link("Forgot Password?");
        forgot.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ForgotPasswordFrame().setVisible(true);
                dispose();
            }
        });

        options.add(chkShowPassword, BorderLayout.WEST);
        options.add(forgot, BorderLayout.EAST);

        g.gridy = 7; g.insets = new Insets(0, 0, 20, 0);
        form.add(options, g);

        btnLogin = new RoundedButton("LOGIN");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setBackground(PRIMARY);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setPreferredSize(new Dimension(340, 46));
        btnLogin.addActionListener(e -> handleLogin());
        btnLogin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnLogin.setBackground(PRIMARY_DARK); }
            public void mouseExited(MouseEvent e) { btnLogin.setBackground(PRIMARY); }
        });

        g.gridy = 8; g.insets = new Insets(0, 0, 17, 0);
        form.add(btnLogin, g);

        JLabel register = link("New User? Register");
        register.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new RegisterFrame().setVisible(true);
                dispose();
            }
        });

        g.gridy = 9; g.fill = GridBagConstraints.NONE;
        g.insets = new Insets(0, 0, 0, 0);
        form.add(register, g);

        GridBagConstraints outerG = new GridBagConstraints();
        outerG.gridx = 0; outerG.gridy = 0;
        outerG.weightx = 1; outerG.weighty = 1;
        outerG.fill = GridBagConstraints.HORIZONTAL;
        outer.add(form, outerG);

        return outer;
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

    private void togglePassword() {
        if (chkShowPassword.isSelected()) {
            txtPassword.setEchoChar((char) 0);
        } else {
            Object echo = UIManager.get("PasswordField.echoChar");
            txtPassword.setEchoChar(echo instanceof Character ? (Character) echo : '\u2022');
        }
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Create/store the current student in the shared application session.
        // For now the project has no credential database, so the entered
        // username is used as the student's name for frontend integration.
        TransportData data = AppData.getTransportData();
        Route route = data.getRoutes().isEmpty() ? null : data.getRoutes().get(0);

        Student student = new Student(
                username,
                username,
                "Computer Science & Engineering",
                route
        );

        StudentSession.setCurrentStudent(student);
        data.addStudent(student);

        JOptionPane.showMessageDialog(this,
                "Login successful!",
                "Login", JOptionPane.INFORMATION_MESSAGE);

        new student.StudentMainFrame().setVisible(true);
        dispose();
    }

    private static class ImageLabel extends JLabel {
        private final Image image;

        ImageLabel(String resource, int w, int h) {
            setPreferredSize(new Dimension(w, h));
            setMinimumSize(new Dimension(40, 40));
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
            Image loaded = null;
            try {
                java.net.URL url = LoginFrame.class.getResource(resource);
                if (url != null) loaded = ImageIO.read(url);
            } catch (IOException ignored) {}
            image = loaded;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image == null) return;

            int aw = Math.max(1, getWidth() - 10);
            int ah = Math.max(1, getHeight() - 10);
            double scale = Math.min(
                    aw / (double) image.getWidth(null),
                    ah / (double) image.getHeight(null));

            int w = Math.max(1, (int)(image.getWidth(null) * scale));
            int h = Math.max(1, (int)(image.getHeight(null) * scale));
            int x = (getWidth() - w) / 2;
            int y = (getHeight() - h) / 2;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.drawImage(image, x, y, w, h, this);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}