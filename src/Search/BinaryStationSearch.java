package Search;

import Arraylist.EfficientArrayList;
import objects.Station;
//https://www.geeksforgeeks.org/linear-search-vs-binary-search/
public class BinaryStationSearch{
    private EfficientArrayList<Station> stations;

    public BinaryStationSearch(EfficientArrayList<Station> stations) {
        this.stations = stations;
    }

    public Station binarySearchByName(String name) {
        int left = 0;
        int right = stations.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Station midStation = stations.get(mid);
            String midName = midStation.getName_short().toString();

            int comparison = midName.compareTo(name);

            if (comparison == 0) {
                return midStation; // Found the station with the given name
            } else if (comparison < 0) {
                left = mid + 1; // Search the right half
            } else {
                right = mid - 1; // Search the left half
            }
        }

        return null; // Station with the given name not found
    }

}
