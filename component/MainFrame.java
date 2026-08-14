package component;

import admin.AdminPanel;
import report.ReportPanel;
import student.StudentPanel;
import transport.TransportPanel;
import utils.Theme;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

/**
 * Single main application window. Uses CardLayout to switch between
 * major screens instead of opening a new JFrame per module.
 *
 * Integration point for other members:
 *   - Student panel lives at student.StudentPanel
 *   - Admin panel lives at admin.AdminPanel
 *   - Transport panel lives at transport.TransportPanel
 *   - Report panel lives at report.ReportPanel
 * Each currently shows a placeholder; replace the panel's internal
 * contents with real UI whenever that module is ready — the public
 * no-arg constructor is the only thing MainFrame depends on, so no
 * changes are needed here when that happens.
 */
public class MainFrame extends JFrame {

    public static final String CARD_DASHBOARD = "DASHBOARD";
    public static final String CARD_STUDENT   = "STUDENT";
    public static final String CARD_ADMIN     = "ADMIN";
    public static final String CARD_TRANSPORT = "TRANSPORT";
    public static final String CARD_REPORT    = "REPORT";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);
    private final Header header = new Header();
    private final Sidebar sidebar = new Sidebar();

    public MainFrame() {
        super("Smart University Transport Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 800);
        setMinimumSize(new Dimension(1100, 650));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initContentCards();
        initSidebar();

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.add(header, BorderLayout.NORTH);
        centerWrapper.add(contentPanel, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(centerWrapper, BorderLayout.CENTER);

        showCard(CARD_DASHBOARD, "Dashboard");
    }

    private void initContentCards() {
        contentPanel.setBackground(Theme.BACKGROUND);

        contentPanel.add(new DashboardPanel(), CARD_DASHBOARD);
        contentPanel.add(new StudentPanel(), CARD_STUDENT);
        contentPanel.add(new AdminPanel(), CARD_ADMIN);
        contentPanel.add(new TransportPanel(), CARD_TRANSPORT);
        contentPanel.add(new ReportPanel(), CARD_REPORT);
    }

    private void initSidebar() {
        sidebar.setNavigationListener(new Sidebar.NavigationListener() {
            @Override
            public void onNavigate(String cardKey) {
                switch (cardKey) {
                    case CARD_STUDENT:
                        showCard(CARD_STUDENT, "Student");
                        break;
                    case CARD_ADMIN:
                        showCard(CARD_ADMIN, "Admin");
                        break;
                    case CARD_TRANSPORT:
                        showCard(CARD_TRANSPORT, "Transport");
                        break;
                    case CARD_REPORT:
                        showCard(CARD_REPORT, "Report");
                        break;
                    default:
                        showCard(CARD_DASHBOARD, "Dashboard");
                }
            }

            @Override
            public void onLogout() {
                int choice = JOptionPane.showConfirmDialog(
                        MainFrame.this,
                        "Are you sure you want to logout?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION
                );
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                    // Integration point once the login module exists:
                    //   new login.LoginFrame().setVisible(true);
                    System.exit(0);
                }
            }
        });
    }

    private void showCard(String cardKey, String title) {
        cardLayout.show(contentPanel, cardKey);
        header.setPageTitle(title);
    }
}
