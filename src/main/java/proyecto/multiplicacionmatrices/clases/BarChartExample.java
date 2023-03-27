package proyecto.multiplicacionmatrices.clases;




import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class BarChartExample extends JFrame {

    public BarChartExample() {
        super("Diagrama de Barras de Promedio De TE(ns)");

        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 350));

        setContentPane(chartPanel);
    }

    public static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Agregar los datos a la tabla
        dataset.addValue(10, "Serie 1", "Categoría 1");
        dataset.addValue(20, "Serie 2", "Categoría 2");
        dataset.addValue(30, "Serie 3", "Categoría 3");

        return dataset;
    }

    public static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Tiempo de ejecucion", // Título del gráfico
                "Eje X",              // Etiqueta del eje X
                "Eje Y",              // Etiqueta del eje Y
                dataset,              // Datos para el gráfico
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true,                 // Incluir leyenda
                true,                 // Incluir tooltips
                false                 // Incluir URLs
        );

        // Personalizar el gráfico
        chart.setBackgroundPaint(Color.white);

        return chart;
    }

    public static void main(String[] args) {
        BarChartExample demo = new BarChartExample();
        demo.pack();
        demo.setVisible(true);
    }
}

