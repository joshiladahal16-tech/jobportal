package com.project.jobportal5.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private String company;

    private String location;

    private String jobType; // Full-time, Part-time, Contract, etc.

    private String experience; // Entry, Mid, Senior

    private String salary;

    @Column(nullable = false)
    private LocalDateTime postedDate;

    private LocalDateTime applicationDeadline;

    @Column(nullable = false)
    private boolean isActive = true;

    // Many jobs can be posted by one employer (User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}