package com.cdpt.pokemon.model.world.script;

import com.badlogic.gdx.graphics.Color;
import com.cdpt.pokemon.model.DIRECTION;
import com.cdpt.pokemon.model.world.World;

public interface WorldInterface {
	public void changeLocation(World newWorld, int x, int y, DIRECTION facing, Color color);

	public World getWorld(String worldName);
}
