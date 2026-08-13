package transport.ui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

import model.BusAllocationResult;
public class AllocationTableModel extends AbstractTableModel {

    private final String[] columns = {
        "Route",
        "Travel Time",
        "Students",
        "Buses Required",
        "Buses Allocated",
        "Status"
    };

    private List<BusAllocationResult> results;

    public AllocationTableModel() {
        results = new ArrayList<>();
    }

    public void setResults(List<BusAllocationResult> results) {
        this.results = results;
        fireTableDataChanged();
    }

    public void addResult(BusAllocationResult result) {
        results.add(result);
        fireTableDataChanged();
    }

    public void clear() {
        results.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return results.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int row, int column) {

        BusAllocationResult result = results.get(row);

        switch (column) {

            case 0:
                return result.getRouteName();

            case 1:
                return result.getTravelTime();

            case 2:
                return result.getStudentCount();

            case 3:
                return result.getBusesRequired();

            case 4:
                return result.getBusesAllocated();

            case 5:
                return result.getStatus();

            default:
                return "";
        }
    }
}