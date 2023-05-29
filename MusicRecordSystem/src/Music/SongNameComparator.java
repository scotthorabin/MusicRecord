package Music;

import java.util.Comparator;

public class SongNameComparator implements Comparator<Record> {
    @Override
    public int compare(Record o1, Record o2) {
        return o1.getSongName().compareTo(o2.getSongName());
    }
}
