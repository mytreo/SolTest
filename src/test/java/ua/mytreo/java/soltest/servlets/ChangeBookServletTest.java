package ua.mytreo.java.soltest.servlets;

import org.junit.Before;
import org.junit.Test;
import ua.mytreo.java.soltest.entity.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author mytreo   28.01.2016.
 * @version 1.0
 */
public class ChangeBookServletTest {
    private List<Book> mainBookList = new ArrayList<>();
    private String testXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<catalog>\n" +
            "    <book id=\"bk101\">\n" +
            "        <author>Gambardella, Matthew</author>\n" +
            "        <title>XML Developer's Guide</title>\n" +
            "        <genre>Computer</genre>\n" +
            "        <price>44.95</price>\n" +
            "        <publish_date>2000-10-01</publish_date>\n" +
            "        <description>An in-depth look at creating applications \n" +
            "with XML.</description>\n" +
            "    </book>\n" +
            "</catalog>";

    @Before
    public void setUp() throws Exception {
        Book book1 = new Book("bk101", "Gambardella, Matthew", "XML Developer's Guide", "Computer", 44.95f, new Date(), "An in-depth look at creating applications \nwith XML.");
        mainBookList.add(book1);
    }


    private HttpServletResponse getMockedResponse(StringWriter stringWriter) throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);

        final PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);

        return response;
    }

    private HttpServletRequest getMockedGoodRequest(String url) throws IOException {
        HttpSession httpSession = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(testXml)));
        when(request.getContentType()).thenReturn("text/xml");
        return request;
    }

    private HttpServletRequest getMockedBadRequest(String url) throws IOException {
        HttpSession httpSession = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("")));
        when(request.getContentType()).thenReturn("text");
        return request;
    }

    @Test
    public void testDoPostError() throws Exception {
        final StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = getMockedResponse(stringWriter);
        HttpServletRequest request = getMockedBadRequest(ChangeBookServlet.PAGE_URL);
        ChangeBookServlet changeBookSrv = new ChangeBookServlet(mainBookList);

        changeBookSrv.doPost(request, response);

        assertEquals("400 BAD_REQUEST", stringWriter.toString().trim());
    }

    @Test
    public void testDoPostCreate() throws Exception {
        final StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = getMockedResponse(stringWriter);
        HttpServletRequest request = getMockedGoodRequest(ChangeBookServlet.PAGE_URL);
        ChangeBookServlet changeBookSrv = new ChangeBookServlet(mainBookList);

        changeBookSrv.doPost(request, response);

        assertEquals(testXml, stringWriter.toString().trim());
    }
    @Test
    public void testDoPostRead() throws Exception {
        final StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = getMockedResponse(stringWriter);
        HttpServletRequest request = getMockedGoodRequest(ChangeBookServlet.PAGE_URL);
        ChangeBookServlet changeBookSrv = new ChangeBookServlet(mainBookList);

        changeBookSrv.doPost(request, response);

        assertEquals(testXml, stringWriter.toString().trim());
    }
    @Test
    public void testDoPostUpdate() throws Exception {
        final StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = getMockedResponse(stringWriter);
        HttpServletRequest request = getMockedGoodRequest(ChangeBookServlet.PAGE_URL);
        ChangeBookServlet changeBookSrv = new ChangeBookServlet(mainBookList);

        changeBookSrv.doPost(request, response);

        assertEquals(testXml, stringWriter.toString().trim());
    }
    @Test
    public void testDoPostDelete() throws Exception {
        final StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = getMockedResponse(stringWriter);
        HttpServletRequest request = getMockedGoodRequest(ChangeBookServlet.PAGE_URL);
        ChangeBookServlet changeBookSrv = new ChangeBookServlet(mainBookList);

        changeBookSrv.doPost(request, response);

        assertEquals(testXml, stringWriter.toString().trim());
    }
}