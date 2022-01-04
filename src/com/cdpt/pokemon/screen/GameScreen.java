package com.cdpt.pokemon.screen;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cdpt.pokemon.PokemonGame;
import com.cdpt.pokemon.Settings;
import com.cdpt.pokemon.battle.Party;
import com.cdpt.pokemon.battle.pokemon.Pokemon;
import com.cdpt.pokemon.battle.pokemon.WildPokemonGen;
import com.cdpt.pokemon.controller.DialogueController;
import com.cdpt.pokemon.controller.PlayerController;
import com.cdpt.pokemon.dialogue.Dialogue;
import com.cdpt.pokemon.dialogue.DialogueNode;
import com.cdpt.pokemon.model.Actor;
import com.cdpt.pokemon.model.Camera;
import com.cdpt.pokemon.model.DIRECTION;
import com.cdpt.pokemon.model.Actor.ACTOR_STATE;
import com.cdpt.pokemon.model.world.World;
import com.cdpt.pokemon.model.world.script.WorldInterface;
import com.cdpt.pokemon.screen.renderer.WorldRenderer;
import com.cdpt.pokemon.ui.DialogueBox;
import com.cdpt.pokemon.ui.OptionBox;
import com.cdpt.pokemon.util.AnimationSet;
import com.cdpt.pokemon.util.MapUtil;

public class GameScreen extends AbstractScreen implements WorldInterface {
	private InputMultiplexer multiplexer;
	private DialogueController dialogueController;
	private PlayerController playerController;
	private HashMap<String, World> worlds = new HashMap<String, World>();
	private World world;
	private Actor player;
	private Camera camera;
	private MapUtil mapUtil;
	private SpriteBatch batch;
	private Viewport gameViewport;
	private WorldRenderer worldRenderer;
	private Stage uiStage;
	private Table root;
	private DialogueBox dialogueBox;
	private OptionBox optionsBox;
	private Dialogue dialogue;
	private PokemonGame app2;
	private Party party;

	public GameScreen(PokemonGame app) {
		super(app);
		this.app2 = app;
		gameViewport = new ScreenViewport();
		batch = new SpriteBatch();
		
		TextureAtlas atlas = app.getAssetManager().get("res/packed/tiles/tilepack.atlas", TextureAtlas.class);
		
		AnimationSet animations = new AnimationSet(
				new Animation(0.3f/2f, atlas.findRegions("brendan_walk_north"), PlayMode.LOOP_PINGPONG),
				new Animation(0.3f/2f, atlas.findRegions("brendan_walk_south"), PlayMode.LOOP_PINGPONG),
				new Animation(0.3f/2f, atlas.findRegions("brendan_walk_east"), PlayMode.LOOP_PINGPONG),
				new Animation(0.3f/2f, atlas.findRegions("brendan_walk_west"), PlayMode.LOOP_PINGPONG),
				atlas.findRegion("brendan_stand_north"),
				atlas.findRegion("brendan_stand_south"),
				atlas.findRegion("brendan_stand_east"),
				atlas.findRegion("brendan_stand_west")
		);
		
		mapUtil = new MapUtil(app.getAssetManager(), this, animations);
		worlds.put("test_level", mapUtil.loadWorld());
		
		world = worlds.get("test_level");
		
		camera = new Camera();
		player = new Actor(world, 48, 48, animations);
		world.addActor(player);
		
		initUI();
		
		multiplexer = new InputMultiplexer();
		
		playerController = new PlayerController(player);
		dialogueController = new DialogueController(dialogueBox, optionsBox);
		multiplexer.addProcessor(0, dialogueController);
		multiplexer.addProcessor(1, playerController);
		
		worldRenderer = new WorldRenderer(getApp().getAssetManager(), world);
		
		dialogue = new Dialogue();

		party = new Party();
		party.addPokemon(WildPokemonGen.generatePokemon());
		Pokemon p = party.getPokemon(0);

		DialogueNode n0 = new DialogueNode("Hello Adventurer!", 0);
		DialogueNode n1 = new DialogueNode("Welcome to our Pokemon!", 1);
		DialogueNode n2 = new DialogueNode("First of all, let's get a pokemon,\nshall we?", 2);
		DialogueNode n3 = new DialogueNode("Your first pokemon is " + p.getName() + ",\ngood luck and have fun!", 3);
		
		n0.makeLinear(n1.getID());
		n1.makeLinear(n2.getID());
		n2.makeLinear(n3.getID());
		
		dialogue.addNode(n0);
		dialogue.addNode(n1);
		dialogue.addNode(n2);
		dialogue.addNode(n3);
		
		dialogueController.startDialogue(dialogue);
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
		playerController.update(delta);
		dialogueController.update(delta);
		
		camera.update(player.getWorldX() + 0.5f, player.getWorldY() + 0.5f);
		world.update(delta);
		uiStage.act(delta);
		
		if (player.getState() == ACTOR_STATE.WALKING) {
			double c = Math.random();
			if (c > 0.99) {
				playerController.stop();
				getApp().setScreen(new BattleScreen(app2, party));
			}
		}
	}

	@Override
	public void render(float delta) {
		gameViewport.apply();
		batch.begin();
		worldRenderer.render(batch, camera);
		batch.end();
		
		uiStage.draw();
	}

	@Override
	public void render() {

	}

	@Override
	public void resize(int width, int height) {
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		uiStage.getViewport().update((int)(width / Settings.UI_SCALE), (int)(height / Settings.UI_SCALE), true);
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
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	private void initUI() {
		uiStage = new Stage(new ScreenViewport());
		uiStage.getViewport().update((int)(Gdx.graphics.getWidth() / Settings.UI_SCALE), (int)(Gdx.graphics.getHeight() / Settings.UI_SCALE), true);
		
		root = new Table();
		root.setFillParent(true);
		uiStage.addActor(root);
		
		dialogueBox = new DialogueBox(getApp().getSkin());
		
		optionsBox = new OptionBox(getApp().getSkin());
		optionsBox.setVisible(false);
		
		Table dialogTable = new Table();
		dialogTable.add(optionsBox).expand().align(Align.right).space(8f).row();
		dialogTable.add(dialogueBox).expand().align(Align.bottom).space(8f).row();
		
		root.add(dialogTable).expand().align(Align.bottom);
	}
	
	public void changeWorld(World newWorld, int x, int y, DIRECTION face) {
		player.changeWorld(newWorld);
		this.world = newWorld;
		player.teleport(x, y);
		player.refaceWithoutAnimation(face);
		this.worldRenderer.setWorld(newWorld);
		this.camera.update(player.getWorldX() + 0.5f, player.getWorldY() + 0.5f);
	}

	@Override
	public void changeLocation(World newWorld, int x, int y, DIRECTION facing, Color color) {

	}

	@Override
	public World getWorld(String worldName) {
		return worlds.get(worldName);
	}
}
