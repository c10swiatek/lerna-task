import client.Client;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ClientRetryTest {

    @Test
    public void testClient1Retry() throws IOException {
        Client client1 = new Client();
        client1.connect("127.0.0.1", 7777);
        client1.send("1");
        int retryCounterValue = client1.getRetryCounter();
        assertEquals(retryCounterValue, 10);

    }

}
