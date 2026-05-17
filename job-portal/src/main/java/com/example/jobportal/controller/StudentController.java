package com.example.jobportal.controller;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.JobApplication;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.JobApplicationService;
import com.example.jobportal.service.JobService;
import com.example.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobApplicationService applicationService;

    @Autowired
    private UserService userService;

    // Helper to get current user
    private User getCurrentUser(Authentication authentication) {
        return userService.findByUsername(authentication.getName()).orElse(null);
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication, @RequestParam(value = "query", required = false) String query) {
        User student = getCurrentUser(authentication);
        List<Job> jobs = jobService.searchJobs(query);
        model.addAttribute("jobs", jobs);
        model.addAttribute("query", query);
        model.addAttribute("user", student);
        return "student/dashboard";
    }

    @GetMapping("/job/{id}")
    public String viewJob(@PathVariable Long id, Model model, Authentication authentication) {
        Optional<Job> jobOpt = jobService.findById(id);
        if (jobOpt.isEmpty()) return "redirect:/student/dashboard";
        
        User student = getCurrentUser(authentication);
        Job job = jobOpt.get();
        boolean hasApplied = applicationService.hasApplied(student, job);
        
        model.addAttribute("job", job);
        model.addAttribute("hasApplied", hasApplied);
        return "student/job_details";
    }

    @PostMapping("/job/{id}/apply")
    public String applyForJob(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAttributes) {
        Optional<Job> jobOpt = jobService.findById(id);
        if (jobOpt.isEmpty()) return "redirect:/student/dashboard";

        User student = getCurrentUser(authentication);
        Job job = jobOpt.get();

        if (applicationService.hasApplied(student, job)) {
            redirectAttributes.addFlashAttribute("error", "You have already applied for this job.");
            return "redirect:/student/job/" + id;
        }

        JobApplication application = new JobApplication();
        application.setApplicant(student);
        application.setJob(job);
        applicationService.saveApplication(application);

        redirectAttributes.addFlashAttribute("success", "Application submitted successfully!");
        return "redirect:/student/job/" + id;
    }

    @GetMapping("/applications")
    public String myApplications(Model model, Authentication authentication) {
        User student = getCurrentUser(authentication);
        List<JobApplication> applications = applicationService.findApplicationsByApplicant(student);
        model.addAttribute("applications", applications);
        return "student/applications";
    }
    
    @PostMapping("/resume/upload")
    public String uploadResume(@RequestParam("resume") MultipartFile file, Authentication authentication, RedirectAttributes redirectAttributes) {
        // Simplified resume upload - normally this would save the file to a directory or S3
        User student = getCurrentUser(authentication);
        if (!file.isEmpty()) {
            student.setResumePath(file.getOriginalFilename());
            userService.registerUser(student); // Updates existing user
            redirectAttributes.addFlashAttribute("success", "Resume uploaded successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Please select a file to upload.");
        }
        return "redirect:/student/dashboard";
    }
}
