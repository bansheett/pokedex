package com.example.pokedex.repository;

import com.example.pokedex.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    // Ricerca per nome
    List<Pokemon> findByNomeContainingIgnoreCase(String nome);

    // Ricerca per tipo
    List<Pokemon> findByTipoIgnoreCase(String tipo);

    // Ricerca per nome e tipo
    List<Pokemon> findByNomeContainingIgnoreCaseAndTipoIgnoreCase(String nome, String tipo);
}