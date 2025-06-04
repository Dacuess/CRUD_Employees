package com.kowmadmood.services;

import java.util.List;
import java.util.UUID;

import com.knowmadmood.dtos.TeammateDTO;
import com.knowmadmood.exceptions.CrudEmployeesExceptions;

public interface CrudEmployeesService {

	public TeammateDTO getTeammateById(UUID uuid) throws CrudEmployeesExceptions;

	public List<TeammateDTO> getAllTeammate() throws CrudEmployeesExceptions;
	
	public String updateTeammate(UUID uuid, TeammateDTO teammateDto) throws CrudEmployeesExceptions;
	
	public String deleteTeammate(UUID uuid) throws CrudEmployeesExceptions;
}
