package com.example.parser.service;

import lombok.AllArgsConstructor;
import com.example.parser.entity.EDI_DC40;
import com.example.parser.parser.Parser;
import com.example.parser.repository.EDI_DC40Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class XmlWorker implements Worker {

    private final static String PATH = "src/main/resources/WP_PL.xml";

    private final Parser parser;
    private final EDI_DC40Repository ediDc40Repository;

    @Override
    @Transactional
    public String parseAndPush() {
        Map<String, EDI_DC40> map = parser.parseEDI_DC40(Paths.get(PATH));

        List<EDI_DC40> ediDc40s = ediDc40Repository.saveAll(new ArrayList<>(map.values()));

        return ediDc40s.get(0).getDOCNUM();
    }
}
