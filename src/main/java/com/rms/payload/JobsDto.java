package com.rms.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.rms.models.Application;
import com.rms.models.User;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class JobsDto {

	private int jobId;
	private String jobTitle;
	private String description;
	private Date postedOn;
	private int totalApplications;
	private String comapanyName;

	
	private Set<ApplicationDto> applications = new HashSet<>();

	public JobsDto() {

	}

	public JobsDto(int jobId, String jobTitle, String description, Date postedOn, int totalApplications,
			String comapanyName, Set<ApplicationDto> applications) {
		super();
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.description = description;
		this.postedOn = postedOn;
		this.totalApplications = totalApplications;
		this.comapanyName = comapanyName;
		this.applications = applications;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public int getTotalApplications() {
		return totalApplications;
	}

	public void setTotalApplications(int totalApplications) {
		this.totalApplications = totalApplications;
	}

	public String getComapanyName() {
		return comapanyName;
	}

	public void setComapanyName(String comapanyName) {
		this.comapanyName = comapanyName;
	}

	public Set<ApplicationDto> getApplications() {
		return applications;
	}

	public void setApplications(Set<ApplicationDto> applications) {
		this.applications= applications;
	}

	

}
