package authentication;

import java.awt.*;
import javax.swing.*;
import components.RoundedButton;
import components.RoundedTextField;

public class ProfileSetupFrame extends JFrame {

    private JPanel leftPanel;
    private JPanel rightPanel;

    private JLabel lblTitle;
    private JLabel lblStudentId;
    private JLabel lblName;
    private JLabel lblDepartment;
    private JLabel lblPhone;
    private JLabel lblEmergency;
    private JLabel lblRoute;
    private JLabel lblPickup;

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

        createLeftPanel();

        createRightPanel();

        addTitle();

        addStudentId();

        addName();

        addDepartment();

        addPhone();

        addEmergencyContact();

        addRoute();

        addPickupPoint();

        addSaveButton();
    }

    private void initializeFrame() {

        setTitle("Smart University Transport System");

        setSize(1000, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);
    }

    private void createLeftPanel() {

        leftPanel = new JPanel();

        leftPanel.setBounds(0, 0, 350, 600);

        leftPanel.setBackground(new Color(21, 101, 192));

        leftPanel.setLayout(null);

        add(leftPanel);

        JLabel title = new JLabel("YOUR PROFILE");

        title.setBounds(70, 130, 230, 40);

        title.setForeground(Color.WHITE);

        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        leftPanel.add(title);

        JLabel text = new JLabel("Complete your");

        text.setBounds(100, 200, 180, 30);

        text.setForeground(Color.WHITE);

        text.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        leftPanel.add(text);

        JLabel text2 = new JLabel("transport profile");

        text2.setBounds(75, 235, 220, 30);

        text2.setForeground(Color.WHITE);

        text2.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        leftPanel.add(text2);

        JLabel tagline = new JLabel("This helps us plan your trips.");

        tagline.setBounds(65, 285, 240, 25);

        tagline.setForeground(new Color(220, 235, 255));

        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        leftPanel.add(tagline);
    }

    private void createRightPanel() {

        rightPanel = new JPanel();

        rightPanel.setBounds(350, 0, 650, 600);

        rightPanel.setBackground(Color.WHITE);

        rightPanel.setLayout(null);

        add(rightPanel);
    }

    private void addTitle() {

        lblTitle = new JLabel("PROFILE SETUP");

        lblTitle.setBounds(225, 25, 250, 40);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));

        lblTitle.setForeground(new Color(21, 101, 192));

        rightPanel.add(lblTitle);
    }

    private void addStudentId() {

        lblStudentId = new JLabel("Student ID");

        lblStudentId.setBounds(70, 85, 120, 25);

        lblStudentId.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblStudentId);

        txtStudentId = new RoundedTextField();

        txtStudentId.setBounds(70, 110, 230, 38);

        rightPanel.add(txtStudentId);
    }

    private void addName() {

        lblName = new JLabel("Full Name");

        lblName.setBounds(330, 85, 120, 25);

        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblName);

        txtName = new RoundedTextField();

        txtName.setBounds(330, 110, 230, 38);

        rightPanel.add(txtName);
    }

    private void addDepartment() {

        lblDepartment = new JLabel("Department");

        lblDepartment.setBounds(70, 165, 120, 25);

        lblDepartment.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblDepartment);

        txtDepartment = new RoundedTextField();

        txtDepartment.setBounds(70, 190, 230, 38);

        rightPanel.add(txtDepartment);
    }

    private void addPhone() {

        lblPhone = new JLabel("Phone");

        lblPhone.setBounds(330, 165, 120, 25);

        lblPhone.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblPhone);

        txtPhone = new RoundedTextField();

        txtPhone.setBounds(330, 190, 230, 38);

        rightPanel.add(txtPhone);
    }

    private void addEmergencyContact() {

        lblEmergency = new JLabel("Emergency Contact");

        lblEmergency.setBounds(70, 245, 150, 25);

        lblEmergency.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblEmergency);

        txtEmergency = new RoundedTextField();

        txtEmergency.setBounds(70, 270, 230, 38);

        rightPanel.add(txtEmergency);
    }

    private void addRoute() {

        lblRoute = new JLabel("Preferred Route");

        lblRoute.setBounds(330, 245, 150, 25);

        lblRoute.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblRoute);

        String[] routes = {
            "Select Route",
            "Mirpur",
            "Dhanmondi",
            "Uttara",
            "Mohammadpur",
            "Badda",
            "Rampura",
            "Jatrabari"
        };

        cmbRoute = new JComboBox<>(routes);

        cmbRoute.setBounds(330, 270, 230, 38);

        cmbRoute.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(cmbRoute);
    }

    private void addPickupPoint() {

        lblPickup = new JLabel("Pickup Point");

        lblPickup.setBounds(70, 350, 120, 25);

        lblPickup.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(lblPickup);

        String[] pickupPoints = {
            "Select Pickup Point",
            "Mirpur 10",
            "Mirpur 1",
            "Dhanmondi 27",
            "Dhanmondi 32",
            "Uttara",
            "Mohammadpur",
            "Badda"
        };

        cmbPickup = new JComboBox<>(pickupPoints);

        cmbPickup.setBounds(70, 375, 230, 38);

        cmbPickup.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        rightPanel.add(cmbPickup);
    }

    private void addSaveButton() {

        btnSave = new RoundedButton("SAVE PROFILE");

        btnSave.setBounds(330, 375, 230, 45);

        rightPanel.add(btnSave);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new ProfileSetupFrame().setVisible(true);

        });
    }
}