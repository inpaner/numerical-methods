package methods;

public class IterationChecker {
    private static int totalIterations = 50;
    private static final int divergenceLimit = 50;
    private static boolean isActive = true;
    
    public static boolean isFinishedIterating(int iterations) {
        boolean isFinished = false; 
        if (IterationChecker.isActive) {
           isFinished = iterations >= totalIterations;  
        }
        else {
            isFinished = iterations >= divergenceLimit;
        }
        return isFinished;
    }
    
    public static void setTotalIterations(int iterations) {
        IterationChecker.totalIterations = iterations;
    }
    
    public static void enable() {
        isActive = true;
    }
    
    public static void disable() {
        isActive = false;
    }
}
