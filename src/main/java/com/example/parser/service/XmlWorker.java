package com.example.parser.service;

import com.example.parser.repository.E1WPA01Repository;
import lombok.AllArgsConstructor;
import com.example.parser.entity.E1WPA01;
import com.example.parser.entity.EDI_DC40;
import com.example.parser.parser.Parser;
import com.example.parser.repository.EDI_DC40Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class XmlWorker implements Worker {

    private final static String PATH = "src/main/resources/WP_PL.xml";

    private final Parser parser;

    private final E1WPA01Repository e1WPA01Repository;
    private final EDI_DC40Repository ediDc40Repository;

    @Override
    public String parseAndPush() throws IOException {
        Map<String, E1WPA01> map = new HashMap<>();
        List<EDI_DC40> ediDc40 = parser.parseEDI_DC40(Paths.get(PATH));
        List<E1WPA01> e1WPA01 = parser.parseE1WPA01(Paths.get(PATH));

        e1WPA01Repository.saveAll(e1WPA01);

        ediDc40.forEach(entity -> entity.setRCVPRN(map.get(entity.getRCVPRN().getFILIALE())));

        ediDc40Repository.saveAll(ediDc40);


        return ediDc40.get(0).getDOCNUM();
    }
}
