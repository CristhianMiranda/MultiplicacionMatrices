package proyecto.multiplicacionmatrices.hilos;

import proyecto.multiplicacionmatrices.clases.Algoritmos;

public class NaivOnArrayThread extends Thread {

    private double[][] matrizA;
    private double[][] matrizB;
    private double[][] matrizC;
    private int size;

    public NaivOnArrayThread(double[][] matrizA, double[][] matrizB, double[][] matrizC, int size, int i, int size1) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.matrizC = matrizC;
        this.size = size;
    }



    @Override
    public void run() {
        Algoritmos.naivOnArray(matrizA, matrizB, matrizC, size, size, size);
    }
}