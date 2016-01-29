package ua.mytreo.java.soltest.IO;

import org.xml.sax.SAXException;
import ua.mytreo.java.soltest.entity.Book;
import ua.mytreo.java.soltest.entity.Catalog;
import ua.mytreo.java.soltest.parser.Parser;
import ua.mytreo.java.soltest.parser.impl.ParserJaxbImpl;

import javax.xml.bind.JAXBException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * @author mytreo 29.01.2016.
 * @version 1.0
 */
public class Saver implements Runnable {
    private final int SLEEPING_TIME;
    private AtomicInteger outerCounter;
    private List<Book> saveList;
    private String filepath;
    int innerCounter;
    public Saver(AtomicInteger outerCounter, List<Book> saveList, String filepath, int sleepingTime){
        this.outerCounter=outerCounter;
        this.innerCounter=0;
        this.saveList=saveList;
        this.filepath=filepath;
        this.SLEEPING_TIME=sleepingTime;
    }
    @Override
    public void run() {
        while(true){
            if(outerCounter.intValue()>innerCounter){
                innerCounter=outerCounter.intValue();
                try {
                    saveToFile();
                } catch (Exception e) {
                    innerCounter--;
                }
                try {
                    sleep(SLEEPING_TIME);
                } catch (InterruptedException e) {
                    System.out.println("Interrupt sleeping saver ");
                }
            }

        }

    }

    private void saveToFile() throws Exception {
        Catalog cat = new Catalog();
        cat.setBooks(saveList);
        String toWriteString;
        try {
            Parser parser = new ParserJaxbImpl();
            toWriteString=parser.marshall(cat);
        } catch (SAXException | JAXBException e) {
           throw new Exception("Can't parse!");
        }

         //WritingFile
        Writer out= new BufferedWriter(new OutputStreamWriter(System.out));
        out.write(toWriteString);
        out.flush();

    }
}
