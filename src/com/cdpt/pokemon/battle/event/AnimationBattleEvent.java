package com.cdpt.pokemon.battle.event;

import com.cdpt.pokemon.battle.BATTLE_PARTY;
import com.cdpt.pokemon.battle.animation.BattleAnimation;

public class AnimationBattleEvent extends BattleEvent {
	private BATTLE_PARTY primary;
	private BattleAnimation animation;

	public AnimationBattleEvent(BATTLE_PARTY primary, BattleAnimation animation) {
		this.animation = animation;
		this.primary = primary;
	}

	@Override
	public void begin(BattleEventPlayer player) {
		super.begin(player);
		player.playBattleAnimation(animation, primary);
	}
	
	@Override
	public void update(float delta) {
		animation.update(delta);
	}

	@Override
	public boolean finished() {
		return this.getPlayer().getBattleAnimation().isFinished();
	}

}
