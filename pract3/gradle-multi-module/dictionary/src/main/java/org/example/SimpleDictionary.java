package org.example;

import java.io.File;
import java.util.*;

public class SimpleDictionary implements Dictionary {

    private final Map<String, List<Integer>> posting = new HashMap<>();
    private final Map<Integer, String> files = new HashMap<>();

    @Override
    public void addFilesFromDirectory(String directory) {
        File folder = FileLoader.loadFolder(directory);
        for (File file : Objects.requireNonNull(folder.listFiles()))
            addTermsFromFile(file);
    }

    @Override
    public void addFile(String fileName) {
        File file = FileLoader.loadFile(fileName);
        addTermsFromFile(file);
    }

    @Override
    public List<String> findWord(String word) {
        String normalizedWord = word.toLowerCase();
        List<Integer> fileIds = posting.get(normalizedWord);
        return getFileNames(fileIds);
    }

    private void addTermsFromFile(File file) {
        int docID = files.size();
        files.put(files.size(), file.getName());
        TxtParser parser = new TxtParser();
        addTerms(parser.parse(file), docID);
    }

    private void addTerms(List<String> terms, int docID) {
        for (String term : terms) {
            List<Integer> files = posting.getOrDefault(term, new ArrayList<>());
            if (!files.contains(docID))
                files.add(docID);
            posting.put(term, files);
        }
    }

    private List<String> getFileNames(List<Integer> docIDs) {
        if (docIDs == null)
            return new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        for (int i : docIDs)
            fileNames.add(files.get(i));
        return fileNames;
    }

}
