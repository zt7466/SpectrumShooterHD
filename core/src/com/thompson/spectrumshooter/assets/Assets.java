package com.thompson.spectrumshooter.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import com.thompson.spectrumshooter.SpectrumShooter;
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

	public MusicAssets music; //Holds our game's music
	public SoundAssets sound; //Holds our game's sounds


	/**
	 * Constructor for Assets
	 */
	private Assets(){
		init();
	}

	/**
	 * Initialize a new instance of the Assets class.
	 */
	public void init(){
		music = new MusicAssets();
		sound = new SoundAssets();
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
	}
}
