package com.cdpt.pokemon.battle.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class BattleSprite {
	private Vector2 position;
	protected TextureRegion region;
	private float width;
	private float height;
	private float rotation = 0f;
	private float alpha = 1f;

	public BattleSprite(TextureRegion region, float startX, float startY, float width, float height) {
		this.region = region;
		this.width = width;
		this.height = height;
		this.position = new Vector2(startX, startY);
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public void setX(float newX) {
		position.x = newX;
	}
	
	public void setY(float newY) {
		position.y = newY;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setWidth(float newWidth) {
		this.width = newWidth;
	}
	
	public void setHeight(float newHeight) {
		this.height = newHeight;
	}
	
	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public TextureRegion getRegion() {
		return region;
	}
	
	public float getAlpha() {
		return alpha;
	}
	
	public void setAlpha(float newAlpha) {
		this.alpha = newAlpha;
	}
}
