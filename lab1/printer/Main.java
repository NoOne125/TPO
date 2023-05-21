package printer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            int choose = System.in.read();
            char[] symbols = {'-', '|'};

            if(choose == '0'){
                for (int i = 0; i < symbols.length; i++) {
                    AsyncSymbolThread symbolThread = new AsyncSymbolThread(symbols[i]);
                    symbolThread.start();
                }
            } else{
                Syncer syncer = new Syncer('-', symbols);
                for (int i = 0; i < symbols.length; i++) {
                    SyncSymbolThread symbolThread = new SyncSymbolThread(syncer, symbols[i]);
                    symbolThread.start();
                }
            }
        } catch(IOException e) {
        }
    }
}
