package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Record;
import com.example.demo.repository.RecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
	
	private final RecordRepository recordRepository;
	
	@Override
	public List<Record> findAll() {
		return this.recordRepository.findAll();
	}

	@Override
	public Optional<Record> findById(Long id) {
		return this.recordRepository.findById(id);
	}

	@Override
	public Record save(Record record) {
		return this.recordRepository.save(record);
	}

	@Override
	public Record update(Record record) {
		return this.recordRepository.save(record);
	}

	@Override
	public void deleteById(Long id) {
		this.recordRepository.deleteById(id);
	}

	@Override
	public List<Record> findAllByUsername(String username) {
		return this.recordRepository.findAllByUsername(username);
	}

}
