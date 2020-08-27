package com.flippost.DAO.Samsung.NewDelivery.Models;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.flippost.Tools.RuleEngine.RuleEngine;
import com.flippost.Tools.Tools.FlippostTools;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SamsungNewDeliveryBase {

    private String newDeliveryXML;

    public void setNewDeliveryXML(final String XML) {
        this.newDeliveryXML = XML;
        Date date = new Date();
        SimpleDateFormat pattern = new SimpleDateFormat("dd-MM-yyyy_H_m_s");
        final String XML_FILE_NAME =
                "newDelivery"
                + pattern.format(date)
                + ".xml";
        final String XML_SAVE_PATH = RuleEngine.workPathRule()
                + "\\Flippost\\Samsung\\XML";
        final File PATH = new File(XML_SAVE_PATH);
        if(!PATH.exists())
            PATH.mkdirs();

        try {
            FlippostTools.saveToFile(XML_SAVE_PATH + "\\" + XML_FILE_NAME, XML);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void validateXML()
            throws SAXException,
                    IOException {
        final String SCHEMA_XSD_PATH = RuleEngine.workPathRule()
                + "\\Flippost\\"
                + "\\Samsung"
                + "\\XSD"
                + "\\PKGINF.xsd";
        Source source = new StreamSource(this.newDeliveryXML);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(SCHEMA_XSD_PATH));
        Validator validator = schema.newValidator();
        validator.validate(source);
    }

    public SamsungNewDelivery xmlToModel() throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        SamsungNewDelivery delivery = mapper.readValue(this.newDeliveryXML, SamsungNewDelivery.class);
        System.out.println(delivery.toString());
        return  delivery;
    }

}
