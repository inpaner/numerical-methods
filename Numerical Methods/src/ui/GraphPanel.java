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
    private XYSeriesCollection dataset;
    private XYSeries polynomialSeries;

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
        
        dataset.removeAllSeries();
        polynomialSeries = new XYSeries(polynomial.toString());
        
        double range = upperBound - lowerBound;
        double stepSize = range / GRAPH_STEPS; 
        
        for (double x = lowerBound; x <= upperBound; x += stepSize) {
            double y = polynomial.evaluate(x);
            polynomialSeries.add(x, y);
        }
        
        dataset.addSeries(polynomialSeries);
    }
    
    void updateLine(Line line) {
        XYSeries lineSeries = new XYSeries("");
        lineSeries.add(line.getX0(), line.getY0());
        lineSeries.add(line.getX1(), line.getY1());
        
        dataset.removeAllSeries();
        dataset.addSeries(polynomialSeries);
        dataset.addSeries(lineSeries);
    }
}
