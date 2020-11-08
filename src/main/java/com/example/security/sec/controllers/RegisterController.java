package com.example.security.sec.controllers;

import com.example.security.sec.model.Authorities;
import com.example.security.sec.model.User;
import com.example.security.sec.repositories.AuthoritiesRepository;
import com.example.security.sec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @GetMapping("/")
    public String index() {
        return "HellO! Welcome!";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public User doRegister(@RequestBody User user) {
        String encodedPassword  = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Authorities boardAuthority = new Authorities();
        boardAuthority.setAuthority("BOARD");
        boardAuthority.setUser(user);
        user.setAuthorities(boardAuthority);
        userRepository.save(user);

        return user;
    }

    @GetMapping("/teises")
    public List<Authorities> getAuthorities() {
        return authoritiesRepository.findAll();
    }

    @PostMapping("/addteise")
    public List<Authorities> addAuthority(@RequestBody Authorities authorities) {
        authoritiesRepository.save(authorities);

        return authoritiesRepository.findAll();
    }
}
