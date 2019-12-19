
import static org.junit.Assert.*;

import client.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServerTest {

  @Before
  public void setUp() throws IOException {
  }

  @Test
  public void T01_handleRequest() throws IOException{
    String request = "ping server";

    assertEquals(Client.sendRequest(request, "127.0.0.1",25225)
        ,"server.Server got request: " + request);
  }

  @Test
  public void T02_stopServerTest() throws IOException{
    String request = "stop server";

    assertEquals(Client.sendRequest(request, "127.0.0.1",25225)
        ,"server.Server got request: " + request);
  }
}
