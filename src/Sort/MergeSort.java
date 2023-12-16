package Sort;

import Arraylist.EfficientArrayList;
import objects.Track;
//https://www.baeldung.com/java-merge-sort
public class MergeSort {

    public void mergeSortByLength(EfficientArrayList<Track> tracks) {
        int n = tracks.size();
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        EfficientArrayList<Track> left = tracks.subList(0, mid);
        EfficientArrayList<Track> right = tracks.subList(mid, n);

        mergeSortByLength(left);
        mergeSortByLength(right);

        merge(tracks, left, right);
        showTracks(tracks);

    }

    private static void merge(EfficientArrayList<Track> tracks, EfficientArrayList<Track> left, EfficientArrayList<Track> right) {
        int nLeft = left.size();
        int nRight = right.size();
        int i = 0, j = 0, k = 0;

        while (i < nLeft && j < nRight) {
            if (left.get(i).getLength() <= right.get(j).getLength2()) {
                tracks.set(k++, left.get(i++));
            } else {
                tracks.set(k++, right.get(j++));
            }
        }

        while (i < nLeft) {
            tracks.set(k++, left.get(i++));
        }

        while (j < nRight) {
            tracks.set(k++, right.get(j++));
        }
    }
    private static void showTracks(EfficientArrayList<Track> tracks) {
        System.out.println("objects.Track Data:");
        for (int i = 0; i < tracks.size(); i++) {
            Track track = tracks.get(i);
            System.out.println("Track: " + track.getSourceStation() + " - " + track.getDestinationStation());
            System.out.println("Length: " + track.getLength());
            System.out.println();
        }
    }




}
