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
    private final Parser parser;
    private final EDI_DC40Repository ediDc40Repository;

    @Override
    @Transactional
    public List<EDI_DC40> parseAndPush(String path) {
        List<EDI_DC40> listEntitiesToSave = parser.parseEDI_DC40(Paths.get(path));

        ediDc40Repository.saveAll(listEntitiesToSave);

        return listEntitiesToSave;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EDI_DC40> findAllMhdhbLessThan50() {
        return ediDc40Repository.findAllMhdhbLessThan50();
    }
}
