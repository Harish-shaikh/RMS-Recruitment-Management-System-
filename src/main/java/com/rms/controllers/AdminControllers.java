package com.rms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.payload.JobsDto;
import com.rms.payload.UserDto;
import com.rms.service.JobService;
import com.rms.service.UserService;

@RestController
@RequestMapping("/api/v2/admin")
public class AdminControllers {
	@Autowired
	private JobService jobService;
	@Autowired
	private UserService userService;

	// POst Jobs
	@PostMapping("/jobs")
	public ResponseEntity<JobsDto> createJobs(@RequestBody JobsDto jobsDto) {
		JobsDto jobs = this.jobService.createJob(jobsDto);
		return new ResponseEntity<JobsDto>(jobs, HttpStatus.CREATED);
	}

	// get ALl user
	@GetMapping("/alluser")
	public ResponseEntity<List<UserDto>> getAllUser() {

		return ResponseEntity.ok(this.userService.allUser());
	}

//get single User
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getSignleUser(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(this.userService.getUser(userId));
	}

	// get Single Jobs
	@GetMapping("/jobs/{jobId}")
	public ResponseEntity<JobsDto> getSignleJobs(@PathVariable("jobId") Integer jobId) {
		return ResponseEntity.ok(this.jobService.getSignleJObs(jobId));
	}

}
