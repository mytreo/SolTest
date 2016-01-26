package ua.mytreo.java.soltest.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mytreo   27.01.2016.
 * @version 1.1
 */

@XmlRootElement(name = "catalog")
@XmlType(propOrder = {"books"})
public class Catalog {

    private List<Book> books=new ArrayList<>();

    @XmlElement(name = "book")
    @XmlElementWrapper
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "books=" + books +
                '}';
    }
}
