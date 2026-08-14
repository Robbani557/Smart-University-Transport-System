package component;

import utils.FontManager;
import utils.Theme;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Reusable, theme-consistent JTable. Any module can build a table with:
 *
 *   CustomTable table = new CustomTable(data, columnNames);
 *   panel.add(CustomTable.wrapInScrollPane(table));
 */
public class CustomTable extends JTable {

    public CustomTable(TableModel model) {
        super(model);
        applyStyle();
    }

    public CustomTable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
        applyStyle();
    }

    private void applyStyle() {
        setFont(FontManager.tableText());
        setRowHeight(34);
        setShowGrid(true);
        setGridColor(Theme.BORDER);
        setIntercellSpacing(new Dimension(0, 0));
        setSelectionBackground(Theme.TABLE_SELECTION);
        setSelectionForeground(Theme.TEXT_DARK);
        setFillsViewportHeight(true);
        setBackground(Theme.SURFACE);
        setForeground(Theme.TEXT_DARK);

        JTableHeader header = getTableHeader();
        header.setFont(FontManager.label().deriveFont(Font.BOLD, 15f));
        header.setBackground(Theme.TABLE_HEADER_BG);
        header.setForeground(Theme.TEXT_DARK);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 38));
        header.setReorderingAllowed(false);

        setDefaultRenderer(Object.class, new StripedCellRenderer());
    }

    /** Alternating row shading for readability, consistent with the design. */
    private static class StripedCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                         boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {
                c.setBackground(row % 2 == 0 ? Theme.SURFACE : Theme.TABLE_ROW_ALT);
                c.setForeground(Theme.TEXT_DARK);
            }
            setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            return c;
        }
    }

    /** Wraps a table in a clean, theme-bordered scroll pane. */
    public static JScrollPane wrapInScrollPane(JTable table) {
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createLineBorder(Theme.BORDER));
        sp.getViewport().setBackground(Theme.SURFACE);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return sp;
    }
}
