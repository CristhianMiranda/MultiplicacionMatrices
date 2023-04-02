package proyecto.multiplicacionmatrices.algoritmosimplementacion;

import proyecto.multiplicacionmatrices.algoritmosinterfaces._14_IV_4ParallelBlock;

import java.util.stream.IntStream;

public class _14_IV_4ParallelBlockImpl implements _14_IV_4ParallelBlock {
    @Override
    public void algoritmoParallelBlockDos(double[][] matrizA, double[][] matrizB, int size, int bsize) {
        double[][] matrizC = new double[size][size];
        IntStream.range(0, size / bsize).parallel().forEach(i1 -> {
            for (int j1 = 0; j1 < size; j1 += bsize) {
                for (int k1 = 0; k1 < size; k1 += bsize) {
                    for (int i = i1 * bsize; i < (i1 + 1) * bsize && i < size; i++) {
                        for (int j = j1; j < j1 + bsize && j < size; j++) {
                            for (int k = k1; k < k1 + bsize && k < size; k++) {
                                matrizC[i][k] += matrizA[i][j] * matrizB[j][k];
                            }
                        }
                    }
                }
            }
        });
    }
}
