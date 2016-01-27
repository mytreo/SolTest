package ua.mytreo.java.soltest.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Сервлет,отвечающий за работу со списком книг
 *
 * @author mytreo   27.01.2016.
 * @version 1.1
 */
public class ChangeBookServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {


        if (!(request.getContentType().equals("text/xml") ||
                request.getContentType().equals("application/xml"))) {
            response.getWriter().println("400 BAD_REQUEST");
            response.setContentType("text/xml;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //read xml
        String value;
        BufferedReader b = new BufferedReader(request.getReader());
        StringBuilder workBuffer = new StringBuilder();
        while ((value = b.readLine()) != null) {
            workBuffer.append(value);
        }
        value = workBuffer.toString();

        if (!value.equals(""))
            response.getWriter().println(value);


        response.setContentType("text/xml;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
