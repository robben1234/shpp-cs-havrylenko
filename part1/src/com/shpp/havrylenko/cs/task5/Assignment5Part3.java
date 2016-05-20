package com.shpp.havrylenko.cs.task5;

 /*
 * Assignment5Part3   5/20/16, 15:26
 *
 * By Kyrylo Havrylenko
 *
 */

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Allows user to play in american road game via console
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment5Part3 {

    private static List<String> stringsFromFile;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            URL path = Assignment5Part3.class.getResource("en-dictionary.txt");
            stringsFromFile = Files.readAllLines(Paths.get(path.toURI()));
        } catch(IOException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }


        while(true) {
            String word = in.nextLine();
            System.out.println("Resulting words: ");
            giveAnswersForGame(word.toLowerCase()).forEach(System.out::println);
        }
    }

    /**
     * Looks for needed words in dictionary-list
     * @param word 3-letter string
     * @return list of strings
     */
    private static List<String> giveAnswersForGame(String word) {

        List<String> resultingStrings = new LinkedList<>();
        stringsFromFile.forEach((String wordFromFile) -> {
            for(int i = 0; i < wordFromFile.length(); i++) {
                if(wordFromFile.charAt(i) == word.charAt(0)) {
                    for(int j = i + 1; j < wordFromFile.length(); j++) {
                        if(wordFromFile.charAt(j) == word.charAt(1)) {
                            for(int k = j + 1; k < wordFromFile.length(); k++) {
                                if(wordFromFile.charAt(k) == word.charAt(2)) {
                                    resultingStrings.add(wordFromFile);
                                }
                            }
                        }
                    }
                }
            }
        });
        return resultingStrings;
    }
}
