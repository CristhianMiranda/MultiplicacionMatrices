package org.proyecto.multiplicacionmatrices;

import org.proyecto.multiplicacionmatrices.clases.Algoritmos;
import org.proyecto.multiplicacionmatrices.clases.Excel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class MultiplicacionMatricesApplication {

    public static void main(String[] args) {

        SpringApplication.run(MultiplicacionMatricesApplication.class, args);
        calculoTiempoEjecucionMultiplicacionMatrices();
    }

    public static void calculoTiempoEjecucionMultiplicacionMatrices(){
        int i=1;
        int tamano = 1;



        while(i<=16) {

            // Tamaño de las matrices
            int size = tamano*i*2 ;

            //Raiz cuadrada del tamaño de las matrices
            int bsize = (int) Math.sqrt(size);

            double[][] matrizA = llenarMatrizAleatoria(size,size);


            double[][] matrizB = llenarMatrizAleatoria(size,size);
            escribirArchivoTxt(matrizString(convertirMatrizDoubleAEntera(matrizA)),matrizString(convertirMatrizDoubleAEntera(matrizB)));

            double[][] matrizC = new double[size][size];

            System.out.println("\n\nCaso"+i+": / Tamaño:"+ size);





            i++;
        }
    }
    //int id,int caso,String tamano,String tiempoRespuesta
    public static String tiempoRespuesta(double[][] matrizA,double[][] matrizB,double[][] matrizC,int id,int caso,String tamano,int... sizeUno)
    {
        long startTime, endTime;
        int size = sizeUno[0];


        startTime = System.nanoTime();
        Algoritmos.naivStandard(matrizA,matrizB,matrizC,size,size,size);
        endTime = System.nanoTime();
        System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (NaivStandard)");
        Excel.escribirExcel(id,caso,tamano,endTime+"");//int id,int caso,String tamano,String tiempoRespuesta
        return "";
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

    public static String matrizString(int[][] matriz)
    {
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



    public static void escribirArchivoTxt(String matrizA,String matrizB)
    {
        File file = new File("matricesGeneradas.txt");

        // Verificar si el archivo ya existe
        if (file.exists()) {
            try {
                // Crear un nuevo archivo
                FileWriter writer = new FileWriter(file);
                writer.write("MatrizA:\n"+"{\n"+matrizA+"}"+"\n"+"\n"+"MatrizB:\n"+"{\n"+matrizB+"}\n");
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





}
