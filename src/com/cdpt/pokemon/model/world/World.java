package com.cdpt.pokemon.model.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.GridPoint2;
import com.cdpt.pokemon.model.Actor;
import com.cdpt.pokemon.model.TileMap;

public class World {
	private String name;
	private TileMap map;
	private List<Actor> actors;
	private List<WorldObject> objects;
	
	public World(String name, int width, int height) {
		this.name = name;
		this.map = new TileMap(width, height);
		actors = new ArrayList<Actor>();
		objects = new ArrayList<WorldObject>();
	}
	
	public void addActor(Actor a) {
		map.getTile(a.getX(), a.getY()).setActor(a);
		actors.add(a);
	}
	
	public void addObject(WorldObject o) {
		for (GridPoint2 p : o.getTiles()) {
			map.getTile(o.getX() + p.x, o.getY() + p.y).setObject(o);
		}
		objects.add(o);
	}
	
	public void removeActor(Actor actor) {
		map.getTile(actor.getX(), actor.getY()).setActor(null);
		actors.remove(actor);
	}
	
	public void update(float delta) {
		for (Actor a : actors) {
			a.update(delta);
		}
		for (WorldObject o : objects) {
			o.update(delta);
		}
	}

	public TileMap getMap() {
		return map;
	}
	
	public List<Actor> getActors() {
		return actors;
	}
	
	public List<WorldObject> getWorldObjects() {
		return objects;
	}

	public String getName() {
		return name;
	}
}
