package transport.ui;

import transport.model.Bus;
import transport.model.TransportData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BusDetailsPanel extends JFrame {

    private TransportData transportData;
    private DefaultTableModel tableModel;

    public BusDetailsPanel(TransportData transportData) {

    this.transportData = transportData;

    setTitle("Bus Details");

        setSize(900, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();

        loadBusDetails();
    }

    private void createUI() {

        setLayout(new BorderLayout(10, 10));

        JLabel title =
                new JLabel("Bus Details");

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
                                "Bus ID",
                                "Bus Number",
                                "Capacity",
                                "Driver",
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

        JTable table =
                new JTable(tableModel);

        table.setRowHeight(35);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.addActionListener(
                e -> loadBusDetails()
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

    private void loadBusDetails() {

        tableModel.setRowCount(0);

        for (Bus bus :
                transportData.getBuses()) {

            tableModel.addRow(
                    new Object[]{
                            bus.getBusId(),
                            bus.getBusNumber(),
                            bus.getCapacity(),
                            bus.getDriverName(),
                            bus.getStatus()
                    }
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BusDetailsPanel panel =
                   new BusDetailsPanel(new TransportData());

            panel.setVisible(true);
        });
    }
}