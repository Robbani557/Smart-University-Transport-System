package transport.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RoutePanel extends JFrame {

    private DefaultTableModel tableModel;

    public RoutePanel() {

        setTitle("Route Management");

        setSize(800, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();
    }

    private void createUI() {

        setLayout(new BorderLayout(10, 10));

        JLabel title =
                new JLabel(
                        "University Transport Routes",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        add(
                title,
                BorderLayout.NORTH
        );

        tableModel = new DefaultTableModel(
                new String[]{
                    "Route",
                    "Start",
                    "Destination",
                    "Bus Capacity"
                },
                0
        );

        tableModel.addRow(
                new Object[]{
                    "Mirpur",
                    "Mirpur",
                    "University",
                    50
                }
        );

        tableModel.addRow(
                new Object[]{
                    "Dhanmondi",
                    "Dhanmondi",
                    "University",
                    50
                }
        );

        tableModel.addRow(
                new Object[]{
                    "Uttara",
                    "Uttara",
                    "University",
                    50
                }
        );

        tableModel.addRow(
                new Object[]{
                    "Mohammadpur",
                    "Mohammadpur",
                    "University",
                    50
                }
        );

        JTable table =
                new JTable(tableModel);

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            RoutePanel panel =
                    new RoutePanel();

            panel.setVisible(true);
        });
    }
}