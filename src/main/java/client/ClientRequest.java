package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ClientRequest implements Callable<String> {

  private String request;
  private Socket socket;

  public ClientRequest(String request, Socket socket) {
    this.request = request;
    this.socket = socket;
  }

  @Override
  public String call() {
    return requestToServer(request, socket);
  }

  public static String requestToServer(String request, Socket socket) {
    String answer = "";
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

      String sb = request;

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
}
