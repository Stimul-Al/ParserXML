package com.example.parser.parser;

import com.example.parser.entity.EDI_DC40;

import java.nio.file.Path;
import java.util.Map;

public interface Parser {

    Map<String, EDI_DC40> parseEDI_DC40(Path path);
}
