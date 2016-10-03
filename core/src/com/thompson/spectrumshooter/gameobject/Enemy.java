package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Enemy.java
 *
 * Manages and Enemy enity, include ensureing that the main sprite's position matches
 * that of it's Fixture's position and ensuring that the inner health Sprite is in
 * the correct position.
 *
 * @author Christopher Boyer
 */
public class Enemy extends GameObject
{
	private static final float KNOCKBACK = 10f;

	private Vector2 linearVelocity;
	private Sprite centerSprite;

	private static final float INNER_MULTIPLIER = 0.67f;

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

		// Create the inner sprite of this Enemy
		Pixmap pixmap = new Pixmap(300, 300, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fillCircle(150, 150, 150);
		centerSprite = new Sprite(new Texture(pixmap));
		// Set as white so it's tint can be it's true color
		centerSprite.setColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, 0);
		centerSprite.setSize(spriteSize * INNER_MULTIPLIER, spriteSize * INNER_MULTIPLIER);

		// Set the initial position of the Sprite
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
		// set the position of the main sprite
		this.setPosition(fixture.getBody().getPosition().x - spriteSize/2.0f,
				 fixture.getBody().getPosition().y - spriteSize/2.0f);
		// set the position of the damage sprite
		centerSprite.setPosition(fixture.getBody().getPosition().x - (spriteSize * INNER_MULTIPLIER)/2.0f,
				 fixture.getBody().getPosition().y - (spriteSize * INNER_MULTIPLIER)/2.0f);
		centerSprite.setColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, (1-(health/maxHealth)));
	}

	/**
	 * Get the center Sprite representing damage.
	 * @return center Sprite representing damage
	 */
	public Sprite getCenterSprite()
	{
		return centerSprite;
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
