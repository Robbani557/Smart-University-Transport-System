package transport.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentPanel extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField routeField;

    private DefaultTableModel tableModel;
    private JTable table;

    public StudentPanel() {

        setTitle("Student Management");

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
                new JPanel(new GridLayout(2, 5, 10, 10));

        idField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();
        routeField = new JTextField();

        JButton addButton =
                new JButton("Add Student");

        formPanel.add(new JLabel("Student ID"));
        formPanel.add(new JLabel("Name"));
        formPanel.add(new JLabel("Email"));
        formPanel.add(new JLabel("Route"));
        formPanel.add(new JLabel(""));

        formPanel.add(idField);
        formPanel.add(nameField);
        formPanel.add(emailField);
        formPanel.add(routeField);
        formPanel.add(addButton);

        addButton.addActionListener(e -> addStudent());

        add(
                formPanel,
                BorderLayout.NORTH
        );

        tableModel = new DefaultTableModel(
                new String[]{
                    "Student ID",
                    "Name",
                    "Email",
                    "Route"
                },
                0
        );

        table = new JTable(tableModel);

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );
    }

    private void addStudent() {

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String route = routeField.getText().trim();

        if (id.isEmpty()
                || name.isEmpty()
                || email.isEmpty()
                || route.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
            );

            return;
        }

        tableModel.addRow(
                new Object[]{
                    id,
                    name,
                    email,
                    route
                }
        );

        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        routeField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentPanel panel =
                    new StudentPanel();

            panel.setVisible(true);
        });
    }
}