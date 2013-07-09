package com.jordan.lemongame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Image;
import com.jordan.framework.Music;
import com.jordan.framework.Sound;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//this class stores all the images, sound, constants, and can read/write files
public class Assets {

    //enables delta time counter (fps), allows 40000 skip
    public static boolean cheats = true;

    //you can pick where the highscore is stored
    public static final String highScoreFile = "high1.txt";
    public static File fileLocation;
    public static int highScore = Integer.parseInt(readFromMemory(highScoreFile));

    //constant for handling time, higher means game runs faster
    public static final double TIME = 0.57;
    public static final float VOLUME = 0.35f;

	public static Image splash, background;
    public static Image guye, guys, guyl, guyr;
    public static Image basicplat, movingplat, vanishplat, superplat, riseplat;
    public static Sound bounce;
    public static Music theme;

	public static void loadSound(Game game) {
        theme = game.getAudio().createMusic("SherbetLand.mid");
		theme.setLooping(true);
		theme.setVolume(0.85f);
        bounce = game.getAudio().createSound("bounce.mp3");
	}

    public static void loadImages(Game game) {
        Graphics g = game.getGraphics();
        background = g.newImage("gamebgike.png", Graphics.ImageFormat.RGB565);
        guye = g.newImage("guyeike.png", Graphics.ImageFormat.RGB565);
        guys = g.newImage("guysike.png", Graphics.ImageFormat.RGB565);
        guyr = g.newImage("guyrike.png", Graphics.ImageFormat.RGB565);
        guyl = g.newImage("guylike.png", Graphics.ImageFormat.RGB565);
        basicplat = g.newImage("basicplatike.png", Graphics.ImageFormat.RGB565);
        movingplat = g.newImage("movingplatike.png", Graphics.ImageFormat.RGB565);
        vanishplat = g.newImage("vanishplatike.png", Graphics.ImageFormat.RGB565);
        superplat = g.newImage("superplatike.png", Graphics.ImageFormat.RGB565);
        riseplat = g.newImage("riserplatike.png", Graphics.ImageFormat.RGB565);
    }

    public static void toggleMusic()
    {
        if (theme.isPlaying())
            theme.pause();
        else theme.play();
    }

    public static void writeToMemory(String fileName, String text)
    {//needed to instantiate file directory in sample game activity
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileLocation+File.separator+fileName)));
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {}
    }

    public static String readFromMemory(String fileName)
    {
        String a = "0";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileLocation+File.separator+fileName)));
            String read;
            StringBuilder builder = new StringBuilder("");
            while((read = bufferedReader.readLine()) != null){
                builder.append(read);
            }
            a = builder.toString();
            bufferedReader.close();
        } catch (IOException e) {}
        return a;
    }
	
}
