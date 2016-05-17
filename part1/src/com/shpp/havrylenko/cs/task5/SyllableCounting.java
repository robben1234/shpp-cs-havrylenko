package com.shpp.havrylenko.cs.task5;

 /*
 * Assignment5Part1   5/17/16, 16:33
 *
 * By Kyrylo Havrylenko
 *
 */

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculate syllables in word
 *
 * @author Kyrylo Havrylenko
 * @see
 */

public class SyllableCounting extends TextProgram {
    @Override
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while(true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     *
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {

        int syllables = 0;

        List<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');

        for(int i = 0; i < word.length(); i++) {
            Character curChar = word.charAt(i);
            if(vowels.contains(curChar)) {
                syllables++;
                if((i == word.length() - 1 && curChar == 'e') || (i > 0 &&
                        vowels.contains(word.charAt(i - 1)))) {
                    syllables--;
                }
            }
        }

        return (syllables > 0) ? syllables : 1;
    }
}

