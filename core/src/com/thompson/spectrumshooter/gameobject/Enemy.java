package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Enemy objects.
 *
 * @author Christopher Boyer
 */
public class Enemy extends GameObject
{

	private static final float KNOCKBACK = 17f;
	
	private Vector2 linearVelocity;
	
	/**
	 * Create a new Enemy with the given texture and the given color code.
	 * @param texture		the texture of this Enemy
	 * @param fixture		the fixture corresponding to this Enemy
	 * @param colorCode		the color code of this Enemy
	 */
	public Enemy(Color color, float health, Fixture fixture, Texture texture, float spriteSize, float damage)
	{
		super(color, health, fixture, texture, spriteSize, damage);
		// this is done inside of this class so that it saves as a Enemy.class
		fixture.setUserData(this);
		this.linearVelocity = fixture.getBody().getLinearVelocity();
		// set the initial position of the Sprite
		this.update();
	}

	/**
	 * Update the location of this sprite to represent the fixture of this
	 * GameObject
	 */
	@Override
	public void update()
	{
		// when the block gets hit it starts to move backwards, doing this reverses that
		fixture.getBody().setLinearVelocity(linearVelocity);
		this.setPosition(fixture.getBody().getPosition().x - spriteSize/2.0f,
		  		  		 fixture.getBody().getPosition().y - spriteSize/2.0f);
	}
	
	/**
	 * Take a hit. If this hit causes the Enemy to run out of health then isAlive is set to
	 * false. Additionally, the linear velocity is inverted and increases to move the Enemy
	 * back upon hit.
	 */
	public void takeHit(float damage)
	{
		health = health - damage;
		if (health <= 0)
		{
			isAlive = false;
		}
		fixture.getBody().setLinearVelocity(-linearVelocity.x * KNOCKBACK, -linearVelocity.y * KNOCKBACK);
	}
}
