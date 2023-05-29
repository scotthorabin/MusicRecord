package Music;

import java.io.Serializable;
import java.util.Comparator;

public class Record implements Serializable, Comparable<Record> {

    @Override
    public String toString() {
        return "Record" + "{" +
                "songName='" + songName + '\'' +
                ", songArtist='" + songArtist + '\'' +
                ", dateReleased=" + dateReleased +
                "}";
    }

    private String songName;
    // Song Name will store the name of the song

    private String songArtist;
    // This will store the artist that created the song

    private int dateReleased;
    // This will store date that the Record was released


    public Record(String songName, String songArtist, int dateReleased) {
        this.songName = songName;
        this.songArtist = songArtist;
        this.dateReleased = dateReleased;
    }

    //***SETTERS****
    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public void setDateReleased(int dateReleased) {
        this.dateReleased = dateReleased;
    }

    //***GETTERS****
    public String getSongName() {
        return songName;
    }

    public String getSongArtist() {

        return songArtist;
    }

    @Override
    public int compareTo(Record o) {
        return 0;
    }
}

