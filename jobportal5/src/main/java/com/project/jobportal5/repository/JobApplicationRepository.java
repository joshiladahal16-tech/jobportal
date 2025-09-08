package com.project.jobportal5.repository;

import com.project.jobportal5.entity.Job;
import com.project.jobportal5.entity.JobApplication;
import com.project.jobportal5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUser(User user);
    List<JobApplication> findByJob(Job job);
    Optional<JobApplication> findByUserAndJob(User user, Job job);
    boolean existsByUserAndJob(User user, Job job);
}