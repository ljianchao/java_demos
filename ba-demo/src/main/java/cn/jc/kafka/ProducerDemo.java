package cn.jc.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by muzhiye on 2017/7/7.
 */
public class ProducerDemo {


    private final Producer<String, String> producer;

    public ProducerDemo(String bootstrapServers){
        if (bootstrapServers == null || bootstrapServers.isEmpty())
            throw new NullPointerException("bootstrapServers is null");
        Properties props = buildProperties(bootstrapServers);

        producer = new KafkaProducer<String, String>(props);
    }

    private Properties buildProperties(String bootstrapServers){
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 100);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return props;
    }

    public void send(String topic, String message){
        ProducerRecord record = new ProducerRecord(topic, message);
        producer.send(record);
    }

    public void close(){
        producer.close();
    }
}
