package com.tanmay.discourse.serdes;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DiscourseKafkaSerializer<T> implements Serializer<T> {

	@Override
	public byte[] serialize(String topic, T data) {
		ObjectMapper mapper = new ObjectMapper();
		byte[] bytes = null;
		try {
			bytes = mapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return bytes;
	}

}
