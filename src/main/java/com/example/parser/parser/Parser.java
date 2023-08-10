package com.example.parser.parser;

import com.example.parser.entity.E1WPA01;
import com.example.parser.entity.EDI_DC40;

import java.nio.file.Path;

public interface Parser {

    EDI_DC40 parseEDI_DC40(Path path);

    E1WPA01 parseE1WPA01(Path path);
}
