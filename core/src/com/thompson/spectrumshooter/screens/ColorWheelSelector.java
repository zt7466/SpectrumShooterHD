package com.thompson.spectrumshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.thompson.spectrumshooter.overlayScreen.ColorSelector;

public class ColorWheelSelector extends ColorSelector 
{
	protected ProgressBar shadeBar;
	protected Sprite colorWheel;
	protected Image image;
	
	
	public ColorWheelSelector(Stage stage) 
	{
		super(stage, 350, 300);
		
		Table secondTable = new Table();
		secondTable.center();
	
		Sprite selector = new Sprite(new Texture(Gdx.files.local("EdgePiece.png")));
		selector.setSize(10, 10);
		secondTable.add(new Image(new SpriteDrawable(selector))).row();;
		
		colorWheel = new Sprite(new Texture(Gdx.files.local("colorWheelNew.png")));
		colorWheel.setSize(250, 250);
		image = new Image(new SpriteDrawable(colorWheel));
		image.setOrigin(image.getHeight()/2, image.getWidth()/2);
		secondTable.add(image);
		
		inerdTable.add(secondTable);
	}

	@Override
	public void changeColor() 
	{
		boolean aPressed = Gdx.input.isKeyPressed(Input.Keys.A);
		boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.D);
		
		if((aPressed || dPressed) && !(aPressed && dPressed))
		{
			if(aPressed)
			{
				image.rotateBy(5f);
				
			}
			else
			{
				image.rotateBy(-5f);
			}
		}
	}

	@Override
	public Color selectColor() 
	{
		Pixmap pixMap = colorWheel.getTexture().getTextureData().consumePixmap();;
		Color color = new Color(pixMap.getPixel(pixMap.getWidth()/2, pixMap.getHeight() * 3 / 4)); 
		return color;
	}

}
