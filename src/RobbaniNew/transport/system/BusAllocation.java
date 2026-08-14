package smart.university.transport.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BusAllocation extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    public BusAllocation(JFrame parent) {

        setTitle("Bus Allocation");
        setSize(900, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(15, 15));

        main.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel title = new JLabel("Bus Allocation");
        title.setFont(new Font("Arial", Font.BOLD, 26));

        main.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{
                    "Allocation ID",
                    "Bus Number",
                    "Route",
                    "Driver"
                },
                0
        );

        table = new JTable(model);

        main.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JPanel bottom = new JPanel();

        JButton allocate = new JButton("Allocate Bus");
        JButton delete = new JButton("Remove");
        JButton close = new JButton("Close");

        bottom.add(allocate);
        bottom.add(delete);
        bottom.add(close);

        main.add(bottom, BorderLayout.SOUTH);

        allocate.addActionListener(e -> allocateBus());
        delete.addActionListener(e -> deleteAllocation());
        close.addActionListener(e -> dispose());

        add(main);
    }

    private void allocateBus() {

        JTextField id = new JTextField();
        JTextField bus = new JTextField();
        JTextField route = new JTextField();
        JTextField driver = new JTextField();

        Object[] fields = {
            "Allocation ID:", id,
            "Bus Number:", bus,
            "Route:", route,
            "Driver:", driver
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Allocate Bus",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            model.addRow(new Object[]{
                id.getText(),
                bus.getText(),
                route.getText(),
                driver.getText()
            });
        }
    }

    private void deleteAllocation() {

        int row = table.getSelectedRow();

        if (row >= 0) {
            model.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an allocation."
            );
        }
    }
}
