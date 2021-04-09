import client.Client;
import communicate.CommunicateEnum;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ClientServerTest {


    @Test
    public void testClient1() throws IOException {
        Client client1 = new Client();
        client1.connect("127.0.0.1", 7777);
        String message1 = client1.send("1");
        String message2 = client1.send("2");
        String endCommunication = client1.send(CommunicateEnum.END_COMMUNICATION.name());

        assertEquals(message1, CommunicateEnum.CONFIRMED.name());
        assertEquals(message2, CommunicateEnum.CONFIRMED.name());
        assertEquals(endCommunication, CommunicateEnum.CONNECTION_ENDED.name());
    }


    @Test
    public void testClient2() throws IOException {
        Client client1 = new Client();
        client1.connect("127.0.0.1", 7777);
        String message1 = client1.send("1b");
        String message2 = client1.send("2b");
        String endCommunication = client1.send(CommunicateEnum.END_COMMUNICATION.name());

        assertEquals(message1, CommunicateEnum.CONFIRMED.name());
        assertEquals(message2, CommunicateEnum.CONFIRMED.name());
        assertEquals(endCommunication, CommunicateEnum.CONNECTION_ENDED.name());
    }

}
