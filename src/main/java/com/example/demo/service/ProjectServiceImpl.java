package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	private final ProjectRepository projectRepository;
	
	@Override
	public List<Project> findAll() {
		return this.projectRepository.findAll();
	}

	@Override
	public Optional<Project> findById(Long id) {
		return this.projectRepository.findById(id);
	}

	@Override
	public Project save(Project project) {
		return this.projectRepository.save(project);
	}

	@Override
	public Project udpate(Project project) {
		return this.projectRepository.save(project);
	}

	@Override
	public void deleteById(Long id) {
		this.projectRepository.deleteById(id);
	}

	@Override
	public List<Project> findByUsername(String username) {
		return this.projectRepository.findByUsername(username);
	}

}
