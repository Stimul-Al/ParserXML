package com.example.parser.parser;

import com.example.parser.entity.EDI_DC40;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Paths;
import java.util.List;

@SpringBootTest
class ParserImplTest {

    @Value("${project.file.path}")
    private String path;
    @Autowired
    private Parser parser;

    @Test
    void testOnlyParse(){
        long start = System.currentTimeMillis();

        List<EDI_DC40> answer = parser.parseEDI_DC40(Paths.get(path));

        long finish = System.currentTimeMillis();

        System.out.println("Consumed time for read and parse xml: " + (finish - start) + " millis");

        Assertions.assertNotNull(answer);
    }

}