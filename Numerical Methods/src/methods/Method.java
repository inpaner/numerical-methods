package methods;

import java.util.List;

public abstract class Method {
    
    protected boolean isFinished(double y, int iterations) {
        boolean result = false;
        
        if (AccuracyChecker.isAccurate(y)
                && IterationChecker.isFinishedIterating(iterations)) {
            result = true;
        }
                
        return result;
    }

    public abstract List<String> getColumnNames();
    public abstract List<List<Double>> getIterationValues();
    
}
