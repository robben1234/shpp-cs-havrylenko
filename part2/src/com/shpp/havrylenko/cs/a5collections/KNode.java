package com.shpp.havrylenko.cs.a5collections;

import java.io.Serializable;

/**
 * Tree data structure for Huffman coding
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class KNode<T> implements Comparable<KNode<T>>, Serializable {

    protected int freq;
    protected T data;
    protected KNode<T> parent;
    protected KNode<T> leftChild;
    protected KNode<T> rightChild;

    public KNode(int freq) {
        this.freq = freq;
    }

    public KNode(KNode<T> left, KNode<T> right) {
        this(left.getFreq() + right.getFreq());
        leftChild = left;
        rightChild = right;
    }

    public KNode(int freq, T val) {
        this(freq);
        data = val;
    }

    /**
     * Builds tree based on Character frequencies
     * More frequent character, more higher it is in tree
     *
     * @param freqMap Map of Characters and Frequencies
     * @param <T>     Character
     *
     * @return KNode<T> head of tree
     */
    public static <T> KNode<T> buildTree(KHashMap<T, Integer> freqMap) {

        KPriorityQueue<KNode<T>> nodes = new KPriorityQueue<>();

        freqMap.keySet()
               .stream()
               .forEach(key -> nodes.offer(new KNode<T>(freqMap.get(key), key)));

        while (nodes.size() > 1) {
            KNode<T> left = nodes.poll();
            KNode<T> right = nodes.poll();

            nodes.offer(new KNode<>(left, right));
        }

        return nodes.poll();
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public KNode<T> getParent() {
        return parent;
    }

    public void setParent(KNode<T> parent) {
        this.parent = parent;
    }

    public KNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(KNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public KNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(KNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(KNode KNode) {
        return freq - KNode.getFreq();
    }

    /**
     * Determines if {@code KNode} has children
     *
     * @return Boolean true if does have children
     */
    public boolean doesHaveChildren() {
        return (leftChild != null && rightChild != null);
    }
}
