package com.example.jobportal.repository;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.JobApplication;
import com.example.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByApplicant(User applicant);
    List<JobApplication> findByJob(Job job);
    List<JobApplication> findByJobEmployer(User employer);
    boolean existsByApplicantAndJob(User applicant, Job job);
}
