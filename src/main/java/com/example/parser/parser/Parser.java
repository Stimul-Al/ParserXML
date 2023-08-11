package com.example.parser.parser;

import com.example.parser.entity.E1WPA01;
import com.example.parser.entity.EDI_DC40;

import java.nio.file.Path;
import java.util.List;

public interface Parser {

    List<EDI_DC40> parseEDI_DC40(Path path);

    List<E1WPA01> parseE1WPA01(Path path);
}
