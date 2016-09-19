package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Enemy objects.
 *
 * @author Christopher Boyer
 */
public class Enemy extends Sprite
{

	private int colorCode;	// The color code of this Enemy
	private Fixture fixture;
	public boolean isAlive;

	/**
	 * Create a new Enemy with the given texture and the given color code.
	 * @param texture		the texture of this Enemy
	 * @param fixture		the fixture corresponding to this Enemy
	 * @param colorCode		the color code of this Enemy
	 */
	public Enemy(int colorCode, Fixture fixture, Texture texture)
	{
		super(texture);
		this.fixture = fixture;
		this.colorCode = colorCode;
		this.isAlive = true;
	}

	public void update()
	{
		this.setPosition(fixture.getBody().getPosition().x,
						 fixture.getBody().getPosition().y);
		if (inCenter(this.getX(), this.getY()))
		{
			isAlive = false;
		}
	}

	public void dispose()
	{
		this.getTexture().dispose();
	}

	/**
	 * Get the color code of this Enemy
	 * @return color code of this Enemy
	 */
	public int getColorCode()
	{
		return this.colorCode;
	}

	public Fixture getFixture()
	{
		return this.fixture;
	}
	
	private boolean inCenter(float x, float y)
	{
		return x > -0.1f && x < 0.1f && y >-0.1f && y < 0.1f;
	}
}
