import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.general.DefaultPieDataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdresDefteriTestSonuc extends ApplicationFrame {

    public AdresDefteriTestSonuc (String title) {
        super(title);
        JFreeChart pieChart = createChart(loadTestResults());
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultPieDataset loadTestResults() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try (BufferedReader br = new BufferedReader(new FileReader("test-results.csv"))) {
            String line;
            int successCount = 0;
            int failureCount = 0;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[1].equals("SUCCESS")) {
                    successCount++;
                } else if (fields[1].equals("FAILURE")) {
                    failureCount++;
                }
            }
            dataset.setValue("Success", successCount);
            dataset.setValue("Failure", failureCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    private JFreeChart createChart(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Test Results",
                dataset,
                true,
                true,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Success", new java.awt.Color(0, 255, 0));
        plot.setSectionPaint("Failure", new java.awt.Color(255, 0, 0));
        return chart;
    }

    public static void main(String[] args) {
        AdresDefteriTestSonuc chart = new AdresDefteriTestSonuc("Test Results Visualization");
        chart.pack();
        UIUtils.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
