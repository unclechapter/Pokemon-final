package com.cdpt.pokemon.battle.event;

import com.badlogic.gdx.Gdx;
import com.cdpt.pokemon.Settings;
import com.cdpt.pokemon.ui.DialogueBox;

public class TextEvent extends BattleEvent {
	private boolean finished = false;
	private float timer = 0f;
	private float delay;
	private boolean awaitInput = false;
	private String text;
	private DialogueBox dialogue;
	
	public TextEvent(String text) {
		this.text = text;
		this.delay = 0f;
	}
	
	public TextEvent(String text, float delay) {
		this.text = text;
		this.delay = delay;
	}
	
	public TextEvent(String text, boolean awaitInput) {
		this(text);
		this.delay = 0f;
		this.awaitInput = awaitInput;
	}
	
	@Override
	public void begin(BattleEventPlayer player) {
		super.begin(player);
		dialogue = player.getDialogueBox();
		dialogue.setVisible(true);
		dialogue.animateText(text);
	}
	
	@Override
	public void update(float delta) {
		if (dialogue.isFinished()) {
			if (awaitInput) {
				if (Gdx.input.isKeyJustPressed(Settings.CHOOSE)) {
					finished = true;
				}
			} else {
				timer += delta;
				if (timer >=  delay) {
					timer = delay;
					finished = true;
				}
			}
		}
	}

	@Override
	public boolean finished() {
		return finished;
	}
}
