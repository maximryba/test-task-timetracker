package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUsers() {
		return this.userService.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		return this.userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public User createUser(@Valid @RequestBody User user) {
		return this.createUser(user);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<User> udpateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
		return this.userService.getById(id)
	            .map(user -> {
	                user.setUsername(userDetails.getUsername());
	                user.setEmail(userDetails.getEmail());
	                user.setPassword(userDetails.getPassword());
	                User updatedUser  = userService.updateUser(user);
	                return ResponseEntity.ok(updatedUser);
	            })
	            .orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        this.userService.getById(id)
                .map(user -> {
                    this.userService.deleteUser(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.noContent().build();
    }
	
}
