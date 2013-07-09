package com.jordan.lemongame;

import com.jordan.framework.Image;
import com.jordan.framework.Music;
import com.jordan.framework.Sound;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Assets {

    public static boolean cheats = false;
    public static final String highScoreFile = "high1.txt";
    public static File fileLocation;
    public static int highScore = Integer.parseInt(readFromMemory(highScoreFile));
	
	public static Image splash, background;
    public static Image guye, guys, guyl, guyr;
    public static Image basicplat, movingplat, vanishplat, superplat, riseplat;

    public static Sound bounce;
    public static Sound superBounce;
    public static Music theme;

	public static void load(SampleGame sampleGame) {
        theme = sampleGame.getAudio().createMusic("SherbetLand.mid");
		theme.setLooping(true);
		theme.setVolume(0.85f);
        bounce = sampleGame.getAudio().createSound("funny-bounce.mp3");
        superBounce = sampleGame.getAudio().createSound("countdick.m4a");
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
