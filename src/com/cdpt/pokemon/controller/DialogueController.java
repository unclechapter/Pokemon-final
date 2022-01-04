package com.cdpt.pokemon.controller;

import com.badlogic.gdx.InputAdapter;
import com.cdpt.pokemon.Settings;
import com.cdpt.pokemon.dialogue.Dialogue;
import com.cdpt.pokemon.dialogue.DialogueNode;
import com.cdpt.pokemon.dialogue.DialogueAdmin;
import com.cdpt.pokemon.dialogue.DialogueNode.NODE_TYPE;
import com.cdpt.pokemon.ui.DialogueBox;
import com.cdpt.pokemon.ui.OptionBox;

public class DialogueController extends InputAdapter {
	private DialogueAdmin admin;
	private DialogueBox dialogueBox;
	private OptionBox optionBox;
	
	public DialogueController(DialogueBox box, OptionBox optionBox) {
		this.dialogueBox = box;
		this.optionBox = optionBox;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (dialogueBox.isVisible()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if (optionBox.isVisible()) {
			if (keycode == Settings.SCROLL_UP) {
				optionBox.moveUp();
				return true;
			} else if (keycode == Settings.SCROLL_DOWN) {
				optionBox.moveDown();
				return true;
			}
		}
		if (admin != null && keycode == Settings.CHOOSE && dialogueBox.isFinished()) {
			if (admin.getType() == NODE_TYPE.END) {
				admin = null;
				dialogueBox.setVisible(false);
			} else if (admin.getType() == NODE_TYPE.SINGLE) {
				progress(0);
			} else if (admin.getType() == NODE_TYPE.MULTI) {
				progress(optionBox.getIndex());
			}
			return true;
		}
		if (dialogueBox.isVisible()) {
			return true;
		}
		return false;
	}
	
	public void update(float delta) {
		if (dialogueBox.isFinished() && admin != null) {
			if (admin.getType() == NODE_TYPE.MULTI) {
				optionBox.setVisible(true);
			}
		}
	}
	
	public void startDialogue(Dialogue dialogue) {
		admin = new DialogueAdmin(dialogue);
		dialogueBox.setVisible(true);
		dialogueBox.animateText(admin.getText());
		if (admin.getType() == NODE_TYPE.MULTI) {
			optionBox.clear();
			for (String s : dialogue.getNode(dialogue.getStart()).getLabels()) {
				optionBox.addOption(s);
			}
		}
	}
	
	private void progress(int index) {
		optionBox.setVisible(false);
		DialogueNode nextNode = admin.getNextNode(index);
		dialogueBox.animateText(nextNode.getText());
		if (nextNode.getType() == NODE_TYPE.MULTI) {
			optionBox.clearChoices();
			for (String s : nextNode.getLabels()) {
				optionBox.addOption(s);
			}
		}
	}
	
	public boolean isDialogueShowing() {
		return dialogueBox.isVisible();
	}
}
