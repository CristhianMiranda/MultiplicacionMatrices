package proyecto.multiplicacionmatrices.clases;

import java.awt.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

public class Grafica extends JFrame {

    public Grafica() {
        super("Grafica de una funcion");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear datos de la función
        XYSeries datos = new XYSeries("y = 2x + 1");
        for (int i = -10; i <= 10; i++) {
            datos.add(i, 2 * i + 1);
        }

        // Crear gráfico
        XYSeriesCollection dataset = new XYSeriesCollection(datos);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "y = 2x + 1", "x", "y",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        // Mostrar gráfico en ventana
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public static void main(String[] args) {
        Grafica grafica = new Grafica();
        grafica.setVisible(true);
    }

}
