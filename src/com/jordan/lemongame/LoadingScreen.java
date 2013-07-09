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
        long startTime = System.currentTimeMillis();

		/*Assets.backgrounddef = g.newImage("gamebg.png", ImageFormat.RGB565);
        Assets.guyedef = g.newImage("guye.png", ImageFormat.RGB565);
        Assets.guysdef = g.newImage("guys.png", ImageFormat.RGB565);
        Assets.guyrdef = g.newImage("guyr.png", ImageFormat.RGB565);
        Assets.guyldef = g.newImage("guyl.png", ImageFormat.RGB565);
        Assets.basicplatdef = g.newImage("basicplat.png", ImageFormat.RGB565);
        Assets.movingplatdef = g.newImage("movingplat.png", ImageFormat.RGB565);
        Assets.vanishplatdef = g.newImage("vanishplat.png", ImageFormat.RGB565);
        Assets.superplatdef = g.newImage("superplat.png", ImageFormat.RGB565);
        Assets.riseplatdef = g.newImage("riseplat.png", ImageFormat.RGB565);*/

        Assets.backgroundike = g.newImage("gamebgike.png", ImageFormat.RGB565);
        Assets.guyeike = g.newImage("guyeike.png", ImageFormat.RGB565);
        Assets.guysike = g.newImage("guysike.png", ImageFormat.RGB565);
        Assets.guyrike = g.newImage("guyrike.png", ImageFormat.RGB565);
        Assets.guylike = g.newImage("guylike.png", ImageFormat.RGB565);
        Assets.basicplatike = g.newImage("basicplatike.png", ImageFormat.RGB565);
        Assets.movingplatike = g.newImage("movingplatike.png", ImageFormat.RGB565);
        Assets.vanishplatike = g.newImage("vanishplatike.png", ImageFormat.RGB565);
        Assets.superplatike = g.newImage("superplatike.png", ImageFormat.RGB565);
        Assets.riseplatike = g.newImage("riserplatike.png", ImageFormat.RGB565);

        Assets.ike = !Assets.ike;
        Assets.reloadImages();

        long t = (System.currentTimeMillis() - startTime);
        try {
            if (t < 3000)
                Thread.sleep(3000 - t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assets.theme.play();

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