package methods;

import java.util.ArrayList;
import java.util.List;

public class RegulaFalsi extends Method {
    private Polynomial function;
    private double lowerBound;
    private double upperBound;
    private List<List<Double>> iterationValues; 
    
    public static void main(String[] args) {
        Polynomial poly = new Polynomial(1, 0, -78.8);
        System.out.println(poly);
        RegulaFalsi falsi = new RegulaFalsi(poly, 6, 12);
        falsi.solveAndStoreValues();
    }
    
    public RegulaFalsi(Polynomial polynomial, double x0, double x1) {
        function = polynomial;
        this.lowerBound = x0;
        this.upperBound = x1;
    }
    
    @Override
    public List<List<Double>> getIterationValues() {
        if (iterationValues == null) {
            solveAndStoreValues();
        }
        
        return iterationValues;
    }

    public void solveAndStoreValues() {
        iterationValues = new ArrayList<>();
        double x0 = lowerBound;
        double x1 = upperBound;
        double x2;
        double y2;
        int iterations = 0;
        
        do {
            // solve
            double y0 = function.evaluate(x0);
            double y1 = function.evaluate(x1);            
            x2 = (x0 * y1 - x1 * y0) / (y1 - y0);
            y2 = function.evaluate(x2);
            
            if (y1 * y2 < 0) {
                x0 = x2;
            }
            else {
                x1 = x2;
            }
            iterations++;
    
            // store
            List<Double> row = new ArrayList<>();
            row.add(x0);
            row.add(x1);
            row.add(y0);
            row.add(y1);
            iterationValues.add(row);
        } while (!isFinished(y2, iterations));
    }

    @Override
    public List<String> getColumnNames() {
        List<String> result = new ArrayList<>();
        result.add("x0");
        result.add("x1");
        result.add("y0");
        result.add("y1");
        return result;
    }
}
