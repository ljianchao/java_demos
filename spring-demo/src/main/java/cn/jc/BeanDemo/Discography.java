package cn.jc.BeanDemo;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */
public class Discography {
    String artist;
    List<CompactDisc> cds;

    public Discography(String artist, List<CompactDisc> cds) {
        this.artist = artist;
        this.cds = cds;
    }
}
