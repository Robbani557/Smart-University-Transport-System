package authentication;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RoleSelectionFrame extends JFrame {

    private static final Color PRIMARY = new Color(21, 101, 192);
    private static final Color PRIMARY_DARK = new Color(13, 71, 161);
    private static final Color TEXT_DARK = new Color(35, 45, 60);
    private static final Color TEXT_MUTED = new Color(105, 115, 130);
    private static final Color LIGHT_BG = new Color(245, 247, 250);

    public RoleSelectionFrame() {
        setTitle("Smart University Transport System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(850, 560));
        setSize(1050, 680);
        setLocationRelativeTo(null);
        buildInterface();
    }

    private void buildInterface() {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(LIGHT_BG);

        JPanel brandPanel = createBrandPanel();
        JPanel choicePanel = createChoicePanel();

        GridBagConstraints left = new GridBagConstraints();
        left.gridx = 0;
        left.gridy = 0;
        left.weightx = 0.43;
        left.weighty = 1.0;
        left.fill = GridBagConstraints.BOTH;

        GridBagConstraints right = new GridBagConstraints();
        right.gridx = 1;
        right.gridy = 0;
        right.weightx = 0.57;
        right.weighty = 1.0;
        right.fill = GridBagConstraints.BOTH;

        root.add(brandPanel, left);
        root.add(choicePanel, right);

        setContentPane(root);
    }

    private JPanel createBrandPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PRIMARY);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.CENTER;

        g.gridy = 0;
        g.weighty = 0.15;
        g.fill = GridBagConstraints.NONE;
        panel.add(new ImageLabel("/images/logo.png", 130, 130), g);

        JLabel title = label("SMART UNIVERSITY", 27, Font.BOLD, Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 1;
        g.weighty = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(7, 0, 0, 0);
        panel.add(title, g);

        JLabel title2 = label("TRANSPORT SYSTEM", 27, Font.BOLD, Color.WHITE);
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 2;
        g.insets = new Insets(0, 0, 5, 0);
        panel.add(title2, g);

        JLabel subtitle = label(
                "Safe and smart university transportation",
                14,
                Font.PLAIN,
                new Color(220, 235, 255)
        );
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 3;
        g.insets = new Insets(5, 0, 8, 0);
        panel.add(subtitle, g);

        g.gridy = 4;
        g.weighty = 0.85;
        g.fill = GridBagConstraints.BOTH;
        g.insets = new Insets(8, 0, 0, 0);
        panel.add(new ImageLabel("/images/bus.png", 430, 250), g);

        return panel;
    }

    private JPanel createChoicePanel() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(Color.WHITE);
        outer.setBorder(BorderFactory.createEmptyBorder(35, 45, 35, 45));

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 229, 235)),
                BorderFactory.createEmptyBorder(35, 40, 35, 40)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = label("WELCOME", 28, Font.BOLD, PRIMARY);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(0, 0, 5, 0);
        card.add(title, g);

        JLabel subtitle = label(
                "Please select how you want to continue",
                14,
                Font.PLAIN,
                TEXT_MUTED
        );
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 1;
        g.insets = new Insets(0, 0, 30, 0);
        card.add(subtitle, g);

        JButton studentButton = createRoleButton(
                "STUDENT",
                "/images/ic_profile.png"
        );

        JButton adminButton = createRoleButton(
                "ADMIN",
                "/images/ic_dashboard.png"
        );

        studentButton.addActionListener(e -> openStudentLogin());
        adminButton.addActionListener(e -> openAdminLogin());

        g.gridy = 2;
        g.insets = new Insets(0, 0, 14, 0);
        card.add(studentButton, g);

        g.gridy = 3;
        g.insets = new Insets(0, 0, 0, 0);
        card.add(adminButton, g);

        JLabel footer = label(
                "Choose your account type to continue",
                12,
                Font.PLAIN,
                TEXT_MUTED
        );
        footer.setHorizontalAlignment(SwingConstants.CENTER);

        g.gridy = 4;
        g.insets = new Insets(22, 0, 0, 0);
        card.add(footer, g);

        GridBagConstraints outerG = new GridBagConstraints();
        outerG.gridx = 0;
        outerG.gridy = 0;
        outerG.weightx = 1.0;
        outerG.weighty = 1.0;
        outerG.fill = GridBagConstraints.HORIZONTAL;
        outerG.anchor = GridBagConstraints.CENTER;

        outer.add(card, outerG);

        return outer;
    }

    private JButton createRoleButton(String title, String iconPath) {

        JButton button = new JButton();
        button.setPreferredSize(new Dimension(360, 82));
        button.setMinimumSize(new Dimension(300, 82));
        button.setBackground(Color.WHITE);
        button.setForeground(TEXT_DARK);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 223)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        JPanel content = new JPanel(new FlowLayout(
                FlowLayout.CENTER, 12, 0
        ));
        content.setOpaque(false);

        ImageIcon icon = loadScaledIcon(iconPath, 34, 34);

        if (icon != null) {
            JLabel iconLabel = new JLabel(icon);
            content.add(iconLabel);
        }

        JLabel titleLabel = label(title, 17, Font.BOLD, PRIMARY);
        content.add(titleLabel);

        button.setLayout(new GridBagLayout());
        button.add(content);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(239, 246, 255));
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(PRIMARY),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(210, 215, 223)),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
        });

        return button;
    }

    private void openStudentLogin() {
        new LoginFrame().setVisible(true);
        dispose();
    }

    private void openAdminLogin() {
        new admin.AdminLogin().setVisible(true);
        dispose();
    }

    private JLabel label(String text, int size, int style, Color color) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", style, size));
        l.setForeground(color);
        return l;
    }

    private ImageIcon loadScaledIcon(String path, int width, int height) {
        try {
            java.net.URL url = getClass().getResource(path);
            if (url != null) {
                ImageIcon original = new ImageIcon(url);
                Image resized = original.getImage().getScaledInstance(
                        width, height, Image.SCALE_SMOOTH
                );
                return new ImageIcon(resized);
            }
        } catch (Exception ignored) {
        }
        return null;
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
                java.net.URL url = RoleSelectionFrame.class.getResource(resource);
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
                    1,
                    (int) (image.getWidth(null) * scale)
            );

            int height = Math.max(
                    1,
                    (int) (image.getHeight(null) * scale)
            );

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new RoleSelectionFrame().setVisible(true)
        );
    }
}
