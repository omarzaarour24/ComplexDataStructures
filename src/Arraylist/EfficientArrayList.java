package Arraylist;

import java.util.Arrays;
import java.util.Iterator;
public class EfficientArrayList<T> implements Iterable<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] data;
    private int size;

    public EfficientArrayList() {
        this.data = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void add(T value) {
        if (size == data.length) {
            // If the current array is full, double its capacity
            ensureCapacity();
        }
        data[size++] = value;
    }
//
    public boolean contains(T value) {
        for (T element : this) {
            if (element != null && element.equals(value)) {
                return true;
            }
        }
        return false;
    }


    public T remove(T key) {
        if (size==0){
            throw  new IndexOutOfBoundsException("list is empty");
        }
        for (int i = 0; i < size; i++) {
            if (data[i] != null && ((T) data[i]).equals(key)) {
                T removedValue = (T) data[i];
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                data[size - 1] = null;
                size--;
                return removedValue;
            }
        }
        return null; // Element not found
    }

    private void ensureCapacity() {
        int newCapacity = data.length * 2;
        data = Arrays.copyOf(data, newCapacity);
    }

    //this makes it so that we can use an enhanced for loop
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return (T) data[currentIndex++];
            }
        };
    }

    public int size() {
        return size;
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        data[index] = element;
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return (T) data[index];
    }

    public void reverse() {
        int left = 0;
        int right = size() - 1;
        while (left < right) {
            T temp = get(left);
            set(left, get(right));
            set(right, temp);
            left++;
            right--;
        }
    }
    public EfficientArrayList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > toIndex || toIndex > size) {
            throw new IndexOutOfBoundsException("Invalid indices for subList.");
        }

        EfficientArrayList<T> subList = new EfficientArrayList<>();
        subList.data = Arrays.copyOfRange(data, fromIndex, toIndex);
        subList.size = toIndex - fromIndex;

        return subList;
    }
    public boolean isEmpty(){
        if (size==0){
            return true;
        }else{
            return false;
        }
    }
}
