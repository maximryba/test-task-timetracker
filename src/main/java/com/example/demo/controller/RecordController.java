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

import com.example.demo.service.RecordService;

import jakarta.validation.Valid;

import com.example.demo.entity.Record;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/records")
public class RecordController {
	
	private final RecordService recordService;
	
	@GetMapping("/list")
    public List<Record> getAllRecords(@AuthenticationPrincipal UserDetails userDetails) {
		String username = userDetails.getUsername();
        return this.recordService.findAllByUsername(username);
    }
	
	@GetMapping("/admin/list")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Record> getAllRecordsForAdmin() {
	    return this.recordService.findAll();
	}

    @PostMapping("/create")
    public Record createRecord (@Valid @RequestBody Record record) {
        return this.recordService.save(record);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Long id) {
        return this.recordService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Record> updateRecord (@PathVariable Long id, @Valid @RequestBody Record recordDetails) {
        return this.recordService.findById(id)
                .map(record -> {
                    record.setStartTime(recordDetails.getStartTime());
                    record.setEndTime(recordDetails.getEndTime());
                    record.setDescription(recordDetails.getDescription());
                    return ResponseEntity.ok(this.recordService.save(record));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecord (@PathVariable Long id) {
        this.recordService.findById(id)
                .map(record -> {
                    this.recordService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.noContent().build();
    }

}
