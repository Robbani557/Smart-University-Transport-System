package transport.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookingPanel extends JFrame {

    private JTextField studentIdField;
    private JTextField routeField;
    private JComboBox<String> timeBox;

    private DefaultTableModel tableModel;

    public BookingPanel() {

        setTitle("Booking Management");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();
    }

    private void createUI() {

        setLayout(new BorderLayout(10, 10));

        JPanel formPanel =
                new JPanel(
                        new GridLayout(2, 4, 10, 10)
                );

        studentIdField = new JTextField();
        routeField = new JTextField();

        timeBox = new JComboBox<>(
                new String[]{
                    "Morning",
                    "Noon",
                    "Afternoon",
                    "Evening"
                }
        );

        JButton addButton =
                new JButton("Add Booking");

        formPanel.add(
                new JLabel("Student ID")
        );

        formPanel.add(
                new JLabel("Route")
        );

        formPanel.add(
                new JLabel("Travel Time")
        );

        formPanel.add(
                new JLabel("")
        );

        formPanel.add(studentIdField);
        formPanel.add(routeField);
        formPanel.add(timeBox);
        formPanel.add(addButton);

        addButton.addActionListener(
                e -> addBooking()
        );

        add(
                formPanel,
                BorderLayout.NORTH
        );

        tableModel =
                new DefaultTableModel(
                        new String[]{
                            "Student ID",
                            "Route",
                            "Travel Time"
                        },
                        0
                );

        JTable table =
                new JTable(tableModel);

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );
    }

    private void addBooking() {

        String studentId =
                studentIdField.getText().trim();

        String route =
                routeField.getText().trim();

        String time =
                (String) timeBox.getSelectedItem();

        if (studentId.isEmpty()
                || route.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
            );

            return;
        }

        tableModel.addRow(
                new Object[]{
                    studentId,
                    route,
                    time
                }
        );

        studentIdField.setText("");
        routeField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BookingPanel panel =
                    new BookingPanel();

            panel.setVisible(true);
        });
    }
}