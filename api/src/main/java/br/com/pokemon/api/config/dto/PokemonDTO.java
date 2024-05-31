package br.com.pokemon.api.config.dto;

import java.util.List;

public record PokemonDTO(
        String name,
        String sprite,
        List<AbilityDTO> abilities
) {
}
