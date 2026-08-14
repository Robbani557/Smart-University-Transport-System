package utils;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Reusable icon lookup. Looks for real assets under src/images first;
 * if an asset is missing it generates a lightweight fallback dot icon
 * instead of crashing, so the app never breaks over a missing PNG.
 *
 * Usage: IconUtils.getIcon("dashboard.png", 18, 18)
 */
public final class IconUtils {

    private IconUtils() {
        // no instances
    }

    private static final String BASE_PATH = "/images/";
    private static final Map<String, ImageIcon> CACHE = new HashMap<>();

    public static ImageIcon getIcon(String fileName, int width, int height) {
        String key = fileName + "_" + width + "x" + height;
        ImageIcon cached = CACHE.get(key);
        if (cached != null) {
            return cached;
        }

        ImageIcon icon = ImageUtils.loadScaledIcon(BASE_PATH + fileName, width, height);
        if (icon == null) {
            icon = fallbackIcon(width, height);
        }

        CACHE.put(key, icon);
        return icon;
    }

    /** Simple translucent circle so missing icons still occupy space cleanly. */
    private static ImageIcon fallbackIcon(int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255, 190));
        g2.fillOval(1, 1, Math.max(width - 2, 1), Math.max(height - 2, 1));
        g2.dispose();
        return new ImageIcon(img);
    }
}
