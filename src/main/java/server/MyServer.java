package server;

import communicate.CommunicateEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyServer {
    private ServerSocket serverSocket;

    public static List<String> syncList = Collections.synchronizedList(new ArrayList<String>());

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        WriterThread writerThread = new WriterThread(syncList);
        writerThread.start();
        while (true) {
            new ClientHandler(serverSocket.accept(), syncList).start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private List<String> list;

        public ClientHandler(Socket socket, List<String> syncList) {
            this.clientSocket = socket;
            this.list = list;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (CommunicateEnum.END_COMMUNICATION.name().equals(inputLine)) {
                        out.println(CommunicateEnum.CONNECTION_ENDED.name());
                        break;
                    }
                    addToList(inputLine);
                    out.println(CommunicateEnum.CONFIRMED.name());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        synchronized public void addToList(String inputLine) {
            syncList.add(inputLine);
        }
    }


}
