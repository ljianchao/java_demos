package cn.jc.BeanDemo;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/4/24.
 */
@Component
public class SegPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
