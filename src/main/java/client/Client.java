package client;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {

  private static int cores = 1;
  private static int requestsCount = 1;

  public static String sendRequest(String request, String ipHost, int port) {
    String answer = null;

    cores = Runtime.getRuntime().availableProcessors();

    ExecutorService es = Executors.newFixedThreadPool(cores);

    for(int i = 0; i < requestsCount; i++) {
      try {
        Socket socket = openSocket(ipHost,port);
        if (socket == null) {
          throw new SocketException("Server is not available. Cannot open socket " + ipHost + ":" + port);
        }

        Future<String> s = es.submit(new ClientRequest(request, socket));
        answer = s.get();

        System.out.println(answer);

      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace(System.out);
      } catch (SocketException e) {
        e.printStackTrace();
        break;
      }
      if (request == "stop server" ) {
        break;
      }
    }
    es.shutdown();
    return answer;
  }

  public static void setRequestsCount(int requestsCount) {
    Client.requestsCount = requestsCount;
  }

  public static Socket openSocket(String ipHost, int port) {
    Socket socket = null;
    try {
      socket = new Socket(ipHost,port);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return socket;
  }

  public static void closeSocket(Socket socket) {
    try {
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
