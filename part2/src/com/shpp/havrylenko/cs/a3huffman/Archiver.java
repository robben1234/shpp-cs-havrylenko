package com.shpp.havrylenko.cs.a3huffman;

import java.io.IOException;
import java.io.PrintWriter;
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


    public static void main(String[] args) throws URISyntaxException, IOException {

        if (args.length != 4) {
            System.out.println("USAGE: java Archiver <mode> <filename>");
            System.out.println("<mode>: encode | decode");
            System.out.println("<filename>: URL of file to encode/decode");
            return;
        }

        switch (args[2]) {
            case "encode":

                URL path = Archiver.class.getResource(args[3]);
                String input = new String(Files.readAllBytes(Paths.get(path.toURI())));

                Map<Character, Integer> freqMap = new HashMap<>();

                for (Character c : input.toCharArray()) {
                    Integer freqC = freqMap.get(c);
                    freqMap.put(c, (freqC != null ? freqC : 0) + 1);
                }

                Node<Character> freqTree = buildTree(freqMap);
                String encodedString = encode(freqTree, input);
                EncodedFile<Character> resultsOfEncoding = new EncodedFile<>(freqTree, encodedString);
                Serializer.serialize(args[3] + ".ser", resultsOfEncoding);
                System.out.println("Your file was successfully archived. Name of archive: " + args[3] + ".ser");


                break;
            case "decode":

                EncodedFile deserData = Serializer.deserialize(args[3]);
                System.out.println(deserData.getEncodedString());

                String decodedString = decode(deserData.getTree(), deserData.getEncodedString());
                String filename = args[3].substring(0, args[3].length() - 4);

                PrintWriter out = new PrintWriter(filename);
                out.print(decodedString);
                out.close();
                System.out.println("Your file was successfully dearchived. Name of file: " + filename);

                break;
            default:
                System.err.println("INCORRECT INPUT");
                break;
        }
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

}
