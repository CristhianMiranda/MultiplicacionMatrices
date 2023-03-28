package proyecto.multiplicacionmatrices.clases;




import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import javax.swing.JFrame;

public class BarChartExample extends JFrame {







    public BarChartExample(){
        super("Diagrama de Barras de Promedio de TE(ns)");

        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 500));

        setContentPane(chartPanel);
    }



    public static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Agregar los datos a la tabla
        //dataset.addValue(promedio, "Serie 1", id+"");
        dataset.addValue(25, "Serie 2", "Categoria 4");
        dataset.addValue(30, "Serie 3", "Categoría 3");

        return dataset;
    }

    public static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Tiempo promedio de ejecucion", // Título del gráfico
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

