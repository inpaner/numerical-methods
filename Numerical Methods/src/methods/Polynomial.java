package methods;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private List<Double> coefficients;
        
    public static void main(String[] args) {
        List<Double> c = new ArrayList<Double>();
        c.add(1.0);
        c.add(0.0);
        c.add(-78.8);
        Polynomial p = new Polynomial(c);
        System.out.println(p.toString());
        System.out.println(p.evaluate(6.0));
    }
    
    public Polynomial(double... coefficients) {
        this.coefficients = new ArrayList<>();
        for (double coefficient : coefficients) {
            this.coefficients.add(coefficient);
        }
    }
    
    public Polynomial(List<Double> coefficients) {
        this.coefficients = coefficients;
    }
    
    /** 
     * 
     * @param equation  A validated equation string.
     */
    public Polynomial(String equation) {
        coefficients = new ArrayList<>();
        
        String[] splitEquation = equation.split(" ");       
        for (String singleValue : splitEquation) {
            double convertedValue = Double.valueOf(singleValue);
            coefficients.add(convertedValue);
        }
    }
    
    public int getDegree() {
        return coefficients.size() - 1;
    }
    
    public List<Double> getCoefficients() {
        return coefficients;
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
            if (coefficient == 0) {
                degree--;
                continue;
            }
                
            if (coefficient > 0) {
                equation.append(" + ");
                equation.append(coefficient);
            }
            else {
                equation.append(" – ");
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
