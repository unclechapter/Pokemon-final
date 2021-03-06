package com.cdpt.pokemon.battle.moves;

public class MoveSpecification {
	private MOVE_TYPE type;
	private MOVE_CATEGORY category;
	private int power;
	private float accuracy;
	private int pp;
	private String name;
	
	public MoveSpecification(MOVE_TYPE type, MOVE_CATEGORY category, int power, float accuracy, int pp, String name) {
		this.type = type;
		this.category = category;
		this.power = power;
		this.accuracy = accuracy;
		this.pp = pp;
		this.name = name;
	}

	public MOVE_TYPE getType() {
		return type;
	}

	public MOVE_CATEGORY getCategory() {
		return category;
	}

	public int getPower() {
		return power;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public int getPP() {
		return pp;
	}

	public String getName() {
		return name;
	}
}
