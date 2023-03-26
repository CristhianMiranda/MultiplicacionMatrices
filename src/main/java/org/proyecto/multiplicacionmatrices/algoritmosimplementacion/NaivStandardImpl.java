package org.proyecto.multiplicacionmatrices.algoritmosimplementacion;

import org.proyecto.multiplicacionmatrices.algoritmosinterfaces.NaivStandard;

public class NaivStandardImpl implements NaivStandard {
    @Override
    public void algoritmoNaivStandard(double[][] matrizA, double[][] matrizB, double[][] matrizC, int n, int p, int m) {
        double aux;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                aux = 0.0;
                for (int k = 0; k < p; k++) {
                    aux += matrizA[i][k] * matrizB[k][j];
                }
                matrizC[i][j] = aux;
            }
        }
    }
}
