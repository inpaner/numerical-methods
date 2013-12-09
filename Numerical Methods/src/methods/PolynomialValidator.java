package methods;

public class PolynomialValidator {
    public static boolean isEquationValid(String equation) {
        boolean isValid = true;
        
        try {
            String[] splitEquation = equation.split(" ");
            
            for (String singleValue : splitEquation) {
                Double.valueOf(singleValue);
            }
        }
        catch (NumberFormatException ex) {
            isValid = false;
        }
        
        return isValid;
    }
}
