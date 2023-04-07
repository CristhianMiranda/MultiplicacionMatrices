package proyecto.multiplicacionmatrices;


import proyecto.multiplicacionmatrices.clases.Algoritmos;
import proyecto.multiplicacionmatrices.clases.GraficaBarrasPromedio;
import proyecto.multiplicacionmatrices.clases.GraficaBarrasOrdenadas;
import proyecto.multiplicacionmatrices.clases.Excel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proyecto.multiplicacionmatrices.hilos.*;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

@SpringBootApplication
public class MultiplicacionMatricesApplication extends JFrame {

    public static void main(String[] args) {

        SpringApplication.run(MultiplicacionMatricesApplication.class, args);
        calculoTiempoEjecucionMultiplicacionMatrices();

    }

    public static void calculoTiempoEjecucionMultiplicacionMatrices() {

        int tamano = 60;
        eliminarArchivo();

        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= 16; j++) {
                // Tamaño de las matrices
                int size = tamano * i * 2;

                double[][] matrizA = llenarMatrizAleatoria(size, size);


                double[][] matrizB = llenarMatrizAleatoria(size, size);

                escribirArchivoTxt(matrizString(convertirMatrizDoubleAEntera(matrizA)), matrizString(convertirMatrizDoubleAEntera(matrizB)));

                double[][] matrizC = new double[size][size];

                if(j==1)
                {System.out.println("\n\nCaso" + i + ": / Tamaño:" + size /*+ " / Algoritmo:" + j*/);}

                tiempoRespuesta(matrizA, matrizB, matrizC, j, i, size);
                // Cálculo del tiempo promedio
                double averageTime = calculateAverageExecutionTime(j);

                // Formateo del tiempo promedio con 2 decimales
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                String formattedAverageTime = decimalFormat.format(averageTime);

                guardarPromedioTiempoEjecucion(j, i, Double.parseDouble(formattedAverageTime));
                Excel.escribirEnHojaEspecifica(Double.parseDouble(formattedAverageTime), j - 1);

                if (i == 12) {
                    System.out.println("Tiempo promedio de ejecución: " + formattedAverageTime + " ns\n");
                }



            }
        }
        try {
            guardarValoresFinales();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        graficaBarras();
        graficaBarrasOrden();


    }
    private static void graficaBarras() {
        unify();
        double[] promedios = readNumbersFromFile("assets/promedio/promedios.txt");
        GraficaBarrasPromedio graficaBarrasPromedio = new GraficaBarrasPromedio(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},promedios);
    }
    private static void graficaBarrasOrden() {
        //unify();
        double[] tiempos = readNumbersFromFile("assets/datos/execution_times_sorted.txt");
        GraficaBarrasOrdenadas barChartExample = new GraficaBarrasOrdenadas(readTxtFile("assets/datos/ids.txt"),tiempos);
    }

    public static void unify() {
        String folderPath = "assets/promedio/";
        String outputFilePath = folderPath + "promedios.txt";
        DecimalFormat df = new DecimalFormat("#.##");

        try (PrintWriter writer = new PrintWriter(outputFilePath)) {
            for (int i = 1; i <= 16; i++) {
                String inputFilePath = folderPath + "promedio_times" + i + ".txt";
                File inputFile = new File(inputFilePath);

                if (!inputFile.exists()) {
                    continue;
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        double value = Double.parseDouble(line.trim());
                        String formatted = df.format(value);
                        writer.print(formatted + ",");
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + inputFilePath);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + outputFilePath);
            e.printStackTrace();
        }
    }
    public static ArrayList<String> readTxtFile(String fileName) {
        ArrayList<String> data = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static double[] readNumbersFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.useDelimiter(",");

            // Contar la cantidad de números en el archivo
            int count = 0;
            while (scanner.hasNextDouble()) {
                count++;
                scanner.nextDouble();
            }

            // Crear el arreglo y volver a leer el archivo
            double[] numbers = new double[count];
            scanner = new Scanner(new File(filename));
            scanner.useDelimiter(",");
            for (int i = 0; i < count; i++) {
                numbers[i] = scanner.nextDouble();
            }

            scanner.close();

            return numbers;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + filename);
            return null;
        }
    }


    public static void tiempoRespuesta(double[][] matrizA, double[][] matrizB, double[][] matrizC, int id, int caso, int size) {
        long startTime, endTime;
        int bsize = (int) Math.sqrt(size);
        Thread t;

        switch (id) {
            case 1:
                startTime = System.nanoTime();
                t = new Thread(new NaivStandardThread(matrizA, matrizB, matrizC, size, size, size));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivStandard)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;

            case 2:
                startTime = System.nanoTime();
                t = new Thread(new NaivOnArrayThread(matrizA, matrizB, matrizC, size, size, size));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivOnArray)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 3:
                startTime = System.nanoTime();
                t = new Thread(new NaivKhanThread(matrizA, matrizB, matrizC, size, size, size));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivKhan)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 4:
                startTime = System.nanoTime();
                t = new Thread(new NaivLoopUnrollingTwoThread(matrizA, matrizB, matrizC, size, size, size));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivLoopUnrollingTwo)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 5:
                startTime = System.nanoTime();
                t = new Thread(new NaivLoopUnrollingThreeThread(matrizA, matrizB, matrizC, size, size, size));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivLoopUnrollingThree)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 6:
                startTime = System.nanoTime();
                t = new Thread(new NaivLoopUnrollingFourThread(matrizA, matrizB, matrizC, size, size, size));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivLoopUnrollingFour)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 7:
                startTime = System.nanoTime();
                t = new Thread(new WinogradOriginalThread(matrizA, matrizB, matrizC, size, size, size));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (WinogradOriginal)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 8:
                startTime = System.nanoTime();
                t = new Thread(new WinogradScaledThread(matrizA, matrizB, matrizC, size, size, size));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (WinogradScaled)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime));
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 9:
                startTime = System.nanoTime();
                t = new Thread(new StrassenNaivThread(matrizA, matrizB, matrizC, size, size, size));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (StrassenNaiv)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 10:
                startTime = System.nanoTime();
                t = new Thread(new StrassenWinogradThread(matrizA, matrizB, matrizC, size, size, size));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (StrassenWinograd)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 11:
                startTime = System.nanoTime();
                t = new Thread(new V1_SequentialBlockThread(matrizA, matrizB, size, bsize));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V1_SequentialBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime));
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 12:
                startTime = System.nanoTime();
                t = new Thread(new V1_ParallelBlockThread(matrizA, matrizB, size, bsize));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V1_ParallelBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime));
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 13:
                startTime = System.nanoTime();
                t = new Thread(new V2_SequentialBlockThread(matrizA, matrizB, size, bsize));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V2_SequentialBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 14:
                startTime = System.nanoTime();
                t = new Thread(new V2_ParallelBlockThread(matrizA, matrizB, size, bsize));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V2_ParallelBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime));
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 15:
                startTime = System.nanoTime();
                t = new Thread(new V3_SequentialBlockThread(matrizA, matrizB, size, bsize));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V3_SequentialBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 16:
                startTime = System.nanoTime();
                t = new Thread(new V3_ParallelBlockThread(matrizA, matrizB, size, bsize));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (V3_ParallelBlock)");
                Excel.escribirExcel(id, caso, size + "", (endTime - startTime));
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



    private static void guardarPromedioTiempoEjecucion(int id, int caso, double averageTime) {
        if (caso == 12) {
            File file = new File("assets/promedio/promedio_times" + id + ".txt");
            if (!file.exists()) {
                FileWriter writer = null;
                try {
                    writer = new FileWriter(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    DecimalFormat df = new DecimalFormat("#.##");
                    String formatted = df.format(averageTime);
                    writer.write(formatted);
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

    public static void guardarValoresFinales() throws IOException {
        List<List<Double>> executionTimesList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        int numFiles = 16;

        // Leer los archivos y extraer los valores y los ids
        for (int i = 1; i <= numFiles; i++) {
            String fileName = "assets/datos/execution_times" + i + ".txt";
            File file = new File(fileName);

            List<Double> executionTimes = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double lastValue = Double.parseDouble(values[values.length - 1]);
                executionTimes.add(lastValue);
            }

            executionTimesList.add(executionTimes);

            // Agregar el id correspondiente
            if (i == 1) {
                ids.add("NaivStandard");
            } else if (i == 2) {
                ids.add("NaivOnArray");
            } else if (i == 3) {
                ids.add("NaivKahan");
            } else if (i == 4) {
                ids.add("NaivLoopUnrollingTwo");
            } else if (i == 5) {
                ids.add("NaivLoopUnrollingThree");
            } else if (i == 6) {
                ids.add("NaivLoopUnrollingFour");
            } else if (i == 7) {
                ids.add("WinogradOriginal");
            } else if (i == 8) {
                ids.add("WinogradScaled");
            } else if (i == 9) {
                ids.add("StrassenNaiv");
            } else if (i == 10) {
                ids.add("StrassenWinograd");
            } else if (i == 11) {
                ids.add("V1_Sequential block");
            } else if (i == 12) {
                ids.add("V1_Parallel Block");
            } else if (i == 13) {
                ids.add("V2_Sequential block");
            } else if (i == 14) {
                ids.add("V2_Parallel Block");
            } else if (i == 15) {
                ids.add("V3_Sequential block");
            } else {
                ids.add("V3_Parallel Block");
            }

            br.close();
        }

        // Ordenar los valores de menor a mayor y crear los archivos de salida
        List<Double> sortedValues = new ArrayList<>();
        FileWriter valuesFileWriter = new FileWriter("assets/datos/execution_times_sorted.txt");
        FileWriter idsFileWriter = new FileWriter("assets/datos/ids.txt");

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        for (int i = 0; i < numFiles; i++) {
            sortedValues.addAll(executionTimesList.get(i));
        }

        Collections.sort(sortedValues);

        for (int i = 0; i < sortedValues.size(); i++) {
            double value = sortedValues.get(i);
            valuesFileWriter.write(decimalFormat.format(value) + ",");
            int index = -1;
            for (int j = 0; j < numFiles; j++) {
                if (executionTimesList.get(j).contains(value)) {
                    index = j;
                    break;
                }
            }
            idsFileWriter.write(ids.get(index) + "\n");
        }

        valuesFileWriter.close();
        idsFileWriter.close();
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
        try {
            Scanner scanner = new Scanner(new File("assets/datos/execution_times" + i + ".txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                sum += Double.parseDouble(scanner.next());
                count++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Formatear el resultado con dos decimales
        DecimalFormat df = new DecimalFormat("#.##");
        return count > 0 ? Double.parseDouble(df.format(sum / count)) : 0;
    }


    public static void eliminarArchivo() {
        File fileExc = new File("assets/datos/execution_times_sorted.txt");
        fileExc.delete();
        File id = new File("assets/datos/ids.txt");
        id.delete();
        for (int i = 1; i <= 16; i++) {
            File filePromds = new File("assets/promedio/promedios.txt");
            File file = new File("assets/datos/execution_times" + i + ".txt");
            File fileProm = new File("assets/promedio/promedio_times" + i + ".txt");

            if (file.exists()) {
                filePromds.delete();
                fileProm.delete();
                file.delete();
            }
        }


    }
}
