
import org.apache.logging.log4j.core.util.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

public class ServerTest {

  @Before
  public void setUp() throws IOException {
    Server.startServer();
  }

  @Test
  public void startServer() throws IOException{
    assertEquals(Client.sendRequest("Client 1", "127.0.0.1",25225)
        ,"Client 1 has connected to server");
  }
}
