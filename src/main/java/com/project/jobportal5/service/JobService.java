package com.project.jobportal5.service;

import com.project.jobportal5.entity.Job;
import com.project.jobportal5.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public List<Job> findActiveJobs() {
        return jobRepository.findByActiveTrue();
    }

    public Optional<Job> findById(Long id) {
        return jobRepository.findById(id);
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> searchJobs(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findActiveJobs();
        }
        return jobRepository.findByKeyword(keyword.trim());
    }

    public List<Job> findByCompany(String company) {
        return jobRepository.findByCompanyContainingIgnoreCase(company);
    }

    public List<Job> findByLocation(String location) {
        return jobRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Job> findByTitle(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }

    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }
}