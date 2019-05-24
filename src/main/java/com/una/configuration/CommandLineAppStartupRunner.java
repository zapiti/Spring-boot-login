package com.una.configuration;

import com.una.model.Role;
import com.una.model.User;
import com.una.repository.RoleRepository;
import com.una.repository.UserRepository;
import com.una.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void run(String...args) throws Exception {
        if(!userRepository.existsByActive(1)) {
            User user = new User();
            user.setName("ADMIN");
            user.setEmail("ADMIN@ADMIN.com");
            user.setPassword("123456");
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setActive(1);
            userRepository.save(user);
        }
    }
}