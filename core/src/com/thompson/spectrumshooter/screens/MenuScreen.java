package com.thompson.spectrumshooter.screens;

import javax.swing.SpringLayout.Constraints;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.thompson.spectrumshooter.color.ColorWheel;
import com.thompson.spectrumshooter.util.Constants;

public class MenuScreen implements Screen 
{
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	
	Stage stage;
	
	Label title;
	LabelStyle titleLabelStyle;
	private ColorWheel colorWheel;
	private int currentColorCode;
	
	public MenuScreen()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		
		viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		batch = new SpriteBatch();
		
		stage = new Stage(viewport, batch);
		
		colorWheel = new ColorWheel();
		currentColorCode = colorWheel.incrementColorCode(currentColorCode);
		camera = new OrthographicCamera();
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = Constants.GAME_HEIGHT/5;
		parameter.borderWidth = 3;
		parameter.borderColor = Color.BLACK;
		parameter.shadowOffsetX = 2;
		parameter.shadowOffsetY = 2;
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.local("master_of_break.ttf"));
		titleLabelStyle = new LabelStyle(generator.generateFont(parameter), new Color(0.255f, 0.412f, 0.882f, 1f));
		
		Table mainTable = new Table();
		mainTable.setFillParent(true); 
		mainTable.add(new Label("Spectrum Shooter", titleLabelStyle));
		mainTable.row();
		
		stage.addActor(mainTable);
		generator.dispose();
	}
	
	@Override
	public void render(float delta) 
	{	
		updateColors();
		camera.update();
		stage.draw();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() 
	{
		// TODO Auto-generated method stub

	}
	
	/**
	 * Increments the CurrentColor background and text color
	 */
	public void updateColors()
	{
		Color newColor = createColor(currentColorCode);
		Gdx.gl.glClearColor(newColor.r, newColor.g, newColor.b, newColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		titleLabelStyle.fontColor = new Color(1 - newColor.r,1 - newColor.g,1 - newColor.b, 1);
		
		currentColorCode = colorWheel.incrementColorCode(currentColorCode);
	}
	
	public Color createColor(int currentColorCode)
	{
		return new Color(
				colorWheel.getRedValue(currentColorCode)/255.0f,
				colorWheel.getGreenValue(currentColorCode)/255.0f,
				colorWheel.getBlueValue(currentColorCode)/255.0f,
				1);
	}
}
