package com.shpp.havrylenko.cs.a5collections;

import java.util.Arrays;

/**
 * My version of {@code ArrayList}
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class KArrayList<E> {
    protected static final int CAP = 10;
    protected int size = 0;
    protected Object elements[];

    /**
     * Constructor
     */
    public KArrayList() {
        elements = new Object[CAP];
    }

    /**
     * Adds an element to the tail of list
     * @param e element
     */
    public void add(E e) {
        add(e, size);
    }

    /**
     * Adds an element to the specific index of list
     * @param e element
     * @param index index
     */
    public void add(E e, int index) {
        if (size == elements.length) {
            makeCapBigger();
        }
        size++;
        for (int j = index; j < size; j++) {
            elements[j + 1] = elements[j];
        }
        elements[index] = e;
    }

    /**
     * Makes inner array twice bigger
     */
    private void makeCapBigger() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    /**
     * Gets element of index
     * @param index index
     * @return element
     */
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        return (E) elements[index];
    }

    /**
     * Removes element of index from list
     * @param index index
     */
    public void remove(int index) {
        size--;
        for (int j = index; j < size; j++) {
            elements[j] = elements[j + 1];
        }
    }

    /**
     * Gets size of list
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Finds index of element in list
     * @param e element
     * @return index || -2 if element is not in list
     */
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == e)
                return i;
        }
        return -2;
    }

    /**
     * Returns array of elements from list
     * @return array of elements
     */
    public E[] getElements() {
        return (E[]) elements;
    }
}
