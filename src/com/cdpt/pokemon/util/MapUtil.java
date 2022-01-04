package com.cdpt.pokemon.util;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.cdpt.pokemon.Settings;
import com.cdpt.pokemon.model.world.World;
import com.cdpt.pokemon.model.world.WorldObject;
import com.cdpt.pokemon.model.world.script.WorldInterface;

public class MapUtil {
	private Animation flowerAnimation;
	
	public MapUtil(AssetManager assetManager, WorldInterface worldInterface, AnimationSet npcAnimations) {
		TextureAtlas atlas = assetManager.get("res/packed/tiles/tilepack.atlas", TextureAtlas.class);
		flowerAnimation = new Animation(0.8f, atlas.findRegions("flowers"), PlayMode.LOOP_PINGPONG);
	}
	
	public World loadWorld() {
		World world = new World("test_level", Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT);	
		for (int i = 0; i < 1000; i++) {
			int x = MathUtils.random(Settings.WORLD_WIDTH - 1);
			int y = MathUtils.random(Settings.WORLD_HEIGHT - 1);
			if (world.getMap().getTile(x, y).getObject() == null) {
				addFlowers(world, x, y);
			}
		}		
		return world;
	}
	
	private void addFlowers(World world, int x, int y) {
		GridPoint2[] gridArray = new GridPoint2[1];
		gridArray[0] = new GridPoint2(0,0);
		WorldObject flowers = new WorldObject(x, y, true, flowerAnimation, 1f, 1f, gridArray);
		world.addObject(flowers);
	}
}
