package authentication;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SplashFrame extends JFrame {

    private static final Color PRIMARY = new Color(21, 101, 192);
    private static final Color PRIMARY_DARK = new Color(13, 71, 161);
    private static final Color LIGHT_TEXT = new Color(220, 235, 255);

    private JProgressBar progressBar;
    private JLabel loadingLabel;
    private JLabel percentLabel;
    private Timer timer;

    public SplashFrame() {
        initializeFrame();
        buildInterface();
        startLoading();
    }

    private void initializeFrame() {
        setTitle("Smart University Transport System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setMinimumSize(new Dimension(700, 480));
        setSize(1000, 650);
        setLocationRelativeTo(null);
    }

    private void buildInterface() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(PRIMARY);
        root.setBorder(new EmptyBorder(35, 45, 30, 45));

        root.add(createTopSection(), BorderLayout.NORTH);
        root.add(createCenterSection(), BorderLayout.CENTER);
        root.add(createBottomSection(), BorderLayout.SOUTH);

        setContentPane(root);
    }

    private JPanel createTopSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel logo = new ImageLabel("/images/logo.png", 115, 115);
        panel.add(logo, BorderLayout.WEST);

        JLabel version = new JLabel("VERSION 1.0");
        version.setForeground(LIGHT_TEXT);
        version.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        version.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(version, BorderLayout.EAST);

        return panel;
    }

    private JPanel createCenterSection() {
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel title1 = createCenteredLabel(
                "SMART UNIVERSITY", 36, Font.BOLD, Color.WHITE);
        JLabel title2 = createCenteredLabel(
                "TRANSPORT SYSTEM", 36, Font.BOLD, Color.WHITE);

        JLabel subtitle = createCenteredLabel(
                "Travel Smarter. Ride Better.", 18, Font.PLAIN, LIGHT_TEXT);

        ImageLabel bus = new ImageLabel("/images/bus.png", 480, 230);
        bus.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(title1);
        content.add(Box.createVerticalStrut(2));
        content.add(title2);
        content.add(Box.createVerticalStrut(8));
        content.add(subtitle);
        content.add(Box.createVerticalStrut(18));
        content.add(bus);

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.weightx = 1.0;
        g.weighty = 1.0;
        g.anchor = GridBagConstraints.CENTER;

        center.add(content, g);
        return center;
    }

    private JPanel createBottomSection() {
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

        loadingLabel = createCenteredLabel(
                "Loading...", 15, Font.PLAIN, Color.WHITE);
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setPreferredSize(new Dimension(500, 8));
        progressBar.setMaximumSize(new Dimension(650, 8));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressBar.setForeground(Color.WHITE);
        progressBar.setBackground(PRIMARY_DARK);
        progressBar.setBorderPainted(false);
        progressBar.setStringPainted(false);

        percentLabel = createCenteredLabel(
                "0%", 12, Font.PLAIN, LIGHT_TEXT);
        percentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        bottom.add(loadingLabel);
        bottom.add(Box.createVerticalStrut(8));
        bottom.add(progressBar);
        bottom.add(Box.createVerticalStrut(5));
        bottom.add(percentLabel);
        bottom.add(Box.createVerticalStrut(2));

        return bottom;
    }

    private JLabel createCenteredLabel(
            String text, int size, int style, Color color) {

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(color);
        label.setFont(new Font("Segoe UI", style, size));
        return label;
    }

    private void startLoading() {
        timer = new Timer(15, null);

        timer.addActionListener(new ActionListener() {
            private int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                progress++;

                progressBar.setValue(progress);
                percentLabel.setText(progress + "%");

                if (progress < 35) {
                    loadingLabel.setText("Preparing transport services...");
                } else if (progress < 70) {
                    loadingLabel.setText("Loading application...");
                } else if (progress < 100) {
                    loadingLabel.setText("Almost ready...");
                } else {
                    loadingLabel.setText("Welcome!");
                    timer.stop();

                    Timer transition = new Timer(350, event -> {
                        ((Timer) event.getSource()).stop();
                        dispose();
                        new LoginFrame().setVisible(true);
                    });

                    transition.setRepeats(false);
                    transition.start();
                }
            }
        });

        timer.start();
    }

    private static class ImageLabel extends JLabel {

        private final Image image;

        ImageLabel(String resource, int width, int height) {
            setPreferredSize(new Dimension(width, height));
            setMinimumSize(new Dimension(40, 40));
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);

            Image loaded = null;

            try {
                java.net.URL url =
                        SplashFrame.class.getResource(resource);

                if (url != null) {
                    loaded = ImageIO.read(url);
                }
            } catch (IOException ignored) {
            }

            image = loaded;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (image == null) {
                return;
            }

            int availableWidth = Math.max(1, getWidth() - 10);
            int availableHeight = Math.max(1, getHeight() - 10);

            double scale = Math.min(
                    availableWidth / (double) image.getWidth(null),
                    availableHeight / (double) image.getHeight(null)
            );

            int width = Math.max(
                    1, (int) (image.getWidth(null) * scale));
            int height = Math.max(
                    1, (int) (image.getHeight(null) * scale));

            int x = (getWidth() - width) / 2;
            int y = (getHeight() - height) / 2;

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR
            );
            g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY
            );

            g2.drawImage(image, x, y, width, height, this);
            g2.dispose();
        }
    }

}