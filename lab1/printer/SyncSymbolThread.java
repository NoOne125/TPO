package printer;

public class SyncSymbolThread extends Thread {
    public static final int LINES_NUMBER = 100;
    public static final int LINE_LENGTH = 50;
    private char symbol;
    private Syncer syncer;

    public SyncSymbolThread(Syncer syncer, char symbol) {
        this.syncer = syncer;
        this.symbol = symbol;
    }

    @Override
    public void run() {
        for (int i = 0; i < LINES_NUMBER; i++) {
            for (int j = 0; j < LINE_LENGTH; j++) {
                syncer.then(symbol, () -> System.out.print(symbol));
            }
        }
    }
}