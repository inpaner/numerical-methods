package methods;

import java.util.ArrayList;
import java.util.List;

public class Secant extends Method {
    private Polynomial function;
    private double x0;
    private double x1;
    private double x2 = 0;
    
    public Secant(Polynomial polynomial, double x0, double x1) {
        super(polynomial);
        function = polynomial;
        this.x0 = x0;
        this.x1 = x1;
    }
    
    @Override
    public void iterate() {
        double y0 = function.evaluate(x0);
        double y1 = function.evaluate(x1);
        x2 = x1 - ( (y1 * (x0 - x1)) / (y0 - y1) );
        x0 = x1;
        x1 = x2;            
    }

    @Override
    protected double getY() {
        return function.evaluate(x2);
    }
    
    @Override
    public List<String> getColumnNames() {
        List<String> result = new ArrayList<>();
        result.add("x0");
        result.add("y0");
        result.add("x1");
        result.add("y1");
        return result;
    }
    
    @Override
    protected void addIterationRow(List<List<Double>> iterationValues) {
        List<Double> row = new ArrayList<>();
        row.add(x0);
        row.add(function.evaluate(x0));
        row.add(x1);
        row.add(function.evaluate(x1));
        iterationValues.add(row);
    }
    
    @Override
    protected Line getCustomIterationLine(List<Double> iterationRow) {
        double x0 = iterationRow.get(0);
        double y0 = iterationRow.get(1);
        double x1 = iterationRow.get(2);
        double y1 = iterationRow.get(3);
        
        double slope = (y1 - y0) / (x1 - x0);
        double newX = (-y1 / slope) + x1;
        
        // both on same side
        if (y0 * y1 > 0) {
            // both positive
            if (y0 > 0) {
                // extend point that's closer to 0
                if (y0 < y1) {
                    x0 = newX;
                    y0 = 0;
                }
                else {
                    x1 = newX; 
                    y1 = 0;
                }
            }
            // both negative
            else {
                if (y0 > y1) {
                    x0 = newX;
                    y0 = 0;
                }
                else {
                    x1 = newX;
                    y1 = 0;
                }
            }
        }
        
        
        return new Line(x0, y0, x1, y1);
    }
}
