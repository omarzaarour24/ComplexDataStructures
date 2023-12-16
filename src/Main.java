import Arraylist.EfficientArrayList;
import Avl.AVLTree;
import DataLoading.DataLoader;
import HashMap.MyHashMap;
import Search.BinaryStationSearch;
import Search.LinearStationSearch;
import Sort.MergeSort;
import Sort.SelectionSort;
import objects.Station;
import objects.Track;
import shortestRouteAlgorithms.AStarShortestRouteFinder;
import shortestRouteAlgorithms.DijkstrasShortestRouteFinder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EfficientArrayList<Station> stations = DataLoader.loadStationsFromCSV("C:\\Users\\omar\\Desktop\\saxion 2023-2024\\complex\\src\\DataLoading\\stations.csv");
        EfficientArrayList<Track> tracks = DataLoader.loadTracksFromCSV("C:\\Users\\omar\\Desktop\\saxion 2023-2024\\complex\\src\\DataLoading\\tracks.csv");
        Scanner scanner = new Scanner(System.in);
        String stationName;

        // Main menu loop
        while (true) {
            System.out.println("==== Track Manager Application ====");
            System.out.println("1. Binary Search for Stations ");
            System.out.println("2. Linear Search for Stations");
            System.out.println("3. sort tracks with selection sort");
            System.out.println("4. sort tracks with mergesort");
            System.out.println("5. Search for station using AvlTree");
            System.out.println("6. find station in hashtable with station code");
            System.out.println("7. Find shortest route with AStar");
            System.out.println("8. Find shortest route with Dijkstra");
            System.out.println("9. Exit");
            System.out.print("Enter your choice (1-9): ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    BinaryStationSearch binaryStationSearch =new BinaryStationSearch(stations);
                    System.out.print("Enter the name of the station to search for: ");
                    stationName = scanner.nextLine();

                    // Search for the station by name
                    Station foundStation = binaryStationSearch.binarySearchByName(stationName);

                    if (foundStation != null) {
                        System.out.println("Station found: " + foundStation.toString());
                        // Print other station details as needed
                    } else {
                        System.out.println("Station not found.");
                    }
                    break;
                case 2:
                    LinearStationSearch lss=new LinearStationSearch(stations);
                    System.out.print("Enter the name of the station to search for: ");
                    stationName = scanner.nextLine();
                    // Perform linear search
                    Station foundStation1 = lss.linearSearchByName(stationName);
                    if (foundStation1 != null) {
                        System.out.println("Station found: " + foundStation1.toString());
                        // Print other station details as needed
                    } else {
                        System.out.println("Station not found.");
                    }
                    break;
                case 3:
                    SelectionSort selectionSort=new SelectionSort();
                    selectionSort.selectionSortByLength(tracks);
                    break;
                case 4:
                    MergeSort mergeSort=new MergeSort();
                    mergeSort.mergeSortByLength(tracks);
                    break;
                case 5:
                    AVLTree<Station> avlTree=new AVLTree<>();
                    for (Station station: stations){
                        avlTree.insert(station);
                    }
                    System.out.print("Enter the name of the station to search for: ");
                    stationName = scanner.nextLine();
                    Station foundStation2 = avlTree.search(getStation(stationName,stations));

                    if (foundStation2 != null) {
                        System.out.println("Station found: " + foundStation2.toString());
                    } else {
                        System.out.println("Station not found.");
                    }
                    break;
                case 6:
                    MyHashMap<String, Station> stationMap = new MyHashMap<>();
                    // Adding stations to the HashMap
                    for (Station station : stations) {
                        stationMap.put(station.getCode(), station);
                    }
                    System.out.print("Enter the code of the station to search for: ");
                    String stationcode = scanner.nextLine();

                    // Retrieving a station by its code
                    Station retrievedStation = stationMap.get(stationcode);
                    if (retrievedStation != null) {
                        if (stationMap.containsKey(stationcode)){
                            System.out.println("Retrieved Station: " + retrievedStation.toString());
                        }else{
                            System.out.println("station code doesnt exist");
                        }

                    } else {
                        System.out.println("Station not found.");
                    }
                    break;
                case 7:
                    AStarShortestRouteFinder<String>finder1=new AStarShortestRouteFinder();
                    System.out.print("Enter the name of the station to search for: ");
                    String startStationName = scanner.nextLine();
                    String startStationCode = getStation(startStationName, stations).getCode().toString().toLowerCase();
                    System.out.print("Enter the name of the station to search for: ");
                    String endStationName = scanner.nextLine();
                    String endStationCode = getStation(endStationName, stations).getCode().toString().toLowerCase();
                    EfficientArrayList<String> shortestTrack = finder1.findShortestRoute(tracks, startStationCode, endStationCode);
                    if (shortestTrack.isEmpty()) {
                        System.out.println("No path found.");
                    } else {
                        System.out.println("Shortest route: " + String.join(" -> ", shortestTrack));
                    }
                    break;
                case 8:
                    System.out.print("Enter the name of the station to search for: ");
                    startStationName = scanner.nextLine();
                    String startStationcode= getStation(startStationName,stations).getCode().toString().toLowerCase();
                    System.out.print("Enter the name of the station to search for: ");
                    endStationName = scanner.nextLine();
                    endStationCode= getStation(endStationName,stations).getCode().toString().toLowerCase();
                    DijkstrasShortestRouteFinder finder = new DijkstrasShortestRouteFinder();
                    EfficientArrayList<String> shortestTrack1 = finder.findShortestRoute(tracks,startStationcode, endStationCode);
                    if (shortestTrack1.size()==0) {
                        System.out.println("No path found.");
                    } else {
                        System.out.println("Shortest route: " + String.join(" -> ", shortestTrack1));
                    }
                    break;
                case 9:
                    // Exit the program
                    System.out.println("Exiting the objects.Track Manager Application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-6).");
            }
        }
    }
    //helper method
    private static Station getStation(String name, EfficientArrayList<Station> stations){
        Station found = null;
        for (Station s :
                stations) {
            if (s.getName_short().equalsIgnoreCase(name)){
                found= s;
            }
        }
        return found;
    }

}