package MinHeap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    private MinHeap.minHeap<Integer> minHeap;

    @BeforeEach
    void setUp() {
        minHeap = new minHeap<>();
    }
    @Test
    void testGraphViz_GoodWeather() {
        minHeap.insert(30, 30);
        minHeap.insert(10, 10);

        String expectedGraph = "digraph MinHeap {\n" +
                "  0 [label=\"10\"];\n" +
                "  0 -> 1 [label=\"L\"];\n" +
                "  1 [label=\"30\"];\n"+"}\n";

        assertEquals(expectedGraph, minHeap.graphViz());
    }

    @Test
    void testPush_GoodWeather() {
        minHeap.push(30);
        minHeap.push(10);
        minHeap.push(20);

        assertEquals(10, minHeap.pop());
        assertEquals(20, minHeap.pop());
        assertEquals(30, minHeap.pop());
        assertTrue(minHeap.isEmpty());
    }
    // Bad Weather Tests
    @Test
    void testGraphViz_EmptyHeap() {
        String expectedGraph = "digraph MinHeap {\n}\n";

        assertEquals(expectedGraph, minHeap.graphViz());
    }
    @Test
    void testPush_BadInput() {
        assertThrows(NullPointerException.class, () -> minHeap.push(null));
    }
    // Good Weather Tests
    @Test
    void testInsertAndExtractMin_ValidInput() {
        minHeap.insert(3, 30);
        minHeap.insert(1, 10);
        minHeap.insert(2, 20);
        minHeap.insert(4, 40);

        assertEquals(10, minHeap.extractMin());
        assertEquals(20, minHeap.extractMin());
        assertEquals(30, minHeap.extractMin());
        assertEquals(40, minHeap.extractMin());
        assertTrue(minHeap.isEmpty());
    }

    @Test
    void testInsertAndPop_ValidInput() {
        minHeap.insert(30, 30);
        minHeap.insert(10, 10);
        minHeap.insert(20, 20);
        minHeap.insert(40, 40);

        assertEquals(10, minHeap.pop()); // Should return the smallest element (10)
        assertEquals(20, minHeap.pop());
        assertEquals(30, minHeap.pop());
        assertEquals(40, minHeap.pop());
        assertTrue(minHeap.isEmpty());
    }

    @Test
    void testPeek_ValidInput() {
        minHeap.insert(3, 30);
        minHeap.insert(1, 10);
        minHeap.insert(2, 20);

        assertEquals(10, minHeap.peek());
        minHeap.extractMin();
        assertEquals(20, minHeap.peek());
    }

    @Test
    void testBuildHeap_ValidInput() {
        Integer[] dataArray = {30, 10, 20, 40, 5, 15};
        minHeap.buildHeap(dataArray);

        assertEquals(5, minHeap.extractMin());
        assertEquals(10, minHeap.extractMin());
        assertEquals(15, minHeap.extractMin());
        assertEquals(20, minHeap.extractMin());
        assertEquals(30, minHeap.extractMin());
        assertEquals(40, minHeap.extractMin());
        assertTrue(minHeap.isEmpty());
    }

    // Bad Weather Tests
    @Test
    void testExtractMin_EmptyHeap() {
        assertThrows(IllegalStateException.class, () -> minHeap.extractMin());
    }

    @Test
    void testPeek_EmptyHeap() {
        assertThrows(IllegalStateException.class, () -> minHeap.peek());
    }

    @Test
    void testPop_EmptyHeap() {
        assertThrows(IllegalStateException.class, () -> minHeap.pop());
    }

    @Test
    void testBuildHeap_NullArray() {
        assertThrows(NullPointerException.class, () -> minHeap.buildHeap(null));
    }
}
