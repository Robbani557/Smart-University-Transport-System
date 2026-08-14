package component;

import utils.FontManager;
import utils.IconUtils;
import utils.Theme;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Reusable sidebar navigation. Communicates with MainFrame purely through
 * a {@link NavigationListener} — it has no knowledge of CardLayout itself,
 * so it can be reused even if the navigation mechanism changes later.
 */
public class Sidebar extends JPanel {

    public interface NavigationListener {
        void onNavigate(String cardKey);
        void onLogout();
    }

    // {key, label, icon file} — add/remove rows here to change nav items,
    // no need to duplicate button wiring logic per item.
    private static final String[][] NAV_ITEMS = {
            {"DASHBOARD", "Dashboard", "dashboard.png"},
            {"STUDENT", "Student", "student.png"},
            {"ADMIN", "Admin", "admin.png"},
            {"TRANSPORT", "Transport", "transport.png"},
            {"REPORT", "Report", "report.png"}
    };

    private final Map<String, JButton> navButtons = new LinkedHashMap<>();
    private NavigationListener listener;
    private String activeKey = "DASHBOARD";

    public Sidebar() {
        setPreferredSize(new Dimension(Theme.SIDEBAR_WIDTH, 0));
        setBackground(Theme.SIDEBAR_BG);
        setLayout(new BorderLayout());

        add(buildBrand(), BorderLayout.NORTH);
        add(buildNavItems(), BorderLayout.CENTER);
        add(buildLogout(), BorderLayout.SOUTH);
    }

    public void setNavigationListener(NavigationListener listener) {
        this.listener = listener;
    }

    /** Lets MainFrame sync the highlighted item if navigation is triggered elsewhere. */
    public void setActive(String key) {
        activeKey = key;
        for (Map.Entry<String, JButton> entry : navButtons.entrySet()) {
            boolean active = entry.getKey().equals(key);
            entry.getValue().setBackground(active ? Theme.SIDEBAR_ACTIVE : Theme.SIDEBAR_BG);
        }
    }

    private JPanel buildBrand() {
        JPanel brand = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 22));
        brand.setOpaque(false);

        JLabel icon = new JLabel(IconUtils.getIcon("bus.png", 26, 26));
        JLabel name = new JLabel("SUTMS");
        name.setFont(FontManager.sectionHeading());
        name.setForeground(Color.WHITE);

        brand.add(icon);
        brand.add(name);
        return brand;
    }

    private JPanel buildNavItems() {
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        for (String[] item : NAV_ITEMS) {
            String key = item[0];
            JButton btn = createNavButton(item[1], item[2], key);
            navButtons.put(key, btn);
            wrapper.add(btn);
            wrapper.add(Box.createVerticalStrut(4));
        }

        setActive("DASHBOARD");
        return wrapper;
    }

    private JButton createNavButton(String label, String iconFile, String key) {
        JButton btn = new JButton(label);
        btn.setIcon(IconUtils.getIcon(iconFile, 18, 18));
        btn.setIconTextGap(14);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFont(FontManager.label());
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBackground(Theme.SIDEBAR_BG);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!key.equals(activeKey)) {
                    btn.setBackground(Theme.SIDEBAR_HOVER_SOLID);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!key.equals(activeKey)) {
                    btn.setBackground(Theme.SIDEBAR_BG);
                }
            }
        });

        // Single centralized handler — no per-button navigation logic duplication.
        btn.addActionListener(e -> {
            setActive(key);
            if (listener != null) {
                listener.onNavigate(key);
            }
        });

        return btn;
    }

    private JPanel buildLogout() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));

        JButton logout = new JButton("Logout");
        logout.setIcon(IconUtils.getIcon("logout.png", 18, 18));
        logout.setIconTextGap(14);
        logout.setHorizontalAlignment(SwingConstants.LEFT);
        logout.setFont(FontManager.label());
        logout.setForeground(Color.WHITE);
        logout.setOpaque(false);
        logout.setContentAreaFilled(false);
        logout.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        logout.setFocusPainted(false);
        logout.setBorderPainted(false);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logout.setForeground(new Color(255, 205, 210));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logout.setForeground(Color.WHITE);
            }
        });

        logout.addActionListener(e -> {
            if (listener != null) {
                listener.onLogout();
            }
        });

        wrapper.add(logout, BorderLayout.CENTER);
        return wrapper;
    }
}
