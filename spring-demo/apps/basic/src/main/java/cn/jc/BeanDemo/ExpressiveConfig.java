package cn.jc.BeanDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by Administrator on 2017/4/25.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class ExpressiveConfig {

    @Value("${disc.title}")
    private String title;

    @Value("${disc.artist}")
    private String artist;

    @Autowired
    Environment evn;

    @Bean
    public BlankDisc disc(){
        return new BlankDisc(
                evn.getProperty("disc.title"),
                evn.getProperty("disc.artist")
        );
    }

    @Bean
    @Qualifier("discT")
    public BlankDisc discT(){
        return new BlankDisc(title, artist);
    }
}
