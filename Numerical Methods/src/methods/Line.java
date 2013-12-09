package methods;

public class Line {
    private final double x0;
    private final double y0;
    private final double x1;
    private final double y1;
    
    Line(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public final double getX0() {
        return x0;
    }

    public final double getY0() {
        return y0;
    }

    public final double getX1() {
        return x1;
    }

    public final double getY1() {
        return y1;
    }
    
    
}
