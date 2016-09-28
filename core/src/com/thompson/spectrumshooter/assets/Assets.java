package com.thompson.spectrumshooter.assets;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.thompson.spectrumshooter.util.Constants;

public class Assets implements Disposable, AssetErrorListener {

	public static final String TAG = Assets.class.getName();

	public static final Assets instance = new Assets(); //Assets instance *Singleton*

	private AssetManager assetManager;

	public Sound randomSound;


	/**
	 * Constructor for Assets
	 */
	private Assets(){

	}

	/**
	 * Initializes the Assets class
	 */
	public void init(){
		assetManager = new AssetManager();
		assetManager.setErrorListener(this);
		String s = Constants.SOUND_PATH + "/ping.wav";
		assetManager.load(s, Sound.class);
		assetManager.finishLoading();
		randomSound = assetManager.get(s, Sound.class);
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
