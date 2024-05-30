import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BruteForceTestGorsellestirme extends ApplicationFrame {

    public BruteForceTestGorsellestirme(String title) {
        super(title);

        JFreeChart barChart = createChart(loadTestResults("brute-force-test-results.csv"));

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset loadTestResults(String fileName) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                int attempt = Integer.parseInt(fields[0]);
                String result = fields[1];
                dataset.addValue(1, result, Integer.toString(attempt));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Brute Force Test Sonuçları",
                "Ataklar",
                "Sayılar",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED); // Failure
        renderer.setSeriesPaint(1, Color.BLUE); // CAPTCHA Displayed

        return barChart;
    }

    public static void main(String[] args) {
        BruteForceTestGorsellestirme chart = new BruteForceTestGorsellestirme("Brute Force Test Results Visualization");
        chart.pack();
        UIUtils.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
