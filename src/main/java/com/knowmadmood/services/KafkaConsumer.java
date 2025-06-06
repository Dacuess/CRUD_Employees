package com.knowmadmood.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.knowmadmood.dtos.TeammateDTO;
import com.knowmadmood.exceptions.CrudEmployeesExceptions;
import com.knowmadmood.models.Teammate;
import com.knowmadmood.persistence.TeammateRepository;

@Service
public class KafkaConsumer {

	private TeammateRepository repository;

	public KafkaConsumer(TeammateRepository repository) {
		this.repository = repository;
	}

	@KafkaListener(topics = "test-topic", groupId = "test-group")
	private void consume(String message) throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		// Registrar este modulo para que Jackson pueda leer las LocalDate, ya que sino
		// da una excepcion tipo:
		// com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8
		// date/time type java.time.LocalDate not supported by default
		mapper.registerModule(new JavaTimeModule());
		try {
			TeammateDTO dto = mapper.readValue(message, TeammateDTO.class);

			Teammate teammate = new Teammate();

			if (dto != null && !StringUtils.isBlank(dto.getEmail())) {

				teammate.setFirstName(dto.getFirstName());
				teammate.setLastName(dto.getLastName());
				teammate.setPosition(dto.getPosition());
				teammate.setActive(dto.getActive());
				teammate.setEmail(dto.getEmail());
				teammate.setStartDate(dto.getStartDate());

				try {
					repository.save(teammate);
				} catch (Exception e) {
					throw new CrudEmployeesExceptions(HttpStatus.CONFLICT,
							"You are trying to insert an existent UUID.");
				}
			} else {
				throw new CrudEmployeesExceptions(HttpStatus.INTERNAL_SERVER_ERROR,
						"Can not insert information in databa base, because there are some empty information.");
			}
		} catch (Exception e) {
			throw e;
		}

	}
}
