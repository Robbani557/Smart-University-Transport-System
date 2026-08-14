package component;

import utils.FontManager;
import utils.Theme;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;

/**
 * Lightweight placeholder for a module screen that hasn't been built yet.
 * Other members can keep using their own Panel class (e.g. StudentPanel)
 * and simply swap this out for real content later — MainFrame's
 * integration point never has to change.
 */
public final class PlaceholderPanel {

    private PlaceholderPanel() {
        // no instances
    }

    public static JComponent forModule(String moduleName) {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(Theme.BACKGROUND);

        CustomPanel card = new CustomPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(380, 160));

        JLabel title = new JLabel(moduleName + " Module");
        title.setFont(FontManager.sectionHeading());
        title.setForeground(Theme.TEXT_DARK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("This section is under construction.");
        subtitle.setFont(FontManager.label());
        subtitle.setForeground(Theme.TEXT_MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());
        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(subtitle);
        card.add(Box.createVerticalGlue());

        outer.add(card);
        return outer;
    }
}
