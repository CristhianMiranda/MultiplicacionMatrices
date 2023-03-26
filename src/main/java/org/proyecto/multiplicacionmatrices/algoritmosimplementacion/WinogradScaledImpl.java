package org.proyecto.multiplicacionmatrices.algoritmosimplementacion;

import org.proyecto.multiplicacionmatrices.algoritmosinterfaces.WinogradScaled;

public class WinogradScaledImpl implements WinogradScaled {

    @Override
    public void algoritmoWinogradScaled(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        int i, j;
        /* Create scaled copies of A and B */
        double[][] CopyA = new double[N][P];
        double[][] CopyB = new double[P][M];
        /* Scaling factors */
        double a = NormInf(A, N, P);
        double b = NormInf(B, P, M);
        double lambda = Math.floor(0.5 + Math.log(b/a)/Math.log(4));
        /* Scaling */
        MultiplyWithScalar(A, CopyA, N, P, Math.pow(2, lambda));
        MultiplyWithScalar(B, CopyB, P, M, Math.pow(2, -lambda));
        /* Using Winograd with scaled matrices */
        algoritmoWinogradOriginal(CopyA, CopyB, Result, N, P, M);
    }
    public void algoritmoWinogradOriginal(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        int i, j, k;
        double aux;
        int upsilon = P % 2;
        int gamma = P - upsilon;
        double[] y = new double[M];
        double[] z = new double[N];

        for (i = 0; i < M; i++) {
            aux = 0.0;
            for (j = 0; j < gamma; j += 2) {
                aux += A[i][j] * A[i][j+1];
            }
            y[i] = aux;
        }

        for (i = 0; i < N; i++) {
            aux = 0.0;
            for (j = 0; j < gamma; j += 2) {
                aux += B[j][i] * B[j+1][i];
            }
            z[i] = aux;
        }

        if (upsilon == 1) {
            /*
             * P is odd
             * The value A[i][P]*B[P][k] is missing in all auxiliary sums.
             */
            int PP = P - 1;
            for (i = 0; i < M; i++) {
                for (k = 0; k < N; k++) {
                    aux = 0.0;
                    for (j = 0; j < gamma; j += 2) {
                        aux += (A[i][j] + B[j+1][k]) * (A[i][j+1] + B[j][k]);
                    }
                    Result[i][k] = aux - y[i] - z[k] + A[i][PP] * B[PP][k];
                }
            }
        } else {
            /*
             * P is even
             * The result can be computed with the auxiliary sums.
             */
            for (i = 0; i < M; i++) {
                for (k = 0; k < N; k++) {
                    aux = 0.0;
                    for (j = 0; j < gamma; j += 2) {
                        aux += (A[i][j] + B[j+1][k]) * (A[i][j+1] + B[j][k]);
                    }
                    Result[i][k] = aux - y[i] - z[k];
                }
            }
        }

        // Liberar memoria
        y = null;
        z = null;
    }
    public static void MultiplyWithScalar(double[][] A, double[][] B, int N, int M, double scalar) {
        int i, j;
        for (i = 0; i < N; i++) {
            for (j = 0; j < M; j++) {
                B[i][j] = A[i][j] * scalar;
            }
        }
    }

    public static double NormInf(double[][] A, int N, int M) {
        int i, j;
        double max = Double.NEGATIVE_INFINITY;
        for (i = 0; i < N; i++) {
            double sum = 0.0;
            for (j = 0; j < M; j++) {
                sum += Math.abs(A[i][j]);
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
