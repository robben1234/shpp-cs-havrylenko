package com.shpp.havrylenko.cs.a3huffman;

 /*
 * Serializer   6/21/16, 04:38
 *
 * By Kyrylo Havrylenko
 *
 */

import java.io.*;

/**
 * Util class to serialize class
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Serializer {

    /**
     * Serializes {@code EncodedFile}
     * @param filename String where to save data (without '.ser')
     * @param toSerialize Instance of class to serialize
     */
    public static void serialize(String filename, EncodedFile toSerialize) {

        try {
            FileOutputStream fos = new FileOutputStream(filename + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(toSerialize);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Deserializes {@code EncodedFile}
     * @param filename String name of file to deserialize
     * @return EncodedFile deserialized data
     */
    public static EncodedFile deserialize(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            EncodedFile result = (EncodedFile) ois.readObject();
            ois.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
