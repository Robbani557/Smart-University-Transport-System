package utils;

import java.awt.Color;

/**
 * Centralized theme constants for the whole application.
 * Every module should pull colors/dimensions from here instead of
 * hard-coding raw Color objects, so the whole app stays visually consistent.
 */
public final class Theme {

    private Theme() {
        // no instances
    }

    // ---- Core palette (matches the approved UI design) ----
    public static final Color PRIMARY        = Color.decode("#1565C0");
    public static final Color PRIMARY_LIGHT  = Color.decode("#1976D2");
    public static final Color BACKGROUND     = Color.decode("#F5F7FA");
    public static final Color SURFACE        = Color.decode("#FFFFFF");

    public static final Color TEXT_DARK      = Color.decode("#212121");
    public static final Color TEXT_MUTED     = Color.decode("#6B7280");
    public static final Color TEXT_ON_PRIMARY = Color.WHITE;

    public static final Color SUCCESS        = Color.decode("#2E7D32");
    public static final Color DANGER         = Color.decode("#C62828");
    public static final Color WARNING        = Color.decode("#F57C00");
    public static final Color INFO_PURPLE    = Color.decode("#7B1FA2");

    public static final Color BORDER         = Color.decode("#E0E4EA");

    // ---- Sidebar specific ----
    public static final Color SIDEBAR_BG          = PRIMARY;
    public static final Color SIDEBAR_ACTIVE      = PRIMARY_LIGHT;
    public static final Color SIDEBAR_HOVER_SOLID = Color.decode("#1E88E5");

    // ---- Table specific ----
    public static final Color TABLE_HEADER_BG  = Color.decode("#EEF2F7");
    public static final Color TABLE_ROW_ALT    = Color.decode("#FAFBFD");
    public static final Color TABLE_SELECTION  = Color.decode("#E3F0FC");

    // ---- Dimensions ----
    public static final int SIDEBAR_WIDTH  = 230;
    public static final int HEADER_HEIGHT  = 64;
    public static final int CORNER_RADIUS  = 12;

    public static final int SPACING_SM = 8;
    public static final int SPACING_MD = 16;
    public static final int SPACING_LG = 24;
}
