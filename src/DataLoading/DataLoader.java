package DataLoading;
import Arraylist.EfficientArrayList;
import objects.Station;
import objects.Track;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLoader {

    public static EfficientArrayList<Track> loadTracksFromCSV(String csvFilePath) {
        EfficientArrayList<Track> tracks = new EfficientArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            // Use regex to match and capture the required fields
            String trackRegex = "([^,]+),([^,]+),(\\d+),(\\d+),(\\d+)";
            Pattern trackPattern = Pattern.compile(trackRegex);

            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = trackPattern.matcher(line);
                if (matcher.matches()) {
                    String name1 = matcher.group(1);
                    String name2 = matcher.group(2);
                    int int1 = Integer.parseInt(matcher.group(3));
                    int int2 = Integer.parseInt(matcher.group(4));
                    int int3 = Integer.parseInt(matcher.group(5));

                    Track track = new Track(name1, name2, int1, int2, int3);
                    tracks.add(track);
                }
            }
            System.out.println("tracks:" + tracks.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tracks;
    }
    public static EfficientArrayList<Station> loadStationsFromCSV(String csvFilePath) {
        EfficientArrayList<Station> stations = new EfficientArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            // Skip the first line (header)
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 11) {
                    int id = Integer.parseInt(data[0]);
                    int uic = Integer.parseInt(data[2]);
                    double geo_lat = Double.parseDouble(data[9]);
                    double geo_lng = Double.parseDouble(data[10]);
                    String code =removeQuotes( data[1]).toLowerCase();
                    String name_short =removeQuotes (data[3]);
                    String name_medium = removeQuotes(data[4]);
                    String name_long = removeQuotes (data[5]);
                    String slug = removeQuotes (data[6]);
                    String country = removeQuotes (data[7]);
                    String type = removeQuotes (data[8]);

                    Station station = new Station(id, uic, geo_lat, geo_lng, code, name_short, name_medium, name_long, slug, country, type);
                    stations.add(station);
                }
            }
            System.out.println("stations:"+ stations.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stations;
    }
    private static String removeQuotes(String input) {
        if (input != null) {
            // Use regex to remove leading and trailing quotes
            return input.replaceAll("^\"|\"$", "");
        }
        return input;
    }
}
