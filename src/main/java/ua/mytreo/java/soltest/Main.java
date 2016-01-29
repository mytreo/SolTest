package ua.mytreo.java.soltest;

import com.sun.jndi.toolkit.url.Uri;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.mytreo.java.soltest.IO.Loader;
import ua.mytreo.java.soltest.entity.Book;
import ua.mytreo.java.soltest.servlets.ChangeBookServlet;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author mytreo   27.01.2016.
 * @version 1.1
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Loader loader=new Loader();

        List<Book> mainBookList= new CopyOnWriteArrayList<>();//ArrayList<>();
        mainBookList.addAll(loader.getBooksFromFile("/main.xml"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ChangeBookServlet(mainBookList)), ChangeBookServlet.PAGE_URL);

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started!");
        server.join();
    }
}
