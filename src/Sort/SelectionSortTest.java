package Sort;

import Arraylist.EfficientArrayList;
import objects.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectionSortTest {

    private SelectionSort selectionSort;

    @BeforeEach
    public void setUp() {
        selectionSort = new SelectionSort();
    }
    @Test
    void testSelectionSortByLengthGoodWeather() {
        // Good weather scenario: Sorting tracks by length
        EfficientArrayList<Track> tracks = new EfficientArrayList<>();
        tracks.add(new Track("A", "B", 100,100,10));
        tracks.add(new Track("C", "D", 50,50,10));
        tracks.add(new Track("E", "F", 75,75,0));

        EfficientArrayList<Track> sortedTracks = selectionSort.selectionSortByLength(tracks);

        // Check if the tracks are sorted in ascending order by length
        for (int i = 0; i < sortedTracks.size() - 1; i++) {
            assertTrue(sortedTracks.get(i).getLength() <= sortedTracks.get(i + 1).getLength());
        }
    }

    @Test
    void testSelectionSortByLengthBadWeatherEmptyList() {
        // Bad weather scenario: Sorting an empty list
        EfficientArrayList<Track> emptyList = new EfficientArrayList<>();
        SelectionSort sorter = new SelectionSort();
        EfficientArrayList<Track> sortedTracks = sorter.selectionSortByLength(emptyList);
        assertTrue(sortedTracks.isEmpty());
    }

    @Test
    void testSelectionSortByLengthBadWeatherSingleElement() {
        // Bad weather scenario: Sorting a list with a single element
        EfficientArrayList<Track> tracks = new EfficientArrayList<>();
        tracks.add(new Track("A", "B", 100,100,10));

        EfficientArrayList<Track> sortedTracks = selectionSort.selectionSortByLength(tracks);
        assertEquals(tracks.get(0), sortedTracks.get(0));
    }

    @Test
    void testSelectionSortByLengthEdgeCaseDuplicates() {
        // Edge case scenario: Sorting a list with duplicate lengths
        EfficientArrayList<Track> tracks = new EfficientArrayList<>();
        tracks.add(new Track("A", "B", 100,100,10));
        tracks.add(new Track("C", "D", 50,50,10));
        tracks.add(new Track("E", "F", 50,50,0));
        EfficientArrayList<Track> sortedTracks = selectionSort.selectionSortByLength(tracks);

        // Check if the tracks are sorted in ascending order by length
        for (int i = 0; i < sortedTracks.size() - 1; i++) {
            assertTrue(sortedTracks.get(i).getLength() <= sortedTracks.get(i + 1).getLength());
        }
    }
}
