package authentication;

import java.awt.*;
import javax.swing.*;

public class SplashFrame extends JFrame {

    private JProgressBar progressBar;
    private JLabel loadingLabel;

    public SplashFrame() {

        setTitle("Smart University Transport System");

        setSize(900, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUndecorated(true);

        getContentPane().setBackground(new Color(21,101,192));

        setLayout(null);

        initializeComponents();

        startLoading();

    }

    private void initializeComponents() {

        JLabel title = new JLabel("SMART UNIVERSITY");
        title.setBounds(220,100,500,40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,34));
        add(title);

        JLabel title2 = new JLabel("TRANSPORT SYSTEM");
        title2.setBounds(220,145,500,40);
        title2.setForeground(Color.WHITE);
        title2.setFont(new Font("Segoe UI",Font.BOLD,34));
        add(title2);

        JLabel subtitle = new JLabel("Travel Smarter. Ride Better.");
        subtitle.setBounds(270,205,400,30);
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI",Font.PLAIN,18));
        add(subtitle);

        loadingLabel = new JLabel("Loading...");
        loadingLabel.setBounds(395,330,120,25);
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setFont(new Font("Segoe UI",Font.PLAIN,16));
        add(loadingLabel);

        progressBar = new JProgressBar();

        progressBar.setBounds(250,370,400,20);

        progressBar.setForeground(Color.WHITE);

        progressBar.setBackground(Color.GRAY);

        progressBar.setBorderPainted(false);

        add(progressBar);

        JLabel version = new JLabel("Version 1.0");
        version.setBounds(390,420,120,20);
        version.setForeground(Color.WHITE);
        version.setFont(new Font("Segoe UI",Font.PLAIN,14));
        add(version);

    }

    private void startLoading(){

        new Thread(() -> {

            try{

                for(int i=0;i<=100;i++){

                    progressBar.setValue(i);

                    Thread.sleep(10);

                }

                dispose();

                new LoginFrame().setVisible(true);

            }

            catch(Exception e){

            }

        }).start();

    }

    public static void main(String args[]){

        EventQueue.invokeLater(() -> {

            new SplashFrame().setVisible(true);

        });

    }

}