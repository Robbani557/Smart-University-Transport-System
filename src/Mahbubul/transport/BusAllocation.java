package transport;

import transport.ui.AllocationUIController;
import transport.model.Bus;
import transport.model.BusAllocationResult;

import javax.swing.*;
import java.awt.*;

public class BusAllocation extends JFrame {

    private JComboBox<String> routeBox;
    private JComboBox<String> timeBox;

    private JLabel studentsLabel;
    private JLabel requiredLabel;
    private JLabel allocatedLabel;
    private JLabel statusLabel;

    private JTextArea resultArea;

   private AllocationUIController controller;

    public BusAllocation() {

       controller = new AllocationUIController();

        setTitle("Bus Allocation");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();
    }

    private void createUI() {

        setLayout(new BorderLayout(15, 15));

        JPanel topPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        10,
                        15
                )
        );

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

        timeBox = new JComboBox<>(
                new String[]{
                    "Morning",
                    "Noon",
                    "Afternoon",
                    "Evening"
                }
        );

        JButton allocateButton =
                new JButton("Allocate Buses");

        JButton resetButton =
                new JButton("Reset");

        allocateButton.addActionListener(
                e -> allocateBuses()
        );

        resetButton.addActionListener(
                e -> reset()
        );

        topPanel.add(
                new JLabel("Route:")
        );

        topPanel.add(routeBox);

        topPanel.add(
                new JLabel("Time:")
        );

        topPanel.add(timeBox);

        topPanel.add(allocateButton);

        topPanel.add(resetButton);

        add(
                topPanel,
                BorderLayout.NORTH
        );

        JPanel informationPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                10,
                                10
                        )
                );

        studentsLabel =
                new JLabel(
                        "Students: 0",
                        SwingConstants.CENTER
                );

        requiredLabel =
                new JLabel(
                        "Required: 0",
                        SwingConstants.CENTER
                );

        allocatedLabel =
                new JLabel(
                        "Allocated: 0",
                        SwingConstants.CENTER
                );

        statusLabel =
                new JLabel(
                        "Status: Waiting",
                        SwingConstants.CENTER
                );

        informationPanel.add(studentsLabel);
        informationPanel.add(requiredLabel);
        informationPanel.add(allocatedLabel);
        informationPanel.add(statusLabel);

        resultArea = new JTextArea();

        resultArea.setEditable(false);

        resultArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(resultArea);

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        20,
                        20
                )
        );

        centerPanel.add(
                informationPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    private void allocateBuses() {

        String route =
                (String) routeBox.getSelectedItem();

        String time =
                (String) timeBox.getSelectedItem();

       //controller.reset();
       BusAllocationResult result =
        controller.allocate(
                route,
                time
        );

        studentsLabel.setText(
                "Students: "
                + result.getStudentCount()
        );

        requiredLabel.setText(
                "Required: "
                + result.getBusesRequired()
        );

        allocatedLabel.setText(
                "Allocated: "
                + result.getBusesAllocated()
        );

        statusLabel.setText(
                "Status: "
                + result.getStatus()
        );

        resultArea.setText("");

        resultArea.append(
                "BUS ALLOCATION RESULT\n"
        );

        resultArea.append(
                "==============================\n\n"
        );

        resultArea.append(
                "Route: "
                + result.getRouteName()
                + "\n"
        );

        resultArea.append(
                "Travel Time: "
                + result.getTravelTime()
                + "\n"
        );

        resultArea.append(
                "Students: "
                + result.getStudentCount()
                + "\n"
        );

        resultArea.append(
                "Buses Required: "
                + result.getBusesRequired()
                + "\n"
        );

        resultArea.append(
                "Buses Allocated: "
                + result.getBusesAllocated()
                + "\n"
        );

        resultArea.append(
                "Status: "
                + result.getStatus()
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

        controller.reset();

        studentsLabel.setText(
                "Students: 0"
        );

        requiredLabel.setText(
                "Required: 0"
        );

        allocatedLabel.setText(
                "Allocated: 0"
        );

        statusLabel.setText(
                "Status: Waiting"
        );

        resultArea.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BusAllocation frame =
                    new BusAllocation();

            frame.setVisible(true);
        });
    }
}