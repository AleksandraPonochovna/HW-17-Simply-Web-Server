package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.RequestServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        RequestServlet requestServlet = new RequestServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(requestServlet), "/*");
        Server server = new Server(8080);
        server.setHandler(context);
        System.out.println("Server started.");
        server.start();
        server.join();
    }
}