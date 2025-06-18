import analyzer.DirectoryAnalyzer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DirectoryAnalyzer analyzer = new DirectoryAnalyzer();

        boolean isRunning = true;
        while (isRunning) {

            System.out.println("Press 1 to add files from directory");
            System.out.println("Press 2 to get the document with most words");
            System.out.println("Press 3 to get the document with most unique words");
            System.out.println("Press 4 to quit");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter directory path: ");
                    String directoryPath = scanner.nextLine();
                    analyzer.addDocumentsFromDirectory(directoryPath);
                    System.out.println("Documents added successfully.");
                    break;
                case 2:
                    System.out.println(analyzer.getDocumentWithMostWords());
                    break;
                case 3:
                    System.out.println(analyzer.getDocumentWithMostUniqueWords());
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;
            }
        }
    }


}