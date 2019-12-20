package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ServerHandleRequest implements Callable<String> {

  private Socket socket;

  private static int counter = 1;

  public ServerHandleRequest(Socket socket) {
    this.socket = socket;
  }

  @Override
  public String call() {
    try {
      return handleRequest(socket);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String handleRequest(Socket socket) throws IOException, InterruptedException {
    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));



    String sb = String.format("%03d", counter++)+ " Server got request: %s";
    String request = br.readLine();

    sb = String.format(sb, request);

    System.out.println(sb);
//    Thread.sleep(2000);

    bw.write(sb);
    bw.newLine();
    bw.flush();

    br.close();
    bw.close();

    socket.close();

    return request;
  }
}
