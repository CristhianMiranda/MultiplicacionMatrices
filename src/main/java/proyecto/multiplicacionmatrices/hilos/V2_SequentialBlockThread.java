package proyecto.multiplicacionmatrices.hilos;

import proyecto.multiplicacionmatrices.clases.Algoritmos;

public class V2_SequentialBlockThread extends Thread {
    private double[][] matrizA;
    private double[][] matrizB;
    private int size;
    private int bsize;

    public V2_SequentialBlockThread(double[][] matrizA, double[][] matrizB, int size, int bsize) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.size = size;
        this.bsize = bsize;
    }

    @Override
    public void run() {
        Algoritmos.v2_sequentialBlock(matrizA, matrizB, size, bsize);
    }
}
