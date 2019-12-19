package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private static boolean serverIsStarted = false;

  public static void main(String[] args) throws IOException {
    ServerSocket socket = new ServerSocket(25225);

    startServer();
    while(serverIsStarted) {
      Socket client = socket.accept();
      handleRequest(client);
    }
    System.out.println("server.Server is stopped.");
  }

  public static void stopServer() {
    Server.serverIsStarted = false;
  }

  public static void startServer() {
    Server.serverIsStarted = true;
    System.out.println("server.Server is started.");
  }

  private static void handleRequest(Socket request) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(request.getOutputStream()));

    String sb = "server.Server got request: %s";
    String clientName = br.readLine();

    sb = String.format(sb, clientName);

    bw.write(sb);
    bw.newLine();
    bw.flush();

    if(clientName.contains("stop server")) {
      stopServer();
    }

    System.out.println(sb);

    br.close();
    bw.close();

    request.close();
  }
}
