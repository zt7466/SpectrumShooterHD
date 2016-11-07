package com.thompson.spectrumshooter.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.thompson.spectrumshooter.util.Constants;
public class MusicAssets {
	public final Music gameMusic; //Music for our game's music
	public final Music mainMenuMusic; //Music for our game's menu

	/**
	 * Constructs the MusicAssets class
	 */
	public MusicAssets(){
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.MAIN_GAME_MUSIC_PATH));
		mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.MAIN_MENU_MUSIC_PATH));
	}
}
