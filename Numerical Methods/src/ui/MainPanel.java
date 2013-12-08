package ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.events.InputListener;
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
    
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        MainPanel panel = new MainPanel();
        frame.setPanel(panel);
    }
    
    public MainPanel() { 
        inputPanel = new InputPanel();
        falsiTab = new MethodTab();
        secantTab = new MethodTab();
        
        tabPane = new JTabbedPane();
        tabPane.add("Regula Falsi", falsiTab);
        tabPane.add("Secant", secantTab);
        tabPane.addChangeListener(new TabPaneListener());
        
        graphPanel = new GraphPanel(); 
        
        setLayout(new MigLayout());
        add(inputPanel, "wrap");
        add(tabPane);
        add(graphPanel);
    }
    
    public void addInputListener(InputListener listener) {
        inputPanel.addListener(listener);
    }
    
    public void updatePolynomial(Polynomial polynomial) {
        
    }
    
    public void updateMethods(RegulaFalsi falsi, Secant secant) {
        
    }
    
    private class TabPaneListener implements ChangeListener {
        
        @Override
        public void stateChanged(ChangeEvent e) {
            
        }
    }
}
