package ua.mytreo.java.soltest.IO;

import org.xml.sax.SAXException;
import ua.mytreo.java.soltest.entity.Book;
import ua.mytreo.java.soltest.entity.Catalog;
import ua.mytreo.java.soltest.parser.Parser;
import ua.mytreo.java.soltest.parser.impl.ParserJaxbImpl;

import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * @author mytreo   29.01.2016.
 * @version 1.0
 */
public class Loader {

    public List<Book> getBooksFromFile(String mainXmlPath){

        StringBuilder sb = new StringBuilder();
        Parser parser;
        Catalog cat;
        String mainXml;
        try (FileReader reader = new FileReader(mainXmlPath)) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException ex) {
            return new ArrayList<>();
        }
         mainXml = sb.toString();
        try {
            parser = new ParserJaxbImpl();
            cat = (Catalog) parser.unMarshall(mainXml, Class.forName("ua.mytreo.java.soltest.entity.Catalog"));
        } catch (SAXException | ClassNotFoundException | JAXBException e) {
            return new ArrayList<>();
        }
        return cat.getBooks();
    }
}
