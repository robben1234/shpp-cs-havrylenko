package com.shpp.havrylenko.cs.a5collections;

import java.util.Arrays;

/**
 * My version of {@code PriorityQueue} based on {@code KArrayList}
 *
 * @author Kyrylo Havrylenko
 * @see KArrayList
 */
public class KPriorityQueue<E> {
    KArrayList<E> list;
    int size;

    /**
     * Constructor
     */
    public KPriorityQueue() {
        list = new KArrayList<E>();
        list.add(null);
        size = 0;
    }

    /**
     * Gets size of queue
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Adds element to the queue
     * @param e element
     * @return true if added
     */
    public boolean offer(E e) {
        list.add(e);
        size++;
        Arrays.sort(list.elements);
        return true;
    }

    /**
     * Removes top element of the queue
     * @return element
     */
    public E poll() {
        E res = list.get(0);
        list.remove(0);
        size--;
        Arrays.sort(list.elements);
        return res;
    }
}
