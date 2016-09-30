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
 * Assets class, where we load the assets
 * @author Abraham Loscher
 *
 */
public class Assets implements Disposable, AssetErrorListener {

	public static final String TAG = Assets.class.getName();

	public static final Assets instance = new Assets(); //Assets instance *Singleton*

	private AssetManager assetManager;

	public Sound enemyDeathSound;
	public Music gameMusic;


	/**
	 * Constructor for Assets
	 */
	private Assets(){
		init();
	}

	/**
	 * Initializes the Assets class
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
	 * This throws an error if it finds one
	 */
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.debug(TAG, "File load error");
	}

	/**
	 * This will free the specified resources
	 */
	@Override
	public void dispose() {
		assetManager.dispose();
	}
}
