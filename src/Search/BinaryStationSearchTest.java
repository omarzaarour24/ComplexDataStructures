package Search;
import Arraylist.EfficientArrayList;
import objects.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryStationSearchTest {
    private BinaryStationSearch binaryStationSearch;

    @BeforeEach
    public void setUp() {
        // Set up test data
        EfficientArrayList<Station> stations = new EfficientArrayList<>();
        stations.add( new Station(1, 12345, 45.678, -123.456, "A", "Station A", "Station A Medium", "Station A Long", "station-a", "Country A", "Type A"));
        stations.add(new Station(2, 67890, 46.789, -124.567, "B", "Station B", "Station B Medium", "Station B Long", "station-b", "Country B", "Type B"));
        stations.add(new Station(3, 13579, 47.123, -125.789, "C", "Station C", "Station C Medium", "Station C Long", "station-c", "Country C", "Type C"));

        binaryStationSearch = new BinaryStationSearch(stations);
    }

    // Good Weather Tests

    @Test
    public void testBinarySearchByName_StationFound() {
        Station foundStation = binaryStationSearch.binarySearchByName("Station A");
        assertNotNull(foundStation);
        assertEquals("Station A", foundStation.getName_short());
    }

    @Test
    public void testBinarySearchByName_StationNotFound() {
        Station foundStation = binaryStationSearch.binarySearchByName("xyz");
        assertNull(foundStation);
    }

    // Edge Case Tests

    @Test
    public void testBinarySearchByName_EmptyList() {
        EfficientArrayList<Station> emptyStations = new EfficientArrayList<>();
        BinaryStationSearch emptySearch = new BinaryStationSearch(emptyStations);
        Station foundStation = emptySearch.binarySearchByName("abc");
        assertNull(foundStation);
    }

    @Test
    public void testBinarySearchByName_SingleElementList() {
        EfficientArrayList<Station> singleElementStations = new EfficientArrayList<>();
        singleElementStations.add(new Station(3, 13579, 47.123, -125.789, "C", "Station C", "Station C Medium", "Station C Long", "station-c", "Country C", "Type C"));
        BinaryStationSearch singleElementSearch = new BinaryStationSearch(singleElementStations);
        Station foundStation = singleElementSearch.binarySearchByName("Station C");
        assertNotNull(foundStation);
        assertEquals("Station C", foundStation.getName_short());
    }

    // Bad Weather Tests

    @Test
    public void testBinarySearchByName_NullInput() {
        assertThrows(NullPointerException.class, () -> binaryStationSearch.binarySearchByName(null));
    }

    @Test
    public void testBinarySearchByName_EmptyInput() {
        Station foundStation = binaryStationSearch.binarySearchByName("");
        assertNull(foundStation);
    }

}