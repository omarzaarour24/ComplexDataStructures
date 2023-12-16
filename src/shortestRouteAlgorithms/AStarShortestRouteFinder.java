package shortestRouteAlgorithms;

import Arraylist.EfficientArrayList;
import HashMap.MyHashMap;
import MinHeap.minHeap;
import objects.Track;

public class AStarShortestRouteFinder<T> {

    public EfficientArrayList<T> findShortestRoute(EfficientArrayList<Track> tracks, T startNode, T endNode) {
        MyHashMap<T, EfficientArrayList<Track>> graph = new MyHashMap<>();
        for (Track track : tracks) {
            T sourceStation = (T) track.getSourceStation();
            if (!graph.containsKey(sourceStation)) {
                graph.put(sourceStation, new EfficientArrayList<>());
            }
            graph.get(sourceStation).add(track);
        }

        MyHashMap<T, Integer> distances = new MyHashMap<>();
        MyHashMap<T, T> predecessors = new MyHashMap<>();
        minHeap<T> openSet = new minHeap<>();
        distances.put(startNode, 0);
        openSet.insert(0, startNode);

        while (!openSet.isEmpty()) {
            T current = openSet.extractMin();

            if (current.equals(endNode)) {
                break;
            }
            if (!graph.containsKey(current)) {
                continue;
            }
            for (Track track : graph.get(current)) {
                T neighbor = (T) track.getDestinationStation();
                int newDistance = distances.get(current) + track.getLength();
                if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    predecessors.put(neighbor, current);
                    openSet.insert(newDistance + heuristic(neighbor, endNode), neighbor);
                }
            }
        }
        EfficientArrayList<T> shortestRoute = new EfficientArrayList<>();
        T current = endNode;
        while (current != null) {
            shortestRoute.add(current);
            current = predecessors.get(current);
        }

        shortestRoute.reverse();

        //if no route was found the start station is removed from the list
        if (shortestRoute.size() <= 1) {
            shortestRoute.remove(shortestRoute.get(0));
        }
        return shortestRoute;
    }

    private int heuristic(T node, T endNode) {
        if (node instanceof Integer && endNode instanceof Integer) {
            return Math.abs((Integer) node - (Integer) endNode);
        } else {
            return 0;
        }
    }
}








//package shortestRouteAlgorithms;
//
//import Arraylist.EfficientArrayList;
//import HashMap.MyHashMap;
//import objects.Track;
//
//import java.util.PriorityQueue;
//
////https://www.baeldung.com/java-a-star-pathfinding
//public class AStarShortestRouteFinder<T> {
//    public EfficientArrayList<T> findShortestRoute(EfficientArrayList<Track> tracks, T startNode, T endNode) {
//        MyHashMap<T, EfficientArrayList<Track>> graph = new MyHashMap<>();
//        for (Track track : tracks) {
//            T sourceStation = (T)track.getSourceStation();
//            if (!graph.containsKey(sourceStation)) {
//                graph.put(sourceStation, new EfficientArrayList<>());
//            }
//            graph.get(sourceStation).add(track);
//        }
//
//        MyHashMap<T, Integer> distances = new MyHashMap<>();
//        MyHashMap<T, T> predecessors = new MyHashMap<>();
//        PriorityQueue<Node<T>> openSet = new PriorityQueue<>();
//        distances.put(startNode, 0);
//        openSet.offer(new Node<>(startNode, 0, heuristic(startNode, endNode)));
//
//        while (!openSet.isEmpty()) {
//            Node<T> current = openSet.poll();
//
//            if (current.node.equals(endNode)) {
//                break;
//            }
//            if (!graph.containsKey(current.node)) {
//                continue;
//            }
//            for (Track track : graph.get(current.node)) {
//                T neighbor =(T) track.getDestinationStation();
//                int newDistance = distances.get(current.node) + track.getLength();
//                if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
//                    distances.put(neighbor, newDistance);
//                    predecessors.put(neighbor, current.node);
//                    int priority = newDistance + heuristic(neighbor, endNode);
//                    openSet.offer(new Node<>(neighbor, newDistance, priority));
//                }
//            }
//        }
//        EfficientArrayList<T> shortestRoute = new EfficientArrayList<>();
//        T current = endNode;
//        while (current != null) {
//            shortestRoute.add(current);
//            current = predecessors.get(current);
//        }
//
//        shortestRoute.reverse();
//
//        //if no route was found the start station is removed from the list
//        if (shortestRoute.size()<=1){
//            shortestRoute.remove(shortestRoute.get(0));
//        }
//        return shortestRoute;
//    }
//
//    private int heuristic(T node, T endNode) {
//        if (node instanceof Integer && endNode instanceof Integer) {
//            return Math.abs((Integer) node - (Integer) endNode);
//        } else {
//            return 0;
//        }
//    }
//
//    private static class Node<T> implements Comparable<Node<T>> {
//        T node;
//        int cost;
//        int priority;
//
//        public Node(T node, int cost, int priority) {
//            this.node = node;
//            this.cost = cost;
//            this.priority = priority;
//        }
//
//        @Override
//        public int compareTo(Node<T> other) {
//            return Integer.compare(this.priority, other.priority);
//        }
//    }
//}
