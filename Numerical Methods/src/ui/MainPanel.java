package ui;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.events.InputListener;
import ui.events.MethodTabListener;
import methods.Bisection;
import methods.Method;
import methods.NewtonRaphson;
import methods.Polynomial;
import methods.RegulaFalsi;
import methods.Secant;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
    
    private JTabbedPane tabPane;
    private MethodTab bisectionTab;
    private MethodTab falsiTab;
    private MethodTab newtonTab;
    private MethodTab secantTab;
    private GraphPanel graphPanel;
    private InputPanel inputPanel;
    
    public MainPanel() { 
        /*
         * Init components
         */
        inputPanel = new InputPanel();
        bisectionTab = new MethodTab();
        falsiTab = new MethodTab();
        newtonTab = new MethodTab();
        secantTab = new MethodTab();
        
        tabPane = new JTabbedPane();
        tabPane.add("Bisection", bisectionTab);
        tabPane.add("Regula Falsi", falsiTab);
        tabPane.add("Newton-Raphson", newtonTab);
        tabPane.add("Secant", secantTab);
        
        
        graphPanel = new GraphPanel(); 
        
        JSplitPane lowerPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                tabPane, graphPanel);
        lowerPane.setDividerLocation(250);
        
        /*
         * Add listeners
         */
        bisectionTab.addListener(new MethodTabListenerI());
        falsiTab.addListener(new MethodTabListenerI());
        newtonTab.addListener(new MethodTabListenerI());
        secantTab.addListener(new MethodTabListenerI());   
        tabPane.addChangeListener(new TabPaneListener());
        
        /*
         * Add components
         */
        setLayout(new MigLayout());
        add(inputPanel, "wrap");
        add(lowerPane);
    }
    
    private void updatePolynomial(Polynomial polynomial) {
        double lowerBound = inputPanel.getLowerBound();
        double upperBound = inputPanel.getUpperBound();
        inputPanel.updateEquation(polynomial);
        graphPanel.updatePolynomial(polynomial, lowerBound, upperBound);
    }

    public void updateMethods(Bisection bisection, RegulaFalsi falsi, NewtonRaphson newton, Secant secant) {
        bisectionTab.updateMethod(bisection);
        falsiTab.updateMethod(falsi);
        newtonTab.updateMethod(newton);
        secantTab.updateMethod(secant);
        updatePolynomial(falsi.getPolynomial());
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
    
    public void setInvalid() {
        inputPanel.setInvalid();
    }
    
    /*
     * Internal listeners
     */
    
    public void addInputListener(InputListener listener) {
        inputPanel.addListener(listener);
    }

    private class TabPaneListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            Method selectedMethod = getSelectedMethod();
            int iteration = getSelectedIteration();
            if (iteration < 0) 
                return;
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
