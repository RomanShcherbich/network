package server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StartServerThread implements Runnable {

  @Override
  public void run() {
    Class cl;
    try {
      cl =  Class.forName("server.Server");
      Constructor csr = cl.getConstructor();
      Object server = csr.newInstance();

      Method meth = cl.getMethod("main", String[].class);


      String[] params = null;
      meth.invoke(null, (Object) params);

    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException ex) {
      ex.printStackTrace();
    } catch (InvocationTargetException ex) {
      System.out.println("Server is started already.");
    }
  }
}
