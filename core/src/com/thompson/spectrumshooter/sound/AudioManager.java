package com.thompson.spectrumshooter.sound;

import com.badlogic.gdx.audio.Sound;
/**
 * This manages all the playing of sounds in the entire game.
 * As the WorldRenderer handles dealing with graphics, the AudioManager handles
 * the playing of sound files when needed.
 * @author abeloscher
 *
 */
public class AudioManager {

	private static AudioManager audioManager; //Current instance of our AudioManager

	/**
	 * Constructor for AudioManager
	 */
	private AudioManager(){

	}

	/**
	 * Plays the sound that is input as a parameter
	 * @param sound - short audio file you would like to play
	 */
	public void play(Sound sound){
		sound.play();
	}

	/**
	 * Gets the current instance of the AudioManager, because AudioManager is a singleton,
	 * we do not want to allow a second instance
	 */
	public void getAudioManager(){
		if(audioManager != null){

		}else{
			audioManager = new AudioManager();
		}
	}

}
