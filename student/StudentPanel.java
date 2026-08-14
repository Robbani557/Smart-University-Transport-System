package student;

import component.PlaceholderPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Owned by the Student module member. This currently shows a placeholder —
 * replace the body of this constructor with the real Student management UI.
 * MainFrame only relies on the public no-arg constructor and this class
 * being a JPanel, so no other file needs to change.
 */
public class StudentPanel extends JPanel {
    public StudentPanel() {
        setLayout(new BorderLayout());
        add(PlaceholderPanel.forModule("Student"), BorderLayout.CENTER);
    }
}
