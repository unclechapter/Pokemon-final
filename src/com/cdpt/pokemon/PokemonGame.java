package com.cdpt.pokemon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.cdpt.pokemon.battle.animation.AnimatedBattleSprite;
import com.cdpt.pokemon.battle.animation.BattleAnimation;
import com.cdpt.pokemon.battle.animation.BattleAnimationAccessor;
import com.cdpt.pokemon.battle.animation.BattleSprite;
import com.cdpt.pokemon.battle.animation.BattleSpriteAccessor;
import com.cdpt.pokemon.battle.moves.MoveDatabase;
import com.cdpt.pokemon.screen.AbstractScreen;
import com.cdpt.pokemon.screen.BattleScreen;
import com.cdpt.pokemon.screen.GameScreen;
import com.cdpt.pokemon.screen.StartScreen;
import com.cdpt.pokemon.util.SkinGenerator;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class PokemonGame extends Game implements Screen {
	private StartScreen startScreen;
	private GameScreen gameScreen;
	private BattleScreen battleScreen;
	private MoveDatabase moveDatabase;
	private AssetManager assetManager;
	private Skin skin;
	private TweenManager tweenManager;
	private ShaderProgram overlayShader;
	private ShaderProgram transitionShader;

	@Override
	public void create() {		
		tweenManager = new TweenManager();
		Tween.registerAccessor(BattleAnimation.class, new BattleAnimationAccessor());
		Tween.registerAccessor(BattleSprite.class, new BattleSpriteAccessor());
		Tween.registerAccessor(AnimatedBattleSprite.class, new BattleSpriteAccessor());
		
		assetManager = new AssetManager();
		assetManager.load("res/packed/tiles/tilepack.atlas", TextureAtlas.class);
		assetManager.load("res/packed/ui/uipack.atlas", TextureAtlas.class);
		assetManager.load("res/packed/battle/battlepack.atlas", TextureAtlas.class);
		assetManager.load("res/unpacked/terrain/grass1.png", Texture.class);
		assetManager.load("res/unpacked/terrain/grass2.png", Texture.class);
		assetManager.load("res/unpacked/graphics/statuseffect/white.png", Texture.class);
		assetManager.load("res/unpacked/font/small_letters_font.fnt", BitmapFont.class);
		for (int i = 0; i < 32; i++) {
			assetManager.load("res/unpacked/graphics/statuseffect/attack_"+i+".png", Texture.class);
		}
		assetManager.finishLoading();
		
		skin = SkinGenerator.generateSkin(assetManager);
		moveDatabase = new MoveDatabase();

		gameScreen = new GameScreen(this);
		startScreen = new StartScreen(this, gameScreen);
		this.setScreen(startScreen);
	}
	
	@Override
	public void render() {
		tweenManager.update(Gdx.graphics.getDeltaTime());
		if (getScreen() instanceof AbstractScreen) {
			((AbstractScreen)getScreen()).update(Gdx.graphics.getDeltaTime());
		}
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getScreen().render(Gdx.graphics.getDeltaTime());
	}
	
	public AssetManager getAssetManager() {
		return assetManager;
	}
	
	public Skin getSkin() {
		return skin;
	}
	
	public TweenManager getTweenManager() {
		return tweenManager;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public BattleScreen getBattleScreen() {
		return battleScreen;
	}
	
	public MoveDatabase getMoveDatabase() {
		return moveDatabase;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void hide() {

	}
}
