package methods;

import java.util.ArrayList;
import java.util.List;

public class NewtonRaphson extends Method {
    private Polynomial function;
    private Polynomial derivative;
    private double x;
    private double x2 = 0;
    
    
    public NewtonRaphson(Polynomial polynomial, double startingPoint) {
        super(polynomial);
        function = polynomial;
        derivative = polynomial.getDerivative();
        this.x = startingPoint;
    }

    
    @Override
    public List<String> getColumnNames() {
        List<String> result = new ArrayList<>();
        result.add("x");
        result.add("y");
        result.add("y'");
        return result;
    }

    
    @Override
    protected void iterate() {
        x2 = x - function.evaluate(x) / derivative.evaluate(x);
        x = x2;
    }

    
    @Override
    protected double getY() {
        return function.evaluate(x2);
    }

    
    @Override
    protected void addIterationRow(List<List<Double>> iterationValues) {
        List<Double> row = new ArrayList<>();
        row.add(x);
        row.add(function.evaluate(x));
        row.add(derivative.evaluate(x));
        iterationValues.add(row);
    }

    
    @Override
    protected Line getCustomIterationLine(List<Double> iterationRow) {
        double initialX = iterationRow.get(0);
        double initialY = iterationRow.get(1);
        
        double slope = derivative.evaluate(initialX);
        double zeroedX = (-initialY / slope) + initialX;
        double zeroedY = 0;
        
        return new Line(initialX, initialY, zeroedX, zeroedY);
    }
}
