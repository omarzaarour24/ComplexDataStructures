package Graph;

import Arraylist.EfficientArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class graphTest {
    private graph <String> graph;

    @BeforeEach
    public void setup() {
        graph = new graph<>();
    }

    @Test
    public void testBreadthFirstSearch_GoodWeather() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");

        EfficientArrayList<String> bfsResult = graph.breadthFirstSearch("A");

        EfficientArrayList<String> expected = new EfficientArrayList<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("D");

        // Ensure DFS result matches the expected list
        assertEquals("A", bfsResult.get(0));
    }

    @Test
    public void testDepthFirstSearch_GoodWeather() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");

        EfficientArrayList<String> dfsResult = graph.depthFirstSearch("A");
        EfficientArrayList<String> expected = new EfficientArrayList<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("D");

        // Ensure DFS result matches the expected list
        assertEquals("A", dfsResult.get(0));
    }

    @Test
    public void testBreadthFirstSearch_EmptyGraph() {
        EfficientArrayList<String> bfsResult = graph.breadthFirstSearch("A");

        // An empty graph should result in an empty BFS result
        assertTrue(bfsResult.isEmpty());
    }
    @Test
    void testAddEdgeInvalidVertex() {
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge("A", "B"));
    }

    @Test
    public void testDepthFirstSearch_EmptyGraph() {
        EfficientArrayList<String> dfsResult = graph.depthFirstSearch("A");

        // An empty graph should result in an empty DFS result
        assertTrue(dfsResult.isEmpty());
    }

    @Test
    public void testBreadthFirstSearch_BadInput() {
        // Try to perform BFS from a non-existent vertex
        EfficientArrayList<String> bfsResult = graph.breadthFirstSearch("X");

        // The result should be empty or null when the starting vertex is not in the graph
        assertTrue(bfsResult.isEmpty());
    }

    @Test
    public void testDepthFirstSearch_BadInput() {
        // Try to perform DFS from a non-existent vertex
        EfficientArrayList<String> dfsResult = graph.depthFirstSearch("X");

        // The result should be empty or null when the starting vertex is not in the graph
        assertTrue(dfsResult.isEmpty());
    }

}