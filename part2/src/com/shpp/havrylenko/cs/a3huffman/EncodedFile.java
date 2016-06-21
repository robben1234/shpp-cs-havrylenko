package com.shpp.havrylenko.cs.a3huffman;

 /*
 * EncodedFile   6/21/16, 04:26
 *
 * By Kyrylo Havrylenko
 *
 */

import java.io.Serializable;
import java.util.Map;

/**
 * Util class to save data for serialization or deserialization
 *
 * @param <T>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class EncodedFile<T> implements Serializable {

    //    Node<T> tree;
    String encodedString;
    Map<Character, Integer> freqs;

    EncodedFile(Map<Character, Integer> freqs, String encodedString) {
        this.freqs = freqs;
        this.encodedString = encodedString;
    }

    public Map<Character, Integer> getFreqs() {
        return freqs;
    }

    public void setFreqs(Map<Character, Integer> freqs) {
        this.freqs = freqs;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }
}
