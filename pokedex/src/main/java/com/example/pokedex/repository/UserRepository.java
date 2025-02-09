package com.example.pokedex.repository;


import java.util.Optional;

import com.example.pokedex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
}
