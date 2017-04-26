package cn.jc.BeanDemo;

import cn.jc.SpringConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class CDPlayerTest {

    @Autowired
    @Qualifier(value = "hardDaysNight")
    private CompactDisc cd;

    @Autowired
    private CDPlayer cdPlayer;

    @Autowired
    private Discography discography;

    @Autowired
    private BlankDisc disc;

    @Autowired
    @Qualifier("discT")
    private BlankDisc discT;


    @Test
    public void cdShouldNotBeNull(){
        Assert.assertNotNull(cd);
    }

    @Test
    public void cdPlayerShouldNotBeNull(){
        Assert.assertNotNull(cdPlayer);
    }

    @Test
    public void discographyShouldNotBeNull(){
        Assert.assertNotNull(discography);
    }

    @Test
    public void blankDistShouldNotBeNull(){
        disc.play();
        Assert.assertNotNull(disc);
    }

    @Test
    public void blankDistTShouldNotBeNull(){
        discT.play();
        Assert.assertNotNull(discT);
    }
}
