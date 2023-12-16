package Arraylist;
import objects.Station;
import objects.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EfficientArrayListTest {

    private EfficientArrayList<Station> stationList;
    private EfficientArrayList<Track> trackList;
    private EfficientArrayList <Integer> list;

    @BeforeEach
    void setUp() {
        stationList = new EfficientArrayList<>();
        trackList = new EfficientArrayList<>();
        list = new EfficientArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    void testAddAndGetStation() {
        Station stationA = new Station(1, 12345, 45.678, -123.456, "A", "Station A", "Station A Medium", "Station A Long", "station-a", "Country A", "Type A");
        Station stationB = new Station(2, 67890, 46.789, -124.567, "B", "Station B", "Station B Medium", "Station B Long", "station-b", "Country B", "Type B");

        stationList.add(stationA);
        stationList.add(stationB);

        assertEquals(stationA, stationList.get(0));
        assertEquals(stationB, stationList.get(1));
    }

    @Test
    void testAddAndGetTrack() {
        Track track1 = new Track("A", "B", 100, 200, 1);
        Track track2 = new Track("C", "D", 150, 250, 2);

        trackList.add(track1);
        trackList.add(track2);

        assertEquals(track1, trackList.get(0));
        assertEquals(track2, trackList.get(1));
    }

    @Test
    void testSize() {
        assertEquals(0, stationList.size());
        assertEquals(0, trackList.size());

        Station stationA = new Station(1, 12345, 45.678, -123.456, "A", "Station A", "Station A Medium", "Station A Long", "station-a", "Country A", "Type A");
        stationList.add(stationA);
        assertEquals(1, stationList.size());

        Track track1 = new Track("A", "B", 100, 200, 1);
        trackList.add(track1);
        assertEquals(1, trackList.size());
    }

    @Test
    void testSet() {
        Station stationA = new Station(1, 12345, 45.678, -123.456, "A", "Station A", "Station A Medium", "Station A Long", "station-a", "Country A", "Type A");
        stationList.add(stationA);

        Station stationB = new Station(2, 67890, 46.789, -124.567, "B", "Station B", "Station B Medium", "Station B Long", "station-b", "Country B", "Type B");
        stationList.set(0, stationB);

        assertEquals(stationB, stationList.get(0));
    }

    @Test
    void testEnsureCapacity() {
        for (int i = 0; i < 20; i++) {
            Track track = new Track("A", "B", 100, 200, 1);
            trackList.add(track);
        }

        assertEquals(20, trackList.size());
    }

    @Test
    void testSubList() {
        Station stationA = new Station(1, 12345, 45.678, -123.456, "A", "Station A", "Station A Medium", "Station A Long", "station-a", "Country A", "Type A");
        Station stationB = new Station(2, 67890, 46.789, -124.567, "B", "Station B", "Station B Medium", "Station B Long", "station-b", "Country B", "Type B");
        Station stationC = new Station(3, 98765, 47.890, -125.678, "C", "Station C", "Station C Medium", "Station C Long", "station-c", "Country C", "Type C");

        stationList.add(stationA);
        stationList.add(stationB);
        stationList.add(stationC);

        EfficientArrayList<Station> subList = stationList.subList(1, 3);

        assertEquals(2, subList.size());
        assertEquals(stationB, subList.get(0));
        assertEquals(stationC, subList.get(1));
    }

    @Test
    void testReverse() {
        Station stationA = new Station(1, 12345, 45.678, -123.456, "A", "Station A", "Station A Medium", "Station A Long", "station-a", "Country A", "Type A");
        Station stationB = new Station(2, 67890, 46.789, -124.567, "B", "Station B", "Station B Medium", "Station B Long", "station-b", "Country B", "Type B");
        Station stationC = new Station(3, 98765, 47.890, -125.678, "C", "Station C", "Station C Medium", "Station C Long", "station-c", "Country C", "Type C");

        stationList.add(stationA);
        stationList.add(stationB);
        stationList.add(stationC);

        stationList.reverse();

        assertEquals(stationC, stationList.get(0));
        assertEquals(stationB, stationList.get(1));
        assertEquals(stationA, stationList.get(2));
    }

    @Test
    void testIsEmpty() {
        assertTrue(stationList.isEmpty());
        assertTrue(trackList.isEmpty());

        Track track = new Track("A", "B", 100, 200, 1);
        trackList.add(track);

        assertFalse(trackList.isEmpty());
    }

    @Test
    void testRemoveValid() {
        list.remove(2);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    void testRemoveFirstElement() {
        list.remove(1);
        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    void testRemoveLastElement() {
        list.remove(3);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testRemoveEmptyList() {
        EfficientArrayList<Integer> emptyList = new EfficientArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.remove(0),"list is empty");
    }

    @Test
    void testGetInvalidIndex() {
        stationList.add(new Station(1, 12345, 45.678, -123.456, "A", "Station A", "Station A Medium", "Station A Long", "station-a", "Country A", "Type A"));
        trackList.add(new Track("A", "B", 100, 200, 1));

        assertThrows(IndexOutOfBoundsException.class, () -> stationList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> stationList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> trackList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> trackList.get(-1));
    }

    @Test
    void testSetInvalidIndex() {
        stationList.add(new Station(1, 12345, 45.678, -123.456, "A", "Station A", "Station A Medium", "Station A Long", "station-a", "Country A", "Type A"));
        trackList.add(new Track("A", "B", 100, 200, 1));

        assertThrows(IndexOutOfBoundsException.class, () -> stationList.set(1, new Station(2, 67890, 46.789, -124.567, "B", "Station B", "Station B Medium", "Station B Long", "station-b", "Country B", "Type B")));
        assertThrows(IndexOutOfBoundsException.class, () -> stationList.set(-1, new Station(2, 67890, 46.789, -124.567, "B", "Station B", "Station B Medium", "Station B Long", "station-b", "Country B", "Type B")));
        assertThrows(IndexOutOfBoundsException.class, () -> trackList.set(1, new Track("C", "D", 150, 250, 2)));
        assertThrows(IndexOutOfBoundsException.class, () -> trackList.set(-1, new Track("C", "D", 150, 250, 2)));
    }

    @Test
    void testSubListInvalidIndices() {
        assertThrows(IndexOutOfBoundsException.class, () -> stationList.subList(-1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> stationList.subList(2, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> stationList.subList(0, 2));

        assertThrows(IndexOutOfBoundsException.class, () -> trackList.subList(-1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> trackList.subList(2, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> trackList.subList(0, 2));
    }

    @Test
    void testContainsOnEmptyList() {
        EfficientArrayList<Integer> emptyList = new EfficientArrayList<>();
        assertFalse(emptyList.contains(5));
    }

    @Test
    void testContainsOnListWithElements() {
        EfficientArrayList<Integer> list = new EfficientArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertFalse(list.contains(4));
    }

    @Test
    void testContainsOnListWithDuplicates() {
        EfficientArrayList<Integer> list = new EfficientArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2); // Adding a duplicate

        assertTrue(list.contains(2));
    }

    @Test
    void testContainsOnListWithNullElements() {
        EfficientArrayList<Integer> list = new EfficientArrayList<>();
        list.add(1);
        list.add(null);

        assertFalse(list.contains(2));
    }
}
