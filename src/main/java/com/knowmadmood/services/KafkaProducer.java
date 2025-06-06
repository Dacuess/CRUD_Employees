package com.knowmadmood.services;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.knowmadmood.dtos.TeammateDTO;

@EnableKafka
@Service
public class KafkaProducer {
	private final KafkaTemplate<String, TeammateDTO> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, TeammateDTO> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	// Método para enviar mensajes
	public void sendMessage(String topic, TeammateDTO message) {
		kafkaTemplate.send(topic, message);
	}
}
