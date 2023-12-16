package DataLoading;
import Arraylist.EfficientArrayList;
import objects.Station;
import objects.Track;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataLoaderTest {
    private static final String STATIONS_CSV_FILE = "C:\\Users\\omar\\Desktop\\saxion 2023-2024\\complex\\src\\DataLoading\\stations.csv";
    private static final String TRACKS_CSV_FILE = "C:\\Users\\omar\\Desktop\\saxion 2023-2024\\complex\\src\\DataLoading\\tracks.csv";
    private static EfficientArrayList<Station> stations;
    private static EfficientArrayList<Track> tracks;

    @BeforeAll
    static void setup() {
        stations = DataLoading.DataLoader.loadStationsFromCSV(STATIONS_CSV_FILE);
        tracks = DataLoading.DataLoader.loadTracksFromCSV(TRACKS_CSV_FILE);
    }

    @Test
    void testLoadStationsFromCSV() {
        assertNotNull(stations);
        assertEquals(578, stations.size());

        // Test properties of the first station
        Station firstStation = stations.get(0);
        assertEquals("Den Bosch", firstStation.getName_short());
        assertEquals("NL", firstStation.getCountry());
    }

    @Test
    void testLoadTracksFromCSV() {
        assertNotNull(tracks);
        assertEquals(1438, tracks.size());

        // Test properties of the first track
        Track firstTrack = tracks.get(0);
        assertEquals("ac", firstTrack.getSourceStation());
        assertEquals("ashd", firstTrack.getDestinationStation());
    }
    @Test
    void testBadWeatherLoadTracksFromCSV() {
        // Bad Weather Test - Invalid CSV input (not enough fields)
        EfficientArrayList<Track> badWeatherTracks = DataLoading.DataLoader.loadTracksFromCSV("invalid.csv");
        assertNotNull(badWeatherTracks);
        assertEquals(0, badWeatherTracks.size());
    }
    @Test
    void testBadWeatherLoadStationsFromCSV() {
        // Bad Weather Test - Invalid CSV input (not enough fields)
        EfficientArrayList<Station> badWeatherStations = DataLoading.DataLoader.loadStationsFromCSV("invalid.csv");
        assertNotNull(badWeatherStations);
        assertEquals(0, badWeatherStations.size());
    }

}
