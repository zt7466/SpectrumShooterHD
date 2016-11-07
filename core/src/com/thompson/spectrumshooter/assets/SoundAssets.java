package com.thompson.spectrumshooter.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.thompson.spectrumshooter.util.Constants;

public class SoundAssets {
	public final Sound enemyDeathSound; //Sound that plays on enemy death

	/**
	 * Constructs our SoundAssets class
	 */
	public SoundAssets(){
		enemyDeathSound = Gdx.audio.newSound(Gdx.files.internal(Constants.ENEMY_DEATH_SOUND_PATH));
	}
}
