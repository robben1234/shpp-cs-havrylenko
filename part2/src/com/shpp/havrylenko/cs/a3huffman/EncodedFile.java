package com.shpp.havrylenko.cs.a3huffman;

 /*
 * EncodedFile   6/21/16, 04:26
 *
 * By Kyrylo Havrylenko
 *
 */

import com.shpp.havrylenko.cs.a5collections.KHashMap;

import java.io.Serializable;

/**
 * Util class to save data for serialization or deserialization
 *
 * @param <T>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class EncodedFile<T> implements Serializable {

    //    KNode<T> tree;
    String encodedString;
    KHashMap<Character, Integer> freqs;

    EncodedFile(KHashMap<Character, Integer> freqs, String encodedString) {
        this.freqs = freqs;
        this.encodedString = encodedString;
    }

    public KHashMap<Character, Integer> getFreqs() {
        return freqs;
    }

    public void setFreqs(KHashMap<Character, Integer> freqs) {
        this.freqs = freqs;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }
}
