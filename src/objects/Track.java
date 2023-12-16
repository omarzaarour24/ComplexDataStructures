package objects;

public class Track {
private int length, length2, trackType;
private String sourceStation, destinationStation;

    public Track(String SourceStation, String DestinationStation, int length, int length2, int trackType) {
        this.length = length;
        this.length2 = length2;
        this.trackType = trackType;
        this.sourceStation = SourceStation;
        this.destinationStation = DestinationStation;
    }
    public int getLength() {
        return length;
    }

    public int getLength2() {
        return length2;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

}
