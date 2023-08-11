package com.example.parser.parser;

import com.example.parser.entity.E1WPA01;
import com.example.parser.entity.EDI_DC40;
import com.example.parser.entity.EntityName;
import com.example.parser.util.StaxStreamProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.xml.stream.XMLStreamConstants.*;

@Component
public class ParserImpl implements Parser {

    private final Logger logger = LoggerFactory.getLogger(ParserImpl.class);

    private XMLStreamReader reader;

    @Override
    public List<EDI_DC40> parseEDI_DC40(Path path) {
        Map<String, EDI_DC40> edidc40Map = new HashMap<>();
        List<EDI_DC40> ediDc40MapWithNullForeignKey = new ArrayList<>();
        List<E1WPA01> e1wpa01List = new ArrayList<>();

        E1WPA01 e1WPA01 = new E1WPA01();
        EDI_DC40 ediDc40 = new EDI_DC40();

        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(path))) {

            this.reader = processor.getReader();

            while (reader.hasNext()) {
                int eventType = reader.next();

                if (eventType == START_ELEMENT) {
                    parseElement(ediDc40, e1WPA01);
                }

                if (eventType == END_ELEMENT && reader.getName().getLocalPart().equals("EDI_DC40")) {
                        EDI_DC40 ediDc40ToSave = ediDc40;

                        if(ediDc40ToSave.getRCVPRN().getFILIALE().isEmpty()) {  // we add entity to the list if it HAS empty field RCVPRN
                            ediDc40MapWithNullForeignKey.add(ediDc40ToSave);
                        } else {                                                // we add entity to the map if it HAS NOT empty field RCVPRN
                            edidc40Map.put(ediDc40ToSave.getRCVPRN().getFILIALE(), ediDc40ToSave);
                        }

                        ediDc40 = new EDI_DC40();
                }

                if (eventType == END_ELEMENT && reader.getName().getLocalPart().equals("E1WPA01")) {
                        E1WPA01 e1WPA01ToSave = e1WPA01;

                        e1wpa01List.add(e1WPA01ToSave);

                        e1WPA01 = new E1WPA01();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (E1WPA01 e : e1wpa01List) {
            if(edidc40Map.containsKey(e.getFILIALE())) {
                edidc40Map.get(e.getFILIALE()).setRCVPRN(e);
            } else {
                logger.info("Entity has empty FILIALE field");
            }

        }

        return getListToSave(edidc40Map, ediDc40MapWithNullForeignKey);
    }

    private void parseElement(EDI_DC40 ediDc40, E1WPA01 e1WPA01) throws XMLStreamException {
        int eventType;

        switch (reader.getName().getLocalPart()) {
            case EntityName.TABNAM:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setTABNAM(reader.getText());
                }
                break;
            case EntityName.MANDT:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setMANDT(reader.getText());
                }
                break;
            case EntityName.DOCNUM:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setDOCNUM(reader.getText());
                }
                break;
            case EntityName.DOCREL:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setDOCREL(reader.getText());
                }
                break;
            case EntityName.STATUS:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setSTATUS(reader.getText());
                }
                break;
            case EntityName.DIRECT:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setDIRECT(reader.getText());
                }
                break;
            case EntityName.OUTMOD:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setOUTMOD(reader.getText());
                }
                break;
            case EntityName.DOCTYP:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setDOCTYP(reader.getText());
                }
                break;
            case EntityName.MESTYP:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setMESTYP(reader.getText());
                }
                break;
            case EntityName.SNDPOR:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setSNDPOR(reader.getText());
                }
                break;
            case EntityName.RCVPRT:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setRCVPRT(reader.getText());
                }
                break;
            case EntityName.SNDPRN:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setSNDPRN(reader.getText());
                }
                break;
            case EntityName.SNDPRT:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setSNDPRT(reader.getText());
                }
                break;
            case EntityName.CREDAT:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setCREDAT(reader.getText());
                }
                break;
            case EntityName.CRETIM:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setCRETIM(reader.getText());
                }
                break;
            case EntityName.SERIAL:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setSERIAL(reader.getText());
                }
                break;
            case EntityName.RCVPRN:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    E1WPA01 e1WPA011 = new E1WPA01();
                    e1WPA011.setFILIALE(reader.getText());

                    ediDc40.setRCVPRN(e1WPA011);
                }
                break;
            case EntityName.FILIALE:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setFILIALE(reader.getText());
                }
                break;
            case EntityName.AENDKENNZ:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setAENDKENNZ(reader.getText());
                }
                break;
            case EntityName.AKTIVDATUM:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setAKTIVDATUM(reader.getText());
                }
                break;
            case EntityName.AENDDATUM:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setAENDDATUM(reader.getText());
                }
                break;
            case EntityName.HAUPTEAN:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setHAUPTEAN(reader.getText());
                }
                break;
            case EntityName.ARTIKELNR:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setARTIKELNR(reader.getText());
                }
                break;
            case EntityName.POSME:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setPOSME(reader.getText());
                }
                break;
            case EntityName.WARENGR:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setWARENGR(reader.getText());
                }
                break;
            case EntityName.VERPGEW:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setVERPGEW(reader.getText());
                }
                break;
            case EntityName.RABERLAUBT:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setRABERLAUBT(reader.getText());
                }
                break;
            case EntityName.PRDRUCK:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setPRDRUCK(reader.getText());
                }
                break;
            case EntityName.ARTIKANZ:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setARTIKANZ(reader.getText());
                }
                break;
            case EntityName.MHDHB:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setMHDHB(Integer.parseInt(reader.getText()));
                }
                break;
            case EntityName.QUALARTTXT:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setQUALARTTXT(reader.getText());
                }
                break;
            case EntityName.SPRASCODE:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setSPRASCODE(reader.getText());
                }
                break;
            case EntityName.TEXT:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setTEXT(reader.getText());
                }
                break;
            case EntityName.LFDNR:
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setLFDNR(reader.getText());
                }
                break;
        }
    }

    private static List<EDI_DC40> getListToSave(Map<String, EDI_DC40> edidc40Map,
            List<EDI_DC40> ediDc40MapWithNullForeignKey) {

        List<EDI_DC40> ediDc40List = new ArrayList<>(edidc40Map.values());
        ediDc40List.addAll(ediDc40MapWithNullForeignKey);

        return ediDc40List;
    }
}
