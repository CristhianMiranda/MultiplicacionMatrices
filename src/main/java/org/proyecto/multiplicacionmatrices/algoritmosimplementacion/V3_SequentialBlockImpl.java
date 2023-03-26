package org.proyecto.multiplicacionmatrices.algoritmosimplementacion;

import org.proyecto.multiplicacionmatrices.algoritmosinterfaces.V3_SequentialBlock;

public class V3_SequentialBlockImpl implements V3_SequentialBlock {
    @Override
    public void algoritmoSequentialBlockTres(double[][] matrizA, double[][] matrizB, int size, int bsize) {
        double[][] matrizC = new double[size][size];
        for (int i1 = 0; i1 < size; i1 += bsize) {
            for (int j1 = 0; j1 < size; j1 += bsize) {
                for (int k1 = 0; k1 < size; k1 += bsize) {
                    for (int i = i1; i < i1 + bsize && i < size; i++) {
                        for (int j = j1; j < j1 + bsize && j < size; j++) {
                            for (int k = k1; k < k1 + bsize && k < size; k++) {
                                matrizC[k][i] += matrizA[k][j] * matrizB[j][i];
                            }
                        }
                    }
                }
            }
        }
    }
}
