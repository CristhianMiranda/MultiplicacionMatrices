package proyecto.multiplicacionmatrices.algoritmosimplementacion;

import proyecto.multiplicacionmatrices.algoritmosinterfaces.NaivKahan;

public class NaivKahanImpl implements NaivKahan {
    @Override
    public void algoritmoNaivKhan(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        double t, sum, err;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum = 0.0;
                err = 0.0;
                for (int k = 0; k < P; k++) {
                    err = err + matrizA[i][k] * matrizB[k][j];
                    t = sum + err;
                    err = (sum - t) + err;
                    sum = t;
                }
                matrizC[i][j] = sum;
            }
        }
    }
}
