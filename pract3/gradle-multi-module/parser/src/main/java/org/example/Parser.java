package org.example;

import java.io.File;
import java.util.List;

public interface Parser {
    List<String> parse(File file);
}
