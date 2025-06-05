package com.knowmadmood.services;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.knowmadmood.dtos.TeammateDTO;

@EnableKafka
@Service
public class KafkaProducer {
	private final KafkaTemplate<String, TeammateDTO> kafkaTemplate;
	private KafkaConsumer consumer;

	public KafkaProducer(KafkaTemplate<String, TeammateDTO> kafkaTemplate, KafkaConsumer consumer) {
		this.kafkaTemplate = kafkaTemplate;
		this.consumer = consumer;
	}

	// Método para enviar mensajes
	public void sendMessage(String topic, TeammateDTO message) {
		try {
			kafkaTemplate.send(topic, message);
		} catch (Exception e) {
			consumer.consume(message);
		}
	}
}
