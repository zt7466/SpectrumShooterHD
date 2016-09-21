package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Enemy objects.
 *
 * @author Christopher Boyer
 */
public class Enemy extends GameObject
{

	/**
	 * Create a new Enemy with the given texture and the given color code.
	 * @param texture		the texture of this Enemy
	 * @param fixture		the fixture corresponding to this Enemy
	 * @param colorCode		the color code of this Enemy
	 */
	public Enemy(int colorCode, Fixture fixture, Texture texture, float spriteSize)
	{
		super(colorCode, fixture, texture, spriteSize);
	}

	/**
	 * Update the location of this sprite to represent the fixture of this
	 * GameObject
	 */
	@Override
	public void update()
	{
		this.setPosition(fixture.getBody().getPosition().x - spriteSize/2.0f,
		  		  		 fixture.getBody().getPosition().y - spriteSize/2.0f);
		if (inCenter(this.getX(), this.getY()))
		{
			this.isAlive = false;
		}
	}

	private boolean inCenter(float x, float y)
	{
		return x > -0.1f && x < 0.1f && y >-0.1f && y < 0.1f;
	}
}
