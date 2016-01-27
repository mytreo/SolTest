package ua.mytreo.java.soltest.parser.impl;

import org.junit.Before;
import org.junit.Test;
import ua.mytreo.java.soltest.entity.Book;
import ua.mytreo.java.soltest.entity.Catalog;
import ua.mytreo.java.soltest.parser.Parser;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

/**
 * @author mytreo   27.01.2016.
 * @version 1.0
 */

//TODO дописать чтобы тесты что то таки проверяли
public class ParserJaxbImplTest {
    private Parser parser;
    private Date date;
    @Before
    public void setUp() throws Exception {
        parser = new ParserJaxbImpl();
    }

    @Test
    public void testUnMarshall() throws Exception {
        String testXml= "<?xml version=\"1.0\"?>\n" +
                "<catalog>\n" +
                "   <book id=\"bk101\">\n" +
                "      <author>Gambardella, Matthew</author>\n" +
                "      <title>XML Developer's Guide</title>\n" +
                "      <genre>Computer</genre>\n" +
                "      <price>44.95</price>\n" +
                "      <publish_date>2000-10-01</publish_date>\n" +
                "      <description>An in-depth look at creating applications \n" +
                "      with XML.</description>\n" +
                "   </book>\n" +
                "</catalog>";
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.OCTOBER, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();

        //date=new Date(2000,10,01);
        Book book1 = new Book("bk101","Gambardella, Matthew","XML Developer's Guide","Computer",44.95f,date,"An in-depth look at creating applications \n with XML.");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        Catalog catalogT= new Catalog();
        catalogT.setBooks(books);
        Catalog catalog =  (Catalog) parser.unMarshall(testXml, Catalog.class);
       // assertEquals("",catalogT.toString(),catalog.toString());
        System.out.println(catalog.getBooks().toString());

    }

    @Test
    public void testMarshall() throws Exception {
        Catalog catalog = new Catalog();
        Book book1 = new Book("bk101","Gambardella, Matthew","XML Developer's Guide","Computer",44.95f,date,"An in-depth look at creating applications \n with XML.");
        Book book2 = new Book("bk102","Ralls, Kim","Midnight Rain","Fantasy",5.95f,date,"A former architect battles corporate zombies, \n" +
                "      an evil sorceress, and her own childhood to become queen \n" +
                "      of the world.");


        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        catalog.setBooks(books);

        System.out.println( parser.marshall(catalog) );

    }
}
