package cn.jc.kafka;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muzhiye on 2017/7/7.
 */
public class producerDemoTest {

    @Test
    public void testProducer(){
        String servers = "192.168.49.220:9092";

        String topic = "DemoEvent";

        ProducerDemo producer = new ProducerDemo(servers);
        Map<String, Object> map = new HashMap<String, Object>(){
            {
                put("id", 1);
            }
        };

        for (int i = 0; i < 5; i++)
            producer.send(topic, JSON.toJSONString(map));

        producer.close();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
