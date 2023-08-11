package com.example.parser.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
