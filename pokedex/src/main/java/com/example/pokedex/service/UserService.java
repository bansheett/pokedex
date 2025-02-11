package com.example.pokedex.service;

import com.example.pokedex.model.User;
import com.example.pokedex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        return userRepository.findByNickname(nickname)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with nickname: " + nickname));
    }

    @Transactional
    public User registerNewUser(String nickname, String rawPassword) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new RuntimeException("Nickname already exists");
        }

        User user = new User(nickname, passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

    @Transactional
    public void updateFailedAttempts(String nickname) {
        userRepository.findByNickname(nickname).ifPresent(user -> {
            user.setAttempts(user.getAttempts() + 1);
            if (user.getAttempts() >= 3) {
                user.setAccountNonLocked(false);
            }
            userRepository.save(user);
        });
    }

    @Transactional
    public void resetAttempts(String nickname) {
        userRepository.findByNickname(nickname).ifPresent(user -> {
            user.setAttempts(0);
            userRepository.save(user);
        });
    }

    @Transactional
    public boolean unlockAccount(String nickname) {
        return userRepository.findByNickname(nickname).map(user -> {
            user.setAccountNonLocked(true);
            user.setAttempts(0);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }
}