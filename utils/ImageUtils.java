package utils;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

/**
 * Reusable helpers for loading and scaling images from the classpath
 * (src/images) without ever throwing if a file is missing.
 */
public final class ImageUtils {

    private ImageUtils() {
        // no instances
    }

    /**
     * Loads an image from the classpath and scales it. Returns null instead
     * of throwing if the resource is missing or unreadable, so callers can
     * fall back to a generated icon (see {@link IconUtils}).
     */
    public static ImageIcon loadScaledIcon(String resourcePath, int width, int height) {
        ImageIcon raw = loadIcon(resourcePath);
        if (raw == null) {
            return null;
        }
        Image scaled = raw.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /** Loads an image from the classpath at its original size, or null if missing. */
    public static ImageIcon loadIcon(String resourcePath) {
        try {
            URL url = ImageUtils.class.getResource(resourcePath);
            if (url == null) {
                return null;
            }
            return new ImageIcon(url);
        } catch (Exception e) {
            return null;
        }
    }
}
