import methods.AccuracyChecker;
import methods.IterationChecker;
import methods.Polynomial;
import methods.PolynomialValidator;
import methods.RegulaFalsi;
import methods.Secant;
import ui.InputPanel;
import ui.MainFrame;
import ui.MainPanel;
import ui.events.InputListener;


public class Main {
    private Polynomial currentPolynomial;
    private RegulaFalsi falsi;
    private Secant secant;
    private double upperLimit;
    private double lowerLimit;
    private double startingPoint;
    private MainPanel panel;
    
    public static void main(String[] args) {
        new Main().begin();
    }
    
    void begin() {
        MainFrame frame = new MainFrame();
        panel = new MainPanel();
        panel.addInputListener(new InputListenerI());
        
        frame.setPanel(panel);
    }
    
    private void refreshEquations() {
        falsi = new RegulaFalsi(currentPolynomial, lowerLimit, upperLimit);
        secant = new Secant(currentPolynomial, lowerLimit, upperLimit);
        panel.updateMethods(falsi, secant);
    }
    
    private class InputListenerI implements InputListener {

        @Override
        public void equationChanged(String text) {
            if (PolynomialValidator.isEquationValid(text)) {
                currentPolynomial = new Polynomial(text);
                refreshEquations();
            }
            else {
                System.out.println("INVALID");
            }
        }
        
        @Override
        public void upperLimitChanged(double x) {
            upperLimit = x;
            refreshEquations();
        }

        @Override
        public void lowerLimitChanged(double x) {
            lowerLimit = x;
            refreshEquations();
        }

        @Override
        public void startingPointChanged(double x) {
            startingPoint = x;
            refreshEquations();
        }

        @Override
        public void iterationLimitChanged(int iterations) {
            IterationChecker.setTotalIterations(iterations);
            refreshEquations();
        }

        @Override
        public void iterationsSelected() {
            IterationChecker.enable();
            refreshEquations();
        }

        @Override
        public void iterationsDeselected() {
            IterationChecker.disable();
            refreshEquations();
        }

        @Override
        public void accuracyLimitChanged(double accuracy) {
            AccuracyChecker.setAccuracy(accuracy);
            refreshEquations();
        }

        @Override
        public void accuracySelected() {
            AccuracyChecker.enable();
            refreshEquations();
        }

        @Override
        public void accuracyDeselected() {
            AccuracyChecker.disable();
            refreshEquations();
        }

    }
}
