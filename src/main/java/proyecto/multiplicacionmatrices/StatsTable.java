package proyecto.multiplicacionmatrices;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatsTable extends Application {

    private List<Double> data;
    private int currentDataIndex = 1;
    private TableView<StatRow> table;
    private ObservableList<StatRow> stats;
    private static final int NUM_GRAPHS = 16;
    private static Image icono = new Image("file:ImagenLogoUniquindio.png");

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Generamos algunos datos de prueba
        // data = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            List<Double> data = readDataFromFile("assets/datos/execution_times"+i+".txt");
            // Calculamos los valores estadísticos
            double media = data.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            double rango = Collections.max(data) - Collections.min(data);
            double varianza = data.stream().mapToDouble(d -> Math.pow(d - media, 2)).sum() / (data.size() - 1);
            double desviacionEstandar = Math.sqrt(varianza);

            // Creamos una lista observable para los datos de la tabla
            ObservableList<StatRow> stats = FXCollections.observableArrayList(
                    new StatRow("Media", String.format("%.2f", media)),
                    new StatRow("Rango", String.format("%.2f", rango)),
                    new StatRow("Varianza", String.format("%.2f", varianza)),
                    new StatRow("Desviación estándar", String.format("%.2f", desviacionEstandar))
            );

            // Creamos la tabla
            TableView<StatRow> table = new TableView<>(stats);

            TableColumn<StatRow, String> statCol = new TableColumn<>("Estadística");
            statCol.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<StatRow, String> valueCol = new TableColumn<>("Valor");
            valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

            table.getColumns().addAll(statCol, valueCol);

            // Creamos el layout y lo mostramos
            VBox root = new VBox();
            root.setPadding(new Insets(10));
            root.setSpacing(10);


            Scene scene = new Scene(root, 312, 170);
            Stage stage = new Stage();
            stage.getIcons().add(icono);

            switch (i-1)
            {
                    case 0:
                        root.getChildren().addAll(new Label("NaivStandard"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;

                    case 1:
                        root.getChildren().addAll(new Label("NaivOnArray"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;

                    case 2:
                        root.getChildren().addAll(new Label("NaivKahan"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 3:
                        root.getChildren().addAll(new Label("NaivLoopUnrollingTwo"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 4:
                        root.getChildren().addAll(new Label("NaivLoopUnrollingThree"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 5:
                        root.getChildren().addAll(new Label("NaivLoopUnrollingFour"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 6:
                        root.getChildren().addAll(new Label("WinogradOriginal"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 7:
                        root.getChildren().addAll(new Label("WinogradScaled"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 8:
                        root.getChildren().addAll(new Label("StrassenNaiv"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 9:
                        root.getChildren().addAll(new Label("StrassenWinograd"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 10:
                        root.getChildren().addAll(new Label("V1_Sequential block"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 11:
                        root.getChildren().addAll(new Label("V1_Parallel Block"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 12:
                        root.getChildren().addAll(new Label("V2_Sequential block"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 13:
                        root.getChildren().addAll(new Label("V2_Parallel Block"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 14:
                        root.getChildren().addAll(new Label("V3_Sequential block"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;
                    case 15:
                        root.getChildren().addAll(new Label("V3_Parallel Block"), table);
                        stage.setTitle("Algoritmo " + i);
                        break;



            }

            //root.getChildren().addAll(new Label("Estadísticas"), table);


            //stage.setTitle("Tabla de estadísticas " + i);
            stage.setScene(scene);
            stage.show();
        }

    }

    public static List<Double> readDataFromFile(String filename) {
        List<Double> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    data.add(Double.parseDouble(value));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + filename + ": " + e.getMessage());
        }
        return data;
    }

    public static void main(String[] args) {

        //Platform.setImplicitExit(false);

        System.out.println("Ejecutando principal A");
        MultiplicacionMatricesApplication.main(new String[] {});
        javafx.application.Application.launch(StatsTable.class, args);

    }


    // Clase auxiliar para representar una fila de estadística en la tabla
    public static class StatRow {
        private String name;
        private String value;

        public StatRow(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }




    }

}
