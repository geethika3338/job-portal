package com.example.jobportal.service;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.JobApplication;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository applicationRepository;

    public JobApplication saveApplication(JobApplication application) {
        return applicationRepository.save(application);
    }

    public List<JobApplication> findApplicationsByApplicant(User applicant) {
        return applicationRepository.findByApplicant(applicant);
    }

    public List<JobApplication> findApplicationsByJob(Job job) {
        return applicationRepository.findByJob(job);
    }

    public List<JobApplication> findApplicationsByEmployer(User employer) {
        return applicationRepository.findByJobEmployer(employer);
    }

    public Optional<JobApplication> findById(Long id) {
        return applicationRepository.findById(id);
    }

    public void updateApplicationStatus(Long id, JobApplication.Status status) {
        Optional<JobApplication> applicationOpt = applicationRepository.findById(id);
        if (applicationOpt.isPresent()) {
            JobApplication application = applicationOpt.get();
            application.setStatus(status);
            applicationRepository.save(application);
        }
    }

    public boolean hasApplied(User applicant, Job job) {
        return applicationRepository.existsByApplicantAndJob(applicant, job);
    }
}
