package smart.university.transport.system;

import javax.swing.*;
import java.awt.*;

public class AdminLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLogin() {
        setTitle("Smart University Transport System - Admin Login");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout());

        // LEFT
        JPanel left = new JPanel(new GridBagLayout());
        left.setBackground(new Color(21, 101, 192));
        left.setPreferredSize(new Dimension(400, 600));

        JLabel title = new JLabel(
                "<html><center>SMART UNIVERSITY<br>TRANSPORT SYSTEM</center></html>"
        );
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        left.add(title);

        // RIGHT
        JPanel right = new JPanel(new GridBagLayout());
        right.setBackground(new Color(245, 247, 250));

        JPanel login = new JPanel();
        login.setPreferredSize(new Dimension(400, 400));
        login.setBackground(Color.WHITE);
        login.setBorder(BorderFactory.createEmptyBorder(35, 40, 35, 40));

        login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));

        JLabel loginTitle = new JLabel("Admin Login");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 28));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));

        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JButton loginButton = new JButton("LOGIN");
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        loginButton.setBackground(new Color(21, 101, 192));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);

        login.add(loginTitle);
        login.add(Box.createVerticalStrut(30));
        login.add(userLabel);
        login.add(Box.createVerticalStrut(8));
        login.add(usernameField);
        login.add(Box.createVerticalStrut(20));
        login.add(passLabel);
        login.add(Box.createVerticalStrut(8));
        login.add(passwordField);
        login.add(Box.createVerticalStrut(30));
        login.add(loginButton);

        right.add(login);

        main.add(left, BorderLayout.WEST);
        main.add(right, BorderLayout.CENTER);

        add(main);

        loginButton.addActionListener(e -> login());
        passwordField.addActionListener(e -> login());
    }

    private void login() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("1234")) {

            dispose();

            new AdminDashboard().setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            passwordField.setText("");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new AdminLogin().setVisible(true);
        });
    }
}
