package analyzer;

import runtime.PathValidationProcessor;
import utils.FileLoader;

import java.io.File;
import java.util.*;

public class DirectoryAnalyzer {
    private final List<Document> documents = new ArrayList<>();

    public void addDocumentsFromDirectory(String filepath) {
        File folder = FileLoader.loadFolder(filepath);
        for (File file : Objects.requireNonNull(folder.listFiles()))
            addDocument(file);
    }

    public Document getDocumentWithLeastWords() {
        documents.sort(new DocumentComparatorByTotalWords());
        return documents.getFirst();
    }

    public Document getDocumentWithMostWords() {
        documents.sort(new DocumentComparatorByTotalWords());
        return documents.getLast();
    }

    public Document getDocumentWithLeastUniqueWords() {
        documents.sort(new DocumentComparatorByUniqueWords());
        return documents.getFirst();
    }

    public Document getDocumentWithMostUniqueWords() {
        documents.sort(new DocumentComparatorByUniqueWords());
        return documents.getLast();
    }

    public List<Document> getDocuments() {
        return documents;
    }

    private void addDocument(File file) {
        Document document = new Document(file.getAbsolutePath());
        validateDocument(document);
        analyzeDocument(file, document);
        documents.add(document);
    }

    private void validateDocument(Document document) {
        try {
            PathValidationProcessor.validate(document);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void analyzeDocument(File file, Document document) {
        TxtParser parser = new TxtParser();
        List<String> tokens = parser.parse(file);
        document.setTotalWords(tokens.size());
        Set<String> words = new HashSet<>(tokens);
        document.setUniqueWords(words.size());
    }

}