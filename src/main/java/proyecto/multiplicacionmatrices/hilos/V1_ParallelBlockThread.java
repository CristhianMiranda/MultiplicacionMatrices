package proyecto.multiplicacionmatrices.hilos;

import proyecto.multiplicacionmatrices.clases.Algoritmos;

public class V1_ParallelBlockThread extends Thread {
    private double[][] matrizA;
    private double[][] matrizB;
    private int size;
    private int bsize;

    public V1_ParallelBlockThread(double[][] matrizA, double[][] matrizB, int size, int bsize) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.size = size;
        this.bsize = bsize;
    }

    public void run() {
        Algoritmos.v1_parallelBlock(matrizA, matrizB, size, bsize);
    }
}