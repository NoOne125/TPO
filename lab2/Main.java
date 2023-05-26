import Algorithms.Fox;
import Algorithms.Standard;
import Algorithms.Striped;
import Models.Matrix;
import Models.Result;

public class Main {
    public static void main(String[] args) {
        int sizeOfMatrix = 3000;
        int numOfThreads = 6;
        int blockSize = 40;

        Matrix matrix1 = new Matrix(sizeOfMatrix,sizeOfMatrix);
        matrix1.generateRandomMatrix();
        System.out.println("Matrix1 Before:");
        //matrix1.print();
        System.out.println();

        Matrix matrix2 = new Matrix(sizeOfMatrix,sizeOfMatrix);
        matrix2.generateRandomMatrix();
        System.out.println("Matrix2 Before:");
        //matrix2.print();
        System.out.println();

        Standard standard = new Standard(matrix1, matrix2);
        Result resultOfStandard = standard.multiply();
        System.out.println("Matrix After Standard Multiply:");
        //resultOfStandard.matrix.print();
        System.out.println("Time:" + resultOfStandard.time);
        System.out.println();

        Fox fox = new Fox(matrix1, matrix2, numOfThreads, blockSize);
        Result resultOfFox = fox.multiply();
        System.out.println("Matrix After Fox Multiply:");
        //resultOfFox.matrix.print();
        System.out.println("Time:" + resultOfFox.time);
        System.out.println("Efficiency:" + (double)resultOfStandard.time / resultOfFox.time);
        System.out.println();

        //Striped striped = new Striped(matrix1, matrix2, numOfThreads);
        //Result resultOfStriped = striped.multiply();
        //System.out.println("Matrix After Striped Callable Multiply:");
        //resultOfStriped.matrix.print();
        //System.out.println("Time:" + resultOfStriped.time);
        //System.out.println("Efficiency:" + (double)resultOfStandard.time / resultOfStriped.time);
        //System.out.println();

    }
}