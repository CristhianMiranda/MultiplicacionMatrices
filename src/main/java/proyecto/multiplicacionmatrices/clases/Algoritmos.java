package proyecto.multiplicacionmatrices.clases;

import proyecto.multiplicacionmatrices.algoritmosimplementacion.*;
import proyecto.multiplicacionmatrices.algoritmosinterfaces.*;

public class Algoritmos {

    public static void naivStandard(double[][] matrizA,double[][] matrizB,double[][] matrizC,int n, int p, int m){
        NaivStandard naivStandard = new NaivStandardImpl();
        naivStandard.algoritmoNaivStandard(matrizA,matrizB,matrizC,n,p,m);
    }

    public static void naivOnArray(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P , int M){
        NaivOnArray naivOnArray = new NaivOnArrayImpl();
        naivOnArray.algoritmoNaivOnArray(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void naivKhan(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        NaivKahan naivKahan = new NaivKahanImpl();
        naivKahan.algoritmoNaivKhan(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void naivLoopUnrollingTwo(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        NaivLoopUnrollingTwo naivLoopUnrollingTwo = new NaivLoopUnrollingTwoImpl();
        naivLoopUnrollingTwo.algoritmoNaivLoopUnrollingTwo(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void naivLoopUnrollingThree(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        NaivLoopUnrollingThree naivLoopUnrollingThree = new NaivLoopUnrollingThreeImpl();
        naivLoopUnrollingThree.algoritmoNaivLoopUnrollingThree(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void naivLoopUnrollingFour(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        NaivLoopUnrollingFour naivLoopUnrollingFour = new NaivLoopUnrollingFourImpl();
        naivLoopUnrollingFour.algoritmoNaivLoopUnrollingFour(A,B,Result,N,P,M);
    }

    public static void winogradOriginal(double[][] A, double[][] B, double[][] Result, int N, int P, int M){
        WinogradOriginal winogradOriginal = new WinogradOriginalImpl();
        winogradOriginal.algoritmoWinogradOriginal(A,B,Result,N,P,M);
    }

    public static void winogradScaled(double[][] A, double[][] B, double[][] Result, int N, int P, int M){
        WinogradScaled winogradScaled = new WinogradScaledImpl();
        winogradScaled.algoritmoWinogradScaled(A,B,Result,N,P,M);
    }

    public static void strassenNaiv(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        StrassenNaiv strassenNaiv = new StrassenNaivImpl();
        strassenNaiv.algoritmoStrassenNaiv(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void strassenWinograd(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        StrassenWinograd strassenWinograd = new StrassenWinogradImpl();
        strassenWinograd.algoritmoStrassenWinograd(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void v1_sequentialBlock(double[][]matrizA,double[][]matrizB,int size,int bsize){
        V1_SequentialBlock v1sequentialBlock = new V1_SequentialBlockImpl();
        v1sequentialBlock.algoritmoSequentialBlock(matrizA,matrizB,size,bsize);
    }


    public static void v1_parallelBlock(double[][]matrizA,double[][]matrizB,int size,int bsize){
        V1_ParallelBlock v1ParallelBlock = new V1_ParallelBlockImpl();
        v1ParallelBlock.algoritmoParallelBlock(matrizA,matrizB,size,bsize);
    }

    public static void v2_sequentialBlock(double[][]matrizA,double[][]matrizB,int size,int bsize)
    {
        V2_SequentialBlock v2SequentialBlock = new V2_SequentialBlockImpl();
        v2SequentialBlock.algoritmoSequentialBlockDos(matrizA,matrizB,size,bsize);
    }

    public static void v2_parallelBlock (double[][]matrizA,double[][]matrizB,int size,int bsize)
    {
        V2_ParallelBlock v2ParallelBlock = new V2_ParallelBlockImpl();
        v2ParallelBlock.algoritmoParallelBlockDos(matrizA,matrizB,size,bsize);
    }

    public static void v3_sequentialBlock(double[][]matrizA,double[][]matrizB,int size,int bsize)
    {
        V3_SequentialBlock v3SequentialBlock = new V3_SequentialBlockImpl();
        v3SequentialBlock.algoritmoSequentialBlockTres(matrizA,matrizB,size,bsize);
    }

    public static void v3_parallelBlock (double[][]matrizA,double[][]matrizB,int size,int bsize)
    {
        V3_ParallelBlock v3ParallelBlock = new V3_ParallelBlockImpl();
        v3ParallelBlock.algoritmoParallelBlockTres(matrizA,matrizB,size,bsize);
    }

}
