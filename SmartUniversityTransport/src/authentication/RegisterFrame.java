package authentication;

import java.awt.*;
import javax.swing.*;
import components.RoundedButton;
import components.RoundedTextField;
import components.RoundedPasswordField;

public class RegisterFrame extends JFrame {

    private JPanel leftPanel;
    private JPanel rightPanel;

    private JLabel lblTitle;
    private JLabel lblStudentId;
    private JLabel lblName;
    private JLabel lblEmail;
    private JLabel lblPhone;
    private JLabel lblDepartment;
    private JLabel lblBatch;
    private JLabel lblPassword;
    private JLabel lblConfirmPassword;

    private JTextField txtStudentId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtDepartment;
    private JTextField txtBatch;

    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;

    private JButton btnRegister;

    private JLabel lblLogin;

    public RegisterFrame() {

        initializeFrame();

        createLeftPanel();

        createRightPanel();

        addTitle();

        addStudentId();

        addName();

        addEmail();

        addPhone();

        addDepartment();

        addBatch();

        addPassword();

        addConfirmPassword();

        addRegisterButton();

        addLoginLink();
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

        JLabel welcome = new JLabel("JOIN US");

        welcome.setBounds(80, 120, 220, 40);

        welcome.setForeground(Color.WHITE);

        welcome.setFont(new Font("Segoe UI", Font.BOLD, 30));

        leftPanel.add(welcome);

        JLabel text = new JLabel("Create your");

        text.setBounds(105, 190, 180, 30);

        text.setForeground(Color.WHITE);

        text.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        leftPanel.add(text);

        JLabel text2 = new JLabel("transport account");

        text2.setBounds(70, 225, 230, 30);

        text2.setForeground(Color.WHITE);

        text2.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        leftPanel.add(text2);

        JLabel tagline = new JLabel("Travel smarter with us.");

        tagline.setBounds(85, 275, 220, 25);

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

        lblTitle = new JLabel("CREATE ACCOUNT");

        lblTitle.setBounds(215, 25, 250, 40);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));

        lblTitle.setForeground(new Color(21, 101, 192));

        rightPanel.add(lblTitle);
    }

    private void addStudentId() {

        lblStudentId = new JLabel("Student ID");

        lblStudentId.setBounds(70, 85, 120, 25);

        lblStudentId.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblStudentId);

        txtStudentId = new RoundedTextField("Enter your Student ID");

        txtStudentId.setBounds(70, 110, 230, 38);

        rightPanel.add(txtStudentId);
    }

    private void addName() {

        lblName = new JLabel("Full Name");

        lblName.setBounds(330, 85, 120, 25);

        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblName);

        txtName = new RoundedTextField("Enter your full name");

        txtName.setBounds(330, 110, 230, 38);

        rightPanel.add(txtName);
    }

    private void addEmail() {

        lblEmail = new JLabel("Email");

        lblEmail.setBounds(70, 165, 120, 25);

        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblEmail);

        txtEmail = new RoundedTextField("Enter your email");

        txtEmail.setBounds(70, 190, 230, 38);

        rightPanel.add(txtEmail);
    }

    private void addPhone() {

        lblPhone = new JLabel("Phone");

        lblPhone.setBounds(330, 165, 120, 25);

        lblPhone.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblPhone);

        txtPhone = new RoundedTextField("Enter your phone number");

        txtPhone.setBounds(330, 190, 230, 38);

        rightPanel.add(txtPhone);
    }

    private void addDepartment() {

        lblDepartment = new JLabel("Department");

        lblDepartment.setBounds(70, 245, 120, 25);

        lblDepartment.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblDepartment);

        txtDepartment = new RoundedTextField("Enter your department");

        txtDepartment.setBounds(70, 270, 230, 38);

        rightPanel.add(txtDepartment);
    }

    private void addBatch() {

        lblBatch = new JLabel("Batch");

        lblBatch.setBounds(330, 245, 120, 25);

        lblBatch.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblBatch);

        txtBatch = new RoundedTextField("Enter your batch");

        txtBatch.setBounds(330, 270, 230, 38);

        rightPanel.add(txtBatch);
    }

    private void addPassword() {

        lblPassword = new JLabel("Password");

        lblPassword.setBounds(70, 350, 120, 25);

        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblPassword);

        txtPassword = new RoundedPasswordField("Enter your password");

        txtPassword.setBounds(70, 375, 230, 38);

        rightPanel.add(txtPassword);
    }

    private void addConfirmPassword() {

        lblConfirmPassword = new JLabel("Confirm Password");

        lblConfirmPassword.setBounds(330, 350, 150, 25);

        lblConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblConfirmPassword);

        txtConfirmPassword = new RoundedPasswordField("Confirm your password");

        txtConfirmPassword.setBounds(330, 375, 230, 38);

        rightPanel.add(txtConfirmPassword);
    }

    private void addRegisterButton() {

        btnRegister = new RoundedButton("REGISTER");

        btnRegister.setBounds(215, 455, 220, 45);

        rightPanel.add(btnRegister);
        btnRegister.addActionListener(e -> {

        new ProfileSetupFrame().setVisible(true);

        dispose();

});
    }

    private void addLoginLink() {

        lblLogin = new JLabel("Already have an account? Login");

        lblLogin.setBounds(215, 515, 220, 25);

        lblLogin.setForeground(new Color(21, 101, 192));

        lblLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        rightPanel.add(lblLogin);
        
        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

        new LoginFrame().setVisible(true);

        dispose();
    }
});
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new RegisterFrame().setVisible(true);

        });
    }
}