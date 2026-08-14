package component;

import utils.FontManager;
import utils.Theme;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

/**
 * Default landing screen shown after login/navigation.
 * Sample data only — the real numbers/wiring belong to whichever
 * module owns dashboard metrics; this establishes the layout & style.
 */
public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new BorderLayout(0, Theme.SPACING_LG));
        setBackground(Theme.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(
                Theme.SPACING_LG, Theme.SPACING_LG, Theme.SPACING_LG, Theme.SPACING_LG));

        add(buildStatCards(), BorderLayout.NORTH);
        add(buildBookingsTable(), BorderLayout.CENTER);
    }

    private JPanel buildStatCards() {
        JPanel row = new JPanel(new GridLayout(1, 4, Theme.SPACING_LG, 0));
        row.setOpaque(false);

        row.add(statCard("1,250", "Total Students", Theme.PRIMARY));
        row.add(statCard("48", "Total Buses", Theme.SUCCESS));
        row.add(statCard("342", "Today's Bookings", Theme.WARNING));
        row.add(statCard("128", "Available Seats", Theme.INFO_PURPLE));

        return row;
    }

    private CustomPanel statCard(String value, String label, Color accent) {
        CustomPanel card = new CustomPanel();
        card.setLayout(new BorderLayout(Theme.SPACING_MD, 0));

        JPanel iconWrap = new JPanel(new BorderLayout());
        iconWrap.setOpaque(false);
        iconWrap.setPreferredSize(new Dimension(44, 44));
        iconWrap.add(new AccentDot(accent), BorderLayout.CENTER);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(FontManager.title());
        valueLabel.setForeground(Theme.TEXT_DARK);

        JLabel captionLabel = new JLabel(label);
        captionLabel.setFont(FontManager.smallText());
        captionLabel.setForeground(Theme.TEXT_MUTED);

        textPanel.add(valueLabel);
        textPanel.add(captionLabel);

        card.add(iconWrap, BorderLayout.WEST);
        card.add(textPanel, BorderLayout.CENTER);
        return card;
    }

    /** Small circular accent used inside stat cards in place of a real icon asset. */
    private static class AccentDot extends JComponent {
        private final Color color;

        AccentDot(Color color) {
            this.color = color;
            setPreferredSize(new Dimension(40, 40));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillOval(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }

    private JScrollPane buildBookingsTable() {
        String[] columns = {"Route", "Bookings", "Required Buses", "Allocated Buses", "Difference"};
        Object[][] data = {
                {"Mirpur", 212, 5, 4, "-1"},
                {"Uttara", 67, 2, 2, "0"},
                {"Dhanmondi", 371, 8, 8, "0"},
                {"Badda", 43, 1, 2, "+1"},
                {"Mohammadpur", 158, 4, 4, "0"}
        };

        CustomTable table = new CustomTable(data, columns);
        JScrollPane innerScroll = CustomTable.wrapInScrollPane(table);
        innerScroll.setPreferredSize(new Dimension(0, 220));

        CustomPanel wrapper = new CustomPanel();
        wrapper.setLayout(new BorderLayout(0, Theme.SPACING_SM));

        JLabel title = new JLabel("Bookings by Route (Today)");
        title.setFont(FontManager.sectionHeading());
        title.setForeground(Theme.TEXT_DARK);

        wrapper.add(title, BorderLayout.NORTH);
        wrapper.add(innerScroll, BorderLayout.CENTER);

        JScrollPane outer = new JScrollPane(wrapper);
        outer.setBorder(BorderFactory.createEmptyBorder());
        outer.getViewport().setBackground(Theme.BACKGROUND);
        outer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return outer;
    }
}
