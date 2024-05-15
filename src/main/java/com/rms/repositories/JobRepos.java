package com.rms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rms.models.Jobs;

public interface JobRepos extends JpaRepository<Jobs, Integer> {

}
