package com.shpp.havrylenko.cs.task5;

 /*
 * Assignment5Part4   5/20/16, 17:10
 *
 * By Kyrylo Havrylenko
 *
 */

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment5Part4 {
    public static void main(String[] args) {
        Assignment5Part4 instance = new Assignment5Part4();
        System.out.println(instance.extractColumn("food-origins.csv", 0));
        System.out.println(instance.extractColumn("food-origins.csv", 1));
        System.out.println(instance.extractColumn("test_csv.csv", 0));
        System.out.println(instance.extractColumn("test_csv.csv", 1));
        System.out.println(instance.extractColumn("test_csv.csv", 2));
    }

    /**
     * Extracts needed column from csv-file
     *
     * @param filename    name of file
     * @param columnIndex needed column
     *
     * @return ArrayList of words
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {

        List<String> stringsFromFile;

        try {
            URL path = Assignment5Part3.class.getResource(filename);
            stringsFromFile = Files.readAllLines(Paths.get(path.toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        ArrayList<String> resultingList = new ArrayList<>();
        for (String stringFromFile : stringsFromFile) {
            resultingList.add(fieldsIn(stringFromFile).get(columnIndex));
        }
        return resultingList;
    }

    /**
     * Gets fields from inserted csv-string
     *
     * @param line string with comma-separated words
     *
     * @return ArrayList of words
     */
    private ArrayList<String> fieldsIn(String line) {

        ArrayList<String> fieldsInString = new ArrayList<>();
        char separator = ',';
        Boolean quoted = false;
        StringBuilder fieldBuilder = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);
            fieldBuilder.append(c);

            if (c == '"') {
                quoted = !quoted;
            }

            if ((!quoted && c == separator) || i + 1 == line.length()) {
                fieldsInString.add(fieldBuilder.toString()
                                               .replaceAll(separator + "$", "")
                                               .replaceAll("^\"|\"$", "")
                                               .replace("\"\"", "\"")
                                               .trim());
                fieldBuilder = new StringBuilder();
            }

            if (c == separator && i + 1 == line.length()) {
                fieldsInString.add("");
            }
        }

        return fieldsInString;
    }
}
