package ui.events;

public interface InputListener {
    void upperLimitChanged(int x);
    void lowerLimitChanged(int x);
    void startingPointChanged(int x);
    
    void iterationLimitChanged(int iterations);
    void iterationsSelected();
    void iterationsDeselected();
    
    void accuracyLimitChanged(double accuracy);
    void accuracySelected();
    void accuracyDeselected();
}
