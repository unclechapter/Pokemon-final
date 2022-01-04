package com.cdpt.pokemon.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class TextureAnimation {
	private Texture[] keyFrames;
	private float frameDuration;
	private float animationDuration;
	private int lastFrameNumber;
	private float lastStateTime;

	public TextureAnimation(float frameDuration, Array<? extends Texture> keyFrames) {
		this.frameDuration = frameDuration;
		this.animationDuration = keyFrames.size * frameDuration;
		this.keyFrames = new Texture[keyFrames.size];
		for (int i = 0, n = keyFrames.size; i < n; i++) {
			this.keyFrames[i] = keyFrames.get(i);
		}
	}

	public TextureAnimation(float frameDuration, Texture... keyFrames) {
		this.frameDuration = frameDuration;
		this.animationDuration = keyFrames.length * frameDuration;
		this.keyFrames = keyFrames;
	}

	public Texture getKeyFrame (float stateTime) {
		int frameNumber = getKeyFrameIndex(stateTime);
		return keyFrames[frameNumber];
	}

	public int getKeyFrameIndex (float stateTime) {
		if (keyFrames.length == 1) return 0;

		int frameNumber = (int)(stateTime / frameDuration);

		frameNumber = frameNumber % ((keyFrames.length * 2) - 2);
		if (frameNumber >= keyFrames.length) frameNumber = keyFrames.length - 2 - (frameNumber - keyFrames.length);

		lastFrameNumber = frameNumber;
		lastStateTime = stateTime;

		return frameNumber;
	}

	public Texture[] getKeyFrames () {
		return keyFrames;
	}

	public boolean isAnimationFinished (float stateTime) {
		int frameNumber = (int)(stateTime / frameDuration);
		return keyFrames.length - 1 < frameNumber;
	}

	public void setFrameDuration (float frameDuration) {
		this.frameDuration = frameDuration;
		this.animationDuration = keyFrames.length * frameDuration;
	}

	public float getFrameDuration () {
		return frameDuration;
	}

	public float getAnimationDuration () {
		return animationDuration;
	}
}
