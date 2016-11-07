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


	/**
	 * Allows us to fade the sound of music
	 * @param music
	 */
	public void fade(Music music){
		float i = music.getVolume();
		i = i -.01f;
		music.setVolume(i);
		System.out.println(i + "");

	}

	//Begin Sound play functions
	/**
	 * Plays the sound that is input as a parameter - series of functions
	 * @param sound		short audio file you would like to play
	 */
	public void play(Sound sound){
		play(sound, 1.0f);
	}

	public void play(Sound sound, float volume){
		sound.play(volume);
	}
	//End Sound play functions


	//Begin Music play functions
	/**
	 * Play the given music - series of functions
	 * @param music		the given mousic
	 */
	public void play(Music music){
		play(music, 1.0f);
	}

	public void play(Music music, float volume){
		music.setVolume(volume);
		music.setLooping(true);
		music.play();
	}
	//End Music play functions


	public void stop(Music music){
		music.stop();
	}

	public void pause(Music music){
		music.pause();
	}


}
