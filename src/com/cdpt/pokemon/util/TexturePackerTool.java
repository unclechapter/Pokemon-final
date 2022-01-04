package com.cdpt.pokemon.util;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerTool {
	
	public static void main(String[] args) {
		TexturePacker.process("res/unpacked/ui/","res/packed/ui/","uipack");
		TexturePacker.process("res/unpacked/tiles/","res/packed/tiles/","tilepack");
		TexturePacker.process("res/unpacked/battle/","res/packed/battle/","battlepack");
		TexturePacker.process("res/unpacked/player/Green/","res/packed/player/Green/","dawn");
	}
}
