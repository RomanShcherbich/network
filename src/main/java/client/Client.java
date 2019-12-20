package client;

import java.net.Socket;
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

        Future<String> s = es.submit(new ClientRequest(request, socket));
        answer = s.get();

      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace(System.out);
      }
      if (request == "stop server") {
        es.shutdown();
        return answer;
      }
      System.out.println(answer);
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
}
