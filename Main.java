import component.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Application entry point. Launches straight into MainFrame.
 *
 * Once the login module exists, swap the body of main() to show
 * login.LoginFrame first and have it call `new MainFrame().setVisible(true)`
 * on a successful login instead of launching MainFrame directly.
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Fall back to the default look and feel.
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
