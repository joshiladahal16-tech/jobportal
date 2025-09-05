package com.project.jobportal5.repository;

import com.project.jobportal5.entity.Job;
import com.project.jobportal5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    // Find active jobs
    List<Job> findByIsActiveTrue();

    // Find jobs by employer
    List<Job> findByEmployerAndIsActiveTrue(User employer);

    // Search jobs by title or company
    @Query("SELECT j FROM Job j WHERE j.isActive = true AND " +
            "(LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(j.company) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Job> findActiveJobsByKeyword(@Param("keyword") String keyword);

    // Find jobs by location
    List<Job> findByLocationContainingIgnoreCaseAndIsActiveTrue(String location);

    // Find jobs by job type
    List<Job> findByJobTypeAndIsActiveTrue(String jobType);
}