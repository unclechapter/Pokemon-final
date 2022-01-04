package com.cdpt.pokemon.controller;

import com.cdpt.pokemon.Settings;
import com.cdpt.pokemon.model.Actor;
import com.cdpt.pokemon.model.DIRECTION;
import com.badlogic.gdx.InputAdapter;

public class PlayerController extends InputAdapter {
	private Actor player;
	private boolean[] buttonPress;
	private float[] buttonTimer;
	private float REFACE_TIME = 0.07f;
	
	public PlayerController(Actor p) {
		this.player = p;
		buttonPress = new boolean[DIRECTION.values().length];
		buttonPress[DIRECTION.NORTH.ordinal()] = false;
		buttonPress[DIRECTION.SOUTH.ordinal()] = false;
		buttonPress[DIRECTION.EAST.ordinal()] = false;
		buttonPress[DIRECTION.WEST.ordinal()] = false;
		buttonTimer = new float[DIRECTION.values().length];
		buttonTimer[DIRECTION.NORTH.ordinal()] = 0f;
		buttonTimer[DIRECTION.SOUTH.ordinal()] = 0f;
		buttonTimer[DIRECTION.EAST.ordinal()] = 0f;
		buttonTimer[DIRECTION.WEST.ordinal()] = 0f;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Settings.UP) {
			buttonPress[DIRECTION.NORTH.ordinal()] = true;
		}
		if (keycode == Settings.DOWN) {
			buttonPress[DIRECTION.SOUTH.ordinal()] = true;
		}
		if (keycode == Settings.LEFT) {
			buttonPress[DIRECTION.WEST.ordinal()] = true;
		}
		if (keycode == Settings.RIGHT) {
			buttonPress[DIRECTION.EAST.ordinal()] = true;
		}
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Settings.UP) {
			releaseDirection(DIRECTION.NORTH);
		}
		if (keycode == Settings.DOWN) {
			releaseDirection(DIRECTION.SOUTH);
		}
		if (keycode == Settings.LEFT) {
			releaseDirection(DIRECTION.WEST);
		}
		if (keycode == Settings.RIGHT) {
			releaseDirection(DIRECTION.EAST);
		}
		return false;
	}
	
	public void update(float delta) {
		if (buttonPress[DIRECTION.NORTH.ordinal()]) {
			updateDirection(DIRECTION.NORTH, delta);
			return;
		}
		if (buttonPress[DIRECTION.SOUTH.ordinal()]) {
			updateDirection(DIRECTION.SOUTH, delta);
			return;
		}
		if (buttonPress[DIRECTION.WEST.ordinal()]) {
			updateDirection(DIRECTION.WEST, delta);
			return;
		}
		if (buttonPress[DIRECTION.EAST.ordinal()]) {
			updateDirection(DIRECTION.EAST, delta);
			return;
		}
	}
	
	private void updateDirection(DIRECTION dir, float delta) {
		buttonTimer[dir.ordinal()] += delta;
		considerMove(dir);
	}
	
	private void releaseDirection(DIRECTION dir) {
		buttonPress[dir.ordinal()] = false;
		considerReface(dir);
		buttonTimer[dir.ordinal()] = 0f;
	}
	
	private void considerMove(DIRECTION dir) {
		if (buttonTimer[dir.ordinal()] > REFACE_TIME) {
			player.move(dir);
		}
	}
	
	private void considerReface(DIRECTION dir) {
		if (buttonTimer[dir.ordinal()] < REFACE_TIME) {
			player.reface(dir);
		}
	}
	
	public void stop() {
		buttonPress[DIRECTION.NORTH.ordinal()] = false;
		buttonPress[DIRECTION.SOUTH.ordinal()] = false;
		buttonPress[DIRECTION.EAST.ordinal()] = false;
		buttonPress[DIRECTION.WEST.ordinal()] = false;
		
		buttonTimer[DIRECTION.NORTH.ordinal()] = 0f;
		buttonTimer[DIRECTION.SOUTH.ordinal()] = 0f;
		buttonTimer[DIRECTION.EAST.ordinal()] = 0f;
		buttonTimer[DIRECTION.WEST.ordinal()] = 0f;
	}
}
