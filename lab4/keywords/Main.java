package keywords;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        File directory = new File("lab4/files");

        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("Macbeth");
        keywords.add("LORDS");
        keywords.add("death");
        keywords.add("writes");
        keywords.add("wood");
        keywords.add("candle");
        keywords.add("enter");

        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        Analyzer task = new Analyzer(directory, keywords);
        ArrayList<Document> result = pool.invoke(task);
        long endTime = System.currentTimeMillis();

        System.out.println("Time: " + (endTime - startTime) + " ms");
        if(result.size() != 0){
            for (Document document : result){
                System.out.println("Document is: " + document.path);
                System.out.println("Count of Keywords: " + document.keywords.size());
                System.out.println("Found Keywords: " + document.keywords);
                System.out.println();
            }
        } else{
            System.out.println("There are no files with keywords");
        }
    }
}