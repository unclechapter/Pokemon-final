package com.cdpt.pokemon.util;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cdpt.pokemon.model.DIRECTION;

public class AnimationSet {
	private HashMap<DIRECTION, Animation> walking;
	private HashMap<DIRECTION, TextureRegion> standing;
	
	public AnimationSet(Animation walkNorth, 
			Animation walkSouth, 
			Animation walkEast, 
			Animation walkWest, 
			TextureRegion standNorth, 
			TextureRegion standSouth, 
			TextureRegion standEast, 
			TextureRegion standWest) {
		walking = new HashMap<DIRECTION, Animation>();
		walking.put(DIRECTION.NORTH, walkNorth);
		walking.put(DIRECTION.SOUTH, walkSouth);
		walking.put(DIRECTION.EAST, walkEast);
		walking.put(DIRECTION.WEST, walkWest);
		standing = new HashMap<DIRECTION, TextureRegion>();
		standing.put(DIRECTION.NORTH, standNorth);
		standing.put(DIRECTION.SOUTH, standSouth);
		standing.put(DIRECTION.EAST, standEast);
		standing.put(DIRECTION.WEST, standWest);
	}
	
	public Animation getWalking(DIRECTION dir) {
		return walking.get(dir);
	}
	
	public TextureRegion getStanding(DIRECTION dir) {
		return standing.get(dir);
	}

}
