package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Project;

public interface ProjectService {

	List<Project> findAll();
	
	Optional<Project> findById(Long id);
	
	Project save(Project project);
	
	Project udpate(Project project);
	
	void deleteById(Long id);
	
	List<Project> findByUsername(String username);
	
}
