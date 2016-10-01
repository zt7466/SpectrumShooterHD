package com.thompson.spectrumshooter.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import com.thompson.spectrumshooter.util.Constants;
/**
 * Assets.java
 * 
 * Class for loading and managing access to Assets.
 * 
 * @author Abraham Loscher
 */
public class Assets implements Disposable, AssetErrorListener {

	public static final String TAG = Assets.class.getName();

	public static final Assets instance = new Assets(); //Assets instance *Singleton*

	private AssetManager assetManager;

	public Sound enemyDeathSound;
	public Music gameMusic;


	private Assets(){
		init();
	}

	/**
	 * Initialize a new instance of the Assets class.
	 */
	public void init(){
		assetManager = new AssetManager();
		assetManager.setErrorListener(this);
		String s = Constants.SOUND_PATH + "/bubbles.wav";
		String m = Constants.MUSIC_PATH + "/evan_music1.mp3";
		assetManager.load(s, Sound.class);
		assetManager.load(m, Music.class);
		assetManager.finishLoading();
		enemyDeathSound = assetManager.get(s, Sound.class);
		gameMusic = assetManager.get(m, Music.class);
	}

	/**
	 * There an error when the AssetManager encounters an issue.
	 */
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.debug(TAG, "File load error");
	}

	/**
	 * Dispose of the resources used in this Asset.
	 */
	@Override
	public void dispose() {
		assetManager.dispose();
	}
}
