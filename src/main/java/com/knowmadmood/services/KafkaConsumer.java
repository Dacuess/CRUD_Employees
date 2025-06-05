package com.knowmadmood.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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
	public void consume(TeammateDTO message) {

		TeammateDTO dto = message;

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
				throw new CrudEmployeesExceptions(HttpStatus.CONFLICT, "You are trying to insert an existent UUID.");
			}
		} else {
			throw new CrudEmployeesExceptions(HttpStatus.INTERNAL_SERVER_ERROR,
					"Can not insert information in databa base, because there are some empty information.");
		}

	}
}
