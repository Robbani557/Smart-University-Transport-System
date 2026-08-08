package authentication;

import java.awt.*;
import javax.swing.*;
import components.RoundedButton;
import components.RoundedTextField;
import components.RoundedPasswordField;

public class ForgotPasswordFrame extends JFrame {

    private JPanel leftPanel;
    private JPanel rightPanel;

    private JLabel lblTitle;
    private JLabel lblStudentId;
    private JLabel lblEmail;
    private JLabel lblNewPassword;
    private JLabel lblConfirmPassword;

    private JTextField txtStudentId;
    private JTextField txtEmail;

    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;

    private JButton btnVerify;
    private JButton btnReset;

    private JLabel lblBackToLogin;

    public ForgotPasswordFrame() {

        initializeFrame();

        createLeftPanel();

        createRightPanel();

        addTitle();

        addStudentId();

        addEmail();

        addVerifyButton();

        addNewPassword();

        addConfirmPassword();

        addResetButton();

        addBackToLogin();
    }

    private void initializeFrame() {

        setTitle("Smart University Transport System");

        setSize(1000, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);
    }

    private void createLeftPanel() {

        leftPanel = new JPanel();

        leftPanel.setBounds(0, 0, 350, 650);

        leftPanel.setBackground(new Color(21, 101, 192));

        leftPanel.setLayout(null);

        add(leftPanel);

        JLabel title = new JLabel("RESET");

        title.setBounds(100, 120, 180, 40);

        title.setForeground(Color.WHITE);

        title.setFont(new Font("Segoe UI", Font.BOLD, 30));

        leftPanel.add(title);

        JLabel text = new JLabel("Forgot your");

        text.setBounds(105, 190, 180, 30);

        text.setForeground(Color.WHITE);

        text.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        leftPanel.add(text);

        JLabel text2 = new JLabel("password?");

        text2.setBounds(115, 225, 160, 30);

        text2.setForeground(Color.WHITE);

        text2.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        leftPanel.add(text2);

        JLabel tagline = new JLabel("Let's get you back on track.");

        tagline.setBounds(65, 275, 240, 25);

        tagline.setForeground(new Color(220, 235, 255));

        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        leftPanel.add(tagline);
    }

    private void createRightPanel() {

        rightPanel = new JPanel();

        rightPanel.setBounds(350, 0, 650, 650);

        rightPanel.setBackground(Color.WHITE);

        rightPanel.setLayout(null);

        add(rightPanel);
    }

    private void addTitle() {

        lblTitle = new JLabel("FORGOT PASSWORD");

        lblTitle.setBounds(190, 45, 300, 40);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 27));

        lblTitle.setForeground(new Color(21, 101, 192));

        rightPanel.add(lblTitle);
    }

    private void addStudentId() {

        lblStudentId = new JLabel("Student ID");

        lblStudentId.setBounds(150, 120, 120, 25);

        lblStudentId.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        rightPanel.add(lblStudentId);

        txtStudentId = new RoundedTextField();

        txtStudentId.setBounds(150, 150, 320, 38);

        rightPanel.add(txtStudentId);
    }

    private void addEmail() {

        lblEmail = new JLabel("Email");

        lblEmail.setBounds(150, 205, 120, 25);

        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        rightPanel.add(lblEmail);

        txtEmail = new RoundedTextField();

        txtEmail.setBounds(150, 235, 320, 38);

        rightPanel.add(txtEmail);
    }

    private void addVerifyButton() {

        btnVerify = new RoundedButton("VERIFY");

        btnVerify.setBounds(235, 295, 150, 42);

        rightPanel.add(btnVerify);
    }

    private void addNewPassword() {

        lblNewPassword = new JLabel("New Password");

        lblNewPassword.setBounds(150, 365, 120, 25);

        lblNewPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        rightPanel.add(lblNewPassword);

        txtNewPassword = new RoundedPasswordField();

        txtNewPassword.setBounds(150, 395, 320, 38);

        rightPanel.add(txtNewPassword);
    }

    private void addConfirmPassword() {

        lblConfirmPassword = new JLabel("Confirm Password");

        lblConfirmPassword.setBounds(150, 440, 150, 25);

        lblConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        rightPanel.add(lblConfirmPassword);

        txtConfirmPassword = new RoundedPasswordField();

        txtConfirmPassword.setBounds(150, 470, 320, 38);

        rightPanel.add(txtConfirmPassword);
    }

    private void addResetButton() {

        btnReset = new RoundedButton("RESET PASSWORD");

        btnReset.setBounds(235, 525, 150, 42);

        rightPanel.add(btnReset);
    }

    private void addBackToLogin() {

        lblBackToLogin = new JLabel("Back to Login");

        lblBackToLogin.setBounds(265, 570, 120, 20);

        lblBackToLogin.setForeground(new Color(21, 101, 192));

        lblBackToLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblBackToLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        rightPanel.add(lblBackToLogin);

        lblBackToLogin.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                new LoginFrame().setVisible(true);

                dispose();
            }
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new ForgotPasswordFrame().setVisible(true);

        });
    }
}