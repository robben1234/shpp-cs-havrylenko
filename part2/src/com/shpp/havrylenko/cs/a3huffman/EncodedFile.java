package com.shpp.havrylenko.cs.a3huffman;

 /*
 * EncodedFile   6/21/16, 04:26
 *
 * By Kyrylo Havrylenko
 *
 */

import java.io.Serializable;

/**
 * Util class to save data for serialization or deserialization
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class EncodedFile<T> implements Serializable {

    Node<T> tree;
    String encodedString;

    EncodedFile(Node<T> tree, String encodedString) {
        this.tree = tree;
        this.encodedString = encodedString;
    }

    public Node<T> getTree() {
        return tree;
    }

    public void setTree(Node<T> tree) {
        this.tree = tree;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }
}
