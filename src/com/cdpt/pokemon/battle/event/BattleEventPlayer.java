package com.cdpt.pokemon.battle.event;

import com.badlogic.gdx.graphics.Texture;
import com.cdpt.pokemon.battle.BATTLE_PARTY;
import com.cdpt.pokemon.battle.animation.BattleAnimation;
import com.cdpt.pokemon.ui.DialogueBox;
import com.cdpt.pokemon.ui.StatusBox;

import aurelienribon.tweenengine.TweenManager;

public interface BattleEventPlayer {
	public void playBattleAnimation(BattleAnimation animation, BATTLE_PARTY party);
	
	public void setPokemonSprite(Texture region, BATTLE_PARTY party);
	
	public DialogueBox getDialogueBox();
	
	public StatusBox getStatusBox(BATTLE_PARTY party);
	
	public BattleAnimation getBattleAnimation();
	
	public TweenManager getTweenManager();
}
