package stats;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import static stats.Methods.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "lab4/files/1";
        ArrayList<String> words = getWords(path);

        long startTimePar = System.nanoTime();
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ArrayList<Word> result1  = pool.invoke(new CounterTask(words));
        long timeSingle =  (System.nanoTime() - startTimePar)/1000;

        long startTimeSingle =  System.nanoTime();
        ArrayList<Word> result2 = countWordsSymbols(words);
        long timePar =  (System.nanoTime() - startTimeSingle)/1000;

        System.out.println("Time Fork: " + timePar + " μs");
        System.out.println("Time Single: " + timeSingle + " μs");
        System.out.println("Speed Up: " + (double)timeSingle/timePar + " μs");
        System.out.println();
        System.out.println("Count Of Words: ");
        System.out.println("Fork: " + result1.size());
        System.out.println("Single: " + result2.size());
        System.out.println("Overall Symbol Length: " + getWordLengthSum(result1));
        System.out.println("Average Word Length: " + getAverageWordLength(result1));
        System.out.println("Dispersion: " + getDispersion(result1));
        System.out.println("Standard Deviation: " + getStandardDeviation(result1));
    }
}