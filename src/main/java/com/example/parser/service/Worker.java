package com.example.parser.service;

import com.example.parser.entity.EDI_DC40;

import java.io.IOException;
import java.util.List;

public interface Worker {

    List<EDI_DC40> parseAndPush(String path) throws IOException;

    List<EDI_DC40> findAllMhdhbLessThan50();

}
