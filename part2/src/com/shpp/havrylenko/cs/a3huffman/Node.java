package com.shpp.havrylenko.cs.a3huffman;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Node<T> implements Comparable<Node<T>> {

    protected int freq;
    protected T data;
    protected Node<T> parent;
    protected Node<T> leftChild;
    protected Node<T> rightChild;

    public Node(int freq) {
        this.freq = freq;
    }

    public Node(Node<T> left, Node<T> right) {
        this(left.getFreq() + right.getFreq());
        leftChild = left;
        rightChild = right;
    }

    public Node(int freq, T val) {
        this(freq);
        data = val;
    }

    protected static <T> Node<T> buildTree(Map<T, Integer> freqMap) {

        PriorityQueue<Node<T>> nodes = new PriorityQueue<>();

        freqMap.keySet()
               .stream()
               .forEach(key -> nodes.offer(new Node<>(freqMap.get(key), key)));

        while (nodes.size() > 1) {
            Node<T> left = nodes.poll();
            Node<T> right = nodes.poll();

            nodes.offer(new Node<>(left, right));
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

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(Node node) {
        return freq - node.getFreq();
    }

    public boolean doesHaveChildren() {
        return (leftChild != null && rightChild != null);
    }
}
