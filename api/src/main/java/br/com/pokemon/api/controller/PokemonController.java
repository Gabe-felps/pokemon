package br.com.pokemon.api.controller;

import br.com.pokemon.api.config.dto.PokemonDTO;
import br.com.pokemon.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/pokemon")
public class PokemonController {

    private static final Logger logger = LoggerFactory.getLogger(PokemonController.class);
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{name}")
    public PokemonDTO getPokemon(@PathVariable String name) {
        logger.info("Received request for Pokemon: " + name);
        return pokemonService.getPokemon(name);
    }
}
