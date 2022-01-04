package com.cdpt.pokemon.screen.renderer;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cdpt.pokemon.Settings;
import com.cdpt.pokemon.model.Actor;
import com.cdpt.pokemon.model.Camera;
import com.cdpt.pokemon.model.TERRAIN;
import com.cdpt.pokemon.model.YSortable;
import com.cdpt.pokemon.model.world.World;
import com.cdpt.pokemon.model.world.WorldObject;

public class WorldRenderer {
	private World world;
	private TextureRegion grass1;
	private TextureRegion grass2;
	private ArrayList<Integer> renderedObjects = new ArrayList<Integer>();
	private ArrayList<YSortable> forRendering = new ArrayList<YSortable>();
	
	public WorldRenderer(AssetManager assetManager, World world) {
		this.world = world;;
		TextureAtlas atlas = assetManager.get("res/packed/tiles/tilepack.atlas", TextureAtlas.class);
		grass1 = atlas.findRegion("grass1");
		grass2 = atlas.findRegion("grass2");
	}
	
	public void render(SpriteBatch batch, Camera camera) {
		float worldStartX = Gdx.graphics.getWidth() / 2 - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
		float worldStartY = Gdx.graphics.getHeight() / 2 - camera.getCameraY() * Settings.SCALED_TILE_SIZE;
		
		for (int x = 0; x < world.getMap().getWidth(); x++) {
			for (int y = 0; y < world.getMap().getHeight(); y++) {
				TextureRegion render;
				if (world.getMap().getTile(x, y).getTerrain() == TERRAIN.GRASS_1) {
					render = grass1;
				} else if (world.getMap().getTile(x, y).getTerrain() == TERRAIN.GRASS_2) {
					render = grass2;
				} else {
					render = null;
				}
				
				if (render != null) {
					batch.draw(render, 
							(int)(worldStartX + x * Settings.SCALED_TILE_SIZE),
							(int)(worldStartY + y * Settings.SCALED_TILE_SIZE),
							(int)(Settings.SCALED_TILE_SIZE),
							(int)(Settings.SCALED_TILE_SIZE));
				}
			}
		}
		
		for (int x = 0; x < world.getMap().getWidth(); x++) {
			for (int y = 0; y < world.getMap().getHeight(); y++) {
				if (world.getMap().getTile(x, y).getActor() != null) {
					Actor actor = world.getMap().getTile(x, y).getActor();
					forRendering.add(actor);
				}
				if (world.getMap().getTile(x, y).getObject() != null) {
					WorldObject object = world.getMap().getTile(x, y).getObject();
					if (renderedObjects.contains(object.hashCode())) {
						continue;
					}
					if (object.isWalkable()) {
						batch.draw(object.getSprite(),
								worldStartX + object.getWorldX() * Settings.SCALED_TILE_SIZE,
								worldStartY + object.getWorldY() * Settings.SCALED_TILE_SIZE,
								Settings.SCALED_TILE_SIZE * object.getSizeX(),
								Settings.SCALED_TILE_SIZE * object.getSizeY());
						continue;
					} else {
						forRendering.add(object);
						renderedObjects.add(object.hashCode());
					}
				}
			}
		}
		
		Collections.sort(forRendering, new WorldObjectYComparator());
		Collections.reverse(forRendering);
		
		for (YSortable loc : forRendering) {
			batch.draw(loc.getSprite(), 
					worldStartX+loc.getWorldX() * Settings.SCALED_TILE_SIZE,
					worldStartY+loc.getWorldY() * Settings.SCALED_TILE_SIZE,
					Settings.SCALED_TILE_SIZE * loc.getSizeX(),
					Settings.SCALED_TILE_SIZE * loc.getSizeY());
		}
		
		renderedObjects.clear();
		forRendering.clear();
	}
	
	public void setWorld(World world) {
		this.world = world;
	}

}
