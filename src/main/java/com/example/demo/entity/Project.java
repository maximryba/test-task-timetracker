package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Project name can not be null")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "Project must have a linked user")
    private User user;
}