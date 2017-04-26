package cn.jc.BeanDemo;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */
public class BlankDisc implements CompactDisc{
    private String title;
    private String artist;
    private List<String> tracks;

    public BlankDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public BlankDisc(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    @Override
    public void play() {
        System.out.println("Playing" + title + " by " + artist);
    }
}
