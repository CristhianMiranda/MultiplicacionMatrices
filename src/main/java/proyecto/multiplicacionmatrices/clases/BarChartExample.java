package proyecto.multiplicacionmatrices.clases;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;

public class BarChartExample extends JFrame {

    private int[] ids;
    private double[] promedios;

    public BarChartExample(int[] ids, double[] promedios) {
        super("Diagrama de Barras de Promedio de TE(ns)");
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

        // Agregar los datos a la tabla
        for (int i = 0; i <= ids.length; i++) {
            switch (i)
            {
                case 0:
                    dataset.addValue(promedios[i], "Promedios", "NaivStandard");
                    break;

                case 1:
                    dataset.addValue(promedios[i], "Promedios", "NaivOnArray");
                    break;

                case 2:
                    dataset.addValue(promedios[i], "Promedios", "NaivKahan");
                    break;
                case 3:
                    dataset.addValue(promedios[i], "Promedios", "NaivLoopUnrollingTwo");
                    break;
                case 4:
                    dataset.addValue(promedios[i], "Promedios", "NaivLoopUnrollingThree");
                    break;
                case 5:
                    dataset.addValue(promedios[i], "Promedios", "NaivLoopUnrollingFour");
                    break;
                case 6:
                    dataset.addValue(promedios[i], "Promedios", "WinogradOriginal");
                    break;
                case 7:
                    dataset.addValue(promedios[i], "Promedios", "WinogradScaled");
                    break;
                case 8:
                    dataset.addValue(promedios[i], "Promedios", "StrassenNaiv");
                    break;
                case 9:
                    dataset.addValue(promedios[i], "Promedios", "StrassenWinograd");
                    break;
                case 10:
                    dataset.addValue(promedios[i], "Promedios", "V1_Sequential block");
                    break;
                case 11:
                    dataset.addValue(promedios[i], "Promedios", "V1_Parallel Block");
                    break;
                case 12:
                    dataset.addValue(promedios[i], "Promedios", "V2_Sequential block");
                    break;
                case 13:
                    dataset.addValue(promedios[i], "Promedios", "V2_Parallel Block");
                    break;
                case 14:
                    dataset.addValue(promedios[i], "Promedios", "V3_Sequential block");
                    break;
                case 15:
                    dataset.addValue(promedios[i], "Promedios", "V3_Parallel Block");
                    break;

            }

        }

        return dataset;
    }

    public JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Tiempo promedio de ejecucion (nanosegundos)", // Título del gráfico
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

   /* public static void main(String[] args) {
        int[] ids = {1, 2, 3, 4, 5};
        double[] promedios = {20.5, 18.2, 22.3, 25.0, 19.1};

        BarChartExample demo = new BarChartExample(ids, promedios);
    }*/
}
