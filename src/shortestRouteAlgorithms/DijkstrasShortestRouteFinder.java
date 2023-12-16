package shortestRouteAlgorithms;
import Arraylist.EfficientArrayList;
import HashMap.MyHashMap;
import MinHeap.minHeap;
import objects.Track;
//https://www.baeldung.com/java-dijkstra
public class DijkstrasShortestRouteFinder <T>{

    public EfficientArrayList<T> findShortestRoute(EfficientArrayList<Track> tracks, T startNode, T endNode) {
        // Step 1: Create a Graph.graph to represent the nodes and tracks.
        MyHashMap<T, EfficientArrayList<Track>> graph = new MyHashMap<>();
        for (Track track : tracks) {
            T sourceStation = (T)track.getSourceStation();
            if (!graph.containsKey( sourceStation)) {
                graph.put( sourceStation, new EfficientArrayList<>());
            }
            graph.get(sourceStation).add(track);
        }

        // Initialize distances and predecessors.
        MyHashMap<T, Integer> distances = new MyHashMap<>();
        MyHashMap<T, T> predecessors = new MyHashMap<>();

        // Initialize the MinHeap for Dijkstra's algorithm.
        minHeap<T> minHeap = new minHeap<>();

        // Step 2: Implement Dijkstra's algorithm.
        distances.put(startNode, 0);
        minHeap.insert(0, startNode);

        while (!minHeap.isEmpty()) {
            T currentNode = minHeap.extractMin();

            if (currentNode == null) {
                continue; // Skip if the node is null.
            }

            if (currentNode.equals(endNode)) {
                break; // Found the shortest path to the end node.
            }

            if (!graph.containsKey(currentNode)) {
                continue; // Skip if the node has no outgoing tracks.
            }

            for (Track track : graph.get(currentNode)) {
                T neighbor = (T)track.getDestinationStation();
                int newDistance = distances.get(currentNode) + track.getLength();

                if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    predecessors.put(neighbor, currentNode);
                    minHeap.insert(newDistance, neighbor);
                }
            }
        }
        // Step 3: Reconstruct the shortest route.
        EfficientArrayList<T> shortestRoute = new EfficientArrayList<>();
        T current = endNode;
        while (current != null) {
            shortestRoute.add(current);
            current = predecessors.get(current);
        }

        shortestRoute.reverse();
        //if no route was found the start station is removed from the list
        if (shortestRoute.size()==1){
            shortestRoute.remove(shortestRoute.get(0));
        }
        return shortestRoute;
    }
}