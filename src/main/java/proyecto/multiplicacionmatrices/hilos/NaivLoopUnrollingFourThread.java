package proyecto.multiplicacionmatrices.hilos;

import proyecto.multiplicacionmatrices.clases.Algoritmos;

public class NaivLoopUnrollingFourThread extends Thread {
    private final double[][] matrizA;
    private final double[][] matrizB;
    private final double[][] matrizC;
    private final int size;

    public NaivLoopUnrollingFourThread(double[][] matrizA, double[][] matrizB, double[][] matrizC, int size, int i, int size1) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.matrizC = matrizC;
        this.size = size;
    }

    @Override
    public void run() {
        Algoritmos.naivLoopUnrollingFour(matrizA, matrizB, matrizC, size, size, size);
    }
}