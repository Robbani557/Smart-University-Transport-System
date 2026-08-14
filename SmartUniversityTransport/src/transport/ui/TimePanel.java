package transport.ui;

import model.Route;
import data.TransportData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TimePanel extends JFrame {

    private TransportData transportData;
    private DefaultTableModel tableModel;

   public TimePanel(TransportData transportData) {

    this.transportData = transportData;

    setTitle("Transport Schedules");

        setSize(950, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();

        loadSchedules();
    }

    private void createUI() {

        setLayout(new BorderLayout(10, 10));

        JLabel title =
                new JLabel("Transport Schedules");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        10,
                        20
                )
        );

        add(
                title,
                BorderLayout.NORTH
        );

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "Route",
                                "Students",
                                "Bus Capacity",
                                "Buses Required",
                                "Buses Allocated",
                                "Status"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        JTable scheduleTable =
                new JTable(tableModel);

        scheduleTable.setRowHeight(35);

        scheduleTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        scheduleTable.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        add(
                new JScrollPane(scheduleTable),
                BorderLayout.CENTER
        );

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.addActionListener(
                e -> loadSchedules()
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottomPanel.add(refreshButton);

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
    }

    private void loadSchedules() {

        tableModel.setRowCount(0);

        for (Route route :
                transportData.getRoutes()) {

            tableModel.addRow(
                    new Object[]{
                            route.getRouteName(),
                            route.getTotalStudents(),
                            route.getBusCapacity(),
                            route.getRequiredBuses(),
                            route.getAllocatedBuses(),
                            route.getStatus()
                    }
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TimePanel panel =
        new TimePanel(new TransportData());

            panel.setVisible(true);
        });
    }
}