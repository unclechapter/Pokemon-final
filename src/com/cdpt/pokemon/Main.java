package com.cdpt.pokemon;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Pokemon by CDPT";
		config.height = 720;
		config.width = 1280;
		
		new LwjglApplication(new PokemonGame(), config);
	}

}
