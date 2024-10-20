package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @NotNull(message = "Start time can not be null")
    private LocalDateTime startTime;
    
    @NotNull(message = "End time can not be null")
    private LocalDateTime endTime;
    
    @Size(max = 255, message = "Description can not be more than 255 symbols")
    private String description;
}