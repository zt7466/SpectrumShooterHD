package com.thompson.spectrumshooter.enemy;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Texture;
import com.thompson.spectrumshooter.color.ColorWheel;


/**
 * A factory that allows for the creation of enemies.
 *
 * @author cb9619
 */
public class EnemyFactroy
{

	private ColorWheel colorWheel;

	public EnemyFactroy()
	{
		this.colorWheel = new ColorWheel();
	}

	public Enemy makeBasicEnemy()
	{
		int width = 42;
		int height = 42;

//		Pixmap pixmap = new Pixmap(width, height, Format.RGB888);
//
//		//Fill square with red color at 50% opacity
//		pixmap.setColor(1, 0, 0, 0.5f);
//		pixmap.fill();
//
//		Texture texture = new Texture(pixmap);
//
//		Enemy enemy = new Enemy(0, texture);
//
//		// Define sprite size to be 1m x 1m in the game world
//		enemy.setSize(1, 1);
//
//		// set origin to spirtes center
//		enemy.setOrigin(enemy.getWidth() / 2.0f, enemy.getHeight() / 2.0f);
//
//		// Calculate random position for sprite
//		float randomX = MathUtils.random(-2.0f, 2.0f);
//		float randomY = MathUtils.random(-2.0f, 2.0f);
//		enemy.setPosition(randomX, randomY);
//
//		return enemy;


		int colorCode = colorWheel.random();
		Pixmap pixmap = new Pixmap(300, 300, Format.RGBA8888);
		pixmap.setColor(colorWheel.getRedValue(colorCode),
						colorWheel.getGreenValue(colorCode),
						colorWheel.getBlueValue(colorCode),
						1);
		pixmap.fillCircle(pixmap.getWidth()/2, pixmap.getHeight()/2, 250);
		Texture texture = new Texture(pixmap);

		Enemy enemy = new Enemy(colorCode, texture);
		enemy.setOrigin(enemy.getWidth() / 2.0f, enemy.getHeight() / 2.0f);
		enemy.setSize(1, 1);
		enemy.setPosition(MathUtils.random(-2.0f, 2.0f),
						  MathUtils.random(-2.0f, 2.0f));

		return enemy;
	}
}