package cn.jc.design.proxy;

import javax.sound.midi.Soundbank;

/**
 * Created by Administrator on 2017/3/24.
 */
public class GamePlayerImpl implements GamePlayer {

    private String name;

    public GamePlayerImpl(String name) {
        this.name = name;
    }

    public void login(String user, String password) {
        System.out.println(String.format("登录名为 %s 的用户登录成功!", user));
    }

    public void killBoss() {
        System.out.println(this.name.concat("正在打怪！"));
    }

    public void upgrade() {
        System.out.println(this.name.concat("又升了一级！"));
    }
}
