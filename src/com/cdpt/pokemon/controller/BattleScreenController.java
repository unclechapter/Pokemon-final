package com.cdpt.pokemon.controller;

import com.cdpt.pokemon.Settings;
import com.cdpt.pokemon.battle.Battle;
import com.cdpt.pokemon.battle.event.EventQueue;
import com.cdpt.pokemon.battle.event.TextEvent;
import com.cdpt.pokemon.battle.moves.Move;
import com.cdpt.pokemon.battle.moves.MoveSpecification;
import com.cdpt.pokemon.ui.DialogueBox;
import com.cdpt.pokemon.ui.MoveSelectBox;
import com.cdpt.pokemon.ui.OptionBox;
import com.badlogic.gdx.InputAdapter;

public class BattleScreenController extends InputAdapter {
	private STATE state = STATE.DEACTIVATED;
	private EventQueue queue;
	private Battle battle;
	private DialogueBox dialogue;
	private OptionBox optionBox;
	private MoveSelectBox moveSelect;

	public enum STATE {
		USE_NEXT_POKEMON,
		SELECT_ACTION,
		DEACTIVATED;
	}
	
	public BattleScreenController(Battle battle, EventQueue queue, DialogueBox dialogue, MoveSelectBox options, OptionBox optionBox) {
		this.battle = battle;
		this.queue = queue;
		this.dialogue = dialogue;
		this.moveSelect = options;
		this.optionBox = optionBox;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (this.state == STATE.DEACTIVATED) {
			return false;
		}
		if (this.state == STATE.USE_NEXT_POKEMON && optionBox.isVisible()) {
			if (keycode == Settings.SCROLL_UP) {
				optionBox.moveUp();
			} else if (keycode == Settings.SCROLL_DOWN) {
				optionBox.moveDown();
			} else if (keycode == Settings.CHOOSE) {
				if (optionBox.getIndex() == 0) {
					for (int i = 0; i < battle.getPlayerTrainer().getTeamSize(); i++) {
						if (!battle.getPlayerTrainer().getPokemon(i).isFainted()) {
							battle.chooseNewPokemon(battle.getPlayerTrainer().getPokemon(i));
							optionBox.setVisible(false);
							this.state = STATE.DEACTIVATED;
							break;
						}
					}
				} else if (optionBox.getIndex() == 1) {
					battle.attemptRun();
					optionBox.setVisible(false);
					this.state = STATE.DEACTIVATED;
				}
			}
		}
		if (moveSelect.isVisible()) {
			if (keycode == Settings.CHOOSE) {
				int selection = moveSelect.getSelection();
				if (battle.getPlayerPokemon().getMove(selection) == null) {
					queue.addEvent(new TextEvent("No such move...", 0.5f));
				} else {
					battle.progress(moveSelect.getSelection());
					endTurn();
				}
			} else if (keycode == Settings.SCROLL_UP) {
				moveSelect.moveUp();
				return true;
			} else if (keycode == Settings.SCROLL_DOWN) {
				moveSelect.moveDown();
				return true;
			} else if (keycode == Settings.SCROLL_LEFT) {
				moveSelect.moveLeft();
				return true;
			} else if (keycode == Settings.SCROLL_RIGHT) {
				moveSelect.moveRight();
				return true;
			}
		}
		return false;
	}
	
	public STATE getState() {
		return state;
	}
	
	public void update(float delta) {
		if (isDisplayingNextDialogue() && dialogue.isFinished() && !optionBox.isVisible()) {
			optionBox.clearChoices();
			optionBox.addOption("YES");
			optionBox.addOption("NO");
			optionBox.setVisible(true);
		}
	}
	
	public void restartTurn() {
		this.state = STATE.SELECT_ACTION;
		dialogue.setVisible(false);
		for (int i = 0; i <= 3; i++) {
			String label = "------";
			MoveSpecification spec = battle.getPlayerPokemon().getMoveSpecification(i);
			if (spec != null) {
				label = spec.getName();
			}
			moveSelect.setLabel(i, label.toUpperCase());
		}
		moveSelect.setVisible(true);
	}
	
	public void displayNextDialogue() {
		this.state = STATE.USE_NEXT_POKEMON;
		dialogue.setVisible(true);
		dialogue.animateText("Send out next pokemon?");
	}
	
	public boolean isDisplayingNextDialogue() {
		return this.state == STATE.USE_NEXT_POKEMON;
	}
	
	private void endTurn() {
		moveSelect.setVisible(false);
		this.state = STATE.DEACTIVATED;
	}
}
