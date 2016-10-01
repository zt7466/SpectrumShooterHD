package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * GameObject.java
 * 
 * Shared information management between all objects existing in the game.
 * 
 * @author Christopher Boyer
 */
public abstract class GameObject extends Sprite
{
	protected Color color;
	protected Fixture fixture;
	public boolean isAlive;
	protected float health;
	protected float spriteSize;
	protected float damage;
	protected float maxHealth;

	public GameObject(Color color, float health, Fixture fixture, Texture texture, float spriteSize, float damage)
	{
		super(texture);
		this.color = color;
		this.health = health;
		this.fixture = fixture;
		this.spriteSize = spriteSize;
		this.setSize(spriteSize, spriteSize);
		this.damage = damage;
		this.maxHealth = health;
		isAlive = true;
	}

	/**
	 * Update this GameObject.
	 */
	abstract public void update();

	/**
	 * Take the given damage out of health.
	 * @param damage	the damage taken
	 */
	abstract public void takeHit(float damage);

	/**
	 * Get the fixture of this GameObject.
	 * @return fixture of this GameObject
	 */
	public Fixture getFixture()
	{
		return this.fixture;
	}
	
	/**
	 * Get the health of this GameObject.
	 * @return health of this GameObject
	 */
	public float getHealth()
	{
		return this.health;
	}

	/**
	 * Get the stored color of this GameObject.
	 */
	@Override
	public Color getColor()
	{
		return this.color;
	}

	/**
	 * Get the damage of this GameObject.
	 * @return damage of this GameObject
	 */
	public float getDamage()
	{
		return this.damage;
	}

	/**
	 * Get the max health of this GameObject.
	 * @return max health of this GameObject
	 */
	public float getMaxHealth()
	{
		return this.maxHealth;
	}

	/**
	 * Dispose the assets of this GameObject
	 */
	public void dispose()
	{
		this.getTexture().dispose();
	}
}
