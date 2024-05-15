package com.rms.payload;

import com.rms.models.Jobs;
import com.rms.models.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ApplicationDto {
	private Integer id;

	private Integer userId;

	private Integer jobId;

	// Constructors, getters, and setters...

	public ApplicationDto() {

	}

	public ApplicationDto(Integer id, Integer userId, Integer jobId) {
		super();
		this.id = id;
		this.userId = userId;
		this.jobId = jobId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	

	
}
