package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * A hero is the GameObject in the center of the screen.
 * @author Christopher Boyer
 */
public class Hero extends GameObject
{

	public Hero(int colorCode, Fixture fixture, Texture texture)
	{
		super(colorCode, fixture, texture);
	}

	/**
	 * Change color based on the background of the game.
	 */
	@Override
	public void update()
	{
		// TODO do this shit
	}

}
