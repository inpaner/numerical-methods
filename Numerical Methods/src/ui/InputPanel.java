package ui;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class InputPanel extends JPanel {
    private final int MAX_X = 1000;
    private JSpinner lowerSpinner;
    private JSpinner upperSpinner;
    private JSpinner startSpinner;
    private JSpinner iterationSpinner;
    private JSpinner accuracySpinner;
    
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        InputPanel panel = new InputPanel();
        frame.setPanel(panel);
    }
    
    public InputPanel() {
        /*
         * Initialize components
         */
        // Equation
        JTextField equationField = new JTextField(20);
        JLabel equationLabel = new JLabel("<html>2x<sup>10</sup></html>");
        
        // Interval
        JLabel intervalLabel = new JLabel("Interval: ");
        
        lowerSpinner = new JSpinner(new SpinnerNumberModel(0, 0, MAX_X, 1));
        JComponent field = ((JSpinner.DefaultEditor) lowerSpinner.getEditor());
        Dimension prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        JLabel toLabel = new JLabel("to: ");
        
        upperSpinner = new JSpinner(new SpinnerNumberModel(0, 0, MAX_X, 1));
        field = ((JSpinner.DefaultEditor) upperSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        // Starting point 
        JLabel startLabel = new JLabel("Starting Point: ");
        
        startSpinner = new JSpinner(new SpinnerNumberModel(0, 0, MAX_X, 1));
        field = ((JSpinner.DefaultEditor) startSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        //Conditions
        // Iterations
        JCheckBox iterationsBox = new JCheckBox("Iterations: ");
        iterationSpinner = new JSpinner(new SpinnerNumberModel(0, 0, MAX_X, 1));
        field = ((JSpinner.DefaultEditor) iterationSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        // Accuracy
        JCheckBox accuracyBox = new JCheckBox("Accuracy: ");
        accuracySpinner = new JSpinner(new SpinnerNumberModel(0, 0, MAX_X, 0.001));
        field = ((JSpinner.DefaultEditor) accuracySpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(50, prefSize.height);
        field.setPreferredSize(prefSize);
        
        /*
         * Add components
         */
        setLayout(new MigLayout("wrap 5"));
        add(equationField, "span, split, wrap");
        add(equationLabel, "wrap 10");
        add(intervalLabel);
        
        add(lowerSpinner);
        add(toLabel);
        add(upperSpinner, "wrap");
        
        add(startLabel);
        add(startSpinner, "wrap 10");
        
        add(iterationsBox);
        add(iterationSpinner, "wrap");
        add(accuracyBox);
        add(accuracySpinner, "wrap");
    }
    
}
