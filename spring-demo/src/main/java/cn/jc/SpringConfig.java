package cn.jc;

import cn.jc.BeanDemo.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

/**
 * ImportResource在JavaConfig和*.xml中只能使用1个，不能两边同时存在
 */
@Configuration
@Import({CdPlayConfig.class, ExpressiveConfig.class})
@ImportResource({"classpath:app-core.xml", "classpath:cdplayer-config.xml",})
@ComponentScan(basePackageClasses = {ScanMarker.class})
public class SpringConfig {

    //显示声明bean
    @Bean(name = "hardDaysNight")
    public CompactDisc hardDaysNight(){
        return new HardDaysNight();
    }


    // Qualifier限定符
    @Bean(name = "cdPlayer")
    public CDPlayer cdPlayer(@Qualifier(value = "segPeppers")CompactDisc  compactDisc){
        return new CDPlayer(compactDisc);
    }
}
