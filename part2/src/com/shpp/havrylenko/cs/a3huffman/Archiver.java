package com.shpp.havrylenko.cs.a3huffman;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static com.shpp.havrylenko.cs.a3huffman.Node.buildTree;

/**
 * Archives files by Huffman coding algorithm
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

                Path path = FileSystems.getDefault().getPath(args[3]);
                String input = new String(Files.readAllBytes(path));
                System.out.println(input);

                Map<Character, Integer> freqMap = new HashMap<>();

                for (Character c : input.toCharArray()) {
                    Integer freqC = freqMap.get(c);
                    freqMap.put(c, (freqC != null ? freqC : 0) + 1);
                }

                Node<Character> freqTree = buildTree(freqMap);
                String encodedString = encode(freqTree, input);
                EncodedFile<Character> resultsOfEncoding = new EncodedFile<>(freqMap, encodedString);
                Serializer.serialize(args[3], resultsOfEncoding);
                System.out.println("Your file was successfully archived. Name of archive: " + args[3] + ".ser");

                break;

            case "decode":

                EncodedFile deserializedData = Serializer.deserialize(args[3]);
                assert deserializedData != null;

                freqTree = buildTree(deserializedData.getFreqs());
                String decodedString = decode(freqTree, deserializedData.getEncodedString());
                String filename = args[3].substring(0, args[3].length() - 4);

                PrintWriter out = new PrintWriter(filename);
                out.print(decodedString);
                out.close();
                System.out.println("Your file was successfully dearchived. Name of file: " + filename);

                break;

            default:
                System.err.println("INCORRECT INPUT");
        }
    }

    /**
     * Encodes file contents
     *
     * @param freqTree tree of used in contents characters and their frequencies
     * @param toEncode String of contents
     * @param <T>      Character
     *
     * @return String of encoded data
     */
    public static <T> String encode(Node<T> freqTree, String toEncode) {
        assert freqTree != null;

        String encodedString = "";
        for (Character c : toEncode.toCharArray()) {
            encodedString += getCodes(freqTree, c, new StringBuilder());
        }
        return encodedString;
    }

    /**
     * Decodes Huffman coding String
     *
     * @param freqTree      tree of used in contents characters and their frequencies
     * @param encodedString String of Huffman code
     * @param <T>           Character
     *
     * @return String origin decoded contents
     */
    public static <T> String decode(Node<T> freqTree, String encodedString) {
        assert freqTree != null;

        String decodedString = "";
        Node<T> node = freqTree;

        for (Character code : encodedString.toCharArray()) {

            Node<T> tempNode;
            if (code == '0') {
                tempNode = node.getLeftChild();
            } else if (code == '1') {
                tempNode = node.getRightChild();
            } else {
                System.err.println("ERROR CODE");
                break;
            }

            T value = tempNode.getData();

            if (value != null) {
                decodedString += value.toString();
                node = freqTree;
            } else {
                node = tempNode;
            }

        }
        return decodedString;
    }

    /**
     * Gets Huffman code of Character
     *
     * @param freqTree   tree of used in contents characters and their frequencies
     * @param element    Character code of which is needed
     * @param codeString Mutable String where to save code
     * @param <T>        Character
     *
     * @return String Huffman code of {@code element}
     */
    private static <T> String getCodes(Node<T> freqTree, Character element, StringBuilder codeString) {
        assert freqTree != null;

        if (freqTree.getData() != null) {
            if (freqTree.getData().toString().equals("" + element)) {
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
