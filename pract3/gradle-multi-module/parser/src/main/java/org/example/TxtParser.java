package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TxtParser implements Parser {

    public List<String> parse(File file) {
        try {
            return parseThrowable(file);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(this.getClass().getName());
            logger.info(e.getMessage());
            return null;
        }
    }

    private List<String> parseThrowable(File file) throws IOException {
        List<String> tokens = new ArrayList<>();
        List<String> lines = readLines(file);
        for (String line : lines)
            tokens.addAll(parseLine(line));
        return tokens;
    }

    private List<String> readLines(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null)
            lines.add(line);
        reader.close();
        return lines;
    }

    private List<String> parseLine(String line) {
        SimpleTokenizer tokenizer = new SimpleTokenizer();
        return tokenizer.tokenize(line);
    }

}