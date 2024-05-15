package com.rms.payload;

import java.util.HashSet;
import java.util.Set;

import com.rms.models.Application;
import com.rms.models.Profile;
import com.rms.models.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class UserDto {
	private int userId;
	private String Name;
	private String email;
	private String address;
	private String password;
	private Role role;
	private Set<ApplicationDto> applications = new HashSet<>();

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Profile profile;

	// Default constructor
	public UserDto() {

	}

	public UserDto(int userId, String name, String email, String address, String password, Role role,
			Set<ApplicationDto> applications, Profile profile) {
		super();
		this.userId = userId;
		Name = name;
		this.email = email;
		this.address = address;
		this.password = password;
		this.role = role;
		this.applications = applications;
		this.profile = profile;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<ApplicationDto> getApplications() {
		return applications;
	}

	public void setApplications(Set<ApplicationDto> applications) {
		this.applications = applications;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}