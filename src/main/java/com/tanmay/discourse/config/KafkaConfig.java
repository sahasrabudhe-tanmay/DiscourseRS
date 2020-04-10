package com.tanmay.discourse.config;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tanmay.discourse.serdes.DiscourseKafkaSerializer;

@Configuration
public class KafkaConfig<K, V> {

	@Bean
	public KafkaProducer<K, V> kafkaProducer() {
		DiscourseKafkaSerializer<K> keySerializer = new DiscourseKafkaSerializer<>();
		DiscourseKafkaSerializer<V> valueSerializer = new DiscourseKafkaSerializer<>();
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer.getClass());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer.getClass());
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "discourse-rs");
		
		return new KafkaProducer<>(props);
	}
	
}
