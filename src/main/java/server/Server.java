package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {

  private static boolean serverIsStarted = false;
  private static int cores = 1;

  public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
    ServerSocket socket = new ServerSocket(25225, 2000);

    cores = Runtime.getRuntime().availableProcessors();

    ExecutorService es = Executors.newFixedThreadPool(cores);

    startServer();
    while (serverIsStarted) {
      Socket client = socket.accept();
      Future<String> s = es.submit(new ServerHandleRequest(client));

      String request = s.get();

      if (request.contains("stop server")) {
        es.shutdown();
        stopServer();
      }
    }
    System.out.println(new Timestamp(System.currentTimeMillis()) + "\tServer is stopped.");
  }

  public static void stopServer() {
    Server.serverIsStarted = false;
  }

  public static void startServer() {
    Server.serverIsStarted = true;
    System.out.println(new Timestamp(System.currentTimeMillis())
        + String.format("\tServer is started with %(d available processors", cores ));
  }
}
