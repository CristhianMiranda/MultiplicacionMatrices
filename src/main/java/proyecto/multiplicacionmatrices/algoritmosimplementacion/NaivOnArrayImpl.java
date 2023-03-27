package proyecto.multiplicacionmatrices.algoritmosimplementacion;

import proyecto.multiplicacionmatrices.algoritmosinterfaces.NaivOnArray;

public class NaivOnArrayImpl implements NaivOnArray {
    @Override
    public void algoritmoNaivOnArray(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrizC[i][j] = 0.0;
                for (int k = 0; k < P; k++) {
                    matrizC[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
    }
}
