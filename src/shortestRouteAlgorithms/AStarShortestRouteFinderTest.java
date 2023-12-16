package shortestRouteAlgorithms;

import Arraylist.EfficientArrayList;
import objects.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AStarShortestRouteFinderTest {
    private AStarShortestRouteFinder<String> finder;
    private EfficientArrayList<Track> tracks;

    @BeforeEach
    void setUp() {
        finder = new AStarShortestRouteFinder<>();
        tracks = new EfficientArrayList<>();

        // Create test data for tracks
        Track track1 = new Track("A", "B", 10, 0, 1);
        Track track2 = new Track("B", "C", 5, 0, 1);
        Track track3 = new Track("A", "C", 15, 0, 1);

        tracks.add(track1);
        tracks.add(track2);
        tracks.add(track3);
    }

    @Test
    void testFindShortestRoute_GoodWeatherReturnsRoute() {
        EfficientArrayList<String> shortestRoute = finder.findShortestRoute(tracks, "A", "C");
        assertEquals(2, shortestRoute.size());
        assertEquals("A", shortestRoute.get(0));
        assertEquals("C", shortestRoute.get(1));
    }

    @Test
    void testFindShortestRoute_StartAndEndNodesSameReturnsRoute() {
        // Test when the start and end nodes are the same
        EfficientArrayList<String> shortestRoute = finder.findShortestRoute(tracks, "A", "A");
        assertTrue(shortestRoute.isEmpty());
    }

    @Test
    void testFindShortestRoute_NoHeuristicReturnsRoute() {
        // Test when heuristic is always 0
        AStarShortestRouteFinder<Integer> integerFinder = new AStarShortestRouteFinder<>();
        EfficientArrayList<Integer> shortestRoute = integerFinder.findShortestRoute(tracks, 1, 3);
        assertEquals(0, shortestRoute.size());
    }

    @Test
    void testFindShortestRoute_EmptyTracksReturnsEmptyList() {
        // Test with empty tracks
        EfficientArrayList<String> shortestRoute = finder.findShortestRoute(new EfficientArrayList<>(), "A", "B");
        assertTrue(shortestRoute.isEmpty());
    }

    @Test
    void testFindShortestRoute_NullTracksThrowsNullPointerException() {
        // Test with null tracks
        assertThrows(NullPointerException.class, () -> finder.findShortestRoute(null, "A", "B"));
    }

    @Test
    void testFindShortestRoute_NoRouteReturnsEmptyList() {
        // Test when there is no route between stations
        EfficientArrayList<String> shortestRoute = finder.findShortestRoute(tracks, "B", "Z");
        assertTrue(shortestRoute.isEmpty());
    }
}
