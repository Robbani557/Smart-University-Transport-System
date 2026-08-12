package transport;

import javax.swing.SwingUtilities;

public class TransportApplication {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TransportDashboard dashboard =
                    new TransportDashboard();

            dashboard.setVisible(true);
        });
    }
}