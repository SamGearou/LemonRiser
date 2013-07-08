package com.jordan.lemongame;

import com.jordan.framework.Image;

public class Assets {

    public static boolean ike = true;
	
	public static Image splash, background;
    public static Image guye, guys, guyl, guyr;
    public static Image basicplat, movingplat, vanishplat, superplat;

    public static Image backgrounddef;
    public static Image guyedef, guysdef, guyldef, guyrdef;
    public static Image basicplatdef, movingplatdef, vanishplatdef, superplatdef;

    public static Image backgroundike;
    public static Image guyeike, guysike, guylike, guyrike;
    public static Image basicplatike, movingplatike, vanishplatike, superplatike;

    //public static Sound click;
    //public static Music theme;

	public static void load(SampleGame sampleGame) {
		//theme = sampleGame.getAudio().createMusic("menutheme.mp3");
		//theme.setLooping(true);
		//theme.setVolume(0.85f);
		//theme.play();
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
        }

    }
	
}
