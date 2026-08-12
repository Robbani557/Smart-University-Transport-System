package transport.ui;

import javax.swing.*;
import java.awt.*;

public class BusAllocationFrame extends JFrame {

    public BusAllocationFrame() {

        setTitle("Smart University Transport System");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setSize(1100, 700);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel header = new JPanel(
                new BorderLayout()
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 15, 20
                )
        );

        JLabel title = new JLabel(
                "BUS ALLOCATION SYSTEM"
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel subtitle = new JLabel(
                "Smart University Transport"
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        header.add(
                title,
                BorderLayout.NORTH
        );

        header.add(
                subtitle,
                BorderLayout.SOUTH
        );

        add(
                header,
                BorderLayout.NORTH
        );

        add(
                new BusAllocationPanel(),
                BorderLayout.CENTER
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BusAllocationFrame frame =
                    new BusAllocationFrame();

            frame.setVisible(true);
        });
    }
}