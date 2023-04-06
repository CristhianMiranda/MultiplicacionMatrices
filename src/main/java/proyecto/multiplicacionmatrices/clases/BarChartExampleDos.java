package proyecto.multiplicacionmatrices.clases;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BarChartExampleDos extends JFrame {

    private final ArrayList<String> ids;
    private double[] promedios;

    public BarChartExampleDos(ArrayList<String> ids, double[] promedios) {
        super("Diagrama de Barras En orden ascendente de los TE(ns)");
        this.ids = ids;
        this.promedios = promedios;

        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 1020));
        setContentPane(chartPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String id : ids) {

            System.out.println(id);
        }
        for (double promedio : promedios) {
            System.out.println(promedio);
        }
        // Agregar los datos a la tabla
        for (int i = 0; i < 16; i++) {

            dataset.addValue(promedios[i], "Algoritmos", ids.get(i));
            //System.out.println(i);

        }

        return dataset;
    }

    public JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Tiempo de ejecucion (nanosegundos) En el caso 12", // Título del gráfico
                "ID",              // Etiqueta del eje X
                "TE(ns)",              // Etiqueta del eje Y
                dataset,              // Datos para el gráfico
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true,                 // Incluir leyenda
                true,                 // Incluir tooltips
                false                 // Incluir URLs
        );

        // Personalizar el gráfico
        chart.setBackgroundPaint(Color.white);
        chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 7));


        return chart;
    }

}
