package com.thompson.spectrumshooter.sound;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
/**
 * AudioManager.java
 *
 * This manages all the playing of sounds in the entire game.
 * As the WorldRenderer handles dealing with graphics, the AudioManager handles
 * the playing of sound files when needed.
 *
 * @author Abraham Loscher
 */
public class AudioManager {

	public static final AudioManager instance = new AudioManager(); //Instance of our AudioManager

	/**
	 * Constructor for AudioManager
	 */
	private AudioManager(){

	}


	public void fade(Music music){
		float i = music.getVolume();
		i = i -.01f;
		music.setVolume(i);
		System.out.println(i + "");

	}


	/**
	 * Plays the sound that is input as a parameter
	 * @param sound		short audio file you would like to play
	 */
	public void play(Sound sound){
		sound.play();
	}

	/**
	 * Play the given music
	 * @param music		the given mousic
	 */
	public void play(Music music){
		music.setLooping(true);
		music.setVolume(1.0f);
		music.play();
	}

	public void stop(Music music){
		music.stop();
	}

}
