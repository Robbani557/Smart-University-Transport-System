package transport.ui;

import model.AllocationController;
import model.Bus;
import model.BusAllocationResult;

import javax.swing.*;
import java.awt.*;

public class BusAllocationPanel extends JPanel {

    private JComboBox<String> routeBox;
    private JComboBox<String> timeBox;
    private JButton allocateButton;
    private JButton resetButton;

    private JLabel studentLabel;
    private JLabel requiredLabel;
    private JLabel allocatedLabel;
    private JLabel statusLabel;

    private JTextArea resultArea;

    private AllocationController controller;

    public BusAllocationPanel(AllocationController controller) {

    this.controller = controller;

    setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        createTopPanel();
        createCenterPanel();
        createBottomPanel();
    }

    private void createTopPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel routeLabel = new JLabel("Route:");

        routeBox = new JComboBox<>(
                new String[]{
                    "Mirpur",
                    "Dhanmondi",
                    "Uttara",
                    "Mohammadpur",
                    "Badda",
                    "Jatrabari",
                    "Gulshan",
                    "Rampura"
                }
        );

        JLabel timeLabel = new JLabel("Travel Time:");

        timeBox = new JComboBox<>(
                new String[]{
                    "Morning",
                    "Noon",
                    "Afternoon",
                    "Evening"
                }
        );

        allocateButton = new JButton("Allocate Buses");
        resetButton = new JButton("Reset");

        allocateButton.addActionListener(e -> allocateBuses());

        resetButton.addActionListener(e -> reset());

        panel.add(routeLabel);
        panel.add(routeBox);
        panel.add(timeLabel);
        panel.add(timeBox);
        panel.add(allocateButton);
        panel.add(resetButton);

        add(panel, BorderLayout.NORTH);
    }

    private void createCenterPanel() {

        JPanel panel = new JPanel(new GridLayout(2, 4, 15, 15));

        studentLabel = new JLabel("Students: 0", SwingConstants.CENTER);
        requiredLabel = new JLabel("Buses Required: 0", SwingConstants.CENTER);
        allocatedLabel = new JLabel("Buses Allocated: 0", SwingConstants.CENTER);
        statusLabel = new JLabel("Status: Waiting", SwingConstants.CENTER);

        panel.add(studentLabel);
        panel.add(requiredLabel);
        panel.add(allocatedLabel);
        panel.add(statusLabel);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scrollPane =
                new JScrollPane(resultArea);

        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);
    }

    private void createBottomPanel() {

        JPanel panel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        JLabel label = new JLabel(
                "Smart University Transport - Bus Allocation"
        );

        label.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        panel.add(label);

        add(panel, BorderLayout.SOUTH);
    }

    private void allocateBuses() {

        String route =
                (String) routeBox.getSelectedItem();

        String time =
                (String) timeBox.getSelectedItem();

        controller.resetAllocation();

        BusAllocationResult result =
                controller.allocateBus(
                        route,
                        time
                );

        studentLabel.setText(
                "Students: "
                + result.getStudentCount()
        );

        requiredLabel.setText(
                "Buses Required: "
                + result.getBusesRequired()
        );

        allocatedLabel.setText(
                "Buses Allocated: "
                + result.getBusesAllocated()
        );

        statusLabel.setText(
                "Status: "
                + result.getStatus()
        );

        resultArea.setText("");

        resultArea.append(
                "ROUTE: "
                + result.getRouteName()
                + "\n"
        );

        resultArea.append(
                "TRAVEL TIME: "
                + result.getTravelTime()
                + "\n\n"
        );

        resultArea.append(
                "ALLOCATED BUSES\n"
        );

        resultArea.append(
                "------------------------------\n"
        );

        for (Bus bus :
                result.getAllocatedBuses()) {

            resultArea.append(
                    bus.toString()
                    + "\n"
            );
        }
    }

    private void reset() {

        controller.resetAllocation();

        studentLabel.setText(
                "Students: 0"
        );

        requiredLabel.setText(
                "Buses Required: 0"
        );

        allocatedLabel.setText(
                "Buses Allocated: 0"
        );

        statusLabel.setText(
                "Status: Waiting"
        );

        resultArea.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame =
                    new JFrame(
                            "Bus Allocation"
                    );

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.setSize(900, 600);

            frame.setLocationRelativeTo(null);

           frame.add(
        new BusAllocationPanel(
                new AllocationController()
        )
);

            frame.setVisible(true);
        });
    }
}