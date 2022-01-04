package com.cdpt.pokemon.dialogue;

import java.util.List;

import com.cdpt.pokemon.dialogue.DialogueNode.NODE_TYPE;

public class DialogueAdmin {
	private Dialogue dialogue;
	private DialogueNode currentNode;
	
	public DialogueAdmin(Dialogue dialogue) {
		this.dialogue = dialogue;
		currentNode = dialogue.getNode(dialogue.getStart());
	}
	
	public DialogueNode getNextNode(int pointerIndex) {
		DialogueNode nextNode = dialogue.getNode(currentNode.getPointers().get(pointerIndex));
		currentNode = nextNode;
		return nextNode;
	}
	
	public List<String> getOptions() {
		return currentNode.getLabels();
	}
	
	public String getText() {
		return currentNode.getText();
	}
	
	public NODE_TYPE getType() {
		return currentNode.getType();
	}
}
