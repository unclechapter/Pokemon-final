package com.cdpt.pokemon.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cdpt.pokemon.PokemonGame;

public class StartScreen extends AbstractScreen implements Screen, InputProcessor {

    private Stage stage;
    private Skin skin;
    private Table table;
    private TextButton startButton;
    private TextButton quitButton;
    private SpriteBatch batch;
    private Sprite sprite;

    public StartScreen(PokemonGame app, GameScreen gs){
        super(app);
        skin = new Skin(Gdx.files.internal("res/ui/uiskin.json"));
        stage = new Stage(new ScreenViewport());
        table = new Table();
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("res/ui/pkmblack.png")));
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.center);

        table.setPosition(0,Gdx.graphics.getHeight()/2);

        startButton = new TextButton("New Game",skin,"default");
        quitButton = new TextButton("Quit",skin,"default");
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getApp().setScreen(gs);
            }
        });
        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.padLeft(30);
        table.add(startButton).padBottom(20);
        table.row();
        table.add(quitButton);
        stage.addActor(table);

        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void create() {

    }


    @Override
    public void show() {
        InputMultiplexer im = new InputMultiplexer(stage,this);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
    @Override
    public void render() {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}