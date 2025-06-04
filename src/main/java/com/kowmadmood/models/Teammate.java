package com.kowmadmood.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TEAMMATE")
public class Teammate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID")
	private UUID id;
	
	@Column(name = "FIRST_NAME", unique = false, nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", unique = false, nullable = false)
	private String lastName;

	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@Column(name = "POSITION", unique = false, nullable = false)
	private String position;

	@Column(name = "START_DATE", unique = false, nullable = false)
	private LocalDate startDate;

	@Column(name = "ACTIVE", unique = false, nullable = false)
	private Boolean active;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
