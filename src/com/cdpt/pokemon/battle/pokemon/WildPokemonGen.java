package com.cdpt.pokemon.battle.pokemon;

public class WildPokemonGen {
    public static Pokemon generatePokemon() {
        return new Pokemon(Species.values()[(int) (Math.random() * Species.values().length)]);
    }
}
