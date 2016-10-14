package com.thompson.spectrumshooter.overlay;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;


/**
 * OverlayingScreen.java
 * 
 * Abstract class for managing OverlayingScreens.
 * 
 * @author Zachary Thompson
 */
public abstract class OverlayingScreen extends Group {
	protected Table totalTable;
	protected Table inerdTable;
	protected ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private final int CORNERSIZE = 10;

	public OverlayingScreen(float xSize, float ySize) 
	{
		setSize(xSize, ySize);
		totalTable = new Table();
		
		inerdTable = new Table();
		
		totalTable.add(createBorder(xSize, ySize));
		
		changeColor(new Color((float) (87.0/255.0),(float)(33.0/255.0), (float)(62.0/255.0),(float)2.0));
		totalTable.row();
		totalTable.setColor(Color.BLACK);
	}

	public void toggleVisibility() {
		if (totalTable.isVisible()) {
			totalTable.setVisible(false);
		} else {
			totalTable.setVisible(true);
		}
	}

	
	public Table createBorder(float xSize, float ySize) {
		Table windowBarTable = new Table();
		windowBarTable.setFillParent(true);
		
		Texture cornerTexture = new Texture(Gdx.files.local("CornerPiece.png"));
		Texture edgeTexture = new Texture(Gdx.files.local("EdgePiece.png"));
		
		windowBarTable.add(createImage(cornerTexture, true, false));
		windowBarTable.add(createImage(edgeTexture, xSize - 2 * CORNERSIZE, CORNERSIZE));
		windowBarTable.add(createImage(cornerTexture, false, false)).row();
		windowBarTable.add(createImage(edgeTexture, CORNERSIZE, ySize - 2 * CORNERSIZE));
		
		Sprite backgroundSprite = new Sprite(edgeTexture);
		sprites.add(backgroundSprite);
		inerdTable.setBackground(new SpriteDrawable(backgroundSprite));
		windowBarTable.add(inerdTable).size(xSize - 2 * CORNERSIZE, ySize - 2 * CORNERSIZE);
		
		windowBarTable.add(createImage(edgeTexture, CORNERSIZE, ySize - 2 * CORNERSIZE)).row();
		windowBarTable.add(createImage(cornerTexture, true, true));
		windowBarTable.add(createImage(edgeTexture, xSize - 2 * CORNERSIZE, CORNERSIZE));
		windowBarTable.add(createImage(cornerTexture, false, true));
		
		return windowBarTable;
	}

	private Image createImage(Texture texture, boolean flipX, boolean flipY)
	{
		Sprite sprite = new Sprite(texture);
		sprite.setFlip(flipX, flipY);
		sprite.setSize(CORNERSIZE, CORNERSIZE);
		sprites.add(sprite);
		return new Image(new SpriteDrawable(sprite));
	}
	
	private Image createImage(Texture texture, float sizeX, float sizeY)
	{
		Sprite sprite = new Sprite(texture);
		sprite.setSize(sizeX, sizeY);
		sprites.add(sprite);
		return new Image(new SpriteDrawable(sprite));
	}
	
	public void changeColor(Color c)
	{
		for(int i = 0; i < sprites.size(); i++)
		{
			sprites.get(i).setColor(new Color(.5f + c.r, .5f + c.g, .5f + c.b, 1));
		}
	}
	
	public Table getTable()
	{
		return totalTable;
	}
}
