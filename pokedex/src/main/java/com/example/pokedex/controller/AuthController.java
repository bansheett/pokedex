package com.example.pokedex.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.pokedex.model.User;
import com.example.pokedex.repository.UserRepository;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByNickname(user.getNickname()).isPresent()) {
            return "Nickname già in uso!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAttempts(0);
        userRepository.save(user);
        return "Registrazione avvenuta con successo!";
    }
    
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByNickname(user.getNickname());
        if (existingUser.isEmpty()) {
            return "Utente non trovato!";
        }
        
        User foundUser = existingUser.get();
        if (foundUser.getAttempts() >= 5) {
            return "Troppi tentativi! Torna alla home.";
        }
        
        if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            foundUser.setAttempts(0);
            userRepository.save(foundUser);
            return "Login riuscito!";
        } else {
            foundUser.setAttempts(foundUser.getAttempts() + 1);
            userRepository.save(foundUser);
            return "Password errata! Tentativi rimasti: " + (5 - foundUser.getAttempts());
        }
    }
    @GetMapping("/current-user")
    @ResponseBody
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        if (principal != null) {
            return ResponseEntity.ok(Collections.singletonMap("nickname", principal.getName()));
        }
        return ResponseEntity.ok(Collections.singletonMap("nickname", "anonymous"));
    }
    
}