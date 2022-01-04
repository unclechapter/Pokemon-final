package com.cdpt.pokemon.model;

public enum DIRECTION {
	NORTH(0,1),
	EAST(1,0),
	SOUTH(0,-1),
	WEST(-1,0);
	
	private int dx, dy;
	
	private DIRECTION(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getDX() {
		return dx;
	}
	
	public int getDY() {
		return dy;
	}

}
