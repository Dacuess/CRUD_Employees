package com.knowmadmood.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.knowmadmood.models.Teammate;
import com.knowmadmood.persistence.TeammateRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase // usa H2 por defecto
class CrudEmployeesControllerTestIntegration {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TeammateRepository repository;

	@BeforeEach
	void cleanDatabase() {
		repository.deleteAll();
	}

	private static final String JSON = """
				        {
			            "firstName": "Juan",
			"lastName": "string",
			"email": "test@test",
			"position": "string",
			"startDate": "2025-06-05",
			"active": true
			}
			      }
			      """;

	@Test
	public void createAndGetEmployeeTest() throws Exception {

		// Call to endpoint POST api/teammates/create to create a new employee
		mockMvc.perform(post("http://localhost:9000/api/teammates/create").contentType(MediaType.APPLICATION_JSON)
				.content(JSON)).andExpect(status().isOk());

		// Call to endpoint GET api/teammates to get all the employees
		mockMvc.perform(get("http://localhost:9000/api/teammates")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName").value("Juan"))
				.andExpect(jsonPath("$[0].email").value("test@test"));
	}

	@Test
	public void putGetAndDeleteEmployeeTest() throws Exception {

		// Insert a new employee to call endpoint GET api/teammates/{UUID} to get a
		// specific employee
		Teammate t = new Teammate();
		t.setEmail("test2@test");
		t.setActive(true);
		t.setFirstName("test");
		t.setLastName("test");
		t.setPosition("developer");
		t.setStartDate(LocalDate.now());

		Teammate t2 = repository.save(t);

		mockMvc.perform(get("http://localhost:9000/api/teammates/".concat(t2.getId().toString())))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("test"))
				.andExpect(jsonPath("$.email").value("test2@test"));

		// Update the information for the employee with th UUID calling enpoint PUT
		// pi/teammates/{UUID} and then endpoint GET api/teammates/{UUID} to get the
		// information updated
		String jsonUpdate = """
				    	        {
				    	              "firstName": "test",
				  "lastName": "test",
				  "email": "pruebaUpdate@test",
				  "position": "developer",
				  "startDate": "2025-06-05",
				  "active": true
				}
				    	        }
				    	        """;

		mockMvc.perform(put("http://localhost:9000/api/teammates/".concat(t2.getId().toString()))
				.contentType(MediaType.APPLICATION_JSON).content(jsonUpdate)).andExpect(status().isOk());

		mockMvc.perform(get("http://localhost:9000/api/teammates/".concat(t2.getId().toString())))
				.andExpect(status().isOk()).andExpect(jsonPath("$.email").value("pruebaUpdate@test"));

		// Delete an Employee
		mockMvc.perform(delete("http://localhost:9000/api/teammates/".concat(t2.getId().toString())))
				.andExpect(status().isOk());

		mockMvc.perform(get("http://localhost:9000/api/teammates/".concat(t2.getId().toString())))
				.andExpect(status().is4xxClientError());
	}
}
