package ua.mytreo.java.soltest;

import com.sun.jndi.toolkit.url.Uri;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.mytreo.java.soltest.IO.Loader;
import ua.mytreo.java.soltest.IO.Saver;
import ua.mytreo.java.soltest.entity.Book;
import ua.mytreo.java.soltest.servlets.ChangeBookServlet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mytreo   27.01.2016.
 * @version 1.1
 */
public class Main {
    public static final String MAIN_XML_PATH="/main.xml";
    public static final int BETWEEN_SAVES_PAUSE=10000;

    public static void main(String[] args) throws Exception {


        List<Book> mainBookList= new CopyOnWriteArrayList<>();//ArrayList<>();
        Loader loader=new Loader();
        mainBookList.addAll(loader.getBooksFromFile(MAIN_XML_PATH));

        AtomicInteger countInsUpdDel = new AtomicInteger(0);
        Thread saverSrv= new Thread(new Saver(countInsUpdDel,mainBookList,MAIN_XML_PATH,BETWEEN_SAVES_PAUSE));
        saverSrv.start();


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ChangeBookServlet(mainBookList,countInsUpdDel)), ChangeBookServlet.PAGE_URL);

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started!");
        server.join();
    }
}
