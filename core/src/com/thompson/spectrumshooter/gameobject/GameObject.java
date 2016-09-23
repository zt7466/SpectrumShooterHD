package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Fixture;

public abstract class GameObject extends Sprite
{

	protected int colorCode;		// the color code of the object
	protected Fixture fixture;	// the physical body of the object
	public boolean isAlive;		// deiscerns if the object is still drawn
	protected float spriteSize;

	public GameObject(int colorCode, Fixture fixture, Texture texture, float spriteSize)
	{
		super(texture);
		this.setSize(spriteSize, spriteSize);
		this.colorCode = colorCode;
		this.fixture = fixture;
		this.spriteSize = spriteSize;
		isAlive = true;
		this.update();
	}

	/**
	 * Update this GameObject.
	 */
	abstract public void update();

	/**
	 * Get the color code of this Enemy
	 * @return color code of this Enemy
	 */
	public int getColorCode()
	{
		return this.colorCode;
	}

	/**
	 * Get the fixture of this GameObject
	 * @return
	 */
	public Fixture getFixture()
	{
		return this.fixture;
	}

	/**
	 * Dispose the assets of this GameObject
	 */
	public void dispose()
	{
		this.getTexture().dispose();
	}
}
