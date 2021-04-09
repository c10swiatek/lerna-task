package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int retryCounter = 0;

    public void connect(String ip, int port) {
        while(retryCounter < 10) {
            try {
                clientSocket = new Socket(ip, port);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                break;
            } catch (IOException ex) {
                ex.printStackTrace();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
            }
            retryCounter++;
            System.out.println("RETRYING");
        }
    }

    public String send(String msg) {
        try {
            out.println(msg);
            return in.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getRetryCounter() {
        return retryCounter;
    }
}
