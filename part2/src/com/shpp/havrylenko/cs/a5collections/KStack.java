package com.shpp.havrylenko.cs.a5collections;

/**
 * My version {@code Stack} based on {@code KArrayList}
 *
 * @author Kyrylo Havrylenko
 * @see KArrayList
 */
public class KStack<E> extends KArrayList<E> {
    /**
     * Constructor
     */
    public KStack() {
        super();
    }

    /**
     * Gets info about emptiness of stack
     * @return true if it's empty, false if is not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets head element of stack but not removes it
     * @return element
     */
    public E peek() {
        return (E) elements[0];
    }

    /**
     * Removes head element of stack and returns it
     * @return element
     */
    public E pop() {
        E res = (E) elements[0];
        remove(0);
        return res;
    }

    /**
     * Pushes element to the head of stack
     * @param item element
     * @return element
     */
    public E push(E item) {
        add(item, 0);
        return item;
    }

    /**
     * Searches for element in stack
     * @param e element
     * @return index of element || -2 if it's not in stack
     */
    public int search(E e) {
        return indexOf(e) + 1;
    }
}
