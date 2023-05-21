package supastriped;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int sizeOfMatrix = 300;
        int numOfThreads = 6;

        Matrix matrix1 = new Matrix(sizeOfMatrix,sizeOfMatrix);
        matrix1.generateRandomMatrix();
        Matrix matrix2 = new Matrix(sizeOfMatrix,sizeOfMatrix);
        matrix2.generateRandomMatrix();

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        long forkStartTime = System.currentTimeMillis();
        Matrix resultOfFork = pool.invoke(new StripedFork(matrix1, matrix2, 0, matrix1.rows));
        long forkTime = System.currentTimeMillis() - forkStartTime;
        System.out.println("Matrix After Striped Fork Multiply:");
        //resultOfFork.print();
        System.out.println("Time:" + forkTime);
        System.out.println();


        long stripStartTime = System.currentTimeMillis();
        Striped striped = new Striped(matrix1, matrix2, numOfThreads);
        Matrix resultOfStriped = striped.multiply();
        long stripTime = System.currentTimeMillis() - stripStartTime;
        System.out.println("Matrix After Striped Thread Multiply:");
        //resultOfStriped.print();
        System.out.println("Time:" + stripTime);
        System.out.println();

        System.out.println("Speed Up: "+ (double)stripTime/forkTime);
    }
}