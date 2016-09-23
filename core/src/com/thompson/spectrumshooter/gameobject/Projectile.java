package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Projectile extends GameObject
{

	public Projectile(int colorCode,int health, Fixture fixture, Texture texture, float spriteSize)
	{
		super(colorCode, health, fixture, texture, spriteSize);
		fixture.setUserData(this);
	}

	@Override
	public void update() {
		this.setPosition(fixture.getBody().getPosition().x - spriteSize/2.0f,
 		  		 		 fixture.getBody().getPosition().y - spriteSize/2.0f);
	}

}
