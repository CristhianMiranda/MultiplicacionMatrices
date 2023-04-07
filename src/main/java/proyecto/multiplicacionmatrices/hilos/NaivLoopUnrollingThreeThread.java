package proyecto.multiplicacionmatrices.hilos;

import proyecto.multiplicacionmatrices.clases.Algoritmos;

public class NaivLoopUnrollingThreeThread extends Thread {

    private double[][] matrizA;
    private double[][] matrizB;
    private double[][] matrizC;
    private int size;

    public NaivLoopUnrollingThreeThread(double[][] matrizA, double[][] matrizB, double[][] matrizC, int size, int i, int size1) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.matrizC = matrizC;
        this.size = size;
    }

    @Override
    public void run() {
        Algoritmos.naivLoopUnrollingThree(matrizA, matrizB, matrizC, size, size, size);
    }
}