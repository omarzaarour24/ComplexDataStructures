package Search;

import Arraylist.EfficientArrayList;
import objects.Station;
//https://www.geeksforgeeks.org/linear-search-vs-binary-search/
public class LinearStationSearch {


    private EfficientArrayList<Station> stations;

    public LinearStationSearch(EfficientArrayList<Station> stations) {
        this.stations = stations;
    }

    public Station linearSearchByName(String name) {
        for (Station station : stations) {
            if (station.getName_short().equalsIgnoreCase(name)) {
                return station; // Found the station with the given name
            }
        }
        return null; // Station with the given name not found
    }


}
