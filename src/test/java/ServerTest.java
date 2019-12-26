
import static org.junit.Assert.*;

import client.Client;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import server.StartServerThread;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServerTest {
  private static Thread startThread;

  @BeforeClass
  public static void setUp(){
    startThread = new Thread(new StartServerThread());
    startThread.start();
  }

  @Test
  public void T01_handleRequest() {
    String request = "ping server";
    String ipHost = "127.0.0.1";
    int port = 25225;

    Client client = new Client();

    int n = 500;
    client.setRequestsCount(n);

    assertEquals(n +" Server got request: " + request
        ,client.sendRequest(request, ipHost,port));
  }

  @Test
  public void T02_stopServerTest() {
    String request = "stop server";
    String ipHost = "127.0.0.1";
    int port = 25225;

    String answer = Client.sendRequest(request, "127.0.0.1",25225);

    assertEquals("Server got request: " + request
        ,answer.substring(answer.indexOf("Server")));
  }

  @AfterClass
  public static void tearDown(){
  }
}
