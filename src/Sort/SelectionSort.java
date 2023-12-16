package Sort;

import Arraylist.EfficientArrayList;
import objects.Track;
//https://www.geeksforgeeks.org/selection-sort/
public class SelectionSort {

    public  EfficientArrayList<Track>  selectionSortByLength(EfficientArrayList<Track> tracks) {
        int n = tracks.size();

        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;

            for (int d = i + 1; d < n; d++) {

                if (tracks.get(d).getLength() < tracks.get(minIndex).getLength()) {

                    minIndex = d;
                }
            }

            if (minIndex != i) {
                // Swap tracks[i] and tracks[minIndex]
                Track temp = tracks.get(i);
                tracks.set(i, tracks.get(minIndex));
                tracks.set(minIndex, temp);
            }
        }
        showTracks(tracks);
        return tracks;
    }
    private static void showTracks(EfficientArrayList<Track> tracks) {
        System.out.println("objects.Track Data:");
        for (Track track : tracks) {
            System.out.println("objects.Track: " + track.getSourceStation() + " - " + track.getDestinationStation());
            System.out.println("Length: " + track.getLength()); // Assuming the track length is stored in int3
            System.out.println(); // Add a newline for readability
        }
    }


}
