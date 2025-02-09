package com.example.pokedex.service;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();  // Restituisce tutti i Pokémon
    }

    public List<Pokemon> getPokemonsByName(String name) {
        return pokemonRepository.findByNomeContainingIgnoreCase(name); // Filtro per nome
    }

    public List<Pokemon> getPokemonsByType(String type) {
        return pokemonRepository.findByTipoIgnoreCase(type); // Filtro per tipo
    }

    public List<Pokemon> getPokemonsByNameAndType(String name, String type) {
        return pokemonRepository.findByNomeContainingIgnoreCaseAndTipoIgnoreCase(name, type); // Filtro per nome e tipo
    }

    // Metodo per recuperare un Pokémon per ID (opzionale, per dettagli specifici)
    public Pokemon getPokemonById(Long id) {
        return pokemonRepository.findById(id).orElse(null);  // Restituisce un Pokémon o null se non trovato
    }
}