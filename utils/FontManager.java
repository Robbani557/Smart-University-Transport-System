package utils;

import java.awt.Font;

/**
 * Centralized font utility so type styles stay consistent across every module.
 * Uses "Segoe UI", which is available by default on Windows and degrades
 * gracefully to the platform's sans-serif default elsewhere.
 */
public final class FontManager {

    private FontManager() {
        // no instances
    }

    private static final String FAMILY = "Segoe UI";

    public static Font title() {
        return new Font(FAMILY, Font.BOLD, 28);
    }

    public static Font sectionHeading() {
        return new Font(FAMILY, Font.BOLD, 20);
    }

    public static Font label() {
        return new Font(FAMILY, Font.PLAIN, 16);
    }

    public static Font buttonText() {
        return new Font(FAMILY, Font.BOLD, 16);
    }

    public static Font tableText() {
        return new Font(FAMILY, Font.PLAIN, 15);
    }

    public static Font smallText() {
        return new Font(FAMILY, Font.PLAIN, 12);
    }
}
