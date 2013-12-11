package ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import methods.Method;
import net.miginfocom.swing.MigLayout;
import ui.events.MethodTabListener;

@SuppressWarnings("serial")
public class MethodTab extends JPanel {
    private Method method;
    private JLabel iterationsValue;
    private JTable iterationsTable;
    private JLabel fOfXValue;
    private List<MethodTabListener> listeners = new ArrayList<>();
    
    MethodTab() {
        JLabel iterationsLabel = new JLabel("Iterations: ");
        iterationsValue = new JLabel("");
        
        JLabel fOfXLabel = new JLabel("Accuracy: ");
        fOfXValue = new JLabel("");
        
        // table
        iterationsTable = new JTable();
        iterationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        iterationsTable.getSelectionModel().addListSelectionListener(new ListListener());
        
        JScrollPane tablePane = new JScrollPane(iterationsTable);
        setLayout(new MigLayout("wrap 2"));
        add(iterationsLabel);
        add(iterationsValue);
        add(fOfXLabel);
        add(fOfXValue);
        add(tablePane, "span, split");
    }
    
    void addListener(MethodTabListener listener) {
        listeners.add(listener);
    }
    
    void updateMethod(Method method) {
        this.method = method;
        iterationsValue.setText( String.valueOf(method.getTotalIterations()) );
        
        DecimalFormat df = new DecimalFormat("0.000000");
        String finalY = df.format(method.getFinalY());
        fOfXValue.setText( finalY );
        
        MethodTableModel methodModel = new MethodTableModel( method );
        iterationsTable.setModel( methodModel );
    }
    
    Method getMethod() {
        return method;
    }
    
    int getSelectedIteration() {
        return iterationsTable.getSelectedRow();
    }
    
    private class ListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent ev) {
            int index = iterationsTable.getSelectedRow();
            
            if (index == -1) 
                return;
            
            for (MethodTabListener listener : listeners) {
                listener.selectedIteration(index);
            }
        }
    }
}
