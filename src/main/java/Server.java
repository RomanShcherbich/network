import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void startServer() throws IOException {
    ServerSocket socket = new ServerSocket(25225);

    System.out.println("Server is started.");
    while(true) {
      Socket client = socket.accept();
      handleRequest(client);
    }
  }

  private static void handleRequest(Socket client) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

    String sb = "{} has connected to server";
    String clientName = br.readLine();

    String.format(sb, clientName);

    bw.write(sb);
    bw.newLine();
    bw.flush();

    br.close();
    bw.close();

    client.close();

    System.out.println(clientName);
  }
}
