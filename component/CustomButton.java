package component;

import utils.FontManager;
import utils.Theme;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Reusable button with rounded corners, hover/press feedback, and
 * theme-based coloring. Any module can use it as a drop-in JButton
 * replacement:
 *
 *   CustomButton button = new CustomButton("Student");
 *   panel.add(button);
 */
public class CustomButton extends JButton {

    private final Color baseColor;
    private final Color hoverColor;
    private final Color pressedColor;
    private int cornerRadius = 10;

    private boolean hovering = false;
    private boolean pressing = false;

    public CustomButton(String text) {
        this(text, Theme.PRIMARY, Theme.PRIMARY_LIGHT, Theme.TEXT_ON_PRIMARY);
    }

    public CustomButton(String text, Color baseColor, Color hoverColor, Color textColor) {
        super(text);
        this.baseColor = baseColor;
        this.hoverColor = hoverColor;
        this.pressedColor = hoverColor.darker();

        setFont(FontManager.buttonText());
        setForeground(textColor);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 22, 10, 22));
        setLayout(new BorderLayout());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
                pressing = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressing = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressing = false;
                repaint();
            }
        });
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color fill = !isEnabled() ? baseColor.brighter()
                : pressing ? pressedColor
                : hovering ? hoverColor
                : baseColor;

        g2.setColor(fill);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        return new Dimension(Math.max(d.width, 120), Math.max(d.height, 42));
    }
}
