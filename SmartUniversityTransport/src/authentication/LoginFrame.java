package authentication;

import java.awt.*;
import javax.swing.*;
import components.RoundedTextField;
import components.RoundedPasswordField;
import components.RoundedButton;

public class LoginFrame extends JFrame {

    // Window Size
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;

    // Panels
    private JPanel leftPanel;
    private JPanel rightPanel;

    // Left Panel Labels
    private JLabel lblWelcome;
    private JLabel lblBookSeat;
    private JLabel lblTravel;

    // Right Panel Labels
    private JLabel lblLogin;
    
    private JLabel lblUsername;

    private JTextField txtUsername;
    
    private JLabel lblPassword;

    private JPasswordField txtPassword;
    
    private JCheckBox chkShowPassword;
    
    private JButton btnLogin;
    
    private JLabel lblForgotPassword;
    
    private JLabel lblRegister;
    
    

    public LoginFrame() {

        initializeFrame();

        createLeftPanel();

        createRightPanel();
        
        addUsernameField();
        
        addPasswordField();
        
        addShowPassword();
        
        addLoginButton();
        
        addForgotPassword();
        
        addRegisterLink();
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                System.out.println("Forgot Password clicked");

            }

        });

        lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                System.out.println("Register clicked");

            }

        });
        
        addBusImage();
    }

    private void initializeFrame() {

        setTitle("Smart University Transport System");

        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

    }

    private void createLeftPanel() {

        leftPanel = new JPanel();

        leftPanel.setBounds(0, 0, 350, FRAME_HEIGHT);

        leftPanel.setBackground(new Color(21, 101, 192));

        leftPanel.setLayout(null);

        add(leftPanel);

        lblWelcome = new JLabel("WELCOME");

        lblWelcome.setBounds(75, 100, 250, 40);

        lblWelcome.setForeground(Color.WHITE);

        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 30));

        leftPanel.add(lblWelcome);

        lblBookSeat = new JLabel("Book Your Seat");

        lblBookSeat.setBounds(60, 165, 250, 30);

        lblBookSeat.setForeground(Color.WHITE);

        lblBookSeat.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        leftPanel.add(lblBookSeat);

        lblTravel = new JLabel("Before Travelling");

        lblTravel.setBounds(35, 200, 280, 30);

        lblTravel.setForeground(Color.WHITE);

        lblTravel.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        leftPanel.add(lblTravel);
        
        JLabel tagline = new JLabel("Smart travel starts here.");

        tagline.setBounds(75, 245, 220, 25);

        tagline.setForeground(new Color(220, 235, 255));

        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        leftPanel.add(tagline);

    }

    private void createRightPanel() {
        

        rightPanel = new JPanel();

        rightPanel.setBounds(350, 0, 650, FRAME_HEIGHT);

        rightPanel.setBackground(Color.WHITE);

        rightPanel.setLayout(null);

        add(rightPanel);

        lblLogin = new JLabel("LOGIN");

        lblLogin.setBounds(250, 70, 200, 40);

        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 32));

        lblLogin.setForeground(new Color(21, 101, 192));

        rightPanel.add(lblLogin);

    }
    
    private void addUsernameField(){

        lblUsername = new JLabel("Username");

        lblUsername.setBounds(150,150,100,25);

        lblUsername.setFont(new Font("Segoe UI",Font.PLAIN,16));

        rightPanel.add(lblUsername);

        txtUsername = new RoundedTextField("Enter your username");

        txtUsername.setBounds(150,180,320,40);

        txtUsername.setFont(new Font("Segoe UI",Font.PLAIN,16));

        rightPanel.add(txtUsername);

}
    
    private void addPasswordField(){

        lblPassword = new JLabel("Password");

        lblPassword.setBounds(150,250,100,25);

        lblPassword.setFont(new Font("Segoe UI",Font.PLAIN,16));

        rightPanel.add(lblPassword);

        txtPassword = new RoundedPasswordField("Enter your password");

        txtPassword.setBounds(150,280,320,40);

        txtPassword.setFont(new Font("Segoe UI",Font.PLAIN,16));

        rightPanel.add(txtPassword);

}
    
    private void addShowPassword(){

        chkShowPassword = new JCheckBox("Show Password");

        chkShowPassword.setBounds(150,330,150,20);

        chkShowPassword.setBackground(Color.WHITE);

        chkShowPassword.setFont(new Font("Segoe UI",Font.PLAIN,14));

        chkShowPassword.addActionListener(e -> {

            if(chkShowPassword.isSelected()){

                txtPassword.setEchoChar((char)0);

            }

            else{

                txtPassword.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));

            }

        });

        rightPanel.add(chkShowPassword);

}
    
    private void addLoginButton() {

        btnLogin = new RoundedButton("LOGIN");

        btnLogin.setBounds(200, 380, 220, 45);

        btnLogin.setBackground(new Color(21, 101, 192));

        btnLogin.setForeground(Color.WHITE);

        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));

        btnLogin.setFocusPainted(false);

        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        rightPanel.add(btnLogin);
        btnLogin.addActionListener(e -> {

    JOptionPane.showMessageDialog(
        this,
        "Login button clicked!",
        "Login",
        JOptionPane.INFORMATION_MESSAGE
    );

});
        
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

        btnLogin.setBackground(new Color(25, 118, 210));

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

        btnLogin.setBackground(new Color(21, 101, 192));

    }

});

}
    
    private void addForgotPassword() {

        lblForgotPassword = new JLabel("Forgot Password?");

        lblForgotPassword.setBounds(240, 440, 150, 20);

        lblForgotPassword.setForeground(new Color(21,101,192));

        lblForgotPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

        rightPanel.add(lblForgotPassword);
        
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {

            new ForgotPasswordFrame().setVisible(true);

            dispose();
        }
});

}
    
    private void addRegisterLink(){

        lblRegister = new JLabel("New User? Register");

        lblRegister.setBounds(220,470,170,20);

        lblRegister.setForeground(new Color(21,101,192));

        lblRegister.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        rightPanel.add(lblRegister);
        
        lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {

            new RegisterFrame().setVisible(true);

            dispose();
    }
});

}
    
    private void addBusImage() {

        ImageIcon icon = new ImageIcon(
                getClass().getResource("/images/bus.png")
        );

        Image image = icon.getImage();

        Image scaled = image.getScaledInstance(
                260,
                160,
                Image.SCALE_SMOOTH
        );

        JLabel busLabel = new JLabel(new ImageIcon(scaled));

        busLabel.setBounds(45, 330, 260, 160);

        leftPanel.add(busLabel);
}

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new LoginFrame().setVisible(true);

        });

    }
}