package com.thompson.spectrumshooter.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Enemy objects.
 *
 * @author Christopher Boyer
 */
public class Enemy extends Sprite
{

	private int colorCode;	// The color code of this Enemy

	/**
	 * Create a new Enemy with the given texture and the given color code.
	 * @param texture		the texture of this Enemy
	 * @param colorCode		the color code of this Enemy
	 */
	public Enemy(Texture texture, int colorCode)
	{
		super(texture);
		this.colorCode = colorCode;
	}

	/**
	 * Get the color code of this Enemy
	 * @return color code of this Enemy
	 */
	public int getColorCode()
	{
		return this.colorCode;
	}
}
