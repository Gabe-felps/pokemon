package br.com.pokemon.api.config.dto;

import br.com.pokemon.api.model.enums.RoleEnum;

public record CreateUserDTO(
        String name,
        String email,
        String password,
        RoleEnum role
) {
}
