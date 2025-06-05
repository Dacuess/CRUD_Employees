package com.knowmadmood.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.knowmadmood.dtos.TeammateDTO;
import com.knowmadmood.dtos.TeammateUUIDDTO;
import com.knowmadmood.services.KafkaProducer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Kafka Controller", description = "APIs for produce messages")
public class KafkaController {

	private final KafkaProducer kafkaProducer;

	public KafkaController(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	@PostMapping("/send")
	@Operation(summary = "Send Message", description = "Send Message by Kafka")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Message sent ok", content = @Content(schema = @Schema(implementation = TeammateUUIDDTO.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content(schema = @Schema())) })
	
	public String sendMessage(@RequestBody TeammateDTO message) {
		kafkaProducer.sendMessage("test-topic", message);
		return "Message sent by Kafka: " + message.toString();
	}
}
