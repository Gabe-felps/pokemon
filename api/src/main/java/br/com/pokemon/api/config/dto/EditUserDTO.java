package br.com.pokemon.api.config.dto;

import br.com.pokemon.api.model.Role;

import java.util.List;

public record EditUserDTO(

        String name,
        String email

) {
}
