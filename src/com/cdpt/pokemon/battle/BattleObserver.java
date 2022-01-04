package com.cdpt.pokemon.battle;

import com.cdpt.pokemon.battle.event.BattleEvent;

public interface BattleObserver {
	public void queueEvent(BattleEvent event);
}
