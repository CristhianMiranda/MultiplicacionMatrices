package proyecto.multiplicacionmatrices.algoritmosimplementacion;

import proyecto.multiplicacionmatrices.algoritmosinterfaces._10_StrassenWinograd;

public class _10_StrassenWinogradImpl implements _10_StrassenWinograd {
    @Override
    public void algoritmoStrassenWinograd(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        int MaxSize, k, m, NewSize, i, j;
        MaxSize = max(N,P);
        MaxSize = max(MaxSize,M);
        if ( MaxSize < 16){
            MaxSize = 16; // otherwise it is not possible to compute k
        }
        k = (int) Math.floor(Math.log(MaxSize)/Math.log(2)) - 4;
        m = (int) Math.floor(MaxSize * Math.pow(2,-k)) + 1;
        NewSize = m * (int) Math.pow(2,k);


        // add zero rows and columns to use Strassens algorithm
        double[][] NewA = new double[NewSize][];
        double[][] NewB = new double[NewSize][];
        double[][] AuxResult = new double[NewSize][];
        for (i = 0; i < NewSize; i++){
            NewA[i] = new double[NewSize];
            NewB[i] = new double[NewSize];
            AuxResult[i] = new double[NewSize];
        }

        for( i = 0; i < NewSize; i++){
            for( j = 0; j < NewSize; j++){
                NewA[i][j] = 0.0;
                NewB[i][j] = 0.0;
            }
        }
        for( i = 0; i < N; i++){
            for( j = 0; j < P; j++){
                NewA[i][j] = matrizA[i][j];
            }
        }
        for( i = 0; i < P; i++){
            for( j = 0; j < M; j++){
                NewB[i][j] = matrizB[i][j];
            }
        }

        StrassenWinogradStep(NewA, NewB, AuxResult, NewSize, m);

        // extract the result
        for( i = 0; i < N; i++){
            for( j = 0; j < M; j++){
                matrizC[i][j] = AuxResult[i][j];
            }
        }
    }

    private void StrassenWinogradStep(double[][] A, double[][] B, double[][] Result, int N, int m) {
        int i, j, NewSize;

        if ((N % 2 == 0) && (N > m)) { // recursive use of StrassenNaivStep
            NewSize = N / 2;

            // decompose A and B
            // create ResultPart, Aux1,...,Aux7 and Helper1, Helper2
            double[][] A1 = new double[NewSize][];
            double[][] A2 = new double[NewSize][];
            double[][] B1 = new double[NewSize][];
            double[][] B2 = new double[NewSize][];

            double[][] A11 = new double[NewSize][];
            double[][] A12 = new double[NewSize][];
            double[][] A21 = new double[NewSize][];
            double[][] A22 = new double[NewSize][];
            double[][] B11 = new double[NewSize][];
            double[][] B12 = new double[NewSize][];
            double[][] B21 = new double[NewSize][];
            double[][] B22 = new double[NewSize][];

            double[][] ResultPart11 = new double[NewSize][];
            double[][] ResultPart12 = new double[NewSize][];
            double[][] ResultPart21 = new double[NewSize][];
            double[][] ResultPart22 = new double[NewSize][];

            double[][] Helper1 = new double[NewSize][];
            double[][] Helper2 = new double[NewSize][];

            double[][] Aux1 = new double[NewSize][];
            double[][] Aux2 = new double[NewSize][];
            double[][] Aux3 = new double[NewSize][];
            double[][] Aux4 = new double[NewSize][];
            double[][] Aux5 = new double[NewSize][];
            double[][] Aux6 = new double[NewSize][];
            double[][] Aux7 = new double[NewSize][];
            double[][] Aux8 = new double[NewSize][];
            double[][] Aux9 = new double[NewSize][];

            for (i = 0; i < NewSize; i++) {
                A1[i] = new double[NewSize];
                A2[i] = new double[NewSize];
                B1[i] = new double[NewSize];
                B2[i] = new double[NewSize];
                A11[i] = new double[NewSize];
                A12[i] = new double[NewSize];
                A21[i] = new double[NewSize];
                A22[i] = new double[NewSize];
                B11[i] = new double[NewSize];
                B12[i] = new double[NewSize];
                B21[i] = new double[NewSize];
                B22[i] = new double[NewSize];

                ResultPart11[i] = new double[NewSize];
                ResultPart12[i] = new double[NewSize];
                ResultPart21[i] = new double[NewSize];
                ResultPart22[i] = new double[NewSize];

                Helper1[i] = new double[NewSize];
                Helper2[i] = new double[NewSize];

                Aux1[i] = new double[NewSize];
                Aux2[i] = new double[NewSize];
                Aux3[i] = new double[NewSize];
                Aux4[i] = new double[NewSize];
                Aux5[i] = new double[NewSize];
                Aux6[i] = new double[NewSize];
                Aux7[i] = new double[NewSize];
                Aux8[i] = new double[NewSize];
                Aux9[i] = new double[NewSize];
            }

            // fill new matrices
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    A11[i][j] = A[i][j];
                }
            }
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    A12[i][j] = A[i][NewSize + j];
                }
            }
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    A21[i][j] = A[NewSize + i][j];
                }
            }
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    A22[i][j] = A[NewSize + i][NewSize + j];
                }
            }
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    B11[i][j] = B[i][j];
                }
            }
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    B12[i][j] = B[i][NewSize + j];
                }
            }
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    B21[i][j] = B[NewSize + i][j];
                }
            }
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    B22[i][j] = B[NewSize + i][NewSize + j];
                }
            }

            // computing the 4 + 9 aux. variables
            Minus(A11, A21, A1, NewSize, NewSize);
            Minus(A22, A1, A2, NewSize, NewSize);
            Minus(B22, B12, B1, NewSize, NewSize);
            Plus(B1, B11, B2, NewSize, NewSize);

            StrassenWinogradStep(A11, B11, Aux1, NewSize, m);
            StrassenWinogradStep(A12, B21, Aux2, NewSize, m);
            StrassenWinogradStep(A2, B2, Aux3, NewSize, m);
            Plus(A21, A22, Helper1, NewSize, NewSize);
            Minus(B12, B11, Helper2, NewSize, NewSize);
            StrassenWinogradStep(Helper1, Helper2, Aux4, NewSize, m);
            StrassenWinogradStep(A1, B1, Aux5, NewSize, m);
            Minus(A12, A2, Helper1, NewSize, NewSize);
            StrassenWinogradStep(Helper1, B22, Aux6, NewSize, m);
            Minus(B21, B2, Helper1, NewSize, NewSize);
            StrassenWinogradStep(A22, Helper1, Aux7, NewSize, m);
            Plus(Aux1, Aux3, Aux8, NewSize, NewSize);
            Plus(Aux8, Aux4, Aux9, NewSize, NewSize);

            // computing the four parts of the result
            Plus(Aux1, Aux2, ResultPart11, NewSize, NewSize);
            Plus(Aux9, Aux6, ResultPart12, NewSize, NewSize);
            Plus(Aux8, Aux5, Helper1, NewSize, NewSize);
            Plus(Helper1, Aux7, ResultPart21, NewSize, NewSize);
            Plus(Aux9, Aux5, ResultPart22, NewSize, NewSize);

            // store results in the "result matrix"
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    Result[i][j] = ResultPart11[i][j];
                }
            }
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    Result[i][NewSize + j] = ResultPart12[i][j];
                }
            }
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    Result[NewSize + i][j] = ResultPart21[i][j];
                }
            }
            for (i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    Result[NewSize + i][NewSize + j] = ResultPart22[i][j];
                }
            }

            // free helper variables
            A1 = null;
            A2 = null;
            B1 = null;
            B2 = null;

            A11 = null;
            A12 = null;
            A21 = null;
            A22 = null;

            B11 = null;
            B12 = null;
            B21 = null;
            B22 = null;

            ResultPart11 = null;
            ResultPart12 = null;
            ResultPart21 = null;
            ResultPart22 = null;

            Helper1 = null;
            Helper2 = null;

            Aux1 = null;
            Aux2 = null;
            Aux3 = null;
            Aux4 = null;
            Aux5 = null;
            Aux6 = null;
            Aux7 = null;

        } else {
            // use naiv algorithm
            algoritmoNaivStandard(A, B, Result, N, N, N);
        }
    }
    public static void algoritmoNaivStandard(double[][] matrizA,double[][] matrizB,double[][] matrizC,int n, int p, int m)
    {
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
    public int max (int N, int P){
        if (N < P){
            return P;
        } else {
            return N;
        }

    }


    private void Minus(double[][] A, double[][] B, double[][] Result, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Result[i][j] = A[i][j] - B[i][j];
            }
        }
    }

    private void Plus(double[][] A, double[][] B, double[][] Result, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Result[i][j] = A[i][j] + B[i][j];
            }
        }
    }
}
