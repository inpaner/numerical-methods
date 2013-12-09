import methods.AccuracyChecker;
import methods.Bisection;
import methods.IterationChecker;
import methods.NewtonRaphson;
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
    private Bisection bisection;
    private RegulaFalsi falsi;
    private NewtonRaphson newton;
    private Secant secant;
    private double upperLimit;
    private double lowerLimit;
    private double startingPoint;
    private MainPanel panel;
    
    public static void main(String[] args) {
        new Main().begin();
    }
    
    void begin() {
        upperLimit = 10;
        lowerLimit = 0;
        
        MainFrame frame = new MainFrame();
        panel = new MainPanel();
        panel.addInputListener(new InputListenerI());
        
        frame.setPanel(panel);
    }
    
    private void refreshEquations() {
        bisection = new Bisection(currentPolynomial, lowerLimit, upperLimit);
        falsi = new RegulaFalsi(currentPolynomial, lowerLimit, upperLimit);
        newton = new NewtonRaphson(currentPolynomial, lowerLimit);
        secant = new Secant(currentPolynomial, lowerLimit, upperLimit);
        panel.updateMethods(bisection, falsi, newton, secant);
    }
    
    private class InputListenerI implements InputListener {

        @Override
        public void equationChanged(String text) {
            if (PolynomialValidator.isEquationValid(text)) {
                currentPolynomial = new Polynomial(text);
                refreshEquations();
            }
            else {
                panel.setInvalid();
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
