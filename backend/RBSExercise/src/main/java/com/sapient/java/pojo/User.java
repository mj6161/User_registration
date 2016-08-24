package com.sapient.java.pojo;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "USER_DETAILS")
@Scope("session")
public class User implements Serializable {

	@Id
	@Column(name = "USERNAME", nullable = false)
	private String username;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;
	@Column(name = "LASTNAME", nullable = false)
	private String lastName;
	@Column(name = "DESIGNATION", nullable = false)
	private String designation;
	@Column(name = "PHONE_NO", nullable = false)
	private String phone_no;

	public User() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName == null)
			throw new NullPointerException(" firstName should not be null");
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName == null)
			throw new NullPointerException("Username should not be null");
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username == null)
			throw new NullPointerException("Username should not be null");
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null)
			throw new NullPointerException("Username should not be null");
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		if (designation == null)
			throw new NullPointerException("designation should not be null");
		this.designation = designation;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) throws InvalidArgumentException {
		if (phone_no == null)
			throw new NullPointerException("Contact number is missing");
		if (phone_no.length() != 10)
			throw new InvalidArgumentException("Number is Invalid");

		this.phone_no = phone_no;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", designation=" + designation + ", phone_no=" + phone_no + "]";
	}

}
