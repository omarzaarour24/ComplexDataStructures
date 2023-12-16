package Graph;
import Arraylist.EfficientArrayList;
import HashMap.MyHashMap;
import java.util.*;
//https://www.baeldung.com/java-graphs
public class graph<T> {
    private MyHashMap<T, EfficientArrayList<T>> adjList;

    public graph() {
        adjList = new MyHashMap<>();
    }

    public void addVertex(T vertex) {
        adjList.put(vertex, new EfficientArrayList<>());
    }

    public void addEdge(T source, T destination) {
        if (!adjList.containsKey(source) || !adjList.containsKey(destination)) {
            throw new IllegalArgumentException("Source or destination vertex does not exist.");
        }
        adjList.get(source).add(destination);
        adjList.get(destination).add(source);
    }
    public EfficientArrayList<T> breadthFirstSearch(T start) {
        EfficientArrayList<T> result = new EfficientArrayList<>();
        if (!adjList.containsKey(start)) {
            return result;
        }

        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            result.add(vertex);

            for (T neighbor : adjList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return result;
    }

    public EfficientArrayList<T> depthFirstSearch(T start) {
        EfficientArrayList<T> result = new EfficientArrayList<>();
        if (!adjList.containsKey(start)) {
            return result;
        }

        Set<T> visited = new HashSet<>();
        depthFirstSearchUtil(start, visited, result);

        return result;
    }

    private void depthFirstSearchUtil(T vertex, Set<T> visited, EfficientArrayList<T> result) {
        visited.add(vertex);
        result.add(vertex);

        for (T neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                depthFirstSearchUtil(neighbor, visited, result);
            }
        }
    }

    public MyHashMap<T, EfficientArrayList<T>> getAdjList() {
        return adjList;
    }
}