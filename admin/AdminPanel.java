package admin;

import component.PlaceholderPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Owned by the Admin module member. This currently shows a placeholder —
 * replace the body of this constructor with the real Admin management UI.
 * MainFrame only relies on the public no-arg constructor and this class
 * being a JPanel, so no other file needs to change.
 */
public class AdminPanel extends JPanel {
    public AdminPanel() {
        setLayout(new BorderLayout());
        add(PlaceholderPanel.forModule("Admin"), BorderLayout.CENTER);
    }
}
