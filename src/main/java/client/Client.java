package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static client.ClientRequest.requestToServer;

public class Client {

  public static String sendRequest(String clientName, String ipHost, int port) {
    Socket socket = openSocket(ipHost,port);
    return requestToServer(clientName, socket);
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
