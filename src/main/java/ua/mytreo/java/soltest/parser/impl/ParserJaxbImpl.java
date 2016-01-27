package ua.mytreo.java.soltest.parser.impl;


import ua.mytreo.java.soltest.parser.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * JAXB parser для маршкаллинга-анмаршаллинга Catalog+Book
 * @author mytreo   27.01.2016.
 * @version 1.0
 */


//TO DO дописать чтобы тесты что то таки проверяли
public class ParserJaxbImpl implements Parser {


    //TO DO unMarshall not working
    @Override
    public Object unMarshall(String inputXml, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        //unMarshall object from xml string
        InputStream reqXmlStream = new ByteArrayInputStream(inputXml.getBytes(StandardCharsets.UTF_8));

        return unmarshaller.unmarshal(reqXmlStream);
    }


   //TO DO remove <books>
    @Override
    public String marshall(Object o) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                Boolean.TRUE);

        //write XML to an array of bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(o, baos);

        return baos.toString();
    }
}
