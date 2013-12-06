package methods;

public class IterationChecker {
    private static int totalIterations = 20;
    private static boolean isActive = true;
    
    public static boolean isFinishedIterating(int iterations) {
        boolean isFinished = true; 
        if (IterationChecker.isActive) {
               isFinished = iterations >= totalIterations;  
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
