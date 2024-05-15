package com.rms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rms.models.Profile;

public interface ProfileRepos extends JpaRepository<Profile, Integer> {

}
