package proyecto.multiplicacionmatrices.algoritmosimplementacion;

import proyecto.multiplicacionmatrices.algoritmosinterfaces.NaivLoopUnrollingThree;

public class NaivLoopUnrollingThreeImpl implements NaivLoopUnrollingThree {
    @Override
    public void algoritmoNaivLoopUnrollingThree(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        int i, j, k;
        double aux;
        if (P % 3 == 0) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < P; k += 3) {
                        aux += matrizA[i][k]*matrizB[k][j] + matrizA[i][k+1]*matrizB[k+1][j] + matrizA[i][k+2]*matrizB[k+2][j];
                    }
                    matrizC[i][j] = aux;
                }
            }
        } else if (P % 3 == 1) {
            int PP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 3) {
                        aux += matrizA[i][k]*matrizB[k][j] + matrizA[i][k+1]*matrizB[k+1][j] + matrizA[i][k+2]*matrizB[k+2][j];
                    }
                    matrizC[i][j] = aux + matrizA[i][PP]*matrizB[PP][j];
                }
            }
        } else {
            int PP = P - 2;
            int PPP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 3) {
                        aux += matrizA[i][k]*matrizB[k][j] + matrizA[i][k+1]*matrizB[k+1][j] + matrizA[i][k+2]*matrizB[k+2][j];
                    }
                    matrizC[i][j] = aux + matrizA[i][PP]*matrizB[PP][j] + matrizA[i][PPP]*matrizB[PPP][j];
                }
            }
        }
    }
}
