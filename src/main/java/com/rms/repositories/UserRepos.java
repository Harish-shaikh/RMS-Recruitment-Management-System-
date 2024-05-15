package com.rms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rms.models.User;

public interface UserRepos extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
