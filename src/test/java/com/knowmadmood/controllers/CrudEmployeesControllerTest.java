package com.knowmadmood.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.knowmadmood.dtos.TeammateDTO;
import com.knowmadmood.dtos.TeammateUUIDDTO;
import com.knowmadmood.services.CrudEmployeesService;

class CrudEmployeesControllerTest {

	@Mock
	CrudEmployeesService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllEmployeesTest() {

		Mockito.when(service.getAllTeammate()).thenReturn(new ArrayList<TeammateUUIDDTO>());

		new CrudEmployeesController(service).getAllEmployees();

		verify(service, times(1)).getAllTeammate();

	}

	@Test
	void getEmployeeByIdTest() {

		UUID uuid = UUID.randomUUID();
		Mockito.when(service.getTeammateById(Mockito.any(UUID.class))).thenReturn(new TeammateUUIDDTO());

		new CrudEmployeesController(service).getEmployeeById(uuid);

		verify(service, times(1)).getTeammateById(Mockito.any(UUID.class));

	}

	@Test
	void updateEmployeeByIdTest() {

		UUID uuid = UUID.randomUUID();
		Mockito.when(service.updateTeammate(Mockito.any(UUID.class), Mockito.any(TeammateDTO.class))).thenReturn("OK");

		new CrudEmployeesController(service).updateEmployeeById(uuid, new TeammateDTO());

		verify(service, times(1)).updateTeammate(Mockito.any(UUID.class), Mockito.any(TeammateDTO.class));

	}

	@Test
	void deleteEmployeeByIdTest() {

		UUID uuid = UUID.randomUUID();
		Mockito.when(service.deleteTeammate(Mockito.any(UUID.class))).thenReturn("OK");

		new CrudEmployeesController(service).deleteEmployeeById(uuid);

		verify(service, times(1)).deleteTeammate(Mockito.any(UUID.class));

	}
	
	@Test
	void createEmployeeTest() {

		Mockito.when(service.createTeammate(Mockito.any(TeammateDTO.class))).thenReturn("OK");

		new CrudEmployeesController(service).createEmployee(new TeammateDTO());

		verify(service, times(1)).createTeammate(Mockito.any(TeammateDTO.class));

	}
}
