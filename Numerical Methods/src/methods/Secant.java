package methods;

import java.util.ArrayList;
import java.util.List;

public class Secant extends Method {
    private Polynomial function;
    private double x0;
    private double x1;
    private double x2 = 0;
    
    public static void main(String[] args) {
        Polynomial poly = new Polynomial(1, 0, -78.8);
        System.out.println(poly);
        Secant secant = new Secant(poly, 12, 10);
    }
    
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
        result.add("x1");
        result.add("y0");
        result.add("y1");
        return result;
    }
    
    @Override
    protected void addIterationRow(List<List<Double>> iterationValues) {
        List<Double> row = new ArrayList<>();
        row.add(x0);
        row.add(x1);
        row.add(function.evaluate(x0));
        row.add(function.evaluate(x1));
        iterationValues.add(row);
    }
}
