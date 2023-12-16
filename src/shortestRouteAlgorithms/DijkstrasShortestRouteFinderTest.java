package shortestRouteAlgorithms;
import Arraylist.EfficientArrayList;
import objects.Station;
import objects.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DijkstrasShortestRouteFinderTest {
    private DijkstrasShortestRouteFinder<String> finder;
    private EfficientArrayList<Track> tracks;
    private EfficientArrayList<Station> stations;

    @BeforeEach
    void setUp() {
        finder = new DijkstrasShortestRouteFinder<>();
        tracks = new EfficientArrayList<>();
        stations = new EfficientArrayList<>();

        // Create test data for tracks and stations
        Track track1 = new Track("A", "B", 10, 0, 1);
        Track track2 = new Track("B", "C", 5, 0, 1);
        Track track3 = new Track("A", "C", 15, 0, 1);

        Station stationA = new Station(1, 12345, 45.678, -123.456, "A", "Station A", "Station A Medium", "Station A Long", "station-a", "Country A", "Type A");
        Station stationB = new Station(2, 67890, 46.789, -124.567, "B", "Station B", "Station B Medium", "Station B Long", "station-b", "Country B", "Type B");
        Station stationC = new Station(3, 13579, 47.123, -125.789, "C", "Station C", "Station C Medium", "Station C Long", "station-c", "Country C", "Type C");

        tracks.add(track1);
        tracks.add(track2);
        tracks.add(track3);

        stations.add(stationA);
        stations.add(stationB);
        stations.add(stationC);
    }
        @Test
    void testFindShortestRoute_NullTracksThrowsNullException() {
        assertThrows(NullPointerException.class, () -> finder.findShortestRoute(null, "A", "C"));
    }
    @Test
    void testFindShortestRoute_GoodWeatherReturnsRoute() {
        EfficientArrayList<String> shortestRoute = finder.findShortestRoute(tracks, "A", "C");
        assertEquals(2, shortestRoute.size());
        assertEquals("A", shortestRoute.get(0));
        assertEquals("C", shortestRoute.get(1));
    }

    @Test
    void testFindShortestRouteBadInputNoRouteReturnsEmptyList() {
        // Test when there is no route between stations.
        EfficientArrayList<String> shortestRoute = finder.findShortestRoute(tracks, "B", "Z");
        assertTrue(shortestRoute.isEmpty());
    }

    @Test
    void testFindShortestRoute_EmptyInput() {
        // Test with empty tracks and stations.
        EfficientArrayList tracksList=new EfficientArrayList<>();
        EfficientArrayList<String> shortestRoute = finder.findShortestRoute(tracksList, "A", "B");
        assertTrue(shortestRoute.isEmpty());
    }
}
