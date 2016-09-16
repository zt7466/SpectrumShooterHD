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

	private static final AudioManager audioManager = new AudioManager(); //Instance of our AudioManager

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

}