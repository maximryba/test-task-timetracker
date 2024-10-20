package com.example.demo.repository;
import com.example.demo.entity.Record;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
	@Query(value = "select * from records where project_id = (select id from projects where user_id = (select id from users where username = ?))", nativeQuery = true)
	List<Record> findAllByUsername(String username);
}
