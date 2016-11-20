package com.thompson.spectrumshooter.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.thompson.spectrumshooter.color.ColorWheel;
import com.thompson.spectrumshooter.util.Constants;

import box2dLight.ChainLight;
import box2dLight.ConeLight;
import box2dLight.DirectionalLight;
import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.PositionalLight;
import box2dLight.RayHandler;


public class NeonMenuScreen implements Screen
{
	public World world;
	public OrthographicCamera camera;
	public RayHandler rayHandler;

	private ColorWheel colorWheel;
	private int currentColorCode;
	
	private ArrayList<backgroundLight> array = new ArrayList<backgroundLight>();
	
	float gameWidth = .01f * Constants.GAME_WIDTH;
	float gameHeight = .01f * Constants.GAME_HEIGHT;
	float value = 0.1f;
	final float DISTANCE = 3;
	
	private final int CIRCLEWIDTH = 7;
	
	float direction = 90;
	public NeonMenuScreen()
	{
		init();
	}

	public void init()
	{
		world = new World(new Vector2(0,0), true);
		world.setContactListener(new contactListener());
			
		camera = new OrthographicCamera(gameWidth,gameHeight);
		camera.update();
		colorWheel = new ColorWheel();
		currentColorCode = 0;
		
		rayHandler = new RayHandler(world);

//		light = new PointLight(rayHandler, 10, Color.RED, 2f, 0, 0);
//		coneLight1 = new ConeLight(rayHandler, 10, Color.RED, 5, 0, 0, direction, 45);
//		coneLight2 = new ConeLight(rayHandler, 10, Color.RED, 5, 0, 0, direction, 45);
		
		for(int i = 0; i < 16; i++)
		{
			array.add(generateEnemy());
		}
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta)
	{
		rayHandler.setCombinedMatrix(camera);
		rayHandler.updateAndRender();
		
		currentColorCode = colorWheel.incrementColorCode(currentColorCode);
		currentColorCode = colorWheel.incrementColorCode(currentColorCode);
		
		Color backgroundColor = new Color(colorWheel.getRedValue(currentColorCode) / 255.0f,
				colorWheel.getBlueValue(currentColorCode) / 255.0f, colorWheel.getGreenValue(currentColorCode) / 255.0f,
				1);
		
		for(int i = 0; i < array.size(); i++)
		{
			array.get(i).updateLight();

		}
		
		for(int i = 0; i < array.size(); i++)
		{
			if(array.get(i).getRespawn())
			{
				array.get(i).dispose();
				array.remove(i);
				array.add(generateEnemy());
			}
		}
		//light.setColor(backgroundColor);
		
		//value = (value + 5) % 360;
		
		//light.setPosition(DISTANCE * MathUtils.sinDeg(value)  , DISTANCE * MathUtils.cosDeg(value));
		//light.setDistance((light.getDistance() + delta)); 
		
//		coneLight1.setDistance(coneLight1.getDistance() + .01f);
//		coneLight2.setDistance(coneLight2.getDistance() + .01f);
//		
//		coneLight1.setDirection(direction = (direction + 10) % 360);
//		coneLight2.setDirection(direction * -1);
		
		
	}

	public backgroundLight generateEnemy()
	{
		int colorCode = colorWheel.random();
		Color Color = new Color(colorWheel.getRedValue(colorCode) / 255f,
				colorWheel.getGreenValue(colorCode) / 255f,
				colorWheel.getBlueValue(colorCode) / 255f,
				1f);

		return new backgroundLight(
				(float)Math.random() * 360f,
				CIRCLEWIDTH/2,
				(float)(Math.random() * .05),
				(float)Math.random(),
				Color, rayHandler);
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
		rayHandler.dispose();
		world.dispose();
	}
	
	private class contactListener implements ContactListener
	{

		@Override
		public void beginContact(Contact contact) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void endContact(Contact contact) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class backgroundLight
	{
		private float deg;
		private float distance;
		private float speed;
		private boolean respawn;
		public PointLight light;
		

		public backgroundLight(float deg, float distance, float speed, float diameter, Color color, RayHandler rayHandler)
		{
			this.deg = deg;
			this.distance = distance;
			this.speed = speed;
			this.respawn = false;
			float width = MathUtils.sinDeg(deg) * distance;
			float height = MathUtils.cosDeg(deg) * distance;
			light = new PointLight(rayHandler, 7, color, 2f, width, height);

		}

		public void updateLight()
		{
			distance = distance - speed;
		
			if(distance < .05)
			{
				respawn = true;
			}

			if(!respawn)
			{
				float width = MathUtils.sinDeg(deg) * distance;
				float height = MathUtils.cosDeg(deg) * distance;
				light.setPosition(width  , height);
			}
		}

		public boolean getRespawn()
		{
			return respawn;
		}
		
		public void dispose()
		{
			light.dispose();
		}
	}
}
