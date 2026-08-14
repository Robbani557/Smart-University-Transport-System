package component;

import utils.FontManager;
import utils.IconUtils;
import utils.Theme;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Reusable top header. Shows the current page title on the left and
 * date/notification/user info on the right. MainFrame updates the page
 * title whenever the sidebar navigates to a new card.
 */
public class Header extends JPanel {

    private final JLabel pageTitleLabel;
    private final JLabel userNameLabel;
    private final JLabel userRoleLabel;

    public Header() {
        setPreferredSize(new Dimension(0, Theme.HEADER_HEIGHT));
        setBackground(Theme.SURFACE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BORDER));

        pageTitleLabel = new JLabel("Dashboard");
        pageTitleLabel.setFont(FontManager.sectionHeading());
        pageTitleLabel.setForeground(Theme.TEXT_DARK);
        pageTitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 0));
        right.setOpaque(false);
        right.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        JLabel dateLabel = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy")));
        dateLabel.setFont(FontManager.smallText());
        dateLabel.setForeground(Theme.TEXT_MUTED);

        JLabel notificationIcon = new JLabel(IconUtils.getIcon("bell.png", 20, 20));
        JLabel avatar = new JLabel(IconUtils.getIcon("user.png", 32, 32));

        JPanel userInfo = new JPanel();
        userInfo.setOpaque(false);
        userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.Y_AXIS));

        userNameLabel = new JLabel("Admin User");
        userNameLabel.setFont(FontManager.label().deriveFont(Font.BOLD));
        userNameLabel.setForeground(Theme.TEXT_DARK);

        userRoleLabel = new JLabel("Administrator");
        userRoleLabel.setFont(FontManager.smallText());
        userRoleLabel.setForeground(Theme.TEXT_MUTED);

        userInfo.add(userNameLabel);
        userInfo.add(userRoleLabel);

        right.add(dateLabel);
        right.add(notificationIcon);
        right.add(avatar);
        right.add(userInfo);

        add(pageTitleLabel, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
    }

    /** Called by MainFrame whenever the active card changes. */
    public void setPageTitle(String title) {
        pageTitleLabel.setText(title);
    }

    /** Called once real auth/session data is available (post-login integration). */
    public void setUser(String name, String role) {
        userNameLabel.setText(name);
        userRoleLabel.setText(role);
    }
}
