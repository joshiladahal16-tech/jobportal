package com.project.jobportal5.controller;

import com.project.jobportal5.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final JobService jobService;

    @Autowired
    public HomeController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("jobs", jobService.findActiveJobs());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("jobs", jobService.searchJobs(keyword));
        model.addAttribute("keyword", keyword);
        return "search-results";
    }
}