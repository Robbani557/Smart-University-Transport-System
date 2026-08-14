package transport;

import component.PlaceholderPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Owned by the Transport module member. This currently shows a placeholder —
 * replace the body of this constructor with the real Transport management UI.
 * MainFrame only relies on the public no-arg constructor and this class
 * being a JPanel, so no other file needs to change.
 */
public class TransportPanel extends JPanel {
    public TransportPanel() {
        setLayout(new BorderLayout());
        add(PlaceholderPanel.forModule("Transport"), BorderLayout.CENTER);
    }
}
