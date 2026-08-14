package component;

import utils.Theme;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Reusable card-style JPanel with rounded corners, theme-based background,
 * and an optional border. Other modules can extend it or use it directly:
 *
 *   CustomPanel card = new CustomPanel();
 *   card.add(someLabel);
 */
public class CustomPanel extends JPanel {

    private int cornerRadius;
    private Color panelBackground;
    private boolean showBorder;
    private Color borderColor;

    public CustomPanel() {
        this(Theme.CORNER_RADIUS, Theme.SURFACE, true, Theme.BORDER);
    }

    public CustomPanel(int cornerRadius, Color panelBackground, boolean showBorder, Color borderColor) {
        this.cornerRadius = cornerRadius;
        this.panelBackground = panelBackground;
        this.showBorder = showBorder;
        this.borderColor = borderColor;

        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(
                Theme.SPACING_MD, Theme.SPACING_MD, Theme.SPACING_MD, Theme.SPACING_MD));
    }

    public void setPanelBackground(Color color) {
        this.panelBackground = color;
        repaint();
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    public void setShowBorder(boolean showBorder) {
        this.showBorder = showBorder;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(panelBackground);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        if (showBorder) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        }
        g2.dispose();

        super.paintComponent(g);
    }
}
