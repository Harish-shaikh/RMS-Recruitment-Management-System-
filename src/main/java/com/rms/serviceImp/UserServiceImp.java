package com.rms.serviceImp;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rms.Exceptions.ResourceNotFoundException;
import com.rms.models.AuthRequest;
import com.rms.models.AuthenticationResponse;
import com.rms.models.Profile;
import com.rms.models.User;
import com.rms.payload.ProfileDto;
import com.rms.payload.UserDto;
import com.rms.repositories.ProfileRepos;
import com.rms.repositories.UserRepos;
import com.rms.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepos userRepos;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ProfileRepos profileRepos;

	private AuthenticationManager authenticationManager; //

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public UserServiceImp(UserRepos userRepos, PasswordEncoder passwordEncoder, JwtService jwtService,
			AuthenticationManager authenticationManager) {
		this.jwtService = jwtService;
		this.userRepos = userRepos;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User saveUser = this.userRepos.save(user);

		return this.modelMapper.map(saveUser, UserDto.class);

	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDto getUser(Integer userId) {
		User user = this.userRepos.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userId));
		return this.modelMapper.map(user, UserDto.class);

	}

	@Override
	public List<UserDto> allUser() {
		List<User> getAllUser = this.userRepos.findAll();
		List<UserDto> getAllUsers = getAllUser.stream().map(u -> this.modelMapper.map(u, UserDto.class))
				.collect(Collectors.toList());

		return getAllUsers;
	}

	@Override
	public ProfileDto createProfile(ProfileDto profileDto, Integer userId) {
		User user = this.userRepos.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userId));
		Profile profile = this.modelMapper.map(profileDto, Profile.class);
		profile.setUser(user);
		Profile saveProfile = this.profileRepos.save(profile);
		return this.modelMapper.map(saveProfile, ProfileDto.class);
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticationResponse authenticate(AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		try {
			if (authentication.isAuthenticated()) {
				User user = userRepos.findByEmail(authRequest.getUsername())
						.orElseThrow(() -> new UsernameNotFoundException("User not Found"));
				String jwt = jwtService.generateToken(user);
//				UserDto userDetail = this.modelMapper.map(user, UserDto.class);
				return new AuthenticationResponse(jwt, "User login was successful");
			} else {
				throw new UsernameNotFoundException("invalid user request!");
			}
		} catch (Exception e) {
			return new AuthenticationResponse("", e.getMessage());

		}

	}
}
