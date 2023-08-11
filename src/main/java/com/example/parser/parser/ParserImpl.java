package com.example.parser.parser;

import com.example.parser.entity.E1WPA01;
import com.example.parser.entity.EDI_DC40;
import com.example.parser.util.StaxStreamProcessor;
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

    private XMLStreamReader reader;

    @Override
    public Map<String, EDI_DC40> parseEDI_DC40(Path path) {
        Map<String, EDI_DC40> ediDc40s = new HashMap<>();
        List<E1WPA01> e1WPA01s = new ArrayList<>();

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

                        ediDc40s.put(ediDc40ToSave.getRCVPRN().getFILIALE(), ediDc40ToSave);

                        ediDc40 = new EDI_DC40();
                }

                if (eventType == END_ELEMENT && reader.getName().getLocalPart().equals("E1WPA01")) {
                        E1WPA01 e1WPA01ToSave = e1WPA01;

                        e1WPA01s.add(e1WPA01ToSave);

                        e1WPA01 = new E1WPA01();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (E1WPA01 e : e1WPA01s) {
            ediDc40s.get(e.getFILIALE()).setRCVPRN(e);
        }

        return ediDc40s;
    }

    private void parseElement(EDI_DC40 ediDc40, E1WPA01 e1WPA01) throws XMLStreamException {
        int eventType;

        switch (reader.getName().getLocalPart()) {
            case "TABNAM":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setTABNAM(reader.getText());
                }
                break;
            case "MANDT":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setMANDT(reader.getText());
                }
                break;
            case "DOCNUM":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setDOCNUM(reader.getText());
                }
                break;
            case "DOCREL":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setDOCREL(reader.getText());
                }
                break;
            case "STATUS":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setSTATUS(reader.getText());
                }
                break;
            case "DIRECT":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setDIRECT(reader.getText());
                }
                break;
            case "OUTMOD":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setOUTMOD(reader.getText());
                }
                break;
            case "IDOCTYP":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setIDOCTYP(reader.getText());
                }
                break;
            case "MESTYP":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setMESTYP(reader.getText());
                }
                break;
            case "SNDPOR":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setSNDPOR(reader.getText());
                }
                break;
            case "RCVPRT":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setRCVPRT(reader.getText());
                }
                break;
            case "SNDPRN":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setSNDPRN(reader.getText());
                }
                break;
            case "SNDPRT":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setSNDPRT(reader.getText());
                }
                break;
            case "CREDAT":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setCREDAT(reader.getText());
                }
                break;
            case "CRETIM":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setCRETIM(reader.getText());
                }
                break;
            case "SERIAL":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    ediDc40.setSERIAL(reader.getText());
                }
                break;
            case "RCVPRN":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    E1WPA01 e1WPA011 = new E1WPA01();
                    e1WPA011.setFILIALE(reader.getText());

                    ediDc40.setRCVPRN(e1WPA011);
                }
                break;
            case "FILIALE":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setFILIALE(reader.getText());
                }
                break;
            case "AENDKENNZ":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setAENDKENNZ(reader.getText());
                }
                break;
            case "AKTIVDATUM":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setAKTIVDATUM(reader.getText());
                }
                break;
            case "AENDDATUM":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setAENDDATUM(reader.getText());
                }
                break;
            case "HAUPTEAN":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setHAUPTEAN(reader.getText());
                }
                break;
            case "ARTIKELNR":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setARTIKELNR(reader.getText());
                }
                break;
            case "POSME":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setPOSME(reader.getText());
                }
                break;
            case "WARENGR":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setWARENGR(reader.getText());
                }
                break;
            case "VERPGEW":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setVERPGEW(reader.getText());
                }
                break;
            case "RABERLAUBT":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setRABERLAUBT(reader.getText());
                }
                break;
            case "PRDRUCK":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setPRDRUCK(reader.getText());
                }
                break;
            case "ARTIKANZ":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setARTIKANZ(reader.getText());
                }
                break;
            case "MHDHB":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setMHDHB(reader.getText());
                }
                break;
            case "QUALARTTXT":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setQUALARTTXT(reader.getText());
                }
                break;
            case "SPRASCODE":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setSPRASCODE(reader.getText());
                }
                break;
            case "TEXT":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setTEXT(reader.getText());
                }
                break;
            case "LFDNR":
                eventType = reader.next();
                if (eventType == CHARACTERS) {
                    e1WPA01.setLFDNR(reader.getText());
                }
                break;
        }
    }
}
