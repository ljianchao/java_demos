package cn.jc;

import cn.jc.BeanDemo.CompactDisc;
import cn.jc.BeanDemo.HardDaysNight;
import cn.jc.BeanDemo.ScanMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/4/24.
 */
@Configuration
@ComponentScan(basePackageClasses = {ScanMarker.class})
public class SpringConfig {

    //显示声明bean
    @Bean(name = "hardDaysNight")
    public CompactDisc hardDaysNight(){
        return new HardDaysNight();
    }
}
