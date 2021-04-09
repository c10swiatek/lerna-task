package server;

import java.util.List;

public class WriterThread extends Thread {
    private List<String> list;

    public WriterThread(List<String> list) {
        this.list = list;
    }

    public void run() {
        try {
            while(1 == 1) {
                Thread.sleep(2000);
                System.out.println(list.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
