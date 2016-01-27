package ua.mytreo.java.soltest.servlets;

import org.xml.sax.SAXException;
import ua.mytreo.java.soltest.entity.Catalog;
import ua.mytreo.java.soltest.parser.Parser;
import ua.mytreo.java.soltest.parser.impl.ParserJaxbImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Сервлет,отвечающий за работу со списком книг
 *
 * @author mytreo   27.01.2016.
 * @version 1.2
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

        response.getWriter().println(responseHelper(value));

        response.setContentType("text/xml;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    private String responseHelper(String reqXml){
        if (reqXml.equals("")) return "all catalog";
        Parser parser;
        Catalog catalog;
        try {
             parser= new ParserJaxbImpl();
        } catch (SAXException e) {
            return "parser error";
        }
        try {
            catalog=(Catalog)parser.unMarshall(reqXml,Class.forName("ua.mytreo.java.soltest.entity.Catalog"));
        } catch (JAXBException |ClassNotFoundException e) {
            e.printStackTrace();
            catalog=new Catalog();
        }
        return catalog.toString();
    }
}
