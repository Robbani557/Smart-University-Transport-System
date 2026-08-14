package report;

import component.PlaceholderPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Owned by the Report module member. This currently shows a placeholder —
 * replace the body of this constructor with the real Report UI.
 * MainFrame only relies on the public no-arg constructor and this class
 * being a JPanel, so no other file needs to change.
 */
public class ReportPanel extends JPanel {
    public ReportPanel() {
        setLayout(new BorderLayout());
        add(PlaceholderPanel.forModule("Report"), BorderLayout.CENTER);
    }
}
