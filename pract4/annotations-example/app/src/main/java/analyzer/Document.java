package analyzer;

import compile.ComparatorProperty;
import runtime.ValidPath;

public class Document {
    @ValidPath
    private String filepath;
    private long totalWords;
    private long uniqueWords;

    public Document(String filepath) {
        this.filepath = filepath;
    }

    public String filepath() {
        return filepath;
    }

    @ComparatorProperty
    public long totalWords() {
        return totalWords;
    }

    @ComparatorProperty
    public long uniqueWords() {
        return uniqueWords;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setUniqueWords(long uniqueWords) {
        this.uniqueWords = uniqueWords;
    }

    public void setTotalWords(long totalWords) {
        this.totalWords = totalWords;
    }

    @Override
    public String toString() {
        return "Document{" +
                "filepath='" + filepath + '\'' +
                ", totalWords=" + totalWords +
                ", uniqueWords=" + uniqueWords +
                '}';
    }
}