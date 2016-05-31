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
    }

    /**
     * Extracts needed column from csv-file
     * @param filename name of file
     * @param columnIndex needed column
     * @return ArrayList of words
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {

        List<String> stringsFromFile;

        try {
            URL path = Assignment5Part3.class.getResource(filename);
            stringsFromFile = Files.readAllLines(Paths.get(path.toURI()));
        } catch(IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        ArrayList<String> resultingList = new ArrayList<>();
        for(String stringFromFile : stringsFromFile) {
            resultingList.add(fieldsIn(stringFromFile).get(columnIndex));
        }
        return resultingList;
    }

    /**
     * Gets fields from inserted csv-string
     * @param line string with comma-separated words
     * @return ArrayList of words
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> fieldsInString = new ArrayList<>();

        int startIndex = 0;
        int endIndex = 0;
        int commaIndex = 0;
        int prevCommaIndex = 0;
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == '\"') {
                startIndex = i;
                i += 1;
                while(line.charAt(i) != '\"' && i < line.length()) {
                    i++;
                }
                endIndex = i;
                fieldsInString.add(line.substring(startIndex, endIndex + 1));
            }
            if(line.charAt(i) == ',') {
                commaIndex = i;
                fieldsInString.add("\"" + line.substring(prevCommaIndex, commaIndex) + "\"");
                prevCommaIndex = commaIndex + 1;
            }
            if(i == line.length() - 1) {
                fieldsInString.add("\"" + line.substring(prevCommaIndex, i + 1) + "\"");
            }
        }
        return fieldsInString;
    }
}
