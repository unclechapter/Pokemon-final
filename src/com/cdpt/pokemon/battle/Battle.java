package com.cdpt.pokemon.battle;

import java.util.ArrayList;

import com.cdpt.pokemon.battle.animation.FaintingAnimation;
import com.cdpt.pokemon.battle.event.AnimationBattleEvent;
import com.cdpt.pokemon.battle.event.BattleEvent;
import com.cdpt.pokemon.battle.event.BattleEventBroadcaster;
import com.cdpt.pokemon.battle.event.HPAnimationEvent;
import com.cdpt.pokemon.battle.event.NameChangeEvent;
import com.cdpt.pokemon.battle.event.PokeSpriteEvent;
import com.cdpt.pokemon.battle.event.TextEvent;
import com.cdpt.pokemon.battle.moves.Move;
import com.cdpt.pokemon.battle.pokemon.Pokemon;

public class Battle implements BattleEventBroadcaster {	
	private STATE state;	
	private BattleMechanics mechanics;	
	private Pokemon player;
	private Pokemon opponent;	
	private Party playerTrainer;
	private Party opponentTrainer;	
	private ArrayList<BattleObserver> observers = new ArrayList<BattleObserver>();
	
	public enum STATE {
		READY,
		SELECTING,
		RAN,
		WIN,
		LOSE;
	}
	
	public Battle(Party player, Pokemon opponent) {
		this.playerTrainer = player;
		this.player = player.getPokemon(0);
		this.opponent = opponent;
		mechanics = new BattleMechanics();
		this.state = STATE.READY;
	}
	
	public void beginBattle() {
		broadcastEvent(new PokeSpriteEvent(opponent.getSprite(), BATTLE_PARTY.OPPONENT));
		broadcastEvent(new PokeSpriteEvent(player.getSprite(), BATTLE_PARTY.PLAYER));
		broadcastEvent(new TextEvent("Go " + player.getName() + "!", 1f));
	}
	
	public void progress(int input) {
		if (state != STATE.READY) {
			return;
		}
		if (mechanics.goesFirst(player, opponent)) {
			playTurn(BATTLE_PARTY.PLAYER, input);	
			if (state == STATE.READY) {
				int rd = (int) (Math.random() * 3);
				playTurn(BATTLE_PARTY.OPPONENT, rd);
			}
		} else {
			int rd = (int) (Math.random() * 3);
			playTurn(BATTLE_PARTY.OPPONENT, rd);
			if (state == STATE.READY) {
				playTurn(BATTLE_PARTY.PLAYER, input);
			}
		}
	}
	
	public void chooseNewPokemon(Pokemon pokemon) {
		this.player = pokemon;
		broadcastEvent(new PokeSpriteEvent(pokemon.getSprite(), BATTLE_PARTY.PLAYER));
		broadcastEvent(new NameChangeEvent(pokemon.getName(), BATTLE_PARTY.PLAYER));
		broadcastEvent(new TextEvent("Go get 'em, "+pokemon.getName()+"!"));
		broadcastEvent(new HPAnimationEvent(BATTLE_PARTY.PLAYER, pokemon.getCurrentHitpoints(), pokemon.getCurrentHitpoints(), pokemon.getStat(STAT.HP), 0f));
		this.state = STATE.READY;
	}
	
	public void attemptRun() {
		broadcastEvent(new TextEvent("Got away successfully...", true));
		this.state = STATE.RAN;
	}
	
	private void playTurn(BATTLE_PARTY user, int input) {
		Pokemon pokeUser = null;
		Pokemon pokeTarget = null;
		if (user == BATTLE_PARTY.PLAYER) {
			pokeUser = player;
			pokeTarget = opponent;
		} else if (user == BATTLE_PARTY.OPPONENT) {
			pokeUser = opponent;
			pokeTarget = player;
		}
		
		Move move = pokeUser.getMove(input);
		
		broadcastEvent(new TextEvent(pokeUser.getName() + " used\n" + move.getName().toUpperCase() + "!", 0.5f));
		
		if (mechanics.attemptHit(move, pokeUser, pokeTarget)) {
			move.useMove(mechanics, pokeUser, pokeTarget, user, this);
		} else {
			broadcastEvent(new TextEvent(pokeUser.getName() + "'s\nattack missed!", 0.5f));
		}
		
		if (player.isFainted()) {
			broadcastEvent(new AnimationBattleEvent(BATTLE_PARTY.PLAYER, new FaintingAnimation()));
			boolean anyoneAlive = false;
			for (int i = 0; i < getPlayerTrainer().getTeamSize(); i++) {
				if (!getPlayerTrainer().getPokemon(i).isFainted()) {
					anyoneAlive = true;
					break;
				}
			}
			if (anyoneAlive) {
				broadcastEvent(new TextEvent(player.getName() + " fainted!", true));
				this.state = STATE.SELECTING;
				player.setCurrentHitpoints(player.getStat(STAT.HP));
			} else {
				broadcastEvent(new TextEvent("Unfortunately, you've lost...", true));
				this.state = STATE.LOSE;
				player.setCurrentHitpoints(player.getStat(STAT.HP));
			}
		} else if (opponent.isFainted()) {
			broadcastEvent(new AnimationBattleEvent(BATTLE_PARTY.OPPONENT, new FaintingAnimation()));
			broadcastEvent(new TextEvent("Congratulations! You Win!", true));
			this.state = STATE.WIN;
			player.setCurrentHitpoints(player.getStat(STAT.HP));
		}
	}
	
	public Pokemon getPlayerPokemon() {
		return player;
	}
	
	public Pokemon getOpponentPokemon() {
		return opponent;
	}
	
	public Party getPlayerTrainer() {
		return playerTrainer;
	}
	
	public Party getOpponentTrainer() {
		return opponentTrainer;
	}
	
	public STATE getState() {
		return state;
	}
	
	public void addObserver(BattleObserver observer) {
		observers.add(observer);
	}
	
	@Override
	public void broadcastEvent(BattleEvent event) {
		for (BattleObserver observer : observers) {
			observer.queueEvent(event);
		}
	}
}
