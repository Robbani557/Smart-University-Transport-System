package application;

import authentication.SplashFrame;
import javax.swing.SwingUtilities;
import student.StudentMainFrame;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new SplashFrame().setVisible(true);

        });
    }
}