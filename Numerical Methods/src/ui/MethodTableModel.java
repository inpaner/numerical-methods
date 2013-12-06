package ui;

import javax.swing.table.AbstractTableModel;

import methods.Method;

@SuppressWarnings("serial")
public class MethodTableModel extends AbstractTableModel {
    private Method method;
    
    MethodTableModel(Method method) {
        this.method = method;
    }
    
    
    @Override
    public int getColumnCount() {
        // iteration number + columns
        return method.getColumnNames().size() + 1;
    }

    @Override
    public int getRowCount() {
        return method.getIterationValues().size();
    }
    
    
    @Override
    public String getColumnName(int column) {
        String columnName = "";
        switch(column) {
        case 0: 
            columnName = "Iteration";
            break;
        
        default:
            int valuesColumn = column - 1;
            columnName = method.getColumnNames().get(valuesColumn);
            break;
        }
        
        return columnName;
    }


    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Object value = "";
        
        // iteration number
        if (colIndex == 0) {
            value = rowIndex + 1;
        }
        else {
            int valuesColumn = colIndex - 1;
            value = method.getIterationValues().get(rowIndex).get(valuesColumn);
        }
        
        return value;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
}
