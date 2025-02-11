package com.example.pokedex.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.pokedex.model.User;
import com.example.pokedex.repository.UserRepository;
import com.example.pokedex.service.UserService;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
   
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    
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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getNickname(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Reset tentativi falliti dopo login riuscito
            userService.resetAttempts(loginRequest.getNickname());

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Login riuscito!");
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            userService.updateFailedAttempts(loginRequest.getNickname());
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Credenziali non valide");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Logout effettuato con successo");
        return ResponseEntity.ok(response);
    }


class LoginRequest {
    private String nickname;
    private String password;

    // Getters and Setters
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    
}
}