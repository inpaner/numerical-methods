package methods;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private List<Double> coefficients;
    
    public static void main(String[] args) {
        List<Double> c = new ArrayList<Double>();
        c.add(-1.0);
        c.add(3.0);
        c.add(4.0);
        Polynomial p = new Polynomial(c);
        System.out.println(p.toString());
        System.out.println(p.evaluate(3.0));
    }
    
    public Polynomial(List<Double> coefficients) {
        this.coefficients = coefficients;
    }
    
    public int getDegree() {
        return coefficients.size() - 1;
    }
    
    public double evaluate(double x) {
        double result = 0;
        int degree = getDegree();
        for (double coefficient : coefficients) {
            result = result + coefficient * Math.pow(x, degree);
            degree--;
        }
            
        return result;
    }
    
    public String toString() {
        int degree = getDegree();
        
        if (degree ==  0) 
            return "" + coefficients.get(0);
        if (degree ==  1) 
            return coefficients.get(0) + "x + " + coefficients.get(1);
        
        StringBuilder equation = new StringBuilder();
        for (double coefficient : coefficients) {
            if (coefficient == 0)
                continue;
            
            if (coefficient > 0) {
                equation.append(" + ");
                equation.append(coefficient);
            }
            else {
                equation.append(" � ");
                equation.append(-coefficient);
            }
            
            if (degree == 1) 
                equation.append("x");
            else if (degree > 1) {
                equation.append("x^");
                equation.append(degree);
            }
            
            degree--;
        } 
        String result = equation.toString();
        if (result.startsWith(" + ")) {
            result = result.substring(3);
        }
        
        return result;
    }
    
    public Polynomial differentiate() {
        List<Double> derivCoefficients = new ArrayList<>();
        int degree = getDegree();
        for (double coefficient : coefficients) {
            if (degree != 0)
                derivCoefficients.add(coefficient * degree);
        }
        
        return new Polynomial(derivCoefficients);
    }
}
