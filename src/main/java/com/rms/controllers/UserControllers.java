package com.rms.controllers;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.Exceptions.ResourceNotFoundException;
import com.rms.models.AuthRequest;
import com.rms.models.AuthenticationResponse;
import com.rms.payload.JobsDto;
import com.rms.payload.ProfileDto;
import com.rms.payload.UserDto;
import com.rms.service.JobService;
import com.rms.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserControllers {
	@Autowired
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JobService jobService;

	private final String API_ENDPOINT = "https://api.apilayer.com/resume_parser/upload";
	private final String API_KEY = "gNiXyflsFu3WNYCz1ZCxdWDb7oQg1Nl1";

	// API endpoint to create a new user
	@PostMapping("/signup")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		// Delegate user creation logic to the UserService
		UserDto saveUserDto = this.userService.createUser(userDto);
		// Return the created user DTO along with HTTP status 201 (Created)
		return new ResponseEntity<UserDto>(saveUserDto, HttpStatus.CREATED);
	}

	// API endpoint to get all jobs
	@GetMapping("/jobs")
	public ResponseEntity<List<JobsDto>> allJobs() {
		// Delegate fetching all jobs logic to the JobService
		return ResponseEntity.ok(this.jobService.getAllJobs());
	}

	// API endpoint to upload resume and create profile
	@PostMapping("/uploadresume/{userId}")
	public ResponseEntity<ProfileDto> uploadData(@RequestParam("file") MultipartFile file,
			@PathVariable("userId") Integer userId, ProfileDto profileDto) {
		// Prepare request headers for the resume parsing API
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.set("apikey", API_KEY);

		// Prepare request entity with the resume file content
		byte[] fileBytes;
		try {
			fileBytes = file.getBytes();
		} catch (IOException e) {
			// Handle IOException
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		HttpEntity<byte[]> requestEntity = new HttpEntity<>(fileBytes, headers);

		// Make API call to the resume parsing endpoint
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(API_ENDPOINT, HttpMethod.POST, requestEntity,
				String.class);

		// Handle API response
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			String responseData = responseEntity.getBody();

			try {
				// Parse JSON string into a JsonNode
				JsonNode jsonNode = objectMapper.readTree(responseData);

				// Extract relevant fields from the JSON response
				String name = jsonNode.get("name").asText();
				String email = jsonNode.get("email").asText();
				String phone = jsonNode.get("phone").asText();
				// Extract skills, education, and experience arrays
				// Convert JSON arrays to strings
				String skills = objectMapper.writeValueAsString(jsonNode.get("skills"));
				String education = objectMapper.writeValueAsString(jsonNode.get("education"));
				String experience = objectMapper.writeValueAsString(jsonNode.get("experience"));

				// Set the extracted profile data to the profile DTO
				profileDto.setResumeFile(file.getOriginalFilename());
				profileDto.setName(name);
				profileDto.setEmail(email);
				profileDto.setPhone(phone);
				profileDto.setSkills(skills);
				profileDto.setEducation("hello");
				profileDto.setExperience(experience);

				// Save the ProfileDto object to the database using UserService
				ProfileDto savedProfile = this.userService.createProfile(profileDto, userId);

				// Return ResponseEntity with saved profile
				return ResponseEntity.ok(savedProfile);
			} catch (IOException e) {
				// Handle parsing exception
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			// Handle unsuccessful API call
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// API endpoint to apply for a job
	@PostMapping("/jobs/apply/{jobsId}/{userId}")
	public ResponseEntity<String> applyJobs(@PathVariable("jobsId") Integer jobId,
			@PathVariable("userId") Integer userId) {
		try {
			// Delegate applying for the job to the JobService
			this.jobService.appplayJob(null, userId, jobId);
			// Return success response
			return new ResponseEntity<String>("You applied successfully!", HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			// Handle the case where Jobs or User not found
			return new ResponseEntity<String>("Failed to apply for the job: " + e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			// Handle any other exceptions
			return new ResponseEntity<String>("Failed to apply for the job: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Login url
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		try {
			// Authenticate the user
			AuthenticationResponse authenticationResponse = userService.authenticate(authRequest);
			return ResponseEntity.ok(authenticationResponse);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
}
