package smart.university.transport.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageRoutes extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    public ManageRoutes(JFrame parent) {

        setTitle("Manage Routes");
        setSize(900, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(15, 15));
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Manage Routes");
        title.setFont(new Font("Arial", Font.BOLD, 26));

        main.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Route ID", "Route Name", "Start Point", "End Point"},
                0
        );

        table = new JTable(model);

        main.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        JButton add = new JButton("Add Route");
        JButton delete = new JButton("Delete Route");
        JButton close = new JButton("Close");

        bottom.add(add);
        bottom.add(delete);
        bottom.add(close);

        main.add(bottom, BorderLayout.SOUTH);

        add.addActionListener(e -> addRoute());
        delete.addActionListener(e -> deleteRoute());
        close.addActionListener(e -> dispose());

        add(main);
    }

    private void addRoute() {

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField start = new JTextField();
        JTextField end = new JTextField();

        Object[] fields = {
            "Route ID:", id,
            "Route Name:", name,
            "Start Point:", start,
            "End Point:", end
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Add Route",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            model.addRow(new Object[]{
                id.getText(),
                name.getText(),
                start.getText(),
                end.getText()
            });
        }
    }

    private void deleteRoute() {

        int row = table.getSelectedRow();

        if (row >= 0) {
            model.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a route."
            );
        }
    }
}
