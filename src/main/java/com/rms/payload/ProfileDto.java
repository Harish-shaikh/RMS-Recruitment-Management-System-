package com.rms.payload;

import com.rms.models.User;

import jakarta.persistence.OneToOne;

public class ProfileDto {

	private int profileId;
	private String resumeFile;
	private String skills;
	private String education;
	private String experience;
	private String name;
	private String email;
	private String phone;
	private UserDto user;

	public ProfileDto() {

	}

	public ProfileDto(int profileId, String resumeFile, String skills, String education, String experience, String name,
			String email, String phone, UserDto user) {
		super();
		this.profileId = profileId;
		this.resumeFile = resumeFile;
		this.skills = skills;
		this.education = education;
		this.experience = experience;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.user = user;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getResumeFile() {
		return resumeFile;
	}

	public void setResumeFile(String resumeFile) {
		this.resumeFile = resumeFile;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
}