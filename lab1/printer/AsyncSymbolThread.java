package printer;

public class AsyncSymbolThread extends Thread{
    public static final int LINES_NUMBER = 100;
    public static final int LINE_LENGTH = 50;
    private char symbol;

    public AsyncSymbolThread(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void run() {
        for (int i = 0; i < LINES_NUMBER; i++) {
            for (int j = 0; j < LINE_LENGTH; j++) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
