package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * A hero is the GameObject in the center of the screen.
 * @author Christopher Boyer
 */
public class Hero extends GameObject
{

	public Hero(int colorCode, int health, Fixture fixture, Texture texture, float spriteSize)
	{
		super(colorCode, health, fixture, texture, spriteSize);
		Gdx.app.debug("HERO", "Color Code: " + this.colorCode);
		// this is done inside of this class so that it saves as a Hero.class
		fixture.setUserData(this);
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
