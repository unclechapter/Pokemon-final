package com.cdpt.pokemon.battle;

import com.badlogic.gdx.math.MathUtils;
import com.cdpt.pokemon.battle.moves.MOVE_CATEGORY;
import com.cdpt.pokemon.battle.moves.Move;
import com.cdpt.pokemon.battle.pokemon.Pokemon;

public class BattleMechanics {
	private String message = "";
	
	private boolean criticalHit(Move move, Pokemon user, Pokemon target) {
		float prob = 1f / 16f;
		if (prob >= MathUtils.random(1.0f)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean goesFirst(Pokemon player, Pokemon opponent) {
		if (player.getStat(STAT.SPEED) > opponent.getStat(STAT.SPEED)) {
			return true;
		} else if (opponent.getStat(STAT.SPEED) > player.getStat(STAT.SPEED)) {
			return false;
		} else {
			return MathUtils.randomBoolean();
		}
	}
	
	public boolean attemptHit(Move move, Pokemon user, Pokemon target) {
		float random = MathUtils.random(1.0f);
		if (move.getAccuracy() >= random) {
			return true;
		} else {
			return false;
		}
	}
	
	public int calculateDamage(Move move, Pokemon user, Pokemon target) {
		message = "";
		
		float attack = 0f;
		if (move.getCategory() == MOVE_CATEGORY.PHYSICAL) {
			attack = user.getStat(STAT.ATTACK);
		} else {
			attack = user.getStat(STAT.SP_ATTACK);
		}
		
		float defence = 0f;
		if (move.getCategory() == MOVE_CATEGORY.PHYSICAL) {
			defence = target.getStat(STAT.DEFENSE);
		} else {
			defence = target.getStat(STAT.SP_DEFENSE);
		}
		
		boolean isCritical = criticalHit(move, user, target);
		
		int level = user.getLevel();
		float base = move.getPower();
		float modifier = MathUtils.random(0.85f, 1.00f);
		if (isCritical) {
			modifier = modifier * 2f;
			message = "A critical hit!";
		}
		
		int damage = (int) (((2f * level + 10f) / 100f * (float) attack / defence * base + 2) * modifier);
		
		return damage;
	}
	
	public boolean hasMessage() {
		return !message.isEmpty();
	}
	
	public String getMessage() {
		return message;
	}
}
