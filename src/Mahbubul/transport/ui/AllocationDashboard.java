package transport.ui;

import javax.swing.*;
import java.awt.*;

public class AllocationDashboard extends JFrame {

    private BusAllocationPanel allocationPanel;

    public AllocationDashboard() {

        setTitle("Smart University Transport - Allocation");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setSize(1000, 650);

        setLocationRelativeTo(null);

        allocationPanel =
                new BusAllocationPanel();

        add(allocationPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            AllocationDashboard dashboard =
                    new AllocationDashboard();

            dashboard.setVisible(true);
        });
    }
}