package com.shpp.havrylenko.cs.a3huffman;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.shpp.havrylenko.cs.a3huffman.Node.buildTree;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Archiver {


    public static void main(String[] args) {

        try {

            URL path = Archiver.class.getResource("test.txt");
            String stringsFromFile = new String(Files.readAllBytes(Paths.get(path.toURI())));
            System.out.println(stringsFromFile);
        } catch (IOException |URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        String input = "asdzxcasd";
        Map<Character, Integer> freqMap = new HashMap<>();

        for (Character c : input.toCharArray()) {
            Integer freqC = freqMap.get(c);
            freqMap.put(c, (freqC != null ? freqC : 0) + 1);
        }

        Node<Character> freqTree = buildTree(freqMap);

        System.out.println("CODES:");
        printCodes(freqTree, new StringBuilder());

        String encodedString = encode(freqTree, input);
        System.out.println("RESULT OF ENCODING:");
        System.out.println(encodedString);

        System.out.println("RESULT OF DECODING:");
        System.out.println(decode(freqTree, encodedString));
    }

    public static <T> String encode(Node<T> freqTree, String toEncode) {
        assert freqTree != null;

        String encodedString = "";
        for (Character c : toEncode.toCharArray()) {
            encodedString += getCodes(freqTree, c, new StringBuilder());
        }
        return encodedString;
    }

    public static <T> String decode(Node<T> freqTree, String encodedString) {
        assert freqTree != null;

        String decodedString = "";

        Node<T> node = freqTree;
        for (Character code : encodedString.toCharArray()) {
            if (code == '0') {
                T value = node.getLeftChild().getData();
                if (value != null) {
                    decodedString += value.toString();
                    node = freqTree;
                } else {
                    node = node.getLeftChild();
                }
            } else if (code == '1') {
                T value = node.getRightChild().getData();
                if (value != null) {
                    decodedString += value.toString();
                    node = freqTree;
                } else {
                    node = node.getRightChild();
                }
            }
        }
        return decodedString;
    }

    private static <T> String getCodes(Node<T> freqTree, Character element, StringBuilder codeString) {
        assert freqTree != null;

        if (freqTree.getData() != null) {
            if (freqTree.getData() == element) {
                return codeString.toString();
            }
        } else if (freqTree.doesHaveChildren()) {

            codeString.append('0');
            String left = getCodes(freqTree.getLeftChild(), element, codeString);
            codeString.deleteCharAt(codeString.length() - 1);

            codeString.append('1');
            String right = getCodes(freqTree.getRightChild(), element, codeString);
            codeString.deleteCharAt(codeString.length() - 1);

            if (left == null)
                return right;
            else
                return left;
        }
        return null;
    }

    private static <T> void printCodes(Node<T> freqTree, StringBuilder codeString) {
        assert freqTree != null;

        if (freqTree.getData() != null) {
            System.out.println(freqTree.getData() + "\t" + freqTree.getFreq() + "\t\t" + codeString);
        } else if (freqTree.doesHaveChildren()) {
            codeString.append('0');
            printCodes(freqTree.getLeftChild(), codeString);
            codeString.deleteCharAt(codeString.length() - 1);

            codeString.append('1');
            printCodes(freqTree.getRightChild(), codeString);
            codeString.deleteCharAt(codeString.length() - 1);
        }
    }

}
