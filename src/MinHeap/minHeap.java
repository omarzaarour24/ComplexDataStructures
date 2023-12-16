package MinHeap;

import java.util.Arrays;
//https://www.geeksforgeeks.org/min-heap-in-java/
public class minHeap<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private Node<T>[] heap;
    private int size;

    public minHeap() {
        heap = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    // Insert an element with a priority into the heap
    public void insert(int priority, T data) {
        if (size == heap.length) {
            // Resize the heap if it's full
            resizeHeap();
        }

        Node<T> newNode = new Node<>(priority, data);
        heap[size] = newNode;
        heapifyUp(size);
        size++;
    }

    // Extract the element with the highest priority (minimum priority)
    public T extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T minData = heap[0].getData();
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);

        return minData;
    }

    // Check if the heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Heapify up (used during insertion)
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index].getPriority() < heap[parentIndex].getPriority()) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Heapify down (used during extraction)
    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        if (leftChild < size && heap[leftChild].getPriority() < heap[smallest].getPriority()) {
            smallest = leftChild;
        }

        if (rightChild < size && heap[rightChild].getPriority() < heap[smallest].getPriority()) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // Resize the heap array
    private void resizeHeap() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        Node<T> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public String graphViz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph MinHeap {\n");

        for (int i = 0; i < size; i++) {
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;

            dot.append("  " + i + " [label=\"" + heap[i].getData() + "\"];\n");

            if (leftChildIndex < size) {
                dot.append("  " + i + " -> " + leftChildIndex + " [label=\"L\"];\n");
            }

            if (rightChildIndex < size) {
                dot.append("  " + i + " -> " + rightChildIndex + " [label=\"R\"];\n");
            }
        }

        dot.append("}\n");
        return dot.toString();
    }
    // Add an element with a default priority of 0
    public void push(T data) {
        if (data==null){
            throw new NullPointerException();
        }
        insert((int) data, data);
    }

    // Peek at the element with the highest priority (minimum priority)
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0].getData();
    }

    // Pop and return the element with the highest priority (minimum priority)
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T minData = heap[0].getData();
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return minData;
    }

    // Build a min-heap from an arbitrary array of elements
    public void buildHeap(T[] dataArray) {
        size = dataArray.length;
        for (int i = 0; i < size; i++) {
            heap[i] = new Node<>((int) dataArray[i], dataArray[i]);
        }

        for (int i = (size / 2 - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }

    // Inner class representing a node with priority and data
    private static class Node<T> {
        private int priority;
        private T data;

        Node(int priority, T data) {
            this.priority = priority;
            this.data = data;
        }

        int getPriority() {
            return priority;
        }

        T getData() {
            return data;
        }
    }
}
