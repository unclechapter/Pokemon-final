package com.cdpt.pokemon.battle.moves;

import com.cdpt.pokemon.battle.BATTLE_PARTY;
import com.cdpt.pokemon.battle.BattleMechanics;
import com.cdpt.pokemon.battle.STAT;
import com.cdpt.pokemon.battle.animation.BattleAnimation;
import com.cdpt.pokemon.battle.event.AnimationBattleEvent;
import com.cdpt.pokemon.battle.event.BattleEventBroadcaster;
import com.cdpt.pokemon.battle.event.HPAnimationEvent;
import com.cdpt.pokemon.battle.event.TextEvent;
import com.cdpt.pokemon.battle.pokemon.Pokemon;

public class DamageMove extends Move {

	public DamageMove(MoveSpecification spec, Class<? extends BattleAnimation> AnimationClass) {
		super(spec, AnimationClass);
	}

	@Override
	public BattleAnimation animation() {
		try {
			return animationClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	@Override
	public String message() {
		return null;
	}

	@Override
	public boolean isDamaging() {
		return true;
	}
	
	@Override
	public int useMove(BattleMechanics mechanics, Pokemon user, Pokemon target, BATTLE_PARTY party, BattleEventBroadcaster broadcaster) {
		int hpBefore = target.getCurrentHitpoints();
		int damage = super.useMove(mechanics, user, target, party, broadcaster);
		
		broadcaster.broadcastEvent(new AnimationBattleEvent(party, animation()));
		broadcaster.broadcastEvent(new HPAnimationEvent(BATTLE_PARTY.getOpposite(party), hpBefore,target.getCurrentHitpoints(), target.getStat(STAT.HP), 0.5f));
		
		if (mechanics.hasMessage()) {
			broadcaster.broadcastEvent(new TextEvent(mechanics.getMessage(), 0.5f));
		}
		return damage;
	}

	@Override
	public Move clone() {
		return new DamageMove(spec, animationClass);
	}
}
