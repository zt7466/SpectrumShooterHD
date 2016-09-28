package com.thompson.spectrumshooter.overlayScreen;

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
 * Abstract method for setting up colorselecting Screens
 * @author Zach
 *
 */
public abstract class OverlayingScreen extends Group {
	protected static Table totalTable;
	protected static Table inerdTable;
	protected static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	protected LabelStyle LABELSTYLE;
	private final static int CORNERSIZE = 10;

	public OverlayingScreen(float xSize, float ySize) 
	{
		setSize(xSize, ySize);
		totalTable = new Table();
		totalTable.setFillParent(true);
		totalTable.center();
		
		inerdTable = new Table();
		inerdTable.center();
		
		totalTable.add(createBorder(xSize, ySize));
		
		changeColor(new Color((float) (87.0/255.0),(float)(33.0/255.0), (float)(62.0/255.0),(float)2.0));
		totalTable.row();
		totalTable.setFillParent(true);
	}

	public void toggleVisibility() {
		if (totalTable.isVisible()) {
			totalTable.setVisible(false);
		} else {
			totalTable.setVisible(true);
		}
	}

	public static void toggleVisibilityStatic() {
		if (totalTable.isVisible()) {
			totalTable.setVisible(false);
		} else {
			totalTable.setVisible(true);
		}
	}

	
	public static Table createBorder(float xSize, float ySize) {
		Table windowBarTable = new Table();
		
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

	private static Image createImage(Texture texture, boolean flipX, boolean flipY)
	{
		Sprite sprite = new Sprite(texture);
		sprite.setFlip(flipX, flipY);
		sprite.setSize(CORNERSIZE, CORNERSIZE);
		sprites.add(sprite);
		return new Image(new SpriteDrawable(sprite));
	}
	
	private static Image createImage(Texture texture, float sizeX, float sizeY)
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
			sprites.get(i).setColor(c);
		}
	
	}
	
	public Table getTable()
	{
		return totalTable;
	}
}
