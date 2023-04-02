package proyecto.multiplicacionmatrices.algoritmosimplementacion;

import proyecto.multiplicacionmatrices.algoritmosinterfaces._12_III_4ParallelBlock;

import java.util.Arrays;

public class _12_III_4ParallelBlockImpl implements _12_III_4ParallelBlock {
    @Override
    public void algoritmoParallelBlock(double[][] matrizA, double[][] matrizB, int size, int bsize) {
        double[][] matrizC = new double[size][size];
        Arrays.stream(new int[]{0}).parallel().forEach(i1 -> {
            for (i1 = 0; i1 <size; i1 += bsize) {
                for (int j1 = 0; j1 < size; j1 += bsize) {
                    for (int k1 = 0; k1 < size; k1 += bsize) {
                        for (int i = i1; i < i1 + bsize && i < size; i++) {
                            for (int j = j1; j < j1 + bsize && j < size; j++) {
                                for (int k = k1; k < k1 + bsize && k < size; k++) {

                                    matrizC[i][j] += matrizA[i][k] * matrizB[k][j];
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
