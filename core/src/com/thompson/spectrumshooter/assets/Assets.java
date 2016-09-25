package com.thompson.spectrumshooter.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import com.thompson.spectrumshooter.util.Constants;

public class Assets implements Disposable, AssetErrorListener {

	public static final String TAG = Assets.class.getName();

	public static final Assets instance = new Assets();

	private AssetManager assetManager;

	private Assets(){

	}

	public void init(){
		this.assetManager = new AssetManager();
		assetManager.load(Constants.SOUND_PATH, Sound.class);
		assetManager.finishLoading();


	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.debug(TAG, "File load error");
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}

}
