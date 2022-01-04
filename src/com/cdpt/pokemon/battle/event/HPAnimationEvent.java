package com.cdpt.pokemon.battle.event;

import com.badlogic.gdx.math.Interpolation;
import com.cdpt.pokemon.battle.BATTLE_PARTY;
import com.cdpt.pokemon.ui.DetailedStatusBox;
import com.cdpt.pokemon.ui.HPBar;
import com.cdpt.pokemon.ui.StatusBox;

public class HPAnimationEvent extends BattleEvent {
	private BATTLE_PARTY party;
	private int hpBefore;
	private int hpAfter;
	private int hpTotal;
	private float duration;
	private BattleEventPlayer eventPlayer;
	private float timer;
	private boolean finished;
	
	public HPAnimationEvent(BATTLE_PARTY party, int hpBefore, int hpAfter, int hpTotal, float duration) {
		this.party = party;
		this.hpBefore = hpBefore;
		this.hpAfter = hpAfter;
		this.hpTotal = hpTotal;
		this.duration = duration;
		this.timer = 0f;
		this.finished = false;
	}

	@Override
	public void update(float delta) {
		timer += delta;
		if (timer > duration) {
			finished = true;
		}
		
		float prog = timer / duration;
		float hpProg = Interpolation.linear.apply(hpBefore, hpAfter, prog);
		float hpPercentLeft = hpProg / hpTotal;
		
		HPBar hpbar = eventPlayer.getStatusBox(party).getHPBar();
		hpbar.displayHPLeft(hpPercentLeft);
		
		StatusBox statusBox = eventPlayer.getStatusBox(party);
		if (statusBox instanceof DetailedStatusBox) {
			((DetailedStatusBox)statusBox).setHPText((int) hpProg, hpTotal);
		}
	}

	@Override
	public void begin(BattleEventPlayer player) {
		super.begin(player);
		this.eventPlayer = player;
	}

	@Override
	public boolean finished() {
		return finished;
	}

}
