package Sort;

import Arraylist.EfficientArrayList;
import objects.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    private MergeSort mergeSort;
    private EfficientArrayList<Track> tracks;

    @BeforeEach
    void setUp() {
        mergeSort = new MergeSort();
        tracks = new EfficientArrayList<>();
        tracks.add(new Track("A", "B", 100,100,10));
        tracks.add(new Track("C", "D", 50,50,0));
        tracks.add(new Track("E", "F", 75,75,10));
        tracks.add(new Track("G", "H", 30,30,0));
    }

    @Test
    void testMergeSortByLengthGoodWeather() {
        mergeSort.mergeSortByLength(tracks);

        // Check if the tracks are sorted in ascending order by length
        for (int i = 0; i < tracks.size() - 1; i++) {
            assertTrue(tracks.get(i).getLength() <= tracks.get(i + 1).getLength());
        }
    }

    @Test
    void testMergeSortByLengthBadWeatherEmptyList() {
        EfficientArrayList<Track> emptyList = new EfficientArrayList<>();
        mergeSort.mergeSortByLength(emptyList);
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void testMergeSortByLengthEdgeCaseDuplicates() {
        tracks.add(new Track("I", "J", 30,30,0));
        mergeSort.mergeSortByLength(tracks);

        // Check if the tracks are sorted in ascending order by length
        for (int i = 0; i < tracks.size() - 1; i++) {
            assertTrue(tracks.get(i).getLength() <= tracks.get(i + 1).getLength());
        }
    }


    @Test
    void testMergeSortByLength_EmptyList() {
        EfficientArrayList<Track> tracks = new EfficientArrayList<>();

        // Sort an empty list, should not throw an exception
        assertDoesNotThrow(() -> mergeSort.mergeSortByLength(tracks));
        assertTrue(tracks.isEmpty());
    }


    @Test
    void testMergeSortByLength_NullList() {
        EfficientArrayList<Track> tracks = null;

        // Sorting a null list should throw an exception
        assertThrows(NullPointerException.class,() -> mergeSort.mergeSortByLength(tracks));
    }
    @Test
    void testMergeSortByLength_OneElement() {
        mergeSort = new MergeSort();
        EfficientArrayList<Track> tracks = new EfficientArrayList<>();
        tracks.add(new Track("A", "B", 100, 100, 10));

        // Sorting a list with a single element, it should remain the same
        mergeSort.mergeSortByLength(tracks);
        assertEquals(1, tracks.size());
    }

    @Test
    void testMergeSortByLength_SortedList() {
        mergeSort = new MergeSort();
        EfficientArrayList<Track> tracks = new EfficientArrayList<>();
        tracks.add(new Track("A", "B", 100, 100, 10));
        tracks.add(new Track("C", "D", 200, 200, 20));
        tracks.add(new Track("E", "F", 300, 300, 30));

        // Sorting an already sorted list, it should remain the same
        mergeSort.mergeSortByLength(tracks);
        assertEquals(100, tracks.get(0).getLength());
    }

    @Test
    void testMergeSortByLength_ReverseSortedList() {
        mergeSort = new MergeSort();
        EfficientArrayList<Track> tracks = new EfficientArrayList<>();
        tracks.add(new Track("A", "B", 300, 300, 30));
        tracks.add(new Track("C", "D", 200, 200, 20));
        tracks.add(new Track("E", "F", 100, 100, 10));

        // Sorting a reverse-sorted list, it should be sorted in ascending order
        mergeSort.mergeSortByLength(tracks);
        for (int i = 0; i < tracks.size() - 1; i++) {
            assertTrue(tracks.get(i).getLength() <= tracks.get(i + 1).getLength());
        }
    }
}
