package proyecto.multiplicacionmatrices;

import proyecto.multiplicacionmatrices.clases.Algoritmos;
import proyecto.multiplicacionmatrices.clases.BarChartExample;
import proyecto.multiplicacionmatrices.clases.Excel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proyecto.multiplicacionmatrices.clases.ExecutionTimeTracker;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class MultiplicacionMatricesApplication {

    public static void main(String[] args) {

        SpringApplication.run(MultiplicacionMatricesApplication.class, args);
        calculoTiempoEjecucionMultiplicacionMatrices();
    }

    public static void calculoTiempoEjecucionMultiplicacionMatrices() {

        int tamano = 1;
        eliminarArchivo();
        for (int i = 1; i <= 12; i++) {

            for (int j = 1; j <= 16; j++) {

                // Tamaño de las matrices
                int size = tamano * i * 2;

                double[][] matrizA = llenarMatrizAleatoria(size, size);


                double[][] matrizB = llenarMatrizAleatoria(size, size);

                escribirArchivoTxt(matrizString(convertirMatrizDoubleAEntera(matrizA)), matrizString(convertirMatrizDoubleAEntera(matrizB)));


                double[][] matrizC = new double[size][size];

                System.out.println("\n\nCaso" + i + ": / Tamaño:" + size + " / Algoritmo:" + j);

                tiempoRespuesta(matrizA, matrizB, matrizC, j, i, size);

                // Cálculo del tiempo promedio
                double averageTime = calculateAverageExecutionTime(j);

                guardarPromedioTiempoEjecucion(j,i,averageTime);

                Excel.escribirEnHojaEspecifica(String.valueOf(averageTime),j-1);

                if(i==12)
                System.out.println("Tiempo promedio de ejecución: " + averageTime + " ns");


                //Excel.leerDatosExcel();

            }
        }


        //eliminarArchivo();

    }

    public static void tiempoRespuesta(double[][] matrizA, double[][] matrizB, double[][] matrizC, int id, int caso, int size) {
        long startTime, endTime;
        int bsize = (int) Math.sqrt(size);
        //int prom1 = 0,prom2 = 0,prom3 = 0,prom4 = 0,prom5 = 0,prom6 = 0,prom7 = 0,prom8 = 0,prom9 = 0,prom10 = 0,prom11 = 0,prom12 = 0,prom13 = 0,prom14 = 0,prom15 = 0,prom16 = 0;
        switch (id) {
            case 1:
                startTime = System.nanoTime();
                Algoritmos.naivStandard(matrizA, matrizB, matrizC, size, size, size);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivStandard)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;

            case 2:
                startTime = System.nanoTime();
                Algoritmos.naivOnArray(matrizA, matrizB, matrizC, size, size, size);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivOnArray)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 3:
                startTime = System.nanoTime();
                Algoritmos.naivKhan(matrizA, matrizB, matrizC, size, size, size);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivKhan)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 4:
                startTime = System.nanoTime();
                Algoritmos.naivLoopUnrollingTwo(matrizA, matrizB, matrizC, size, size, size);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivLoopUnrollingTwo)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 5:
                startTime = System.nanoTime();
                Algoritmos.naivLoopUnrollingThree(matrizA, matrizB, matrizC, size, size, size);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivLoopUnrollingThree)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 6:
                startTime = System.nanoTime();
                Algoritmos.naivLoopUnrollingFour(matrizA, matrizB, matrizC, size, size, size);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivLoopUnrollingFour)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 7:
                startTime = System.nanoTime();
                Algoritmos.winogradOriginal(matrizA, matrizB, matrizC, size, size, size);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (WinogradOriginal)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 8:
                startTime = System.nanoTime();
                Algoritmos.winogradScaled(matrizA, matrizB, matrizC, size, size, size);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (WinogradScaled)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 9:
                startTime = System.nanoTime();
                Algoritmos.strassenNaiv(matrizA, matrizB, matrizC, size, size, size);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (StrassenNaiv)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 10:
                startTime = System.nanoTime();
                Algoritmos.strassenWinograd(matrizA, matrizB, matrizC, size, size, size);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (StrassenWinograd)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 11:
                startTime = System.nanoTime();
                Algoritmos.v1_sequentialBlock(matrizA, matrizB, size, bsize);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V1_SequentialBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 12:
                startTime = System.nanoTime();
                Algoritmos.v1_parallelBlock(matrizA, matrizB, size, bsize);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V1_ParallelBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 13:
                startTime = System.nanoTime();
                Algoritmos.v2_sequentialBlock(matrizA, matrizB, size, bsize);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V2_SequentialBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 14:
                startTime = System.nanoTime();
                Algoritmos.v2_parallelBlock(matrizA, matrizB, size, bsize);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V2_ParallelBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 15:
                startTime = System.nanoTime();
                Algoritmos.v3_sequentialBlock(matrizA, matrizB, size, bsize);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V3_SequentialBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 16:
                startTime = System.nanoTime();
                Algoritmos.v3_parallelBlock(matrizA, matrizB, size, bsize);
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V3_ParallelBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) + "");
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
        }

    }

    public static int[][] convertirMatrizDoubleAEntera(double[][] matrizDouble) {
        int filas = matrizDouble.length;
        int columnas = matrizDouble[0].length;
        int[][] matrizEntera = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizEntera[i][j] = (int) Math.round(matrizDouble[i][j]);
            }
        }

        return matrizEntera;
    }

    public static String matrizString(int[][] matriz) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(matriz[i][j]).append(" ");
            }
            sb.append("\n");
        }
        String matrizStr = sb.toString();
        return matrizStr;
    }


    private static void guardarPromedioTiempoEjecucion(int id,int caso,double averageTime)  {
        if(caso==12)
        {
            File file = new File("assets/promedio/promedio_times"+id+".txt");
            if (!file.exists()) {
                FileWriter writer = null;
                try {
                    writer = new FileWriter(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    writer.write(String.valueOf(averageTime));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void escribirArchivoTxt(String matrizA, String matrizB) {
        File file = new File("matricesGeneradas.txt");

        // Verificar si el archivo ya existe
        if (file.exists()) {
            try {
                // Crear un nuevo archivo
                FileWriter writer = new FileWriter(file);
                writer.write("MatrizA:\n" + "{\n" + matrizA + "}" + "\n" + "\n" + "MatrizB:\n" + "{\n" + matrizB + "}\n\n"/*+"MatrizC:\n"+"{\n"+matrizC+"}\n\n"*/);
                writer.close();
                //System.out.println("El archivo ha sido creado");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo no existe");
        }
    }


    public static double[][] llenarMatrizAleatoria(int filas, int columnas) {
        // Crea una nueva matriz bidimensional de tamaño filas x columnas.
        double[][] matriz = new double[filas][columnas];

        // Crea un nuevo objeto Random para generar valores aleatorios.
        Random random = new Random();

        // Recorre cada elemento de la matriz y asigna un valor entero aleatorio entre 1000 y 9000.
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(8001) + 1000;
            }
        }

        // Devuelve la matriz con valores aleatorios.
        return matriz;
    }

    public static void imprimirMatriz(double[][] matriz) {
        for (double[] fila : matriz) {
            for (double d : fila) {
                System.out.printf("%.1f\t", d);
            }
            System.out.println();
        }
    }

    public static void acumularValores(long time,String id)
    {
        try {
            FileWriter fileWriter = new FileWriter("assets/datos/execution_times"+id+".txt", true);
            fileWriter.write(time + ",");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static double calculateAverageExecutionTime(int i) {
        double sum = 0;
        int count = 0;
        //BarChartExample barChartExample;
        try {
            Scanner scanner = new Scanner(new File("assets/datos/execution_times"+i+".txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                sum += Double.parseDouble(scanner.next());
                count++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return count > 0 ? sum / count : 0;
    }

    public static void eliminarArchivo() {
        for (int i = 1; i <= 16; i++) {
            File file = new File("assets/datos/execution_times" + i + ".txt");
            File fileProm = new File("assets/promedio/promedio_times" + i + ".txt");
            if (file.exists()) {
                fileProm.delete();
                file.delete();
            }
        }


    }
}
