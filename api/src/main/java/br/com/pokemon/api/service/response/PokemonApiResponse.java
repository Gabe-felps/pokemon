package br.com.pokemon.api.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonApiResponse {
    private Long id;
    private String name;
    private Sprites sprites;
    private List<Ability> abilities;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sprites {
        @JsonProperty("front_default")
        private String frontDefault;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Ability {
        private AbilityDetail ability;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class AbilityDetail {
            private String name;
        }
    }
}
