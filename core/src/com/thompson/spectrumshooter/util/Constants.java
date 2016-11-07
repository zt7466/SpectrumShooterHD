package com.thompson.spectrumshooter.util;

/**
 * Constants that need to be a
 * @author Christopher Boyer
 */
public class Constants
{
	public static final String GAME_NAME = "Spectrum Shooter HD";
	public static final int GAME_WIDTH = 1280;
	public static final int GAME_HEIGHT = 720;

	public static final float VIEWPORT_WIDTH = 9.0f;
	public static final float VIEWPORT_HEIGHT = 9.0f;

	public static final float BOX2D_CONVERSION = 0.5f;

	// the distance in meters from the center that the Enemies spawn at.
	public static final float ENEMY_RADIUS = 3.5f;
	// the distance in meters from the center that the Enemies spawn at.
	public static final float PROJECTILE_REDIUS = 0.5f;


	//Assets path constants
	public static final String SOUND_PATH = "../core/assets/sound"; //Path to the sound folder
	public static final String MUSIC_PATH = "../core/assets/music"; //Path to the music folder
	public static final String ENEMY_DEATH_SOUND_PATH = "sound/enemy_death_sound.wav"; //Sound for dying enemies
	public static final String MAIN_MENU_MUSIC_PATH = "music/menu_music_001.ogg"; //Music for the main game menu
	public static final String MAIN_GAME_MUSIC_PATH = "music/game_music_001.mp3"; //Music for the main game screen
	//End Assets path constants

	public static float MAX_COMBINATION = 120; //Max total value a color selector can have



}
