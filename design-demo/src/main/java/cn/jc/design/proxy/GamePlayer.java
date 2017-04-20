package cn.jc.design.proxy;

/**
 * Created by Administrator on 2017/3/24.
 */
public interface GamePlayer {
    void login(String user, String password);

    void killBoss();

    void upgrade();
}
