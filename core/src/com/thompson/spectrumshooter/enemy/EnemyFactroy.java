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
		int colorCode = colorWheel.random();
		Pixmap pixmap = new Pixmap(300, 300, Format.RGBA8888);
		pixmap.setColor(colorWheel.getRedValue(colorCode),
						colorWheel.getGreenValue(colorCode),
						colorWheel.getBlueValue(colorCode),
						1);
		pixmap.fillCircle(150, 150, 150);
		Texture texture = new Texture(pixmap);

		Enemy enemy = new Enemy(colorCode, texture);
		enemy.setOrigin(enemy.getWidth() / 2.0f, enemy.getHeight() / 2.0f);
		float spriteSize = MathUtils.random(0.25f, 0.5f);
		enemy.setSize(spriteSize, spriteSize);
		enemy.setPosition(MathUtils.random(-2.0f, 2.0f),
						  MathUtils.random(-2.0f, 2.0f));

		return enemy;
	}
}
