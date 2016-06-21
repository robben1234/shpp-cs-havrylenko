package com.shpp.havrylenko.cs.a3huffman;

 /*
 * Serializer   6/21/16, 04:38
 *
 * By Kyrylo Havrylenko
 *
 */

import java.io.*;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Serializer {

    public static void serialize(String filename, EncodedFile toSerialize) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(toSerialize);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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
