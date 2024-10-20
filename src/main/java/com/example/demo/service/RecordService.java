package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Record;

public interface RecordService {
	
	List<Record> findAll();
	
	List<Record> findAllByUsername(String username);
	
	Optional<Record> findById(Long id);
	
	Record save(Record record);
	
	Record update(Record record);
	
	void deleteById(Long id);
}
