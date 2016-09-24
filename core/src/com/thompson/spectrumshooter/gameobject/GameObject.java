package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Fixture;

public abstract class GameObject extends Sprite
{

	protected int colorCode;
	protected Fixture fixture;
	public boolean isAlive;
	protected int health;
	protected float spriteSize;

	public GameObject(int colorCode, int health, Fixture fixture, Texture texture, float spriteSize)
	{
		super(texture);
		this.colorCode = colorCode;
		this.health = health;
		this.fixture = fixture;
		this.spriteSize = spriteSize;
		this.setSize(spriteSize, spriteSize);
		isAlive = true;

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
	
	public int getHealth()
	{
		return this.health;
	}

	/**
	 * Dispose the assets of this GameObject
	 */
	public void dispose()
	{
		this.getTexture().dispose();
	}
}
