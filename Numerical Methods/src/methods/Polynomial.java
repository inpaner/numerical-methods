package methods;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private List<Double> coefficients;
        

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
    

    private Polynomial(List<Double> coefficients) {
        this.coefficients = coefficients;
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
    
    
    public Polynomial getDerivative() {
        List<Double> derivativeCoefficients = new ArrayList<>();
        int degree = getDegree();
        if (degree == 0) {
            derivativeCoefficients.add(0.0);
        }
        else {
            for (double coefficient : coefficients) {
                    if (degree != 0) {
                        derivativeCoefficients.add(coefficient * degree);
                    }
                    degree--;   
            }
        }
        return new Polynomial(derivativeCoefficients);
    }
}
