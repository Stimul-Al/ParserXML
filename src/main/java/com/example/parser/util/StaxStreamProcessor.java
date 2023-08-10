package com.example.parser.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import java.io.InputStream;

public class StaxStreamProcessor implements AutoCloseable {

    private final static XMLInputFactory FACTORY = XMLInputFactory.newInstance();
    private final XMLStreamReader reader;
    private final Logger logger = LoggerFactory.getLogger(StaxStreamProcessor.class);

    public StaxStreamProcessor(InputStream is) throws XMLStreamException {
        this.reader = FACTORY.createXMLStreamReader(is);
    }

    public XMLStreamReader getReader() {
        return reader;
    }

    public boolean doUntil(int stopEvent, String value) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == stopEvent && value.equals(reader.getLocalName())) {
                return true;
            }
        }
        return false;
    }

    public boolean startElement(String element, String parent) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (parent != null && event == XMLEvent.END_ELEMENT &&
                    parent.equals(reader.getLocalName())) {
                return false;
            }
            if (event == XMLEvent.START_ELEMENT &&
                    element.equals(reader.getLocalName())) {
                return true;
            }
        }
        return false;
    }

    public String getAttribute(String name) throws XMLStreamException {
        return reader.getAttributeValue(null, name);
    }
    public String getText() throws XMLStreamException {
        return reader.getElementText();
    }

    @Override
    public void close() throws Exception {
        if(reader!=null) {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                logger.error("File doeesn't work:" + e.getMessage());
            }
        }
    }
}
