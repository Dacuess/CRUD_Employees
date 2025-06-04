package com.knowmadmood.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowmadmood.dtos.TeammateDTO;
import com.knowmadmood.dtos.TeammateUUIDDTO;
import com.knowmadmood.exceptions.CrudEmployeesExceptions;
import com.knowmadmood.services.CrudEmployeesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "CRUD Employees", description = "APIs for managing employees")
public class CrudEmployeesController {

	private CrudEmployeesService service;

	public CrudEmployeesController(CrudEmployeesService service) {
		this.service = service;
	}

	// To view the API in browser
	// http://localhost:9000/swagger-ui/index.html#/

	// http://localhost:9000/api/teammates
	@Operation(summary = "Get all employees", description = "Get all employees in data base")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employees found successfully", content = @Content(schema = @Schema(implementation = TeammateUUIDDTO.class))),
			@ApiResponse(responseCode = "404", description = "There are not employees in data base", content = @Content(schema = @Schema())) })
	@GetMapping("/teammates")
	public List<TeammateUUIDDTO> getAllEmployees() throws CrudEmployeesExceptions {

		return service.getAllTeammate();

	}

	// http://localhost:9000/api/teammates/1b25ba72-67da-4146-b5ca-25a9f47b2507
	@Operation(summary = "Get employee by UUID", description = "Get the information for the employee by UUID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee found successfully", content = @Content(schema = @Schema(implementation = TeammateUUIDDTO.class))),
			@ApiResponse(responseCode = "404", description = "There are not any employee with the UUID requested", content = @Content(schema = @Schema())) })
	@GetMapping("/teammates/{uuid}")
	public TeammateUUIDDTO getEmployeeById(@Parameter(description = "UUID") @PathVariable UUID uuid)
			throws CrudEmployeesExceptions {

		return service.getTeammateById(uuid);

	}

	@PutMapping("/teammates/{uuid}")
	@Operation(summary = "Update employee by UUID", description = "Upddate the different parameters for the employee by UUID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee updated successfully", content = @Content(schema = @Schema(implementation = TeammateUUIDDTO.class))),
			@ApiResponse(responseCode = "404", description = "There are not any employee with the UUID requested", content = @Content(schema = @Schema())) })
	public String updateEmployeeById(@Parameter(description = "UUID") @PathVariable UUID uuid,
			@RequestBody TeammateDTO teammateDto) throws CrudEmployeesExceptions {

		return service.updateTeammate(uuid, teammateDto);

	}

	@DeleteMapping("/teammates/{uuid}")
	@Operation(summary = "Delete employee by UUID", description = "Delete the different parameters for the employee by UUID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee deleted successfully", content = @Content(schema = @Schema(implementation = TeammateUUIDDTO.class))),
			@ApiResponse(responseCode = "404", description = "There are not any employee with the UUID requested", content = @Content(schema = @Schema())) })
	public String deleteEmployeeById(@Parameter(description = "UUID") @PathVariable UUID uuid)
			throws CrudEmployeesExceptions {

		return service.deleteTeammate(uuid);

	}

	@PostMapping("/teammates/create")
	@Operation(summary = "Create a new employee", description = "Create employee in data base")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Message with the new DTO sent successfully", content = @Content(schema = @Schema(implementation = TeammateUUIDDTO.class))),
			@ApiResponse(responseCode = "409", description = "You are trying to insert an existing email", content = @Content(schema = @Schema())),
			@ApiResponse(responseCode = "500", description = "YCan not insert information in databa base, because there are some empty information.", content = @Content(schema = @Schema()))})
	public String createEmployee(@Parameter(description = "The different parameters to create an Employee") @RequestBody TeammateDTO dto) throws CrudEmployeesExceptions {

		return service.createTeammate(dto);

	}

}
