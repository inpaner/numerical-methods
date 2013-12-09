package ui.events;

public interface InputListener {
    void equationChanged(String text);
    
    void upperLimitChanged(double x);
    void lowerLimitChanged(double x);
    
    void upperBoundAsStart();
    void lowerBoundAsStart();    
    
    void iterationLimitChanged(int iterations);
    void iterationsSelected();
    void iterationsDeselected();
    
    void accuracyLimitChanged(double accuracy);
    void accuracySelected();
    void accuracyDeselected();

}
