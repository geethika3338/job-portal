package com.example.jobportal.repository;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrLocationContainingIgnoreCase(String title, String category, String location);
    List<Job> findByEmployer(User employer);
}
