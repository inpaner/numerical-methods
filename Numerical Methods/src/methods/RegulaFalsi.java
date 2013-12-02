package methods;

public class RegulaFalsi {
    private Polynomial function;
    private double lowerBound;
    private double upperBound;
    
    public static void main(String[] args) {
        Polynomial poly = new Polynomial(1, 0, -78.8);
        System.out.println(poly);
        RegulaFalsi falsi = new RegulaFalsi(poly, 6, 12);
        falsi.solve();
    }
    
    public RegulaFalsi(Polynomial polynomial, double x0, double x1) {
        function = polynomial;
        this.lowerBound = x0;
        this.upperBound = x1;
    }
    
    public void solve() {
        double x0 = lowerBound;
        double x1 = upperBound;
        double y0 = function.evaluate(x0);
        double y1 = function.evaluate(x1);
        double x2 = -1;
        double y2 = -1;
        int iterations = 0;
        do {
            System.out.println(x0 + "\t" + x1);
            
            x2 = (x0 * y1 - x1 * y0) / (y1 - y0);
            y2 = function.evaluate(x2);
            
            if (y1 * y2 < 0) {
                x0 = x2;
            }
            else {
                x1 = x2;
            }
        
            iterations++;

            
        } while (iterations != 20);
        
    }
}
