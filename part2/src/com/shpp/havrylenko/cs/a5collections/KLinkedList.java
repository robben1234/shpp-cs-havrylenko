package com.shpp.havrylenko.cs.a5collections;

import java.util.Iterator;

/**
 * My version of {@code LinkedList}
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class KLinkedList<E> implements Iterable<E>
{

    protected ListNode<E> head;
    protected ListNode<E> tail;
    protected int size;

    /**
     * Constructor
     */
    public KLinkedList() {
        head = new ListNode<E>(null);
        tail = new ListNode<E>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * Test
     * @param args
     */
    public static void main(String[] args) {
        KLinkedList<String> l = new KLinkedList<>();
        l.add("ASD");
        l.add("QWE");
        l.add("ZXC", 0);
        l.remove(1);
    }

    @Override
    public Iterator<E> iterator() {
        final KLinkedList<E> list = this;
        return new Iterator<E>() {
            final ListNode<E> root = list.head;
            ListNode<E> current = root;

            @Override
            public boolean hasNext() {
                return size != 0 && root.getNext() != null;
            }

            @Override
            public E next() {
                current = current.getNext();
                return current.getData();
            }
        };
    }

    /**
     * Adds element to the list
     * @param data element
     * @return true if added
     */
    public boolean add(E data) {
        ListNode<E> temp = new ListNode<E>(data);
        temp.setPrev(tail.getPrev());
        tail.getPrev().setNext(temp);
        temp.setNext(tail);
        tail.setPrev(temp);
        size++;
        return true;
    }

    /**
     * Adds element to the list on specified index
     * @param data element
     * @param index index
     * @return true if added
     */
    public boolean add(E data, int index) {
        ListNode<E> temp = new ListNode<>(data);
        ListNode<E> current = head.getNext();

        for (int i = 0; i < index && current.getNext() != tail; i++) {
            current = current.getNext();
        }

        temp.setPrev(current.getPrev());
        current.getPrev().setNext(temp);
        temp.setNext(current);
        current.setPrev(temp);
        size++;
        return true;
    }

    /**
     * Gets node of list by index
     * @param index index
     * @return node
     */
    protected ListNode<E> getNode(int index) {
        if (index < 0) {
            return null;
        }
        if (index == 0) {
            return head.getNext();
        }
        if (index == size) {
            return tail.getPrev();
        }

        ListNode<E> current = head.getNext();

        for (int i = 0; i < index; i++) {
            if (current.getNext() == null) {
                return null;
            }
            current = current.getNext();
        }
        return current;

    }

    /**
     * Gets value of specified index
     * @param index index
     * @return value
     */
    public E get(int index) {
        return getNode(index).getData();
    }

    /**
     * Removes node of specified index
     * @param index index
     * @return true if removed
     */
    public boolean remove(int index) {
        if (index < 1 || index > size) {
            return false;
        }

        ListNode<E> current = getNode(index);
        current.getNext().setPrev(current.getPrev());
        current.getPrev().setNext(current.getNext());
        size--;
        return true;

    }

    /**
     * Gets size of list
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether list contains item
     * @param item item
     * @return true if contains
     */
    public boolean contains(E item) {
        ListNode<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.getData() == item) {
                return true;
            }
            current.getNext();
        }
        return false;
    }

    /**
     * Node of list
     * @param <T> type of data
     */
    private class ListNode<T> {
        T data;
        ListNode<T> prev;
        ListNode<T> next;

        ListNode(ListNode<T> prev, ListNode<T> next, T data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }

        ListNode(T data) {
            this(null, null, data);
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public ListNode<T> getPrev() {
            return prev;
        }

        public void setPrev(ListNode<T> prev) {
            this.prev = prev;
        }

        public ListNode<T> getNext() {
            return next;
        }

        public void setNext(ListNode<T> next) {
            this.next = next;
        }
    }

}
