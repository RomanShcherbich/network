import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

  public static String sendRequest(String clientName, String ipHost, int port) throws IOException{
    Socket socket = openSocket(ipHost,port);
    return checkConnection(clientName, socket);
  }

  private static String checkConnection(String clientName, Socket socket) {
    String answer = "";
    try {
    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    String sb = clientName;

    bw.write(sb);
    bw.newLine();
    bw.flush();

    answer = br.readLine();

    br.close();
    bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return answer;
  }

  public static Socket openSocket(String ipHost, int port) {
    Socket socket = null;
    try {
      socket = new Socket(ipHost,port);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return socket;
  }
}
