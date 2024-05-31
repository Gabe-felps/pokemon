package br.com.pokemon.api.service;

import br.com.pokemon.api.config.dto.AbilityDTO;
import br.com.pokemon.api.config.dto.PokemonDTO;
import br.com.pokemon.api.service.response.PokemonApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final RestTemplate restTemplate;

    @Autowired
    public PokemonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokemonDTO getPokemon(String name) {
        String url = UriComponentsBuilder.fromUriString("https://pokeapi.co/api/v2/pokemon/{name}")
                .buildAndExpand(name)
                .toUriString();

        PokemonApiResponse response = restTemplate.getForObject(url, PokemonApiResponse.class);

        List<AbilityDTO> abilities = response.getAbilities().stream()
                .map(a -> new AbilityDTO(a.getAbility().getName()))
                .collect(Collectors.toList());

        return new PokemonDTO(response.getName(), response.getSprites().getFrontDefault(), abilities);
    }
}
