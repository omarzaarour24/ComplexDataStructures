package Avl;

import Arraylist.EfficientArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    private AVLTree<Integer> avlTree;

    @BeforeEach
    void setUp() {
        avlTree = new AVLTree<>();
    }

    @Test
    void testInsertAndSearch() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(8);
        avlTree.insert(2);
        avlTree.insert(4);

        assertTrue(avlTree.search(5) == 5);
        assertTrue(avlTree.search(3) == 3);
        assertTrue(avlTree.search(8) == 8);
        assertTrue(avlTree.search(2) == 2);
        assertTrue(avlTree.search(4) == 4);

        assertNull(avlTree.search(1));
        assertNull(avlTree.search(7));
    }

    @Test
    void testStationSearch() {
        // Create a sample list of stations
        EfficientArrayList<String> stations = new EfficientArrayList<>();
        stations.add("Station A");
        stations.add("Station B");
        stations.add("Station C");
        stations.add("Station D");
        stations.add("Station E");

        // Insert stations into the AVL tree
        AVLTree<String> stationAVLTree = new AVLTree<>();
        for (int i = 0; i < stations.size(); i++) {
            stationAVLTree.insert(stations.get(i));
        }

        // Search for existing and non-existing stations
        assertEquals("Station B", stationAVLTree.search("Station B"));
        assertNull(stationAVLTree.search("Station F"));
    }

    @Test
    void testInsertDuplicate() {
        avlTree.insert(5);
        assertThrows(IllegalArgumentException.class,()->avlTree.insert(5));

        assertEquals(5, avlTree.search(5));
    }
    @Test
    void testSearchNotFound() {
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);

        assertNull(avlTree.search(40));
    }
    @Test
    void testEmptyTreeSearch() {
        assertNull(avlTree.search(10));
    }

    @Test
    void testBadInput() {
        assertThrows(NullPointerException.class, () -> avlTree.insert(null),"Null data cannot be inserted.");
    }
    @Test
    void testInsertAndSearch_EmptyTree() {
        AVLTree<Integer> avlTree = new AVLTree<>();

        assertNull(avlTree.search(5)); // Searching in an empty tree should return null
    }

    @Test
    void testInsertNull() {
        AVLTree<Integer> avlTree = new AVLTree<>();

        assertThrows(NullPointerException.class, () -> avlTree.insert(null), "Null data cannot be inserted.");
    }

    @Test
    void testStationSearch_EmptyTree() {
        AVLTree<String> stationAVLTree = new AVLTree<>();
        assertNull(stationAVLTree.search("Station A"));
    }

    @Test
    void testInsertAndSearchEdgeCase() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.insert(1);
        avlTree.insert(2);
        avlTree.insert(3);

        assertEquals(2, avlTree.search(2));
    }

    @Test
    void testHeightAndBalanceFactor() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.insert(1);
        avlTree.insert(2);
        avlTree.insert(3);

        assertEquals(2, avlTree.search(2));
        assertEquals(1, avlTree.search(1));
        assertEquals(3, avlTree.search(3));
    }
    @Test
    void testGraphViz() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(8);
        avlTree.insert(2);
        avlTree.insert(4);

        String expectedDot = "digraph AVLTree {\n" +
                "  5 [label=\"5\\nHeight: 3\"];\n" +
                "  5 -> 3 [label=\"L\"];\n" +
                "  3 [label=\"3\\nHeight: 2\"];\n" +
                "  3 -> 2 [label=\"L\"];\n"+
                "  2 [label=\"2\\nHeight: 1\"];\n" +
                "  3 -> 4 [label=\"R\"];\n" +
                "  4 [label=\"4\\nHeight: 1\"];\n" +
                "  5 -> 8 [label=\"R\"];\n" +
                "  8 [label=\"8\\nHeight: 1\"];\n" +
                "}\n";

        String graphVizOutput = avlTree.graphViz();

        assertEquals(expectedDot, graphVizOutput);
    }

}
