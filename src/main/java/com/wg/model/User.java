package com.wg.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.wg.model.enums.Gender;
import com.wg.model.enums.Role;

public class User {
	private String userId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String userEmail;
	private Gender gender;
	private Role role;
	private Timestamp createdAt;
	private String password;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId() {
		this.userId = UUID.randomUUID().toString();
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String userId, String firstName, String lastName, String phoneNumber, String userEmail, Gender gender,
			Role role, Timestamp createdAt, String password) {
		super();
		this.userId = (userId == null) ? UUID.randomUUID().toString() : userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.userEmail = userEmail;
		this.gender = gender;
		this.role = role;
		this.createdAt = createdAt;
		this.password = password;
	}
	
	public User() {
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", userEmail=" + userEmail + ", gender=" + gender + ", createdAt=" + createdAt
				+ ", password=" + password + "]";
	}
	
	
	
}
