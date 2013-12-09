package methods;

import java.util.ArrayList;
import java.util.List;

public abstract class Method {
    private boolean isSolved = false;
    private List<List<Double>> iterationValues; 
    private int iterations;
    private Polynomial polynomial;
    
    protected Method(Polynomial polynomial) {
        this.polynomial = polynomial;
    }
    
    public List<List<Double>> getIterationValues() {
        if (!isSolved) {
            solveAndStoreValues();
        }
        return iterationValues;
    }
    
    public int getTotalIterations() {
        if (!isSolved) {
            solveAndStoreValues();
        }
        return iterations;
    }
    
    public double getFinalY() {
        if (!isSolved) {
            solveAndStoreValues();
        }
        return getY();
    }
    
    public Polynomial getPolynomial() {
        return polynomial;
    }
    
    public abstract List<String> getColumnNames();
    
    protected abstract void iterate();
    protected abstract void addIterationRow(List<List<Double>> iterationValues);
    protected abstract double getY();
    protected abstract Line getCustomIterationLine(List<Double> iterationRow);
    
    public Line getIterationLine(int iteration) {
        if (!isSolved) {
            solveAndStoreValues();
        }
        
        List<Double> iterationValue = iterationValues.get(iteration);
        return getCustomIterationLine(iterationValue);
    }
    
    protected boolean isFinished() {
        boolean result = false;
        
        if (AccuracyChecker.isAccurate(getY())
                || IterationChecker.isFinishedIterating(iterations)) {
            result = true;
        }
                
        return result;
    }

    private void solveAndStoreValues() {
        iterationValues = new ArrayList<>();
        iterations = 0; 
        addIterationRow(iterationValues);
        do {
            iterate();
            addIterationRow(iterationValues);
            iterations++;
        } while (!isFinished());
        isSolved = true;
    }

}
