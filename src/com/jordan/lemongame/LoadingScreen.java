package com.jordan.lemongame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Graphics.ImageFormat;
import com.jordan.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();

        String ike = "";
        if(Assets.ike) ike = "ike";

		Assets.background = g.newImage("gamebg.png", ImageFormat.RGB565);

        Assets.guye = g.newImage("guye.png", ImageFormat.RGB565);
        Assets.guys = g.newImage("guys.png", ImageFormat.RGB565);
        Assets.guyr = g.newImage("guyr.png", ImageFormat.RGB565);
        Assets.guyl = g.newImage("guyl.png", ImageFormat.RGB565);

        Assets.basicplat = g.newImage("basicplat.png", ImageFormat.RGB565);
        Assets.movingplat = g.newImage("movingplat.png", ImageFormat.RGB565);
        Assets.vanishplat = g.newImage("vanishplat.png", ImageFormat.RGB565);
        Assets.superplat = g.newImage("superplat.png", ImageFormat.RGB565);

		//This is how you would load a sound if you had one.
		//Assets.click = game.getAudio().createSound("explode.ogg");
		
		game.setScreen(new GameScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}