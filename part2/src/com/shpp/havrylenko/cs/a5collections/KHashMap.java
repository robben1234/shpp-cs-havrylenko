package com.shpp.havrylenko.cs.a5collections;

import java.util.HashSet;
import java.util.Set;

/**
 * My version of {@code HashMap} based on {@code KArrayList}
 *
 * @author Kyrylo Havrylenko
 * @see KArrayList
 */
public class KHashMap<K, V> {
    protected KArrayList<Entry<K, V>> map;

    /**
     * Constructor
     */
    public KHashMap() {
        map = new KArrayList<>();
    }

    /**
     * Puts an Entry to Map
     * @param key key of Entry
     * @param value value of Entry
     * @return value
     */
    public V put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);
        map.add(entry);
        return value;
    }

    /**
     * Gets a value of key from Map
     * @param key key
     * @return value
     */
    public V get(K key) {
        int index = getIndex(key);
        return map.getElements()[index].value;
    }

    /**
     * Removes Entry from Map by key
     * @param key key
     * @return value
     */
    public V remove(K key) {
        int index = getIndex(key);
        V res = map.getElements()[index].value;
        map.remove(index);
        return res;
    }

    /**
     * Finds index of key Entry in Map
     * @param key key
     * @return index || -2 if it's not in Map
     */
    private int getIndex(K key) {
        for (int i = 0; i < map.size(); i++) {
            if (map.getElements()[i].key == key)
                return i;
        }
        return -2;
    }

    /**
     * Gets size of Map
     * @return size
     */
    public int size() {
        return map.size();
    }

    /**
     * Returns HashSet of keys
     * @return keySet
     */
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        for (int i = 0; i < size(); i++) {
            keySet.add(map.getElements()[i].key);
        }
        return keySet;
    }

    /**
     * Class to store keys and values
     * @param <K> key
     * @param <V> value
     */
    protected class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
