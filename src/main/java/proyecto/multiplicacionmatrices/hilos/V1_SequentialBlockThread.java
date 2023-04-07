package proyecto.multiplicacionmatrices.hilos;

import proyecto.multiplicacionmatrices.clases.Algoritmos;

public class V1_SequentialBlockThread implements Runnable {
    private double[][] matrizA;
    private double[][] matrizB;
    private int size;
    private int bsize;

    public V1_SequentialBlockThread(double[][] matrizA, double[][] matrizB, int size, int bsize) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.size = size;
        this.bsize = bsize;
    }

    @Override
    public void run() {
        Algoritmos.v1_sequentialBlock(matrizA, matrizB, size, bsize);
    }
}