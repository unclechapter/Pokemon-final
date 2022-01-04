package com.cdpt.pokemon.screen.renderer;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.cdpt.pokemon.battle.event.BattleEvent;
import com.cdpt.pokemon.battle.event.EventQueue;

public class EventQueueRenderer {
	private float boxHeight = 30;
	private NinePatch patch;
	private BitmapFont font;
	private GlyphLayout layout;
	private EventQueue eventQueue;
	
	public EventQueueRenderer(Skin skin, EventQueue eventQueue) {
		this.eventQueue = eventQueue;
		patch = skin.getPatch("optionbox");
		font = skin.getFont("font");
		layout = new GlyphLayout();
	}
	
	public void render(SpriteBatch batch, BattleEvent event) {
		renderEvent(batch, event.getClass().getSimpleName(), 0);
		
		float y = boxHeight + 10f;
		for (BattleEvent e : eventQueue.getEvents()) {
			renderEvent(batch, e.getClass().getSimpleName(), y);
			y += boxHeight;
		}
	}
	
	private void renderEvent(SpriteBatch batch, String text, float y) {
		layout.setText(font, text);
		float textWidth = layout.width;
		float textHeight = layout.height;
		
		patch.draw(batch, 0, y, textWidth + 10, boxHeight);
		font.draw(batch, text, (textWidth + 10) / 2 - textWidth / 2, boxHeight / 2 + textHeight / 2 + y);
	}
}
