package com.kowmadmood.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.knowmadmood.dtos.TeammateDTO;
import com.knowmadmood.exceptions.CrudEmployeesExceptions;
import com.kowmadmood.models.Teammate;
import com.kowmadmood.persistence.TeammateRepository;

@Service
public class CrudEmployeesServiceImpl implements CrudEmployeesService {

	private TeammateRepository repository;

	public CrudEmployeesServiceImpl(TeammateRepository repository) {
		this.repository = repository;
	}

	@Override
	public TeammateDTO getTeammateById(UUID uuid) throws CrudEmployeesExceptions {

		Optional<Teammate> optional = repository.findById(uuid);

		TeammateDTO result = new TeammateDTO();

		if (optional.isPresent()) {

			Teammate teammate = optional.get();

			result.setId(teammate.getId());
			result.setFirstName(teammate.getFirstName());
			result.setLastName(teammate.getLastName());
			result.setEmail(teammate.getEmail());
			result.setPosition(teammate.getPosition());
			result.setStartDate(teammate.getStartDate());
			result.setActive(teammate.getActive());
		} else {
			throw new CrudEmployeesExceptions(HttpStatus.NOT_FOUND,
					"Employer with UUID:".concat(uuid.toString()).concat(" has not been found."));

		}

		return result;
	}

	@Override
	public List<TeammateDTO> getAllTeammate() throws CrudEmployeesExceptions {

		List<Teammate> listEmployees = repository.findAll();

		List<TeammateDTO> result = new ArrayList<>();

		if (Boolean.FALSE.equals(listEmployees.isEmpty())) {

			for (Teammate teammate : listEmployees) {

				TeammateDTO item = new TeammateDTO();
				item.setId(teammate.getId());
				item.setFirstName(teammate.getFirstName());
				item.setLastName(teammate.getLastName());
				item.setEmail(teammate.getEmail());
				item.setPosition(teammate.getPosition());
				item.setStartDate(teammate.getStartDate());
				item.setActive(teammate.getActive());
				result.add(item);
			}

		} else {
			throw new CrudEmployeesExceptions(HttpStatus.NOT_FOUND, "There are not any Employee in the Data Base");

		}

		return result;
	}

	@Override
	public String updateTeammate(UUID uuid, TeammateDTO teammateDto) throws CrudEmployeesExceptions {

		Optional<Teammate> optional = repository.findById(uuid);

		if (optional.isPresent()) {

			Teammate teammate = mapTeamMate(uuid, teammateDto);
			repository.save(teammate);

		} else {
			throw new CrudEmployeesExceptions(HttpStatus.NOT_FOUND,
					"Employer with UUID:".concat(uuid.toString()).concat(" has not been found."));

		}

		return "The update has been finished for UUID: ".concat(uuid.toString());
	}

	@Override
	public String deleteTeammate(UUID uuid) throws CrudEmployeesExceptions {

		Optional<Teammate> optional = repository.findById(uuid);

		if (optional.isPresent()) {
			repository.deleteById(uuid);

		} else {
			throw new CrudEmployeesExceptions(HttpStatus.NOT_FOUND,
					"Employer with UUID:".concat(uuid.toString()).concat(" has not been found."));

		}

		return "The UUID: ".concat(uuid.toString()).concat(" has been deleted from data base");
	}

	private Teammate mapTeamMate(UUID uuid, TeammateDTO teammateDto) {
		Teammate teammate = new Teammate();

		teammate.setId(uuid);
		teammate.setEmail(teammateDto.getEmail());
		teammate.setFirstName(teammateDto.getFirstName());
		teammate.setLastName(teammateDto.getLastName());
		teammate.setPosition(teammateDto.getPosition());
		teammate.setActive(teammateDto.getActive());
		teammate.setStartDate(teammateDto.getStartDate());
		return teammate;
	}

}
