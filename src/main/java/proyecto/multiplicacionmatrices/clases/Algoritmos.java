package proyecto.multiplicacionmatrices.clases;

import proyecto.multiplicacionmatrices.algoritmosimplementacion.*;
import proyecto.multiplicacionmatrices.algoritmosinterfaces.*;
import proyecto.multiplicacionmatrices.algoritmosinterfaces._11_III_3SequentialBlock;

public class Algoritmos {

    public static void naivStandard(double[][] matrizA,double[][] matrizB,double[][] matrizC,int n, int p, int m){
        _1_NaivStandard naivStandard = new _1_NaivStandardImpl();
        naivStandard.algoritmoNaivStandard(matrizA,matrizB,matrizC,n,p,m);
    }

    public static void naivOnArray(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P , int M){
        _2_NaivOnArray naivOnArray = new _2_NaivOnArrayImpl();
        naivOnArray.algoritmoNaivOnArray(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void naivKhan(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        _3_NaivKahan naivKahan = new _3_NaivKahanImpl();
        naivKahan.algoritmoNaivKhan(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void naivLoopUnrollingTwo(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        _4_NaivLoopUnrollingTwo naivLoopUnrollingTwo = new _4_NaivLoopUnrollingTwoImpl();
        naivLoopUnrollingTwo.algoritmoNaivLoopUnrollingTwo(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void naivLoopUnrollingThree(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        _5_NaivLoopUnrollingThree naivLoopUnrollingThree = new _5_NaivLoopUnrollingThreeImpl();
        naivLoopUnrollingThree.algoritmoNaivLoopUnrollingThree(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void naivLoopUnrollingFour(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        _6_NaivLoopUnrollingFour naivLoopUnrollingFour = new _6_NaivLoopUnrollingFourImpl();
        naivLoopUnrollingFour.algoritmoNaivLoopUnrollingFour(A,B,Result,N,P,M);
    }

    public static void winogradOriginal(double[][] A, double[][] B, double[][] Result, int N, int P, int M){
        _7_WinogradOriginal winogradOriginal = new _7_WinogradOriginalImpl();
        winogradOriginal.algoritmoWinogradOriginal(A,B,Result,N,P,M);
    }

    public static void winogradScaled(double[][] A, double[][] B, double[][] Result, int N, int P, int M){
        _8_WinogradScaled winogradScaled = new _8_WinogradScaledImpl();
        winogradScaled.algoritmoWinogradScaled(A,B,Result,N,P,M);
    }

    public static void strassenNaiv(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        _9_StrassenNaiv strassenNaiv = new _9_StrassenNaivImpl();
        strassenNaiv.algoritmoStrassenNaiv(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void strassenWinograd(double[][] matrizA, double[][] matrizB, double[][] matrizC, int N, int P, int M) {
        _10_StrassenWinograd strassenWinograd = new _10_StrassenWinogradImpl();
        strassenWinograd.algoritmoStrassenWinograd(matrizA,matrizB,matrizC,N,P,M);
    }

    public static void III_3SequentialBlockImpl(double[][]matrizA,double[][]matrizB,int size,int bsize){
        _11_III_3SequentialBlock III3sequentialBlock = new proyecto.multiplicacionmatrices.algoritmosimplementacion._11_III_3SequentialBlockImpl();
        III3sequentialBlock.algoritmoSequentialBlock(matrizA,matrizB,size,bsize);
    }


    public static void III_4ParallelBlockImpl(double[][]matrizA,double[][]matrizB,int size,int bsize){
        _12_III_4ParallelBlock III3ParallelBlock = new _12_III_4ParallelBlockImpl();
        III3ParallelBlock.algoritmoParallelBlock(matrizA,matrizB,size,bsize);
    }

    public static void IV_3SequentialBlockImpl(double[][]matrizA,double[][]matrizB,int size,int bsize) {
        _13_IV_3SequentialBlock IV3SequentialBlock = new _13_IV_3SequentialBlockImpl();
        IV3SequentialBlock.algoritmoSequentialBlockDos(matrizA,matrizB,size,bsize);
    }

    public static void IV_4ParallelBlockImpl (double[][]matrizA,double[][]matrizB,int size,int bsize) {
        _14_IV_4ParallelBlock IV4ParallelBlock = new _14_IV_4ParallelBlockImpl();
        IV4ParallelBlock.algoritmoParallelBlockDos(matrizA,matrizB,size,bsize);
    }

    public static void V_3SequentialBlockImpl(double[][]matrizA,double[][]matrizB,int size,int bsize) {
        _15_V_3SequentialBlock v3SequentialBlock = new _15_V_3SequentialBlockImpl();
        v3SequentialBlock.algoritmoSequentialBlockTres(matrizA,matrizB,size,bsize);
    }

    public static void V_4ParallelBlockImpl (double[][]matrizA,double[][]matrizB,int size,int bsize) {
        _16_V_4ParallelBlock v4ParallelBlock = new _16_V_4ParallelBlockImpl();
        v4ParallelBlock.algoritmoParallelBlockTres(matrizA,matrizB,size,bsize);
    }

}
