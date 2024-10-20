package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

	private final ProjectService projectService;
	
	private final UserService userService;
	
	@GetMapping("/list")
	public List<Project> getAllProjects(@AuthenticationPrincipal UserDetails userDetails) {
		String username = userDetails.getUsername();
	    return this.projectService.findByUsername(username);
	}
	
	@GetMapping("/admin/list")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Project> getAllProjectsForAdmin() {
	    return this.projectService.findAll();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return this.projectService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	@PostMapping("/create")
    public Project createProject (@Valid @RequestBody Project project) {
		User user = this.userService.getById(project.getUser().getId()).get();
		project.setUser(user);
        return this.projectService.save(project);
    }
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject (@PathVariable Long id, @Valid @RequestBody Project projectDetails) {
        return this.projectService.findById(id)
                .map(project -> {
                    project.setName(projectDetails.getName());
                    return ResponseEntity.ok(this.projectService.save(project));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProject (@PathVariable Long id) {
        this.projectService.findById(id)
                .map(project -> {
                    this.projectService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.noContent().build();
    }
	
}
