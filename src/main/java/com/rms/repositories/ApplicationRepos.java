package com.rms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rms.models.Application;

public interface ApplicationRepos extends JpaRepository<Application, Integer> {

}
