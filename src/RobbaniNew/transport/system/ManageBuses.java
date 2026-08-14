package smart.university.transport.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageBuses extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    public ManageBuses(JFrame parent) {

        setTitle("Manage Buses");
        setSize(900, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(15, 15));

        main.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel title = new JLabel("Manage Buses");
        title.setFont(new Font("Arial", Font.BOLD, 26));

        main.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{
                    "Bus ID",
                    "Bus Number",
                    "Driver",
                    "Capacity"
                },
                0
        );

        table = new JTable(model);

        main.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JPanel bottom = new JPanel();

        JButton add = new JButton("Add Bus");
        JButton delete = new JButton("Delete Bus");
        JButton close = new JButton("Close");

        bottom.add(add);
        bottom.add(delete);
        bottom.add(close);

        main.add(bottom, BorderLayout.SOUTH);

        add.addActionListener(e -> addBus());
        delete.addActionListener(e -> deleteBus());
        close.addActionListener(e -> dispose());

        add(main);
    }

    private void addBus() {

        JTextField id = new JTextField();
        JTextField number = new JTextField();
        JTextField driver = new JTextField();
        JTextField capacity = new JTextField();

        Object[] fields = {
            "Bus ID:", id,
            "Bus Number:", number,
            "Driver Name:", driver,
            "Capacity:", capacity
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Add Bus",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            model.addRow(new Object[]{
                id.getText(),
                number.getText(),
                driver.getText(),
                capacity.getText()
            });
        }
    }

    private void deleteBus() {

        int row = table.getSelectedRow();

        if (row >= 0) {
            model.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a bus."
            );
        }
    }
}
