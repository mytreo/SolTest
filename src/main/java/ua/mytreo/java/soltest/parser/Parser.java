package ua.mytreo.java.soltest.parser;

import ua.mytreo.java.soltest.entity.Catalog;

import javax.xml.bind.JAXBException;

/**
 * Parser для маршкаллинга-анмаршаллинга Catalog+Book
 * @author mytreo   27.01.2016.
 * @version 1.0
 */
public interface Parser {
    Object unMarshall(String inputXml, Class c) throws JAXBException;
    String marshall(Object o) throws JAXBException;
}