package com.example.pokedex.controller;

import com.example.pokedex.service.PokemonService;
import com.example.pokedex.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonRestController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> getPokemons(@RequestParam(required = false) String name, @RequestParam(required = false) String type) {
        if (name != null && type != null) {
            return pokemonService.getPokemonsByNameAndType(name, type); // Filtro per nome e tipo
        } else if (name != null) {
            return pokemonService.getPokemonsByName(name); // Filtro per nome
        } else if (type != null) {
            return pokemonService.getPokemonsByType(type); // Filtro per tipo
        } else {
            return pokemonService.getAllPokemons();  // Se non c'è filtro, restituisce tutti
        }
    }
}