package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
import ui.events.InputListener;

@SuppressWarnings("serial")
public class InputPanel extends JPanel {
    private final int MAX_X = 1000;
    private List<InputListener> listeners = new ArrayList<>();
    
    private JSpinner lowerSpinner;
    private JSpinner upperSpinner;
    private JSpinner startSpinner;
    private JSpinner iterationSpinner;
    private JSpinner accuracySpinner;
    private JCheckBox iterationsBox;
    private JCheckBox accuracyBox;
    
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
        
        lowerSpinner = new JSpinner( new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1) );
        JComponent field = ((JSpinner.DefaultEditor) lowerSpinner.getEditor());
        Dimension prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        JLabel toLabel = new JLabel("to: ");
        
        upperSpinner = new JSpinner( new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1) );
        field = ((JSpinner.DefaultEditor) upperSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        // Starting point 
        JLabel startLabel = new JLabel("Starting Point: ");
        
        startSpinner = new JSpinner( new SpinnerNumberModel(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 1) );
        field = ((JSpinner.DefaultEditor) startSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        // Iterations
        iterationsBox = new JCheckBox("Iterations: ");
        iterationSpinner = new JSpinner(new SpinnerNumberModel(0, 0, MAX_X, 1));
        field = ((JSpinner.DefaultEditor) iterationSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        // Accuracy
        accuracyBox = new JCheckBox("Accuracy: ");
        accuracySpinner = new JSpinner(new SpinnerNumberModel(0, 0, MAX_X, 0.001));
        field = ((JSpinner.DefaultEditor) accuracySpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(50, prefSize.height);
        field.setPreferredSize(prefSize);
        
        /*
         * Add listeners
         */
        lowerSpinner.addChangeListener(new LowerSpinnerListener());
        upperSpinner.addChangeListener(new UpperSpinnerListener());
        startSpinner.addChangeListener(new StartSpinnerListener());
        iterationsBox.addActionListener(new IterationsBoxListener());
        iterationSpinner.addChangeListener(new IterationsValueListener());
        accuracyBox.addActionListener(new AccuracyBoxListener());
        accuracySpinner.addChangeListener(new AccuracyValueListener());
        
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
    
    public void addListener(InputListener listener) {
        listeners.add(listener);
    }
    
    
    /*
     * Private Listeners
     */
    
    private class LowerSpinnerListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent event) {         
            int lowerLimit = (Integer) lowerSpinner.getValue();
            int upperLimit = (Integer) upperSpinner.getValue();
            
            if (lowerLimit > upperLimit) {
                upperLimit = lowerLimit;
            }
            
            for (InputListener listener : listeners) {
                listener.lowerLimitChanged(lowerLimit);
            }
        }
    }
    
    private class UpperSpinnerListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent event) {         
            int lowerLimit = (Integer) lowerSpinner.getValue();
            int upperLimit = (Integer) upperSpinner.getValue();
            
            if (upperLimit < lowerLimit) {
                lowerLimit = upperLimit;   
            }
            
            for (InputListener listener : listeners) {
                listener.upperLimitChanged(upperLimit);
            }
        }
    }
    
    private class StartSpinnerListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent event) {         
            int startingPoint = (Integer) startSpinner.getValue();
            
            for (InputListener listener : listeners) {
                listener.startingPointChanged(startingPoint);
            }
        }
    }
    
    private class IterationsBoxListener implements ActionListener {  
        @Override
        public void actionPerformed(ActionEvent event) {
            if (iterationsBox.isSelected()) {
                iterationSpinner.setValue(true);
                for (InputListener listener : listeners) {
                    listener.iterationsSelected();
                }
            }
            else {
                iterationSpinner.setEnabled(false);
                for (InputListener listener : listeners) {
                    listener.iterationsDeselected();
                }
            }
        }
    }
    
    private class IterationsValueListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent event) {         
            int iterations = (int) iterationSpinner.getValue();
            
            for (InputListener listener : listeners) {
                listener.iterationLimitChanged(iterations);
            }
        }
    }
        
    private class AccuracyBoxListener implements ActionListener {  
        @Override
        public void actionPerformed(ActionEvent event) {
            if (accuracyBox.isSelected()) {
                accuracySpinner.setValue(true);
                for (InputListener listener : listeners) {
                    listener.accuracySelected();
                }
            }
            else {
                accuracySpinner.setEnabled(false);
                for (InputListener listener : listeners) {
                    listener.accuracyDeselected();
                }
            }
        }
    }
    
    private class AccuracyValueListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent event) {         
            double accuracy = (double) accuracySpinner.getValue();
            
            for (InputListener listener : listeners) {
                listener.accuracyLimitChanged(accuracy);
            }
        }
    }
}
