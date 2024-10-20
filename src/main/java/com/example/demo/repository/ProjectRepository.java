package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	@Query(value = "select id, name, user_id from projects where user_id = (select id from users where username = ?)", nativeQuery = true)
	List<Project> findByUsername(String username);
}
