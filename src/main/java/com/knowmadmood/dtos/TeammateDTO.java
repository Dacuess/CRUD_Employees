package com.knowmadmood.dtos;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public class TeammateDTO {

	@Schema(description = "Employee first name")
	private String firstName;

	@Schema(description = "Employee last name")
	private String lastName;

	@Schema(description = "Employee email address")
	private String email;

	@Schema(description = "Employee working position")
	private String position;

	@Schema(description = "Employee start date at the company")
	private LocalDate startDate;

	@Schema(description = "Employee status active/inactive")
	private Boolean active;

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
