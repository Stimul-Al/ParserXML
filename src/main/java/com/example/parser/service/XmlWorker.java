package com.example.parser.service;

import lombok.AllArgsConstructor;
import com.example.parser.entity.E1WPA01;
import com.example.parser.entity.EDI_DC40;
import com.example.parser.parser.Parser;
import com.example.parser.repository.E1WPA01Repository;
import com.example.parser.repository.EDI_DC40Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@AllArgsConstructor
public class XmlWorker implements Worker {

    private final static String PATH = "src/main/resources/WP_PL.xml";

    private final Parser parser;
    private final E1WPA01Repository e1WPA01Repository;
    private final EDI_DC40Repository ediDc40Repository;

    @Override
    public boolean parseAndPush() throws IOException {
        EDI_DC40 ediDc40 = parser.parseEDI_DC40(Paths.get(PATH));
        E1WPA01 e1WPA01 = parser.parseE1WPA01(Paths.get(PATH));

        ediDc40.setE1WPA01(e1WPA01);

        EDI_DC40 savedEDI_DC40 = ediDc40Repository.save(ediDc40);


        return Objects.nonNull(savedEDI_DC40.getId());
    }
}
