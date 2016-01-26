package ua.mytreo.java.soltest.entity;

import ua.mytreo.java.soltest.parser.ParserDateFormatter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mytreo   27.01.2016.
 * @version 1.1
 */

public class Book {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String id;
    private String author;
    private String title;
    private String genre;
    private float price;
    private Date publishDate;
    private String description;

    public Book(String id, String author, String title, String genre, float price, Date publishDate, String description){
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publishDate = publishDate;
        this.description = description;
    }

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement(name = "price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @XmlElement(name = "publish_date")
    @XmlJavaTypeAdapter(ParserDateFormatter.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate){
        this.publishDate = publishDate;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", publishDate='" + publishDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
