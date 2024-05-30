import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestSonucGorsellestirme extends ApplicationFrame {

    public TestSonucGorsellestirme(String title) {
        super(title);

        DefaultPieDataset pieDataset1 = loadTestResults("n11-test-results.csv");
        DefaultPieDataset pieDataset2 = loadTestResults("adres-defteri-test-results.csv");

        JFreeChart pieChart1 = createPieChart(pieDataset1, "GUI Test Sonuçları");
        JFreeChart pieChart2 = createPieChart(pieDataset2, "Birim Test Sonuçları");

        DefaultCategoryDataset barDataset1 = createCategoryDataset(pieDataset1);
        DefaultCategoryDataset barDataset2 = createCategoryDataset(pieDataset2);

        JFreeChart barChart1 = createBarChart(barDataset1, "GUI Test Sonuçları");
        JFreeChart barChart2 = createBarChart(barDataset2, "Birim Test Sonuçları");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        ChartPanel chartPanel1 = new ChartPanel(pieChart1);
        chartPanel1.setPreferredSize(new Dimension(800, 600));
        panel.add(chartPanel1);

        ChartPanel chartPanel2 = new ChartPanel(pieChart2);
        chartPanel2.setPreferredSize(new Dimension(800, 600));
        panel.add(chartPanel2);

        ChartPanel chartPanel3 = new ChartPanel(barChart1);
        chartPanel3.setPreferredSize(new Dimension(800, 600));
        panel.add(chartPanel3);

        ChartPanel chartPanel4 = new ChartPanel(barChart2);
        chartPanel4.setPreferredSize(new Dimension(800, 600));
        panel.add(chartPanel4);

        setContentPane(panel);
    }

    private DefaultPieDataset loadTestResults(String fileName) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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
            dataset.setValue("Başarılı", successCount);
            dataset.setValue("Hatalı", failureCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    private DefaultCategoryDataset createCategoryDataset(DefaultPieDataset pieDataset) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue((Number) pieDataset.getValue("Başarılı"), "Başarılı", "Sonuçlar");
        dataset.addValue((Number) pieDataset.getValue("Hatalı"), "Hatalı", "Sonuçlar");
        return dataset;
    }

    private JFreeChart createPieChart(DefaultPieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Başarılı", new Color(0, 0, 255)); // Mavi
        plot.setSectionPaint("Hatalı", new Color(255, 0, 0)); // Kırmızı

        return chart;
    }

    private JFreeChart createBarChart(DefaultCategoryDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                "Kategori",
                "Değer",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        return chart;
    }

    public static void main(String[] args) {
        TestSonucGorsellestirme chart = new TestSonucGorsellestirme("Test Results Visualization");
        chart.pack();
        UIUtils.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
