package  ui;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import methods.Line;
import methods.Polynomial;

@SuppressWarnings("serial")
public class GraphPanel extends JPanel {
    private final int GRAPH_STEPS = 100;
    private Polynomial polynomial;
    private XYSeriesCollection dataset;
    private double inputLowerBound;
    private double inputUpperBound;

    GraphPanel() {
        dataset = new XYSeriesCollection();
        
        JFreeChart chart = ChartFactory.createXYLineChart("Polynomial",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        ChartPanel panel = new ChartPanel(chart);
        add(panel, BorderLayout.CENTER);    
    }
    
    void updatePolynomial(Polynomial polynomial, double lowerBound, double upperBound) {
        assert lowerBound <= upperBound : "lower bound > upper bound";
        this.polynomial = polynomial;
        inputLowerBound = lowerBound;
        inputUpperBound = upperBound;
        
        dataset.removeAllSeries();
        XYSeries polynomialSeries = createPolynomialSeries(polynomial, lowerBound, upperBound);
        
        dataset.addSeries(polynomialSeries);
    }
    
    private XYSeries createPolynomialSeries(Polynomial polynomial, double lowerBound, double upperBound) {
        XYSeries result = new XYSeries(polynomial.toString());
        
        double range = upperBound - lowerBound;
        double stepSize = range / GRAPH_STEPS; 
        
        for (double x = lowerBound; x <= upperBound; x += stepSize) {
            double y = polynomial.evaluate(x);
            result.add(x, y);
        }
        
        return result;
    }
    
    void updateLine(Line line) {
        XYSeries lineSeries = new XYSeries("");
        lineSeries.add(line.getX0(), line.getY0());
        lineSeries.add(line.getX1(), line.getY1());
        
        System.out.println("inputlb: " + inputLowerBound + " ,x0: " + line.getX0());
        
        double chartLowerBound = inputLowerBound;
        if (chartLowerBound > Math.min(line.getX0(), line.getX1())) {
            chartLowerBound = Math.min(line.getX0(), line.getX1());
        }
        double chartUpperBound = inputUpperBound;
        if (chartUpperBound < Math.max(line.getX0(), line.getX1())) {
            chartUpperBound = Math.max(line.getX0(), line.getX1());
        }
        
        XYSeries polynomialSeries = createPolynomialSeries(
                polynomial, chartLowerBound, chartUpperBound);
        
        dataset.removeAllSeries();
        dataset.addSeries(polynomialSeries);
        dataset.addSeries(lineSeries);
    }
}
