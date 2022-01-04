package com.cdpt.pokemon.battle;

import java.util.ArrayList;

import com.cdpt.pokemon.battle.pokemon.Pokemon;

public class Party {
	private ArrayList<Pokemon> party = new ArrayList<>();
	
	public boolean addPokemon(Pokemon pokemon) {
		if (party.size() >= 6) {
			return false;
		} else {
			party.add(pokemon);
			return true;
		}
	}
	
	public Pokemon getPokemon(int index) {
		return party.get(index);
	}
	
	public int getTeamSize() {
		return party.size();
	}
}
