package com.knowmadmood.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.knowmadmood.dtos.TeammateDTO;
import com.knowmadmood.dtos.TeammateUUIDDTO;
import com.knowmadmood.exceptions.CrudEmployeesExceptions;
import com.knowmadmood.models.Teammate;
import com.knowmadmood.persistence.TeammateRepository;

@Service
public class CrudEmployeesServiceImpl implements CrudEmployeesService {

	private TeammateRepository repository;

	public CrudEmployeesServiceImpl(TeammateRepository repository) {
		this.repository = repository;
	}

	@Override
	public TeammateUUIDDTO getTeammateById(UUID uuid) throws CrudEmployeesExceptions {

		Optional<Teammate> optional = repository.findById(uuid);

		TeammateUUIDDTO result = new TeammateUUIDDTO();

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
					"Employer with UUID: ".concat(uuid.toString()).concat(" has not been found."));

		}

		return result;
	}

	@Override
	public List<TeammateUUIDDTO> getAllTeammate() throws CrudEmployeesExceptions {

		List<Teammate> listEmployees = repository.findAll();

		List<TeammateUUIDDTO> result = new ArrayList<>();

		if (Boolean.FALSE.equals(listEmployees.isEmpty())) {

			for (Teammate teammate : listEmployees) {

				TeammateUUIDDTO item = new TeammateUUIDDTO();
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
			throw new CrudEmployeesExceptions(HttpStatus.NOT_FOUND, "There are not any Employee in the Data Base.");

		}

		return result;
	}

	@Override
	public String updateTeammate(UUID uuid, TeammateDTO teammateDto) throws CrudEmployeesExceptions {

		Optional<Teammate> optional = repository.findById(uuid);

		if (optional.isPresent()) {

			if (teammateDto != null && !StringUtils.isBlank(teammateDto.getEmail())
					&& teammateDto.getEmail().equals(optional.get().getEmail())) {
				throw new CrudEmployeesExceptions(HttpStatus.CONFLICT, "You are trying to insert an existent email.");
			}

			Teammate teammate = mapTeamMate(uuid, teammateDto);
			repository.save(teammate);

		} else {
			throw new CrudEmployeesExceptions(HttpStatus.NOT_FOUND,
					"Employer with UUID: ".concat(uuid.toString()).concat(" has not been found."));

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
					"Employer with UUID: ".concat(uuid.toString()).concat(" has not been found."));

		}

		return "The UUID: ".concat(uuid.toString()).concat(" has been deleted from data base");
	}

	private Teammate mapTeamMate(UUID uuid, TeammateDTO teammateDto) {
		Teammate teammate = new Teammate();

		if (teammateDto != null) {
			teammate.setId(uuid);
			teammate.setEmail(teammateDto.getEmail());
			teammate.setFirstName(teammateDto.getFirstName());
			teammate.setLastName(teammateDto.getLastName());
			teammate.setPosition(teammateDto.getPosition());
			teammate.setActive(teammateDto.getActive());
			teammate.setStartDate(teammateDto.getStartDate());
		}
		return teammate;
	}

	@Override
	public String createTeammate(TeammateDTO dto) throws CrudEmployeesExceptions {

		if (dto != null && !StringUtils.isBlank(dto.getEmail())) {

			Teammate teammate = new Teammate();
			teammate.setEmail(dto.getEmail());
			teammate.setFirstName(dto.getFirstName());
			teammate.setLastName(dto.getLastName());
			teammate.setPosition(dto.getPosition());
			teammate.setActive(dto.getActive());
			teammate.setStartDate(dto.getStartDate());

			try {
				repository.save(teammate);
				return "New Employeed saved";
			} catch (Exception e) {
				throw new CrudEmployeesExceptions(HttpStatus.CONFLICT, "You are trying to insert an existent email.");
			}
		} else {
			throw new CrudEmployeesExceptions(HttpStatus.INTERNAL_SERVER_ERROR,
					"Can not insert information in databa base, because there are some empty information.");
		}
	}

}
