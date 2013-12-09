package ui;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.events.InputListener;
import ui.events.MethodTabListener;
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
        /*
         * Init components
         */
        inputPanel = new InputPanel();
        falsiTab = new MethodTab();
        secantTab = new MethodTab();
        
        tabPane = new JTabbedPane();
        tabPane.add("Regula Falsi", falsiTab);
        tabPane.add("Secant", secantTab);
        
        
        graphPanel = new GraphPanel(); 
        
        JSplitPane lowerPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                tabPane, graphPanel);
        
        /*
         * Add listeners
         */
        falsiTab.addListener(new MethodTabListenerI());
        secantTab.addListener(new MethodTabListenerI());
        
        tabPane.addChangeListener(new TabPaneListener());
        
        
        /*
         * Add components
         */
        setLayout(new MigLayout());
        add(inputPanel, "wrap");
        add(lowerPane);
    }
    
    public void addInputListener(InputListener listener) {
        inputPanel.addListener(listener);
    }
    
    public void updateMethods(RegulaFalsi falsi, Secant secant) {
        falsiTab.updateMethod(falsi);
        secantTab.updateMethod(secant);
        updatePolynomial(falsi.getPolynomial());
    }
    
    void updatePolynomial(Polynomial polynomial) {
        double lowerBound = inputPanel.getLowerBound();
        double upperBound = inputPanel.getUpperBound();
        inputPanel.updateEquation(polynomial);
        graphPanel.updatePolynomial(polynomial, lowerBound, upperBound);
    }

    private Method getSelectedMethod() {
        int selectedTabIndex = tabPane.getSelectedIndex();
        MethodTab tab = (MethodTab) tabPane.getComponentAt(selectedTabIndex);
        return tab.getMethod();
    }
    
    private int getSelectedIteration() {
        int selectedTabIndex = tabPane.getSelectedIndex();
        MethodTab tab = (MethodTab) tabPane.getComponentAt(selectedTabIndex);
        return tab.getSelectedIteration();
    }
    
    /*
     * Internal listeners
     */
    
    private class TabPaneListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            Method selectedMethod = getSelectedMethod();
            int iteration = getSelectedIteration();
            graphPanel.updateLine(selectedMethod.getIterationLine(iteration));
        }
    }
    
    private class MethodTabListenerI implements MethodTabListener {

        @Override
        public void selectedIteration(int iteration) {
            Method selectedMethod = getSelectedMethod();
            graphPanel.updateLine(selectedMethod.getIterationLine(iteration));
        }
        
    }
    
    
}
