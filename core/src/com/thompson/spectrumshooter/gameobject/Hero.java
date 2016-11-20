package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Hero.java
 * 
 * A hero is the GameObject in the center of the screen.
 * 
 * @author Christopher Boyer
 */
public class Hero extends GameObject
{

	public Hero(Color color, float health, Fixture fixture, Texture texture, float spriteSize, float damage)
	{
		super(color, health, fixture, texture, spriteSize, damage);
		// this is done inside of this class so that it saves as a Hero.class
		fixture.setUserData(this);
		Texture heroTexture = new Texture(Gdx.files.local("Hero.png"));
		this.setSize(spriteSize * 1.05f, spriteSize * 1.05f);
		this.setTexture(heroTexture);
	}

	/**
	 * Update the status of the Hero GameObject.
	 */
	@Override
	public void update() 
	{
		
	}

	/**
	 * Take the damage out of the Hero's health. If it's health goes below zero than the hero is
	 * no longer alive.
	 */
	@Override
	public void takeHit(float damage)
	{
		health = health - damage;
		if (health <= 0)
		{
			isAlive = false;
		}
		
		Gdx.app.debug("HERO", "" + health);
	}

	/**
	 * Changes the direction of the hero's sprite to an inputed direction
	 */
	public void updateDirection(float direction) 
	{	
		this.setRotation(direction - 90);
	}
	
	
}
