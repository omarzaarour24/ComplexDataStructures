package Search;
import Arraylist.EfficientArrayList;
import Search.LinearStationSearch;
import objects.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class LinearStationSearchTest {
    private LinearStationSearch linearStationSearch;

    @BeforeEach
    public void setUp() {
        // Set up test data
        EfficientArrayList<Station> stations = new EfficientArrayList<>();
        stations.add( new Station(1, 12345, 45.678, -123.456, "A", "Station A", "Station A Medium", "Station A Long", "station-a", "Country A", "Type A"));
        stations.add(new Station(2, 67890, 46.789, -124.567, "B", "Station B", "Station B Medium", "Station B Long", "station-b", "Country B", "Type B"));
        stations.add(new Station(3, 13579, 47.123, -125.789, "C", "Station C", "Station C Medium", "Station C Long", "station-c", "Country C", "Type C"));

        linearStationSearch = new LinearStationSearch(stations);
    }

    // Good Weather Tests

    @Test
    public void testLinearSearchByName_StationFound() {
        Station foundStation = linearStationSearch.linearSearchByName("Station A");
        assertEquals("Station A", foundStation.getName_short());
    }

    @Test
    public void testLinearSearchByName_StationNotFound() {
        Station foundStation = linearStationSearch.linearSearchByName("xyz");
        assertNull(foundStation);
    }

    // Edge Case Tests

    @Test
    public void testLinearSearchByName_EmptyList() {
        EfficientArrayList<Station> emptyStations = new EfficientArrayList<>();
        LinearStationSearch emptySearch = new LinearStationSearch(emptyStations);
        Station foundStation = emptySearch.linearSearchByName("abc");
        assertNull(foundStation);
    }

    // Bad Weather Tests

    @Test
    public void testLinearSearchByName_NullInput() {
        assertNull(linearStationSearch.linearSearchByName(null));
    }

    @Test
    public void testLinearSearchByName_EmptyInput() {
        Station foundStation = linearStationSearch.linearSearchByName("");
        assertNull(foundStation);
    }

}