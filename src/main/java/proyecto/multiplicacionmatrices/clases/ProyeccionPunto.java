package proyecto.multiplicacionmatrices.clases;
import java.awt.*;
import javax.swing.*;

public class ProyeccionPunto extends JFrame {
    private JPanel panel;

    public ProyeccionPunto() {
        super("Proyección de un punto en un plano cartesiano");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear panel para dibujar
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar ejes x e y
                g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
                g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

                // Coordenadas del punto a proyectar
                int x = 50;
                int y = 100;

                // Dibujar punto
                g.fillOval(getWidth() / 2 + x, getHeight() / 2 - y, 10, 10);

                // Dibujar línea punteada vertical
                g.setColor(Color.BLUE);
                g.drawLine(getWidth() / 2 + x, getHeight() / 2, getWidth() / 2 + x, getHeight() / 2 - y);

                // Dibujar línea punteada horizontal
                g.drawLine(getWidth() / 2, getHeight() / 2 - y, getWidth() / 2 + x, getHeight() / 2 - y);
            }
        };

        // Agregar panel al frame
        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        ProyeccionPunto proyeccionPunto = new ProyeccionPunto();
    }
}
