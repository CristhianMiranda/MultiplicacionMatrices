package proyecto.multiplicacionmatrices.clases;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatsTable extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Generamos algunos datos de prueba
        List<Double> data = new ArrayList<>();
        data.add(3.0);
        data.add(4.0);
        data.add(5.0);
        data.add(8.0);
        data.add(11.0);
        data.add(13.0);

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
        root.getChildren().addAll(new Label("Estadísticas"), table);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Tabla de estadísticas");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
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
