package com.rms.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Jobs {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jobId;
	private String jobTitle;
	private String description;
	private Date postedOn;
	private int totalApplications;
	private String comapanyName;

	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Application> applications = new HashSet<>();

	public Jobs() {

	}

	public Jobs(int jobId, String jobTitle, String description, Date postedOn, int totalApplications,
			String comapanyName, Set<Application> applications) {
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

	public Set<Application> getApplications() {
		return applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

}
