package proyecto.multiplicacionmatrices.clases;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class ProyeccionPlano  extends JFrame {

    private static final long serialVersionUID = 1L;

    public ProyeccionPlano() {
        super("Mi Grafica");

        initUI();
    }

    private void initUI() {

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Mi Grafica", // Título del gráfico
                "Eje X", // Etiqueta del eje X
                "Eje Y", // Etiqueta del eje Y
                dataset, // Datos del gráfico
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true, // Mostrar leyenda
                true, // Usar tooltips
                false // Usar urls
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();

        // Configurar el eje X para que sea un eje numérico
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setAutoRangeIncludesZero(false);

        // Configurar el eje Y para que sea un eje numérico
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);

        // Agregar el gráfico al panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);

        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private XYDataset createDataset() {

        // Crear una serie de datos
        XYSeries series = new XYSeries("Datos");

        // Agregar datos a la serie
        series.add(1.0, 1.0);
        series.add(2.0, 2.0);
        series.add(3.0, 3.0);
        series.add(4.0, 4.0);
        series.add(5.0, 5.0);

        // Crear una colección de series de datos
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    public static void main(String[] args) {

        // Crear y mostrar la ventana de la gráfica
        ProyeccionPlano grafica = new ProyeccionPlano();
        grafica.setVisible(true);
    }
}