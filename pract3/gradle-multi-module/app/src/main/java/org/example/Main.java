package org.example;


public class Main {

    public static void main(String[] args) {

        String testDirectory = "C:\\Users\\H2SO4\\Desktop\\test";

        SimpleDictionary dictionary = new SimpleDictionary();
        dictionary.addFilesFromDirectory(testDirectory);

        for (String fileName : dictionary.findWord("the")) {
            System.out.println(fileName);
        }
    }

}
