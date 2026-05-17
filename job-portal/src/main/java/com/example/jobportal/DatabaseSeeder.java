package com.example.jobportal;

import com.example.jobportal.entity.User;
import com.example.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Optional<User> adminOpt = userRepository.findByUsername("admin");
        
        if (adminOpt.isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@novajobs.com");
            admin.setFullName("System Administrator");
            admin.setRole(User.Role.ADMIN);
            
            userRepository.save(admin);
            System.out.println("Default Admin user created [admin/admin]");
        }
    }
}
