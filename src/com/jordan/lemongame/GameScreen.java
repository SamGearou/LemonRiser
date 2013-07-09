package com.jordan.lemongame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Image;
import com.jordan.framework.Input.TouchEvent;
import com.jordan.framework.Screen;

//this is the screen where the game is run
public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	Paint paintCenter, paintLeft;
    private boolean right = false, left = false;

    private long ticks = 0;
    private long pauseTicks;
    private String framesCounter = "0";

	public GameScreen(Game game) {
		super(game);

        //how to animate
		/*hanim = new Animation();
		hanim.addFrame(heliboy, 200);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);*/

		paintCenter = new Paint();
		paintCenter.setTextSize(30);
		paintCenter.setTextAlign(Paint.Align.CENTER);
		paintCenter.setAntiAlias(true);
		paintCenter.setColor(Color.YELLOW);
        paintLeft = new Paint();
        paintLeft.setTextSize(30);
        paintLeft.setTextAlign(Paint.Align.LEFT);
        paintLeft.setAntiAlias(true);
        paintLeft.setColor(Color.rgb(74, 74, 74));
	}


    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

        // 1. All touch input is handled here:
        for (TouchEvent event: touchEvents) {
            if (ticks < 15) break;
            switch(event.type)
            {
                case TouchEvent.TOUCH_DOWN:
                    if (event.x > 430 && event.y < 50) //music toggler
                        Assets.toggleMusic();
                    if (event.x < 50 && event.y < 50) //pauser
                        pause();
                case TouchEvent.TOUCH_DRAGGED:
                    right = false;
                    left = false;
                    if (event.x >= 240)
                        right = true;
                    if (event.x < 240)
                        left = true;
                    break;
                case TouchEvent.TOUCH_UP:
                    right = false;
                    left = false;
            }
        }

        //2. update the game
        GameDisplay.update(right,left,deltaTime);

        //3. check for death
        if (!GameDisplay.guy.checkDeath())
        {
            state = GameState.GameOver;
            pauseTicks = ticks;
        }

        //updates frame counter
        if (ticks % 100 == 1)
            framesCounter = " deltaTime: " + deltaTime;

        //anim.update(10);
    }
    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.clearScreen(Color.BLACK);
        g.drawImage(Assets.background, 0,0);

        //shows the guy. the if chooses the emotion
        Image theguy = Assets.guyr;
        if(GameDisplay.guy.velocity > GameDisplay.guy.getMV())
            theguy = Assets.guye;
        else if (GameDisplay.guy.c.y - GameDisplay.guy.deathHeight < 300 && GameDisplay.guy.velocity < -4)
            theguy = Assets.guys;
        else if (GameDisplay.guy.movement < 0)
            theguy = Assets.guyl;
        g.drawImage(theguy,  (int) GameDisplay.guyCoord.x - Assets.guyr.getWidth()/2,(int) GameDisplay.guyCoord.y - Assets.guyr.getHeight()/2);

        //draws platforms
        for(Platform p: GameDisplay.platforms)
        {
            if (p instanceof VanishPlatform)
                g.drawImage(Assets.vanishplat, (int)p.c.x - Assets.basicplat.getWidth()/2, (int)(800 - (p.c.y - GameDisplay.guy.deathHeight)) - Assets.basicplat.getHeight()+30);
            else if (p instanceof SuperPlatform)
                g.drawImage(Assets.superplat, (int)p.c.x - Assets.basicplat.getWidth()/2, (int)(800 - (p.c.y - GameDisplay.guy.deathHeight)) - Assets.basicplat.getHeight()+30);
            else if (p instanceof MovingPlatform)
                g.drawImage(Assets.movingplat, (int)p.c.x - Assets.basicplat.getWidth()/2, (int)(800 - (p.c.y - GameDisplay.guy.deathHeight)) - Assets.basicplat.getHeight()+30);
            else if (p instanceof RisePlatform)
                g.drawImage(Assets.riseplat, (int)p.c.x - Assets.basicplat.getWidth()/2, (int)(800 - (p.c.y - GameDisplay.guy.deathHeight)) - Assets.basicplat.getHeight()+30);
            else
                g.drawImage(Assets.basicplat, (int)p.c.x - Assets.basicplat.getWidth()/2, (int)(800 - (p.c.y - GameDisplay.guy.deathHeight)) - Assets.basicplat.getHeight()+30);
        }

        //shows score, and deltaTime
        String scoreString = (int)GameDisplay.guy.currentScore + "";
        if (Assets.cheats)
            scoreString += " " + framesCounter;
        g.drawString(scoreString, 140, 35, paintLeft);
    }


	private void updateReady(List<TouchEvent> touchEvents) {
        //starts when they tap the phone
		if (touchEvents.size() > 0)
			state = GameState.Running;
	}
    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.background, 0,0);
        g.drawARGB(200, 0, 0, 0);
        g.drawString("Tap to Start", 240, 400, paintCenter);
    }

	private void updatePaused(List<TouchEvent> touchEvents) {
        if (Assets.cheats && touchEvents.size() > 0 && touchEvents.get(0).x < 50 && touchEvents.get(0).y > 750)
        {//cheats!!!
            GameDisplay.guy.c.y += 40000;
            GameDisplay.guy.velocity = GameDisplay.guy.getMV();
        }

        //resumes when the phone is tapped
		if (touchEvents.size() > 0 && pauseTicks + 15 < ticks)
				resume();
	}
    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        drawRunningUI();
        g.drawARGB(200, 0, 0, 0);
        g.drawString("Tap to Resume", 240, 400, paintCenter);
    }

	private void updateGameOver(List<TouchEvent> touchEvents) {
        if ((int)GameDisplay.guy.currentScore > Assets.highScore)
        {//writes highscore
            Assets.writeToMemory(Assets.highScoreFile, "" + (int)GameDisplay.guy.currentScore);
            Assets.highScore = (int)GameDisplay.guy.currentScore;
        }

        if (touchEvents.size() > 0 && pauseTicks + 15 < ticks)
        {//resets the game on a tap
            GameDisplay.reset();
			game.setScreen(new GameScreen(game));
        }
	}
	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
        drawRunningUI();
        g.drawARGB(200, 0, 0, 0);
        g.drawString("GAME OVER", 240, 325, paintCenter);
        g.drawString("SCORE: " + (int)GameDisplay.guy.currentScore, 240, 400, paintCenter);
		g.drawString("Tap to Retry", 240, 550, paintCenter);
        g.drawString("BEST: " + Assets.highScore, 240, 475, paintCenter);
	}

    @Override
    public void update(float deltaTime) {
        ticks++;
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }
    @Override
    public void paint(float deltaTime) {
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();
    }

	@Override
	public void pause() {
		if (state == GameState.Running)
        {
			state = GameState.Paused;
            pauseTicks = ticks;
        }

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

}