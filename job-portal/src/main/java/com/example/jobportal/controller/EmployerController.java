package com.example.jobportal.controller;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.JobApplication;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.JobApplicationService;
import com.example.jobportal.service.JobService;
import com.example.jobportal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobApplicationService applicationService;

    @Autowired
    private UserService userService;

    private User getCurrentUser(Authentication authentication) {
        return userService.findByUsername(authentication.getName()).orElse(null);
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        User employer = getCurrentUser(authentication);
        List<Job> jobs = jobService.findJobsByEmployer(employer);
        model.addAttribute("jobs", jobs);
        return "employer/dashboard";
    }

    @GetMapping("/job/new")
    public String postJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "employer/post_job";
    }

    @PostMapping("/job/new")
    public String postJob(@Valid @ModelAttribute("job") Job job, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "employer/post_job";
        }
        User employer = getCurrentUser(authentication);
        job.setEmployer(employer);
        jobService.saveJob(job);
        return "redirect:/employer/dashboard";
    }

    @GetMapping("/job/{id}/applicants")
    public String viewApplicants(@PathVariable Long id, Model model, Authentication authentication) {
        User employer = getCurrentUser(authentication);
        Job job = jobService.findById(id).orElse(null);
        if (job == null || !job.getEmployer().getId().equals(employer.getId())) {
            return "redirect:/employer/dashboard";
        }

        List<JobApplication> applications = applicationService.findApplicationsByJob(job);
        model.addAttribute("job", job);
        model.addAttribute("applications", applications);
        return "employer/applicants";
    }

    @PostMapping("/application/{id}/status")
    public String updateApplicationStatus(@PathVariable Long id, @RequestParam("status") JobApplication.Status status, Authentication authentication, RedirectAttributes redirectAttributes) {
        JobApplication application = applicationService.findById(id).orElse(null);
        if (application != null) {
            User employer = getCurrentUser(authentication);
            if (application.getJob().getEmployer().getId().equals(employer.getId())) {
                applicationService.updateApplicationStatus(id, status);
                redirectAttributes.addFlashAttribute("success", "Application status updated to " + status);
                return "redirect:/employer/job/" + application.getJob().getId() + "/applicants";
            }
        }
        return "redirect:/employer/dashboard";
    }
}
