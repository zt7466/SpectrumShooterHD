package com.thompson.spectrumshooter.overlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * RGBBarSelector.java
 * standard color selector
 * @author Zach
 *
 */
public class RGBBarSelector extends ColorSelector 
{
	protected ProgressBar redBar;
	protected ProgressBar greenBar;
	protected ProgressBar blueBar;
	
	private final int MAX_COMBINATION = 120;
	
	public RGBBarSelector(Table table, Color startColor) 
	{
		super(table, 100, 500);
		
		redBar = new ProgressBar(0,1,.01f,true,createBarStyle(new Color(1,0,0,1)));
		greenBar = new ProgressBar(0,1,.01f,true,createBarStyle(new Color(0,1,0,1)));
		blueBar = new ProgressBar(0,1,.01f,true,createBarStyle(new Color(0,0,1,1)));
		
		redBar.setValue(startColor.r);
		greenBar.setValue(startColor.g);
		blueBar.setValue(startColor.b);
		
		inerdTable.add(redBar).padRight(5).padLeft(5).minHeight(450);
		inerdTable.add(greenBar).padRight(5).padLeft(5).minHeight(450);
		inerdTable.add(blueBar).padRight(5).padLeft(5).minHeight(450).row();
	}

	private ProgressBarStyle createBarStyle(Color color) {
		Texture backgroundTexture = new Texture(Gdx.files.local("colorBar.png"));
		Texture progressFill = new Texture(Gdx.files.local("colorBar.png"));
		
		Sprite backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(20, 300);
		SpriteDrawable background = new SpriteDrawable(backgroundSprite);
		
		Sprite fillSprite = new Sprite(progressFill);
		fillSprite.setColor(color);
		fillSprite.setSize(20, 20);
		SpriteDrawable fill = new SpriteDrawable(fillSprite);
		
		ProgressBarStyle style = new ProgressBarStyle(background, fill);
		style.knobBefore = fill;
		
		return style;
	}
	
	@Override
	public Color selectColor() 
	{		
		return new Color(redBar.getValue(), greenBar.getValue(), blueBar.getValue(), 1);
	}

	@Override
	public void changeColor() 
	{
		boolean aPressed = Gdx.input.isKeyPressed(Input.Keys.A);
		boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);
		boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.D);

		if (aPressed || sPressed || dPressed) {
			int count = 0;
			if (aPressed)
				count++;
			if (sPressed)
				count++;
			if (dPressed)
				count++;
			double maxValue = MAX_COMBINATION / count;

			int red = (int) (redBar.getValue() * 100);
			int green = (int) (greenBar.getValue() * 100);
			int blue = (int) (blueBar.getValue() * 100);

			if (aPressed && red < maxValue) {
				red = red + 2;
				if (red > maxValue)
					red = (int) maxValue;

				boolean gDrainable = (sPressed && green > maxValue) || (!sPressed && green > 0);
				boolean bDrainable = (dPressed && blue > maxValue) || (!dPressed && blue > 0);

				if (gDrainable && bDrainable) {
					green--;
					blue--;
				} else if (gDrainable) {
					green = green - 2;
				} else if (bDrainable) {
					blue = blue - 2;
				}
			}
			if (sPressed && green < maxValue) {
				green = green + 2;
				if (green > maxValue)
					green = (int) maxValue;

				boolean rDrainable = (aPressed && red > maxValue) || (!aPressed && red > 0);
				boolean bDrainable = (dPressed && blue > maxValue) || (!dPressed && blue > 0);

				if (rDrainable && bDrainable) {
					red--;
					blue--;
				} else if (rDrainable) {
					red = red - 2;
				} else if (bDrainable) {
					blue = blue - 2;
				}
			}
			if (dPressed && blue < maxValue) {
				blue = blue + 2;
				if (blue > maxValue)
					blue = (int) maxValue;

				boolean rDrainable = (aPressed && red > maxValue) || (!aPressed && red > 0);
				boolean gDrainable = (sPressed && green > maxValue) || (!sPressed && green > 0);

				if (rDrainable && gDrainable) {
					red--;
					green--;
				} else if (rDrainable) {
					red = red - 2;
				} else if (gDrainable) {
					green = green - 2;
				}
			}
			redBar.setValue(((float)red) / 100);
			greenBar.setValue(((float)green) / 100);
			blueBar.setValue(((float)blue) / 100);
		}	
	}
}
