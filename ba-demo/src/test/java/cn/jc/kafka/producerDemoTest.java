package cn.jc.kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muzhiye on 2017/7/7.
 */
public class producerDemoTest {

    String servers = "192.168.49.220:9092";

    String topic = "DemoEvent";

    private Map<String, Object> props = new HashMap<String, Object>(){
        {
            put("bootstrap.servers", servers);
            put("acks", "all");
            put("retries", 0);
            put("batch.size", 100);
            put("linger.ms", 1);
            put("buffer.memory", 33554432);
            put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        }
    };


    @Test
    public void testProducer(){
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

    @Test
    public void testSpringKafkaProducer(){

        ProducerFactory<Integer, String> producerFactory = new DefaultKafkaProducerFactory<Integer, String>(props);

        Producer producer = producerFactory.createProducer();

        Map<String, Object> map = new HashMap<String, Object>(){
            {
                put("id", 1);
            }
        };
        for (int i = 0; i < 5; i++){
            ProducerRecord record = new ProducerRecord(topic, JSON.toJSONString(map));
            producer.send(record);
        }

        producer.close();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
