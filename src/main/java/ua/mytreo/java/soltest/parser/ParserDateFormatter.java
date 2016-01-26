package ua.mytreo.java.soltest.parser;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mytreo   27.01.2016.
 * @version 1.0
 */
public class ParserDateFormatter extends XmlAdapter<String, Date> {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public Date unmarshal(String date) throws Exception {
        return formatter.parse(date);
    }

    public String marshal(Date date) throws Exception {
        return formatter.format(date);
    }
}
