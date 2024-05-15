package com.rms.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rms.repositories.UserRepos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	private UserRepos userRepos;
	// Logger declaration
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			return userRepos.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("User with email '" + username + "' not found"));
		} catch (Exception e) {

			// Inside the loadUserByUsername method
			logger.error("Error occurred while loading user by username: " + e.getMessage(), e);
			throw new UsernameNotFoundException("Error occurred while loading user by username");
		}
	}

}
