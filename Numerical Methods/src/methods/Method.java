package methods;

public abstract class Method {
    private Polynomial polynomial;
    
    protected Method(Polynomial polynomial) {
        this.polynomial = polynomial;
    }
    
    protected boolean isFinished(double y, int iterations) {
        boolean result = false;
        
        if (AccuracyChecker.isAccurate(y)
                && IterationChecker.isFinishedIterating(iterations)) {
            result = true;
        }
                
        return result;
    }
}
