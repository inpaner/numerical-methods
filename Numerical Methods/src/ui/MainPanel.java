package ui;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.events.InputListener;
import methods.Method;
import methods.Polynomial;
import methods.RegulaFalsi;
import methods.Secant;
import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel {
    
    private JTabbedPane tabPane;    
    private MethodTab falsiTab;
    private MethodTab secantTab;
    private GraphPanel graphPanel;
    private InputPanel inputPanel;
    
    public MainPanel() { 
        inputPanel = new InputPanel();
        falsiTab = new MethodTab();
        secantTab = new MethodTab();
        
        tabPane = new JTabbedPane();
        tabPane.add("Regula Falsi", falsiTab);
        tabPane.add("Secant", secantTab);
        tabPane.addChangeListener(new TabPaneListener());
        
        
        graphPanel = new GraphPanel(); 
        
        JSplitPane lowerPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                tabPane, graphPanel);
        
        setLayout(new MigLayout());
        add(inputPanel, "wrap");
        add(lowerPane);
    }
    
    public void addInputListener(InputListener listener) {
        inputPanel.addListener(listener);
    }
    
    public void updatePolynomial(Polynomial polynomial) {
        double lowerBound = inputPanel.getLowerBound();
        double upperBound = inputPanel.getUpperBound();
        graphPanel.updatePolynomial(polynomial, lowerBound, upperBound);
    }
    
    public void updateMethods(RegulaFalsi falsi, Secant secant) {
        falsiTab.updateMethod(falsi);
        secantTab.updateMethod(secant);
        updatePolynomial(falsi.getPolynomial());
    }
    
    private class TabPaneListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            Method selectedMethod = getSelectedMethod();
            graphPanel.updateLine(selectedMethod.getIterationLine(0));
        }
    }
    
    private Method getSelectedMethod() {
        int selectedTabIndex = tabPane.getSelectedIndex();
        MethodTab tab = (MethodTab) tabPane.getComponentAt(selectedTabIndex);
        return tab.getMethod();
    }
}
