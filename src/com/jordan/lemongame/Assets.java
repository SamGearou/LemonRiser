package com.jordan.lemongame;

import com.jordan.framework.Image;
import com.jordan.framework.Music;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Assets {

    public static boolean ike = true;
    public static boolean cheats = true;
    public static final String highScoreFile = "high1.txt";
    public static File fileLocation;
    public static int highScore = Integer.parseInt(readFromMemory(highScoreFile));
	
	public static Image splash, background;
    public static Image guye, guys, guyl, guyr;
    public static Image basicplat, movingplat, vanishplat, superplat, riseplat;

    public static Image backgrounddef;
    public static Image guyedef, guysdef, guyldef, guyrdef;
    public static Image basicplatdef, movingplatdef, vanishplatdef, superplatdef, riseplatdef;

    public static Image backgroundike;
    public static Image guyeike, guysike, guylike, guyrike;
    public static Image basicplatike, movingplatike, vanishplatike, superplatike, riseplatike;


    //public static Sound click;
    public static Music theme;

	public static void load(SampleGame sampleGame) {
		//theme = sampleGame.getAudio().createMusic("DonutPlains.mid");
        theme = sampleGame.getAudio().createMusic("SherbetLand.mid");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}

    public static void toggleMusic()
    {
        if (theme.isPlaying())
            theme.pause();
        else theme.play();
    }

    public static void reloadImages()
    {
        ike = !ike;
        if (ike)
        {
            background = backgroundike;
            guye = guyeike;
            guyl = guylike;
            guys = guysike;
            guyr = guyrike;
            basicplat = basicplatike;
            vanishplat = vanishplatike;
            movingplat = movingplatike;
            superplat = superplatike;
            riseplat = riseplatike;
        }
        else
        {
            background = backgrounddef;
            guye = guyedef;
            guyl = guyldef;
            guys = guysdef;
            guyr = guyrdef;
            basicplat = basicplatdef;
            vanishplat = vanishplatdef;
            movingplat = movingplatdef;
            superplat = superplatdef;
            riseplat = riseplatdef;
        }

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
