package proyecto.multiplicacionmatrices.clases;

import proyecto.multiplicacionmatrices.MultiplicacionMatricesApplication;
import proyecto.multiplicacionmatrices.StatsTable;

public class Controlador {

    public static void main(String[] args) {
        System.out.println("Ejecutando principal A");
        MultiplicacionMatricesApplication.main(new String[] {});

        System.out.println("Ejecutando principal B");
        StatsTable.main(new String[] {});
    }
}