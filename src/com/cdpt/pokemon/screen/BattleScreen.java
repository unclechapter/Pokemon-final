package com.cdpt.pokemon.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cdpt.pokemon.PokemonGame;
import com.cdpt.pokemon.Settings;
import com.cdpt.pokemon.battle.pokemon.WildPokemonGen;
import com.cdpt.pokemon.battle.BATTLE_PARTY;
import com.cdpt.pokemon.battle.Battle;
import com.cdpt.pokemon.battle.BattleObserver;
import com.cdpt.pokemon.battle.Party;
import com.cdpt.pokemon.battle.Battle.STATE;
import com.cdpt.pokemon.battle.animation.BattleAnimation;
import com.cdpt.pokemon.battle.event.BattleEvent;
import com.cdpt.pokemon.battle.event.BattleEventPlayer;
import com.cdpt.pokemon.battle.event.EventQueue;
import com.cdpt.pokemon.controller.BattleScreenController;
import com.cdpt.pokemon.screen.renderer.BattleDebugRenderer;
import com.cdpt.pokemon.screen.renderer.BattleRenderer;
import com.cdpt.pokemon.screen.renderer.EventQueueRenderer;
import com.cdpt.pokemon.ui.DetailedStatusBox;
import com.cdpt.pokemon.ui.DialogueBox;
import com.cdpt.pokemon.ui.MoveSelectBox;
import com.cdpt.pokemon.ui.OptionBox;
import com.cdpt.pokemon.ui.StatusBox;

import aurelienribon.tweenengine.TweenManager;

public class BattleScreen extends AbstractScreen implements BattleObserver, BattleEventPlayer {
	private BattleScreenController controller;
	private BattleEvent currentEvent;
	private EventQueue queue = new EventQueue();
	private Battle battle;
	private BATTLE_PARTY animationPrimary;
	private BattleAnimation battleAnimation = null;
	private Viewport gameViewport;
	private SpriteBatch batch;
	private BattleRenderer battleRenderer;
	private BattleDebugRenderer battleDebugRenderer;
	private EventQueueRenderer eventRenderer;
	private Stage uiStage;
	private Table dialogueRoot;
	private DialogueBox dialogueBox;
	private OptionBox optionBox;
	private Table moveSelectRoot;
	private MoveSelectBox moveSelectBox; 
	private Table statusBoxRoot;
	private DetailedStatusBox playerStatus;
	private StatusBox opponentStatus;
	private boolean uiDebug = false;
	private boolean battleDebug = false;

	public BattleScreen(PokemonGame app, Party party) {
		super(app);
		gameViewport = new ScreenViewport();
		batch = new SpriteBatch();

		battle = new Battle(party, WildPokemonGen.generatePokemon());
		battle.addObserver(this);
		
		animationPrimary = BATTLE_PARTY.PLAYER;
		
		battleRenderer = new BattleRenderer(app.getAssetManager());
		battleDebugRenderer = new BattleDebugRenderer(battleRenderer);
		eventRenderer = new EventQueueRenderer(app.getSkin(), queue);
		
		initUI();
		
		controller = new BattleScreenController(battle, queue, dialogueBox, moveSelectBox, optionBox);
		
		battle.beginBattle();
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}
	
	@Override
	public void update(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.F9)) {
			uiDebug = !uiDebug;
			uiStage.setDebugAll(uiDebug);
		}
		if (Gdx.input.isKeyJustPressed(Keys.F10)) {
			battleDebug = !battleDebug;
		}
		
		while (currentEvent == null || currentEvent.finished()) {
			if (queue.peek() == null) {
				currentEvent = null;
				
				if (battle.getState() == STATE.SELECTING) {
					if (controller.getState() != BattleScreenController.STATE.USE_NEXT_POKEMON) {
						controller.displayNextDialogue();
					}
				} else if (battle.getState() == STATE.READY) {
					controller.restartTurn();
				} else if (battle.getState() == STATE.WIN) {
					getApp().setScreen(getApp().getGameScreen());
				} else if (battle.getState() == STATE.LOSE) {
					getApp().setScreen(getApp().getGameScreen());
				} else if (battle.getState() == STATE.RAN) {
					getApp().setScreen(getApp().getGameScreen());
				}
				break;
			} else {
				currentEvent = queue.pop();
				currentEvent.begin(this);
			}
		}
		
		if (currentEvent != null) {
			currentEvent.update(delta);
		}
		
		controller.update(delta);
		uiStage.act();
	}

	@Override
	public void render(float delta) {
		gameViewport.apply();
		batch.begin();
		battleRenderer.render(batch, battleAnimation, animationPrimary);
		if (currentEvent != null && battleDebug) {
			eventRenderer.render(batch, currentEvent);
		}
		batch.end();
		
		if (battleDebug) {
			battleDebugRenderer.render();
		}
		
		uiStage.draw();
	}

	@Override
	public void render() {

	}

	@Override
	public void resize(int width, int height) {
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		uiStage.getViewport().update(
				(int)(Gdx.graphics.getWidth() / Settings.UI_SCALE), 
				(int)(Gdx.graphics.getHeight() / Settings.UI_SCALE),
				true);
		gameViewport.update(width, height);
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void create() {

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(controller);
	}
	
	private void initUI() {
		uiStage = new Stage(new ScreenViewport());
		uiStage.getViewport().update(
				(int)(Gdx.graphics.getWidth() / Settings.UI_SCALE), 
				(int)(Gdx.graphics.getHeight() / Settings.UI_SCALE),
				true);
		uiStage.setDebugAll(false);
		
		statusBoxRoot = new Table();
		statusBoxRoot.setFillParent(true);
		uiStage.addActor(statusBoxRoot);
		
		playerStatus = new DetailedStatusBox(getApp().getSkin());
		playerStatus.setText(battle.getPlayerPokemon().getName());
		
		opponentStatus = new StatusBox(getApp().getSkin());
		opponentStatus.setText(battle.getOpponentPokemon().getName());
		
		statusBoxRoot.add(playerStatus).expand().align(Align.left);
		statusBoxRoot.add(opponentStatus).expand().align(Align.right);
		
		moveSelectRoot = new Table();
		moveSelectRoot.setFillParent(true);
		uiStage.addActor(moveSelectRoot);
		
		moveSelectBox = new MoveSelectBox(getApp().getSkin());
		moveSelectBox.setVisible(false);
		
		moveSelectRoot.add(moveSelectBox).expand().align(Align.bottom);
		
		dialogueRoot = new Table();
		dialogueRoot.setFillParent(true);
		uiStage.addActor(dialogueRoot);
		
		optionBox = new OptionBox(getApp().getSkin());
		optionBox.setVisible(false);
		
		dialogueBox = new DialogueBox(getApp().getSkin());
		dialogueBox.setVisible(false);
		
		Table dialogTable = new Table();
		dialogTable.add(optionBox).expand().align(Align.right).space(8f).row();
		dialogTable.add(dialogueBox).expand().align(Align.bottom).space(8f);
		
		dialogueRoot.add(dialogTable).expand().align(Align.bottom);
	}
	
	public StatusBox getStatus(BATTLE_PARTY hpbar) {
		if (hpbar == BATTLE_PARTY.PLAYER) {
			return playerStatus;
		} else if (hpbar == BATTLE_PARTY.OPPONENT) {
			return opponentStatus;
		} else {
			return null;
		}
	}

	@Override
	public void queueEvent(BattleEvent event) {
		queue.addEvent(event);
	}

	@Override
	public DialogueBox getDialogueBox() {
		return dialogueBox;
	}
	
	@Override
	public BattleAnimation getBattleAnimation() {
		return battleAnimation;
	}
	
	@Override
	public TweenManager getTweenManager() {
		return getApp().getTweenManager();
	}

	@Override
	public void playBattleAnimation(BattleAnimation animation, BATTLE_PARTY party) {
		this.animationPrimary = party;
		this.battleAnimation = animation;
		animation.initialize(getApp().getAssetManager(), getApp().getTweenManager());
	}

	@Override
	public StatusBox getStatusBox(BATTLE_PARTY party) {
		if (party == BATTLE_PARTY.PLAYER) {
			return playerStatus;
		} else if (party == BATTLE_PARTY.OPPONENT) {
			return opponentStatus;
		} else {
			return null;
		}
	}

	@Override
	public void setPokemonSprite(Texture region, BATTLE_PARTY party) {
		battleRenderer.setPokemonSprite(region, party);
	}
}
