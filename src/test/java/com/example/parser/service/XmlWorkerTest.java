package com.example.parser.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class XmlWorkerTest {

    @Autowired
    private Worker worker;

    @Test
    void test() throws IOException {
        boolean answer = worker.parseAndPush();

        Assertions.assertTrue(answer);
    }

}