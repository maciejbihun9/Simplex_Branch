package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaciekBihun on 2016-05-08.
 */
public class Table {

    private DefaultTableModel tableModel;
    private JTable table;
    public Table(JTable table){
        this.table = table;
        tableModel = (DefaultTableModel) table.getModel();
        System.out.println("Row count: " + tableModel.getRowCount());
    }

    public DefaultTableModel getTableModel(){
        return tableModel;
    }

    /**
     *
     * @param index Add to table as much columns as
     *              was declared in function parameter.
     */
    public  void addColumns(int index){
        for (int i = 0; i < index; i++) {
            tableModel.addColumn("x" + index);
        }
    }

    /**
     *
     * @param zmienne Add row to a table with starting values.
     */
    public void addRow(Object [] zmienne){
        tableModel.addRow(zmienne);
    }

    /**
     *
     * @param whichRow Removes from table specified row.
     */
    public void removeRow(int whichRow) {
        tableModel.removeRow(whichRow);
    }

    /**
     *
     * @param size Set how many titles you want to set
     *             in your table.
     */
    public void setColumnTitle(int size){
        String [] values = new String[size];
        for (int i = 0; i < size - 2; i++) {
            values[i] = "x " + (i + 1);
        }
        values[size - 1] = "sign";
        values[size - 2] = "result";
        tableModel.setColumnIdentifiers(values);
    }

    /**
     *
     * @param index Which row index.
     * @param columnCount How many columns are there.
     * @return List with all elements from specified row.
     */
    public List<Integer> getElementsFromRow(int index,int columnCount){
        List<Integer> rowElements = new ArrayList<Integer>();
        for (int i = 0; i < columnCount; i++) {
            rowElements.add(Integer.parseInt((String)tableModel.getValueAt(index, i)));
        }
        return rowElements;
    }


}
