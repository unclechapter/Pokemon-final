package com.cdpt.pokemon.battle.moves;

import com.cdpt.pokemon.battle.BATTLE_PARTY;
import com.cdpt.pokemon.battle.BattleMechanics;
import com.cdpt.pokemon.battle.animation.BattleAnimation;
import com.cdpt.pokemon.battle.event.BattleEventBroadcaster;
import com.cdpt.pokemon.battle.pokemon.Pokemon;

public abstract class Move {
	public MoveSpecification spec;
	public Class<? extends BattleAnimation> animationClass;
	
	public Move(MoveSpecification spec, Class<? extends BattleAnimation> animationClass) {
		this.spec = spec;
		this.animationClass = animationClass;
	}
	
	public int useMove(BattleMechanics mechanics, Pokemon user, Pokemon target, BATTLE_PARTY party, BattleEventBroadcaster broadcaster) {
		int damage = mechanics.calculateDamage(this, user, target);
		target.applyDamage(damage);
		return damage;
	}
	
	public abstract BattleAnimation animation();
	
	public abstract String message();

	public abstract boolean isDamaging();
	
	public String getName() {
		return spec.getName();
	}
	
	public MOVE_TYPE getType(){
		return spec.getType();
	}
	
	public MOVE_CATEGORY getCategory() {
		return spec.getCategory();
	}
	
	public int getPower() {
		return spec.getPower();
	}

	public int getPP() {
		return spec.getPP();
	}
	
	public float getAccuracy() {
		return spec.getAccuracy();
	}
	
	public MoveSpecification getMoveSpecification() {
		return spec;
	}

	public abstract Move clone();
}
