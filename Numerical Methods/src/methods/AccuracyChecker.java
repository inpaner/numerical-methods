package methods;

public class AccuracyChecker {
    private static double accuracy = 0.001;
    private static boolean isActive = true;
    
    
    public static boolean isAccurate(double y) {
        boolean isAccurate = false; 
        if (AccuracyChecker.isActive) {
            isAccurate = Math.abs(y) < accuracy;   
        }
        return isAccurate;
    }
    
    
    public static void setAccuracy(double accuracy) {
        AccuracyChecker.accuracy = accuracy;
    }
    
    
    public static void enable() {
        isActive = true;
    }
    
    
    public static void disable() {
        isActive = false;
    }
}
