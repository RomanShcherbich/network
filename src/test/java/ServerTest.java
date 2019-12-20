
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
  public void setUp(){
  }

  @Test
  public void T01_handleRequest() {
    String request = "ping server";

    Client client = new Client();

    int n = 500;
    client.setRequestsCount(n);

    assertEquals(n +" Server got request: " + request
        ,client.sendRequest(request, "127.0.0.1",25225));
  }

  @Test
  public void T02_stopServerTest() {
    String request = "stop server";

    String answer = Client.sendRequest(request, "127.0.0.1",25225);

    assertEquals("Server got request: " + request
        ,answer.substring(answer.indexOf("Server")));
  }
}
