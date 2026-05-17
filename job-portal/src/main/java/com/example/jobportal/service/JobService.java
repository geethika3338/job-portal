package com.example.jobportal.service;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> findJobsByEmployer(User employer) {
        return jobRepository.findByEmployer(employer);
    }

    public Optional<Job> findById(Long id) {
        return jobRepository.findById(id);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> searchJobs(String query) {
        if (query == null || query.trim().isEmpty()) {
            return findAllJobs();
        }
        return jobRepository.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrLocationContainingIgnoreCase(
                query, query, query);
    }
}
