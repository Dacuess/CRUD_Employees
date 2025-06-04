package com.knowmadmood.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.knowmadmood.dtos.TeammateDTO;
import com.knowmadmood.dtos.TeammateUUIDDTO;
import com.knowmadmood.exceptions.CrudEmployeesExceptions;
import com.knowmadmood.models.Teammate;
import com.knowmadmood.persistence.TeammateRepository;

class CrudEmployeesServiceImplTest {

	@Mock
	TeammateRepository repository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getTeammateByIdTestOK() {

		UUID uuid = UUID.randomUUID();
		Teammate t = new Teammate();
		t.setId(uuid);
		t.setFirstName("TEST");
		t.setLastName("TEST");
		t.setEmail("TEST@TEST.COM");
		t.setActive(Boolean.TRUE);
		t.setPosition("DEVELOPER");
		t.setStartDate(LocalDate.now());
		Optional<Teammate> optional = Optional.of(t);

		Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(optional);

		TeammateUUIDDTO result = new CrudEmployeesServiceImpl(repository).getTeammateById(uuid);

		assertEquals("DEVELOPER", result.getPosition(), "Test to check that the response has the expected parameters");

	}

	@Test
	void getTeammateByIdTestERROR() {

		UUID uuid = UUID.randomUUID();
		Optional<Teammate> optional = Optional.empty();

		Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(optional);

		CrudEmployeesExceptions thrown = Assertions.assertThrows(CrudEmployeesExceptions.class, () -> {
			new CrudEmployeesServiceImpl(repository).getTeammateById(uuid);

		});

		assertEquals("Employer with UUID: " + uuid + " has not been found.", thrown.getBody().getDetail(),
				"Test to check that the error code expected ");

	}

	@Test
	void getAllTeammateTestOK() {

		UUID uuid = UUID.randomUUID();
		Teammate t = new Teammate();
		t.setId(uuid);
		t.setFirstName("TEST");
		t.setLastName("TEST");
		t.setEmail("TEST@TEST.COM");
		t.setActive(Boolean.TRUE);
		t.setPosition("DEVELOPER");
		t.setStartDate(LocalDate.now());

		java.util.List<Teammate> list = new ArrayList<Teammate>();
		list.add(t);

		Mockito.when(repository.findAll()).thenReturn(list);

		java.util.List<TeammateUUIDDTO> result = new CrudEmployeesServiceImpl(repository).getAllTeammate();

		assertEquals(1, result.size(), "Test to check the size for the returned list");

	}

	@Test
	void getAllTeammateTestERROR() {

		java.util.List<Teammate> list = new ArrayList<Teammate>();

		Mockito.when(repository.findAll()).thenReturn(list);

		CrudEmployeesExceptions thrown = Assertions.assertThrows(CrudEmployeesExceptions.class, () -> {
			new CrudEmployeesServiceImpl(repository).getAllTeammate();

		});

		assertEquals("There are not any Employee in the Data Base.", thrown.getBody().getDetail(),
				"Test to check that the error code expected ");

	}

	@Test
	void updateTeammateTestOK() {

		UUID uuid = UUID.randomUUID();
		TeammateDTO t = new TeammateDTO();
		t.setFirstName("TEST");
		t.setLastName("TEST");
		t.setEmail("TEST@TEST.COM");
		t.setActive(Boolean.TRUE);
		t.setPosition("DEVELOPER");
		t.setStartDate(LocalDate.now());

		Teammate t2 = new Teammate();
		t2.setId(UUID.randomUUID());
		t2.setFirstName("TEST");
		t2.setLastName("TEST");
		t2.setEmail("TEST2@TEST.COM");
		t2.setActive(Boolean.TRUE);
		t2.setPosition("DEVELOPER");
		t2.setStartDate(LocalDate.now());

		Optional<Teammate> optional = Optional.of(t2);

		Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(optional);
		Mockito.when(repository.save(Mockito.any(Teammate.class))).thenReturn(new Teammate());

		String result = new CrudEmployeesServiceImpl(repository).updateTeammate(uuid, t);

		assertEquals("The update has been finished for UUID: " + uuid, result,
				"Test to check correct message to returned");

	}

	@Test
	void updateTeammateTestERROR409() {

		UUID uuid = UUID.randomUUID();
		TeammateDTO t = new TeammateDTO();
		t.setFirstName("TEST");
		t.setLastName("TEST");
		t.setEmail("TEST@TEST.COM");
		t.setActive(Boolean.TRUE);
		t.setPosition("DEVELOPER");
		t.setStartDate(LocalDate.now());

		Teammate t2 = new Teammate();
		t2.setId(UUID.randomUUID());
		t2.setFirstName("TEST");
		t2.setLastName("TEST");
		t2.setEmail("TEST@TEST.COM");
		t2.setActive(Boolean.TRUE);
		t2.setPosition("DEVELOPER");
		t2.setStartDate(LocalDate.now());

		Optional<Teammate> optional = Optional.of(t2);

		Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(optional);
		Mockito.when(repository.save(Mockito.any(Teammate.class))).thenReturn(new Teammate());

		CrudEmployeesExceptions thrown = Assertions.assertThrows(CrudEmployeesExceptions.class, () -> {
			new CrudEmployeesServiceImpl(repository).updateTeammate(uuid, t);

		});

		assertEquals("You are trying to insert an existent email.", thrown.getBody().getDetail(),
				"Test to check that the error code expected ");

	}

	@Test
	void updateTeammateTestERROR404() {

		UUID uuid = UUID.randomUUID();
		TeammateDTO t = new TeammateDTO();
		t.setFirstName("TEST");
		t.setLastName("TEST");
		t.setEmail("TEST@TEST.COM");
		t.setActive(Boolean.TRUE);
		t.setPosition("DEVELOPER");
		t.setStartDate(LocalDate.now());

		Optional<Teammate> optional = Optional.empty();

		Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(optional);
		Mockito.when(repository.save(Mockito.any(Teammate.class))).thenReturn(new Teammate());

		CrudEmployeesExceptions thrown = Assertions.assertThrows(CrudEmployeesExceptions.class, () -> {
			new CrudEmployeesServiceImpl(repository).updateTeammate(uuid, t);

		});

		assertEquals("Employer with UUID: " + uuid + " has not been found.", thrown.getBody().getDetail(),
				"Test to check that the error code expected ");

	}

	@Test
	void deleteTeammateTestOK() {

		UUID uuid = UUID.randomUUID();

		Teammate t2 = new Teammate();
		t2.setId(UUID.randomUUID());
		t2.setFirstName("TEST");
		t2.setLastName("TEST");
		t2.setEmail("TEST2@TEST.COM");
		t2.setActive(Boolean.TRUE);
		t2.setPosition("DEVELOPER");
		t2.setStartDate(LocalDate.now());

		Optional<Teammate> optional = Optional.of(t2);

		Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(optional);

		String result = new CrudEmployeesServiceImpl(repository).deleteTeammate(uuid);

		assertEquals("The UUID: " + uuid + " has been deleted from data base", result,
				"Test to check correct message to returned");

	}

	@Test
	void deleteTeammateTestERROR404() {

		UUID uuid = UUID.randomUUID();

		Optional<Teammate> optional = Optional.empty();

		Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(optional);

		CrudEmployeesExceptions thrown = Assertions.assertThrows(CrudEmployeesExceptions.class, () -> {
			new CrudEmployeesServiceImpl(repository).deleteTeammate(uuid);

		});

		assertEquals("Employer with UUID: " + uuid + " has not been found.", thrown.getBody().getDetail(),
				"Test to check that the error code expected ");

	}

	@Test
	void createTeammateTestOK() {

		UUID uuid = UUID.randomUUID();

		TeammateDTO t2 = new TeammateDTO();
		t2.setFirstName("TEST");
		t2.setLastName("TEST");
		t2.setEmail("TEST2@TEST.COM");
		t2.setActive(Boolean.TRUE);
		t2.setPosition("DEVELOPER");
		t2.setStartDate(LocalDate.now());

		Mockito.when(repository.save(Mockito.any(Teammate.class))).thenReturn(new Teammate());

		String result = new CrudEmployeesServiceImpl(repository).createTeammate(t2);

		assertEquals("New Employeed saved", result, "Test to check correct message to returned");

	}

	@Test
	void createTeammateTestERROR409() {

		TeammateDTO t2 = new TeammateDTO();
		t2.setFirstName("TEST");
		t2.setLastName("TEST");
		t2.setEmail("TEST2@TEST.COM");
		t2.setActive(Boolean.TRUE);
		t2.setPosition("DEVELOPER");
		t2.setStartDate(LocalDate.now());

		Mockito.when(repository.save(Mockito.any(Teammate.class))).thenThrow(new NullPointerException());

		CrudEmployeesExceptions thrown = Assertions.assertThrows(CrudEmployeesExceptions.class, () -> {
			new CrudEmployeesServiceImpl(repository).createTeammate(t2);

		});

		assertEquals("You are trying to insert an existent email.", thrown.getBody().getDetail(),
				"Test to check that the error code expected ");

	}

	@Test
	void createTeammateTestERROR500() {

		TeammateDTO t2 = new TeammateDTO();
		t2.setFirstName("TEST");
		t2.setLastName("TEST");
		t2.setEmail("");
		t2.setActive(Boolean.TRUE);
		t2.setPosition("DEVELOPER");
		t2.setStartDate(LocalDate.now());

		CrudEmployeesExceptions thrown = Assertions.assertThrows(CrudEmployeesExceptions.class, () -> {
			new CrudEmployeesServiceImpl(repository).createTeammate(t2);

		});

		assertEquals("Can not insert information in databa base, because there are some empty information.",
				thrown.getBody().getDetail(), "Test to check that the error code expected ");

	}
}
