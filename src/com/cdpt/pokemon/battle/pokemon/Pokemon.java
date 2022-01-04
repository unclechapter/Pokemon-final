package com.cdpt.pokemon.battle.pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.cdpt.pokemon.battle.STAT;
import com.cdpt.pokemon.battle.moves.Move;
import com.cdpt.pokemon.battle.moves.MoveDatabase;
import com.cdpt.pokemon.battle.moves.MoveSpecification;


public class Pokemon {
	private static MoveDatabase moveDatabase = new MoveDatabase();
	private String nickname;
	private String name;
	private Species species;
	private int level;
	private Map<STAT, Integer> stats;
	private int currentHitpoints;
	private short[] currentStats, inBattleStats;
	private int totalExpForNextLevel, totalExp;
	private final byte[] IVS = new byte[6];
	private byte[] evs;
	private Move[] moves = new Move[5];

	public Pokemon(Species s) {
		this(s, 1);
	}

	public Pokemon(Species s, int level) {
		this.species = s;
		this.name = s.getName();
		this.level=level;
		int currentHP;
		MoveDatabase md = new MoveDatabase();
		currentStats = new short[]{
				s.getBASE_STAT(STAT.HP.ordinal()),
				s.getBASE_STAT(STAT.ATTACK.ordinal()),
				s.getBASE_STAT(STAT.DEFENSE.ordinal()),
				s.getBASE_STAT(STAT.SP_ATTACK.ordinal()),
				s.getBASE_STAT(STAT.SP_DEFENSE.ordinal()),
				s.getBASE_STAT(STAT.SPEED.ordinal())};
		currentHP = s.getBASE_STAT(STAT.HP.ordinal());
		setCurrentHitpoints(currentHP);
		int count = 0;
		for (int i : s.getMoveList()) {
			moves[count] = md.getMove(i);
			count++;
		}
	}
	
	public Texture getSprite() {
		AssetManager am = new AssetManager();
		String str = "res/unpacked/graphics/pokemon/" + species.getName() + ".png";
		am.load(str, Texture.class);
		am.finishLoading();
		return am.get(str, Texture.class);
	}

	public int getCurrentHitpoints() {
		return currentHitpoints;
	}

	public void setCurrentHitpoints(int currentHitpoints) {
		this.currentHitpoints = currentHitpoints;
	}

	public void takeDamage(int damage) {
		inBattleStats[(byte)STAT.HP.ordinal()] -= (short)damage;

		if(inBattleStats[(byte)STAT.HP.ordinal()] < 0)
		{
			inBattleStats[(byte)STAT.HP.ordinal()] = 0;
		}
	}

	public boolean isFainted() {
		return currentHitpoints <= 0;
	}

	public String getName() {
		return name;
	}

	public Move[] getMoveSet() {
		return moves;
	}

	public Move getMove(int index) {
		return moves[index].clone();
	}
	
	public MoveSpecification getMoveSpecification(int index) {
		return moves[index].getMoveSpecification();
	}

	public void setStats(Map<STAT, Integer> stats) {
		this.stats.putAll(stats);
	}
	
	public int getStat(STAT stat) {
		return currentStats[stat.ordinal()];
	}
	
	public void setStat(STAT stat, int value) {
		stats.put(stat, value);
	}

	private short calculateStat(final STAT stat) {
		return (short)(stat == STAT.HP ? (((IVS[(byte)STAT.HP.ordinal()] + (2 * species.getBaseStat((byte)STAT.HP.ordinal())) + (evs[(byte)STAT.HP.ordinal()] / 4) + 100) * level) / 100) + 10 :
				(((IVS[(byte)stat.ordinal()] + (2 * species.getBaseStat((byte)stat.ordinal())) + (evs[(byte)stat.ordinal()] / 4)) * level) / 100) + 5);
	}
	
	public int getLevel() {
		return level;
	}

	public short getExpYield() {
		return species.getExpYield();
	}


	public void applyDamage(int amount) {
		currentHitpoints -= amount;
		if (currentHitpoints < 0) {
			currentHitpoints = 0;
		}
	}
}
