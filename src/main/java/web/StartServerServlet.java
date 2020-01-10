package web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.StartServerThread;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StartServerServlet", urlPatterns = {"/startServer"})
public class StartServerServlet extends HttpServlet {

  private static final Logger logger = LoggerFactory.getLogger(StartServerServlet.class);
  private Thread startThread;

  @Override
  public void init() throws SecurityException {
    logger.info("Servlet is created");
    startThread = new Thread(new StartServerThread());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.getWriter().println("Get ServerStarted - called");
    startThread.start();
    resp.getWriter().println("startThread.isAlive() : " + startThread.isAlive());
    resp.getWriter().println("startThread.isDaemon() : " + startThread.isDaemon());
    resp.getWriter().println("startThread.isInterrupted() : " + startThread.isInterrupted());
  }
}
