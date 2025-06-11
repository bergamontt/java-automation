package org.example;

import java.util.List;

public interface Dictionary {
    void addFilesFromDirectory(String directory);
    void addFile(String fileName);
    List<String> findWord(String word);
}
