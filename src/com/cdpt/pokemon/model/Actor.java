package com.cdpt.pokemon.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.cdpt.pokemon.model.world.World;
import com.cdpt.pokemon.model.world.WorldObject;
import com.cdpt.pokemon.util.AnimationSet;

public class Actor implements YSortable {
	private World world;
	private int x;
	private int y;
	private DIRECTION facing;
	private float worldX, worldY;
	private int srcX, srcY;
	private int destX, destY;
	private float animTimer;
	private float WALK_TIME_PER_TILE = 0.3f;
	private float REFACE_TIME = 0.1f;
	private float walkTimer;
	private boolean moveRequestThisFrame;
	private ACTOR_STATE state;
	private AnimationSet animations;
	
	public enum ACTOR_STATE {
		WALKING,
		STANDING,
		REFACING;
	}
	
	public Actor(World world, int x, int y, AnimationSet animations) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.worldX = x;
		this.worldY = y;
		this.animations = animations;
		this.state = ACTOR_STATE.STANDING;
		this.facing = DIRECTION.SOUTH;
	}
	
	public void update(float delta) {
		if (state == ACTOR_STATE.WALKING) {
			animTimer += delta;
			walkTimer += delta;
			worldX = Interpolation.linear.apply(srcX, destX, animTimer / WALK_TIME_PER_TILE);
			worldY = Interpolation.linear.apply(srcY, destY, animTimer / WALK_TIME_PER_TILE);
			if (animTimer > WALK_TIME_PER_TILE) {
				float leftOverTime = animTimer - WALK_TIME_PER_TILE;
				finishMove();
				if (moveRequestThisFrame) {
					if (move(facing)) {
						animTimer += leftOverTime;
						worldX = Interpolation.linear.apply(srcX, destX, animTimer / WALK_TIME_PER_TILE);
						worldY = Interpolation.linear.apply(srcY, destY, animTimer / WALK_TIME_PER_TILE);
					}
				} else {
					walkTimer = 0f;
				}
			}
		}
		if (state == ACTOR_STATE.REFACING) {
			animTimer += delta;
			if (animTimer > REFACE_TIME) {
				state = ACTOR_STATE.STANDING;
			}
		}
		moveRequestThisFrame = false;
	}
	
	public void reface(DIRECTION dir) {
		if (state != ACTOR_STATE.STANDING) {
			return;
		}
		if (facing == dir) {
			return;
		}
		facing = dir;
		state = ACTOR_STATE.REFACING;
		animTimer = 0f;
	}
	
	public boolean move(DIRECTION dir) {
		if (state == ACTOR_STATE.WALKING) {
			if (facing == dir) {
				moveRequestThisFrame = true;
			}
			return false;
		}
		if (x + dir.getDX() >= world.getMap().getWidth() || x + dir.getDX() < 0 || y + dir.getDY() >= world.getMap().getHeight() || y + dir.getDY() < 0) {
			reface(dir);
			return false;
		}
		if (!world.getMap().getTile(x + dir.getDX(), y + dir.getDY()).walkable()) {
			reface(dir);
			return false;
		}
		if (world.getMap().getTile(x + dir.getDX(), y + dir.getDY()).getActor() != null) {
			reface(dir);
			return false;
		}
		if (world.getMap().getTile(x + dir.getDX(), y + dir.getDY()).getObject() != null) {
			WorldObject o = world.getMap().getTile(x + dir.getDX(), y + dir.getDY()).getObject();
			if (!o.isWalkable()) {
				reface(dir);
				return false;
			}
		}
		initializeMove(dir);
		world.getMap().getTile(x, y).setActor(null);
		x += dir.getDX();
		y += dir.getDY();
		world.getMap().getTile(x, y).setActor(this);
		return true;
	}
	
	public void setState(ACTOR_STATE state) {
		this.state = state;
	}
	
	private void initializeMove(DIRECTION dir) {
		this.facing = dir;
		this.srcX = x;
		this.srcY = y;
		this.destX = x + dir.getDX();
		this.destY = y + dir.getDY();
		this.worldX = x;
		this.worldY = y;
		animTimer = 0f;
		state = ACTOR_STATE.WALKING;
	}
	
	public void finishMove() {
		state = ACTOR_STATE.STANDING;
		this.worldX = destX;
		this.worldY = destY;
		this.srcX = 0;
		this.srcY = 0;
		this.destX = 0;
		this.destY = 0;
		world.getMap().getTile(x, y).actorStep(this);
	}
	
	public void teleport(int x, int y) {
		this.x = x;
		this.y = y;
		this.worldX = x;
		this.worldY = y;
	}

	public void refaceWithoutAnimation(DIRECTION dir) {
		this.facing = dir;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public float getWorldX() {
		return worldX;
	}

	public float getWorldY() {
		return worldY;
	}
	
	public TextureRegion getSprite() {
		if (state == ACTOR_STATE.WALKING) {
			return animations.getWalking(facing).getKeyFrame(walkTimer);
		} else if (state == ACTOR_STATE.STANDING) {
			return animations.getStanding(facing);
		} else if (state == ACTOR_STATE.REFACING) {
			return animations.getWalking(facing).getKeyFrames()[0];
		}
		return animations.getStanding(DIRECTION.SOUTH);
	}

	@Override
	public float getSizeX() {
		return 1;
	}

	@Override
	public float getSizeY() {
		return 1.5f;
	}
	
	public void changeWorld(World world) {
		this.world.removeActor(this);
		this.world = world;
		this.world.addActor(this);
	}
	
	public ACTOR_STATE getState() {
		return state;
	}
}
