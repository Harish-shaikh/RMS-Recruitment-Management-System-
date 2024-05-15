package com.rms.service;

import java.util.List;

import com.rms.models.AuthRequest;
import com.rms.models.AuthenticationResponse;
import com.rms.payload.ProfileDto;
import com.rms.payload.UserDto;

public interface UserService {

	// create user Interface
	UserDto createUser(UserDto userDto);

	// update
	UserDto updateUser(Integer userId, UserDto userDto);

	// delete
	void deleteUser(Integer userId);

	// getUser
	UserDto getUser(Integer userId);

	// getAllUser
	List<UserDto> allUser();

	// Profile create
	ProfileDto createProfile(ProfileDto profileDto, Integer userId);

	// login
	AuthenticationResponse authenticate(AuthRequest authRequest);

}
