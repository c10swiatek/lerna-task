import server.MyServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        MyServer server = new MyServer();
        System.out.println("SERVER INITIATED");
        server.start(7777);



    }

}
