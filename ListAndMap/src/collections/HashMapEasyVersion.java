package collections;


import java.util.Arrays;
import java.util.Objects;

public class HashMapEasyVersion<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] table;
    private int size = 0;


    @SuppressWarnings("unchecked")
    public HashMapEasyVersion() {
        table = new Entry[DEFAULT_CAPACITY];
    }

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        int index = indexFor(hash(key));
        Entry<K, V> current = table[index];


        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }


        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;


        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = indexFor(hash(key));
        Entry<K, V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }


    public void remove(K key) {
        int index = indexFor(hash(key));
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }


    public boolean containsKey(K key) {
        int index = indexFor(hash(key));
        Entry<K, V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }


    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }


    public int size() {
        return size;
    }


    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode();
    }


    private int indexFor(int hash) {
        return Math.abs(hash) % table.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = table.length * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (Entry<K, V> entry : table) {
            while (entry != null) {
                int index = Math.abs(entry.key.hashCode()) % newCapacity;
                Entry<K, V> next = entry.next;
                entry.next = newTable[index];
                newTable[index] = entry;
                entry = next;
            }
        }

        table = newTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                sb.append(entry.key).append("=").append(entry.value).append(", ");
                entry = entry.next;
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}
