package com.rms.serviceImp;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rms.Exceptions.ResourceNotFoundException;
import com.rms.models.Application;
import com.rms.models.Jobs;
import com.rms.models.User;
import com.rms.payload.ApplicationDto;
import com.rms.payload.JobsDto;
import com.rms.repositories.ApplicationRepos;
import com.rms.repositories.JobRepos;
import com.rms.repositories.UserRepos;
import com.rms.service.JobService;

@Service
public class JobServiceImp implements JobService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApplicationRepos applicationRepos;

	@Autowired
	private UserRepos userRepos;

	@Autowired
	private JobRepos jobRepos;

	@Override
	public JobsDto createJob(JobsDto jobsDto) {
		Jobs jobs = this.modelMapper.map(jobsDto, Jobs.class);
		jobs.setPostedOn(new Date());
		Jobs saveJobs = this.jobRepos.save(jobs);

		return this.modelMapper.map(saveJobs, JobsDto.class);

	}

	@Override
	public List<JobsDto> getAllJobs() {
		List<Jobs> allJobs = this.jobRepos.findAll();
		List<JobsDto> jobsDto = allJobs.stream().map(j -> this.modelMapper.map(j, JobsDto.class))
				.collect(Collectors.toList());
		return jobsDto;
	}

	@Override
	public JobsDto getSignleJObs(Integer jobsId) {
		Jobs jobs = this.jobRepos.findById(jobsId)
				.orElseThrow(() -> new ResourceNotFoundException("Jobs", "JobsId", jobsId));
		return this.modelMapper.map(jobs, JobsDto.class);
	}

	@Override
	public ApplicationDto appplayJob(ApplicationDto applicationDto, Integer userId, Integer jobId) {
		Jobs jobs = this.jobRepos.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("Jobs", "JobsId", jobId));
		User user = this.userRepos.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

		// If ApplicationDto contains necessary information, map it to Application
		// entity
		Application application;
		if (applicationDto != null) {
			application = this.modelMapper.map(applicationDto, Application.class);
		} else {
			application = new Application();
			// Set any additional properties if needed
		}

		application.setJob(jobs);
		application.setUser(user);

		Application savedApplication = this.applicationRepos.save(application);

		return this.modelMapper.map(savedApplication, ApplicationDto.class);
	}

}
