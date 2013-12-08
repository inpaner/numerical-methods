package ui;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import methods.Polynomial;

public class GraphPanel extends JPanel {
    private final int GRAPH_STEPS = 100;
    private Polynomial polynomial;
    private XYSeriesCollection dataset;

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        GraphPanel panel = new GraphPanel();
        Polynomial poly = new Polynomial(1, -6, 0, 0, 0);
        panel.updateGraph(poly, 0, 6);
        frame.setPanel(panel);
    }
    
    public GraphPanel() {
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
    
    public void updateGraph(Polynomial polynomial, int lowerBound, int upperBound) {
        assert lowerBound <= upperBound : "lower bound > upper bound";
        
        this.polynomial = polynomial;
        dataset.removeAllSeries();
        XYSeries series = new XYSeries(polynomial.toString());
        
        double range = upperBound - lowerBound;
        double stepSize = range / GRAPH_STEPS; 
        
        for (double x = lowerBound; x <= upperBound; x += stepSize) {
            double y = polynomial.evaluate(x);
            series.add(x, y);
            System.out.println(x + "," + y);
        }
        dataset.addSeries(series);
        System.out.println(polynomial);
    }
}
