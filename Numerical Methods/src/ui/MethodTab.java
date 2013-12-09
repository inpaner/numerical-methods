package ui;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;
import methods.Method;
import methods.Polynomial;
import methods.RegulaFalsi;

public class MethodTab extends JPanel {
    private Method method;
    private JLabel iterationsValue;
    private JTable iterationsTable;
    private JLabel fOfXValue;
    
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        MethodTab tab = new MethodTab();
        frame.setPanel(tab);
        Polynomial poly = new Polynomial(1, 0, -78.8);
        RegulaFalsi falsi = new RegulaFalsi(poly, 6, 12);
        Method method = new RegulaFalsi(poly, 0, 10);
        tab.updateMethod(falsi);
    }
    
    MethodTab() {
        JLabel iterationsLabel = new JLabel("Iterations: ");
        iterationsValue = new JLabel("");
        
        JLabel fOfXLabel = new JLabel("Accuracy: ");
        fOfXValue = new JLabel("");
        
        // table
        iterationsTable = new JTable();
        JScrollPane tablePane = new JScrollPane(iterationsTable);
        
        setLayout(new MigLayout("wrap 2"));
        add(iterationsLabel);
        add(iterationsValue);
        add(fOfXLabel);
        add(fOfXValue);
        add(tablePane, "span, split");
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
}
