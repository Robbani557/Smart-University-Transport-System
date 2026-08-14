package smart.university.transport.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageStudents extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    public ManageStudents(JFrame parent) {

        setTitle("Manage Students");
        setSize(900, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(15, 15));

        main.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel title = new JLabel("Manage Students");
        title.setFont(new Font("Arial", Font.BOLD, 26));

        main.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{
                    "Student ID",
                    "Student Name",
                    "Department",
                    "Phone"
                },
                0
        );

        table = new JTable(model);

        main.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JPanel bottom = new JPanel();

        JButton add = new JButton("Add Student");
        JButton delete = new JButton("Delete Student");
        JButton close = new JButton("Close");

        bottom.add(add);
        bottom.add(delete);
        bottom.add(close);

        main.add(bottom, BorderLayout.SOUTH);

        add.addActionListener(e -> addStudent());
        delete.addActionListener(e -> deleteStudent());
        close.addActionListener(e -> dispose());

        add(main);
    }

    private void addStudent() {

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField department = new JTextField();
        JTextField phone = new JTextField();

        Object[] fields = {
            "Student ID:", id,
            "Student Name:", name,
            "Department:", department,
            "Phone:", phone
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Add Student",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            model.addRow(new Object[]{
                id.getText(),
                name.getText(),
                department.getText(),
                phone.getText()
            });
        }
    }

    private void deleteStudent() {

        int row = table.getSelectedRow();

        if (row >= 0) {
            model.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );
        }
    }
}