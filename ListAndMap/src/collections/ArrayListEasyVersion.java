package collections;

import java.util.Arrays;
import java.util.Objects;

public class ArrayListEasyVersion<T> {

    private Object[] objects;
    private int size = 0;

    private static final int DEF_CAPACITY = 10;


    public ArrayListEasyVersion() {
        objects = new Object[DEF_CAPACITY];
    }

    public ArrayListEasyVersion(int size) {
        objects = new Object[size];
    }


    public void add(T object) {
        if (objects.length == size) {
            increase();
        }
        objects[size++] = object;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) objects[index];
    }

    public void remove(int index) {
        checkIndex(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(objects, index + 1, objects, index, numMoved);
        }
        objects[--size] = null;
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && objects[i] == null) ||
                    (element != null && element.equals(objects[i]))) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        objects = new Object[DEF_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }


    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }


    private void increase() {
        int newSize = objects.length * 2;
        objects = Arrays.copyOf(objects, newSize);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayListEasyVersion<?> that = (ArrayListEasyVersion<?>) o;
        if (size != that.size) return false;
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(objects[i], that.objects[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(objects), size);
    }

    @Override
    public String toString() {
        return "ArrayListEasyVersion{" +
                "objects=" + Arrays.toString(objects) +
                ", size=" + size +
                '}';
    }
}

