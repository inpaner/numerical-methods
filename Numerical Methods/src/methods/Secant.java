package methods;

public class Secant extends Method {
    private Polynomial function;
    private double lowerBound;
    private double upperBound;
    
    public static void main(String[] args) {
        Polynomial poly = new Polynomial(1, 0, -78.8);
        System.out.println(poly);
        Secant secant = new Secant(poly, 12, 10);
        secant.solve();
    }
    
    public Secant(Polynomial polynomial, int x0, int x1) {
        super(polynomial);
        function = polynomial;
        this.lowerBound = x0;
        this.upperBound = x1;
    }
    
    public void solve() {
        double x0 = lowerBound;
        double x1 = upperBound;
        double x2 = -1;
        int iterations = 0;
        double y0;
        do {
            y0 = function.evaluate(x0);
            double y1 = function.evaluate(x1);
            x2 = x1 - ( (y1 * (x0 - x1)) / (y0 - y1) );
            x0 = x1;
            x1 = x2;            
            iterations++;
            
        } while (!isFinished(y0, iterations));
    }
}
