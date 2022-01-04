package com.cdpt.pokemon.battle.moves;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cdpt.pokemon.battle.animation.ChargeAnimation;

public class MoveDatabase {
	private List<Move> moves = new ArrayList<Move>();
	private HashMap<String, Integer> mappings = new HashMap<String, Integer>();
	
	public MoveDatabase() {
		initializeMoves();
	}

	private void initializeMoves() {
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.NORMAL, MOVE_CATEGORY.PHYSICAL, 50, 1f, 35, "Tackle"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.NORMAL, MOVE_CATEGORY.PHYSICAL, 40, 1f, 35, "Scratch"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.NORMAL,MOVE_CATEGORY.PHYSICAL,20,1f,40,"Rapid Spin"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.NORMAL, MOVE_CATEGORY.SPECIAL, 100, 1f, 10, "Mimic"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.NORMAL, MOVE_CATEGORY.PHYSICAL, 100, 1f, 10, "Recycle"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.NORMAL, MOVE_CATEGORY.PHYSICAL, 40, 1f, 35, "Mega Punch"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.NORMAL, MOVE_CATEGORY.PHYSICAL, 40, 1f, 35, "Pay Day"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.NORMAL, MOVE_CATEGORY.PHYSICAL, 40, 1f, 30, "Tackle"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.NORMAL,MOVE_CATEGORY.PHYSICAL,85,1f,10,"DoubleSlap"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.NORMAL, MOVE_CATEGORY.PHYSICAL, 85, 1f, 15, "Comet Punch"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.DRAGON, MOVE_CATEGORY.PHYSICAL, 80, 1f, 15, "Dragon Claw"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.DRAGON, MOVE_CATEGORY.PHYSICAL, 100, 1f, 15, "Outrage"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.DRAGON, MOVE_CATEGORY.SPECIAL, 100, 1f, 20, "Twister"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.DRAGON, MOVE_CATEGORY.SPECIAL, 100, 1f, 20, "DragonBreath"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.DRAGON,MOVE_CATEGORY.SPECIAL,100,1f,10,"Dragon Rage"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GRASS, MOVE_CATEGORY.PHYSICAL,35,1f,15,"Vine Whip"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GRASS,MOVE_CATEGORY.PHYSICAL,55,1f,25,"Razor Leaf"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GRASS,MOVE_CATEGORY.PHYSICAL,80,1f,15,"Seed Bomb"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GRASS,MOVE_CATEGORY.SPECIAL,120,1f,10,"Solarbeam"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GRASS,MOVE_CATEGORY.SPECIAL,120,1f,10,"Petal Dance"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GRASS,MOVE_CATEGORY.SPECIAL,120,1f,10,"Giga Drain"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GRASS,MOVE_CATEGORY.SPECIAL,120,1f,10,"Cotton Spore"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIRE,MOVE_CATEGORY.SPECIAL,40,1f,25,"Ember"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIRE,MOVE_CATEGORY.PHYSICAL,65,1f,15,"Fire Fang"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIRE,MOVE_CATEGORY.PHYSICAL,100,1f,15,"Fire Punch"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIRE,MOVE_CATEGORY.SPECIAL,90,1f,10,"Heat Wave"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIRE,MOVE_CATEGORY.PHYSICAL,100,1f,25,"Flame Wheel"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIRE,MOVE_CATEGORY.SPECIAL,85,1f,5,"Fire Blast"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIRE,MOVE_CATEGORY.PHYSICAL,90,1f,10,"Blaze Kick"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIRE,MOVE_CATEGORY.SPECIAL,35,1f,15,"Fire Spin"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIRE,MOVE_CATEGORY.PHYSICAL,120,1f,15,"Flare Blitz"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.WATER,MOVE_CATEGORY.SPECIAL,20,1f,30,"Bubble"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.WATER, MOVE_CATEGORY.SPECIAL, 40, 1f, 25, "Water Gun"), ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.WATER,MOVE_CATEGORY.SPECIAL,120,1f,5,"Hydro Pump"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.WATER,MOVE_CATEGORY.SPECIAL,100,1f,15,"Waterfall"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.WATER,MOVE_CATEGORY.SPECIAL,120,1f,5,"Clamp"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.WATER,MOVE_CATEGORY.SPECIAL,120,1f,5,"Whirlpool"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.WATER,MOVE_CATEGORY.SPECIAL,120,1f,5,"Dive"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.WATER,MOVE_CATEGORY.SPECIAL,120,1f,5,"Octazooka"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.WATER,MOVE_CATEGORY.SPECIAL,120,1f,5,"Muddy Water"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.STEEL,MOVE_CATEGORY.SPECIAL,80,1f,10,"Flash Cannon"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.STEEL,MOVE_CATEGORY.PHYSICAL,80,1f,10,"Steel Wing"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.STEEL,MOVE_CATEGORY.PHYSICAL,80,1f,10,"Iron Tail"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.STEEL,MOVE_CATEGORY.PHYSICAL,80,1f,10,"Metal Claw"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.STEEL,MOVE_CATEGORY.PHYSICAL,80,1f,10,"Meteor Mash"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.STEEL,MOVE_CATEGORY.SPECIAL,80,1f,10,"Metal Sound"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIGHTING,MOVE_CATEGORY.PHYSICAL,50,1f,25,"Submission"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIGHTING,MOVE_CATEGORY.PHYSICAL,50,1f,25,"Low Kick"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIGHTING,MOVE_CATEGORY.PHYSICAL,50,1f,25,"Counter"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIGHTING,MOVE_CATEGORY.PHYSICAL,50,1f,25,"Seismic Toss"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIGHTING,MOVE_CATEGORY.PHYSICAL,50,1f,25,"Hi Jump Kick"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIGHTING,MOVE_CATEGORY.PHYSICAL,50,1f,25,"Sky Uppercut"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIGHTING,MOVE_CATEGORY.PHYSICAL,50,1f,25,"Karate Chop"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIGHTING,MOVE_CATEGORY.PHYSICAL,50,1f,25,"Triple Kick"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIGHTING,MOVE_CATEGORY.PHYSICAL,50,1f,25,"DynamicPunch"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FIGHTING,MOVE_CATEGORY.PHYSICAL,50,1f,25,"Karate Chop"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ICE,MOVE_CATEGORY.PHYSICAL,90,1f,20,"Ice Ball"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ICE,MOVE_CATEGORY.PHYSICAL,30,1f,30,"Haze"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ICE,MOVE_CATEGORY.PHYSICAL,30,1f,10,"Hail"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ICE,MOVE_CATEGORY.PHYSICAL,100,1f,30,"Icicle Spear"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ICE,MOVE_CATEGORY.SPECIAL,50,1f,25,"Ice Punch"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ELECTRIC,MOVE_CATEGORY.PHYSICAL,100,1f,15,"ThunderPunch"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ELECTRIC,MOVE_CATEGORY.PHYSICAL,100,1f,30,"ThunderShock"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ELECTRIC,MOVE_CATEGORY.PHYSICAL,100,1f,15,"Thunderbolt"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ELECTRIC,MOVE_CATEGORY.SPECIAL,100,1f,20,"Thunder Wave"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ELECTRIC,MOVE_CATEGORY.PHYSICAL,100,1f,20,"Spark"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ELECTRIC,MOVE_CATEGORY.PHYSICAL,30,1f,20,"Shock Wave"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ELECTRIC,MOVE_CATEGORY.SPECIAL,70,1f,10,"Thunder"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ROCK,MOVE_CATEGORY.PHYSICAL,90,1f,10,"Rock Slide"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ROCK,MOVE_CATEGORY.PHYSICAL,30,1f,10,"Rock Slide"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ROCK,MOVE_CATEGORY.PHYSICAL,90,1f,20,"Rock Slide"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ROCK,MOVE_CATEGORY.PHYSICAL,80,1f,10,"Rock Tomb"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ROCK,MOVE_CATEGORY.PHYSICAL,90,1f,10,"Rock Blast"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.ROCK,MOVE_CATEGORY.PHYSICAL,90,1f,15,"Rock Throw"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GROUND,MOVE_CATEGORY.PHYSICAL,90,1f,15,"Earthquake"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GROUND,MOVE_CATEGORY.PHYSICAL,90,1f,15,"Fissure"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GROUND,MOVE_CATEGORY.PHYSICAL,90,1f,15,"Bone Club"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GROUND,MOVE_CATEGORY.SPECIAL,90,1f,15,"Mud-Slap"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GROUND,MOVE_CATEGORY.PHYSICAL ,90,1f,15,"Sand Tomb"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GROUND,MOVE_CATEGORY.PHYSICAL,90,1f,15,"Mud Sport"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GROUND,MOVE_CATEGORY.PHYSICAL,90,1f,15,"Magnitude"),ChargeAnimation.class));
		addMove( new DamageMove(new MoveSpecification(MOVE_TYPE.GROUND,MOVE_CATEGORY.PHYSICAL,90,1f,15,"Dig"),ChargeAnimation.class));
		addMove( new DamageMove(new MoveSpecification(MOVE_TYPE.PSYCHIC,MOVE_CATEGORY.PHYSICAL,90,1f,15,"Confusion"),ChargeAnimation.class));
		addMove( new DamageMove(new MoveSpecification(MOVE_TYPE.PSYCHIC,MOVE_CATEGORY.PHYSICAL,100,1f,10,"Psychic"),ChargeAnimation.class));
		addMove( new DamageMove(new MoveSpecification(MOVE_TYPE.PSYCHIC,MOVE_CATEGORY.PHYSICAL,60,1f,20,"Hypnosis"),ChargeAnimation.class));
		addMove( new DamageMove(new MoveSpecification(MOVE_TYPE.PSYCHIC,MOVE_CATEGORY.PHYSICAL,30,1f,40,"Meditate"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.PSYCHIC,MOVE_CATEGORY.PHYSICAL,30,1f,30,"Agility" ),ChargeAnimation.class));
		addMove( new DamageMove(new MoveSpecification(MOVE_TYPE.PSYCHIC,MOVE_CATEGORY.PHYSICAL,30,1f,30,"Light Screen"),ChargeAnimation.class));
		addMove( new DamageMove(new MoveSpecification(MOVE_TYPE.PSYCHIC,MOVE_CATEGORY.PHYSICAL,30,1f,30,"Barrier"),ChargeAnimation.class));
		addMove( new DamageMove(new MoveSpecification(MOVE_TYPE.PSYCHIC,MOVE_CATEGORY.PHYSICAL,30,1f,20,"Reflect"),ChargeAnimation.class));
		addMove( new DamageMove(new MoveSpecification(MOVE_TYPE.PSYCHIC,MOVE_CATEGORY.PHYSICAL,30,1f,20,"Amesia"),ChargeAnimation.class));
		addMove( new DamageMove(new MoveSpecification(MOVE_TYPE.PSYCHIC,MOVE_CATEGORY.PHYSICAL,80,1f,15,"Kinesis"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GHOST,MOVE_CATEGORY.PHYSICAL,100,1f,15,"Night Shade"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GHOST,MOVE_CATEGORY.PHYSICAL,100,1f,15,"Grudge"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GHOST,MOVE_CATEGORY.PHYSICAL,100,1f,5,"Nightmare"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GHOST,MOVE_CATEGORY.PHYSICAL,100,1f,15,"Curse"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GHOST,MOVE_CATEGORY.PHYSICAL,30,1f,20,"Spite"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GHOST,MOVE_CATEGORY.PHYSICAL,100,1f,10,"Confuse Ray"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GHOST,MOVE_CATEGORY.PHYSICAL,100,1f,15,"Shadow Ball"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.GHOST,MOVE_CATEGORY.PHYSICAL,100,1f,30,"Lick"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.POISON,MOVE_CATEGORY.SPECIAL,80,1f,40,"Poison Gas"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.POISON,MOVE_CATEGORY.SPECIAL,30,1f,40,"Acid Armor"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.POISON,MOVE_CATEGORY.SPECIAL,100,1f,10,"Sludge Bomb"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.POISON,MOVE_CATEGORY.SPECIAL,70,1f,20,"Smog"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.POISON,MOVE_CATEGORY.SPECIAL,100,1f,20,"Sludge"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.POISON,MOVE_CATEGORY.PHYSICAL,100,1f,20,"Mirror Move"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FLYING,MOVE_CATEGORY.PHYSICAL,30,1f,20,"Aerial Ace"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FLYING,MOVE_CATEGORY.SPECIAL,95,1f,5,"Aeroblast"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FLYING,MOVE_CATEGORY.SPECIAL,100,1f,15,"FeatherDance"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FLYING,MOVE_CATEGORY.SPECIAL,95,1f,25,"Air Cutter"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FLYING,MOVE_CATEGORY.SPECIAL,30,1f,10,"ROOST"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.FLYING,MOVE_CATEGORY.PHYSICAL,90,1f,5,"Sky Attack"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.BUG,MOVE_CATEGORY.PHYSICAL,100,1f,15,"Leech Life"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.BUG,MOVE_CATEGORY.SPECIAL,100,1f,10,"Spider Web"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.BUG,MOVE_CATEGORY.PHYSICAL,95,1f,20,"Fury Cutter"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.BUG,MOVE_CATEGORY.PHYSICAL,85,1f,10,"Megahorn"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.BUG,MOVE_CATEGORY.SPECIAL,100,1f,20,"Tail Glow"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.BUG,MOVE_CATEGORY.SPECIAL,100,1f,5,"Silver Wind"),ChargeAnimation.class));
		addMove(new DamageMove(new MoveSpecification(MOVE_TYPE.BUG,MOVE_CATEGORY.SPECIAL,100,1f,15,"Signal Beam"),ChargeAnimation.class));
	}
	
	private void addMove(Move move) {
		moves.add(move);
		mappings.put(move.getName(), moves.size() - 1);
	}
	
	public Move getMove(String moveName) {
		return moves.get(mappings.get(moveName)).clone();
	}
	
	public Move getMove(int index) {
		return moves.get(index).clone();
	}
}
