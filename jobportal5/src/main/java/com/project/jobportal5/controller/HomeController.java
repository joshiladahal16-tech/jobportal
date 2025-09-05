package com.project.jobportal5.controller;

import com.project.jobportal5.entity.Job;
import com.project.jobportal5.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/")
    public String home(Model model) {
        // Get latest 10 jobs for homepage
        List<Job> latestJobs = jobRepository.findByIsActiveTrue()
                .stream()
                .limit(10)
                .toList();

        model.addAttribute("jobs", latestJobs);
        return "index";
    }

    @GetMapping("/jobs")
    public String allJobs(Model model,
                          @RequestParam(required = false) String search,
                          @RequestParam(required = false) String location,
                          @RequestParam(required = false) String jobType) {

        List<Job> jobs;

        if (search != null && !search.trim().isEmpty()) {
            jobs = jobRepository.findActiveJobsByKeyword(search.trim());
        } else if (location != null && !location.trim().isEmpty()) {
            jobs = jobRepository.findByLocationContainingIgnoreCaseAndIsActiveTrue(location.trim());
        } else if (jobType != null && !jobType.trim().isEmpty()) {
            jobs = jobRepository.findByJobTypeAndIsActiveTrue(jobType);
        } else {
            jobs = jobRepository.findByIsActiveTrue();
        }

        model.addAttribute("jobs", jobs);
        model.addAttribute("search", search);
        model.addAttribute("location", location);
        model.addAttribute("jobType", jobType);

        return "jobs";
    }

    @GetMapping("/job/{id}")
    public String jobDetails(@PathVariable Long id, Model model) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null && job.isActive()) {
            model.addAttribute("job", job);
            return "job-details";
        }
        return "redirect:/jobs";
    }
}