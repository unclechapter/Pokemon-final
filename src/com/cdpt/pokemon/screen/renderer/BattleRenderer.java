package com.cdpt.pokemon.screen.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.cdpt.pokemon.Settings;
import com.cdpt.pokemon.battle.BATTLE_PARTY;
import com.cdpt.pokemon.battle.animation.BattleAnimation;
import com.cdpt.pokemon.battle.animation.BattleSprite;

public class BattleRenderer {
	private int squareSize = 100;
	private float playerSquareMiddleX = 0;
	private float playerSquareMiddleY = 0;
	private int opponentSquareMiddleX = 0;
	private int opponentSquareMiddleY = 0;
	private TextureRegion background;
	private TextureRegion platform;
	private Texture playerPokemon;
	private Texture opponentPokemon;
	
	public BattleRenderer(AssetManager assetManager) {
		TextureAtlas atlas = assetManager.get("res/packed/battle/battlepack.atlas", TextureAtlas.class);
		background = atlas.findRegion("background");
		platform = atlas.findRegion("platform");
	}

	public void render(SpriteBatch batch, BattleAnimation animation, BATTLE_PARTY primarilyAnimated) {
		playerSquareMiddleX = Gdx.graphics.getWidth() / 2 - squareSize;
		playerSquareMiddleY = Gdx.graphics.getHeight() / 2;
		
		opponentSquareMiddleX = Gdx.graphics.getWidth() / 2 + squareSize;
		opponentSquareMiddleY = Gdx.graphics.getHeight() / 2;
		
		float platformYOrigin = playerSquareMiddleY - platform.getRegionHeight() / 2 * Settings.SCALE;
		
		float playerAlpha = 1f;
		float opponentAlpha = 1f;
		
		float playerWidth = 1f;
		float playerHeight = 1f;
		float opponentWidth = 1f;
		float opponentHeight = 1f;
		
		float playerX = 0f;
		float playerY = 0f;
		if (playerPokemon != null) {
			playerX = playerSquareMiddleX - playerPokemon.getWidth() / 2 * Settings.SCALE;
			playerY = platformYOrigin;
		}
		
		float opponentX = 0f;
		float opponentY = 0f;
		if (opponentPokemon != null) {
			opponentX = opponentSquareMiddleX - opponentPokemon.getWidth() / 2 * Settings.SCALE;
			opponentY = platformYOrigin;
		}
		
		if (animation != null) {
			if (primarilyAnimated == BATTLE_PARTY.PLAYER) {
				playerWidth = animation.getPrimaryWidth();
				playerHeight = animation.getPrimaryHeight();
				
				playerAlpha = animation.getPrimaryAlpha();
				opponentAlpha = animation.getSecondaryAlpha();
				
				playerX = playerSquareMiddleX - playerPokemon.getWidth() / 2 * Settings.SCALE * playerWidth;
				playerY = platformYOrigin+playerPokemon.getHeight() / 2 * Settings.SCALE - playerPokemon.getHeight() / 2 * Settings.SCALE * playerHeight;
				
				playerX += animation.getPrimaryOffsetX() * squareSize;
				playerY += animation.getPrimaryOffsetY() * squareSize;
				
				opponentX -= animation.getSecondaryOffsetX() * squareSize;
				opponentY -= animation.getSecondaryOffsetY() * squareSize;
				
				opponentWidth = animation.getSecondaryWidth();
				opponentHeight = animation.getSecondaryHeight();
			} else if (primarilyAnimated == BATTLE_PARTY.OPPONENT) {
				opponentWidth = animation.getPrimaryWidth();
				opponentHeight = animation.getPrimaryHeight();
				
				playerAlpha = animation.getSecondaryAlpha();
				opponentAlpha = animation.getPrimaryAlpha();
				
				opponentX = opponentSquareMiddleX - opponentPokemon.getWidth() / 2 * Settings.SCALE * opponentWidth;
				opponentY = platformYOrigin+opponentPokemon.getHeight() / 2 * Settings.SCALE - opponentPokemon.getHeight() / 2 * Settings.SCALE * opponentHeight;
				
				playerX += animation.getSecondaryOffsetX() * squareSize;
				playerY += animation.getSecondaryOffsetY() * squareSize;
				
				opponentX -= animation.getPrimaryOffsetX() * squareSize;
				opponentY += animation.getPrimaryOffsetY() * squareSize;
				
				playerWidth = animation.getSecondaryWidth();
				playerHeight = animation.getSecondaryHeight();
			}
		}
		
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		batch.draw(platform,
				playerSquareMiddleX-platform.getRegionWidth() / 2 * Settings.SCALE,
				platformYOrigin,
				platform.getRegionWidth()*Settings.SCALE,
				platform.getRegionHeight()*Settings.SCALE);
		batch.draw(platform,
				opponentSquareMiddleX-platform.getRegionWidth() / 2 * Settings.SCALE,
				platformYOrigin,
				platform.getRegionWidth() * Settings.SCALE,
				platform.getRegionHeight() * Settings.SCALE);
		
		if (playerPokemon != null) {
			batch.setColor(1f, 1f, 1f, playerAlpha);
			if (animation == null || animation.getPrimaryMask() == null || animation.isFinished()) {
				batch.draw(
						playerPokemon,
						playerX,
						playerY,
						playerWidth*playerPokemon.getWidth() * Settings.SCALE,
						playerHeight*playerPokemon.getHeight() * Settings.SCALE,
						0,
						0,
						playerPokemon.getWidth(),
						playerPokemon.getHeight(),
						true,
						false);
			}
		}
		if (opponentPokemon != null) {
			batch.setColor(1f, 1f, 1f, opponentAlpha);
			batch.draw(
					opponentPokemon, 
					opponentX, 
					opponentY,
					opponentWidth*opponentPokemon.getWidth() * Settings.SCALE,
					opponentHeight*opponentPokemon.getHeight() * Settings.SCALE,
					0,
					0,
					opponentPokemon.getWidth(),
					opponentPokemon.getHeight(),
					false,
					false);
		}
		
		batch.setColor(1f, 1f, 1f, 1f);
		
		if (animation != null && !animation.isFinished()) {
			for (BattleSprite sprite : animation.getSprites()) {
				batch.setColor(1f, 1f, 1f, sprite.getAlpha());
				float spriteX = 0f;
				float spriteY = 0f;
				if (primarilyAnimated == BATTLE_PARTY.PLAYER) {
					spriteX = playerSquareMiddleX + sprite.getX() * squareSize - sprite.getWidth() * sprite.getRegion().getRegionWidth() / 2;
					spriteY = playerSquareMiddleY + sprite.getY() * squareSize - sprite.getHeight() * sprite.getRegion().getRegionHeight() / 2;
				} else if (primarilyAnimated == BATTLE_PARTY.OPPONENT) {
					spriteX = opponentSquareMiddleX - sprite.getX() * squareSize - sprite.getWidth() * sprite.getRegion().getRegionWidth() / 2;
					spriteY = opponentSquareMiddleY + sprite.getY() * squareSize - sprite.getHeight() * sprite.getRegion().getRegionHeight() / 2;
				}
				batch.draw(
						sprite.getRegion(), 
						spriteX - sprite.getRegion().getRegionWidth() * sprite.getWidth() * Settings.SCALE / 2, 
						spriteY - sprite.getRegion().getRegionHeight() * sprite.getHeight() * Settings.SCALE / 2, 
						(sprite.getRegion().getRegionWidth() * sprite.getWidth() * Settings.SCALE) / 2,
						(sprite.getRegion().getRegionHeight() * sprite.getHeight() * Settings.SCALE) / 2,
						Settings.SCALE * sprite.getWidth() * sprite.getRegion().getRegionWidth(), 
						Settings.SCALE * sprite.getHeight() * sprite.getRegion().getRegionHeight(),
						1f,
						1f,
						sprite.getRotation());
				batch.setColor(1f, 1f, 1f, 1f);
			}
		}
	}
	
	public void setPokemonSprite(Texture region, BATTLE_PARTY turn) {
		if (turn == BATTLE_PARTY.PLAYER) {
			playerPokemon = region;
		} else if (turn == BATTLE_PARTY.OPPONENT) {
			opponentPokemon = region;
		}
	}

	public int squareSize() {
		return squareSize;
	}
	
	public float playerSquareMiddleX() {
		return playerSquareMiddleX;
	}
	
	public float playerSquareMiddleY() {
		return playerSquareMiddleY;
	}
	
	public int opponentSquareMiddleX() {
		return opponentSquareMiddleX;
	}
	
	public int opponentSquareMiddleY() {
		return opponentSquareMiddleY;
	}
}
