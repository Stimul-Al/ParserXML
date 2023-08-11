package com.example.parser.service;

import com.example.parser.entity.EDI_DC40;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@TestPropertySource(value = "classpath:application.yml")
class XmlWorkerTest {

    @Value("${project.file.path}")
    private String path;

    @Autowired
    private Worker worker;

    @Test
    void testParseAndPush() throws IOException {
        long start = System.currentTimeMillis();

        List<EDI_DC40> answer = worker.parseAndPush(path);

        long finish = System.currentTimeMillis();

        System.out.println("Consumed time for parse xml and push: " + (finish - start) + " millis");

        Assertions.assertNotNull(answer);
    }
    @Test
    void testFindAll() {
        List<EDI_DC40> answer = worker.findAllMhdhbLessThan50();

        Assertions.assertNotNull(answer);
    }

}