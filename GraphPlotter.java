import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;

public class GraphPlotter {
    public static void main(String[] args) {
        // Create a dataset with sample data
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] data = { {1, 2, 3, 4, 5}, {5, 4, 3, 2, 1} };
        dataset.addSeries("Series 1", data);
        // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart",  // Title
                "X",           // X-axis Label
                "Y",           // Y-axis Label
                dataset       // Dataset
        );
        // Display the chart in a frame
        ChartFrame frame = new ChartFrame("Chart", chart);
        frame.pack();
        frame.setVisible(true);
    }
}