package com.thompson.spectrumshooter.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * ColorWheelSelector.java
 * Color Selector that rotates and shades a color wheel to select color
 * 
 * @author Zachary Thompson
 */
public class ColorWheelSelector extends ColorSelector 
{
	protected ProgressBar shadeBar;
	
	protected Sprite colorWheel;
	protected Sprite whiteCircle;
	protected Image image;
	private Pixmap pixMap = new Pixmap(Gdx.files.local("colorWheelNew.png"));
	
	public ColorWheelSelector() 
	{
		super(350, 300);
		Table firstTable = new Table();
		
		Texture backgroundTexture = new Texture(Gdx.files.local("colorBar.png"));
		Texture progressFill = new Texture(Gdx.files.local("colorBar.png"));
		
		Sprite backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(20, 200);
		SpriteDrawable background = new SpriteDrawable(backgroundSprite);
		
		Sprite fillSprite = new Sprite(progressFill);
		fillSprite.setColor(Color.BLACK);
		fillSprite.setSize(20, 20);
		SpriteDrawable fill = new SpriteDrawable(fillSprite);	
		ProgressBarStyle style = new ProgressBarStyle(background, fill);
		
		shadeBar = new ProgressBar(-1f, 1f, .01f,true,style);
		shadeBar.setValue(0f);
		firstTable.add(shadeBar);
		
		Table secondTable = new Table();
		secondTable.center();
		
		Sprite selector = new Sprite(new Texture(Gdx.files.local("EdgePiece.png")));
		selector.setSize(10, 10);
		secondTable.add(new Image(new SpriteDrawable(selector))).row();;
		
		colorWheel = new Sprite(new Texture(Gdx.files.local("colorWheelNew.png")));
		colorWheel.setSize(250, 250);
		colorWheel.setOriginCenter();
		
		image = new Image(new SpriteDrawable(colorWheel));
		image.setOrigin(image.getHeight()/2, image.getWidth()/2);

		whiteCircle = new Sprite(new Texture(Gdx.files.local("whiteCircleFull.png")));
		whiteCircle.setSize(250, 250);
		whiteCircle.setColor(0, 0, 0, 0);
		Image topImage = new Image(new SpriteDrawable(whiteCircle));
		
		Stack newStack = new Stack();
		newStack.add(image);
		newStack.add(topImage);
		
		secondTable.add(newStack);
		firstTable.add(secondTable).pad(5);
		inerdTable.add(firstTable);
	}

	/**
	 * Rotates the Color Wheel if 'A' or 'D' is pressed 
	 */
	@Override
	public void updateColor() 
	{
		boolean aPressed = Gdx.input.isKeyPressed(Input.Keys.A);
		boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.D);
	
		if((aPressed || dPressed) && !(aPressed && dPressed))
		{
			if(aPressed)
			{
				image.rotateBy(3.5f);	
			}
			else
			{
				image.rotateBy(-3.5f);
			}
		}
		
		boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
		boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);
		
		if((wPressed || sPressed) && !(wPressed && sPressed))
		{		
			if(wPressed)
			{
				shadeBar.setValue(shadeBar.getValue() + .05f);
			}
			else
			{
				shadeBar.setValue(shadeBar.getValue() - .05f);
			}
			
			float barValue = shadeBar.getValue();
			if(barValue > 0)
			{
				whiteCircle.setColor(1f, 1f, 1f, barValue);
			}
			else
			{
				whiteCircle.setColor(0f, 0f, 0f, Math.abs(barValue));
			}
		}
	}

	/**
	 * Returns the color that the color wheel slector is currently on
	 */
	@Override
	public Color selectColor() 
	{
		float rotation = image.getRotation();
		int xValue = (int)(MathUtils.cosDeg(rotation - 90) * 100) + pixMap.getWidth() / 2;
		int yValue = (int)(MathUtils.sinDeg(rotation - 90) * 100) + pixMap.getHeight() / 2;
		Color holdColor = new Color(pixMap.getPixel(xValue,yValue));
		float barValue = shadeBar.getValue();
		return new Color(holdColor.r + barValue, holdColor.g + barValue, holdColor.b + barValue, 1);
	}

}
