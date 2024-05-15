package com.rms.service;

import java.util.List;

import com.rms.payload.ApplicationDto;
import com.rms.payload.JobsDto;

public interface JobService {
	// Post Job
	JobsDto createJob(JobsDto jobsDto);

	// getAllJobs
	List<JobsDto> getAllJobs();

	// getSigle Jobs
	JobsDto getSignleJObs(Integer jobsId);

	// Applay for job
	ApplicationDto appplayJob(ApplicationDto applicationDto, Integer userId, Integer jobId);

}
