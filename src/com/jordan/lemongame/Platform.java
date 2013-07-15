package com.jordan.lemongame;

import java.util.ArrayList;

//the platform framework
public abstract class Platform {

    public Coord c;

    public Platform(Coord c)
    {
        this.c = c;
    }

    public static ArrayList<Platform> spawn(int location)
    {
        ArrayList<Platform> platforms = new ArrayList<Platform>();

        //defines sections
        int section = 0;
        if (location >= 2500) section = 1;
        if (location >= 5000) section = 2;
        if (location >= 20000) section = 3;
        if (location >= 40000) section = 4;
        if (location >= 70000) section = 5;
        if (location >= 100000) section = 6;

        //defines the gap between platforms
        int[] gap = {25, 50, 100, 157, 209, 209, 250};

        //defines the spawn frequency
        int[][] percent = {
                {1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,4,4},
                {1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,4,4},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,4,4,4},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,4,4,4,4},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5},
                {1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,5,5},
                {1,1,1,2,2,2,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,5}};

        for(int y = 0; y < 2500; y += gap[section])
        {
            int randType = (int)(Math.random() * percent[section].length);
            int randX = (int)(Math.random() * 400 - 200);
            switch (percent[section][randType])
            {
                case 1:
                    platforms.add(new SuperPlatform(new Coord(240 + randX, y + location)));
                    break;
                case 2:
                    platforms.add(new BasicPlatform(new Coord(240 + randX, y + location)));
                    break;
                case 3:
                    platforms.add(new MovingPlatform(new Coord(240 + (int) (randX * .5), y + location)));
                    break;
                case 4:
                    platforms.add(new VanishPlatform(new Coord(240 + randX, y + location)));
                    break;
                case 5:
                    platforms.add(new RisePlatform(new Coord(240 + randX * .2, y + location), gap[section], location + 2500));
                    y += gap[section];
            }
        }
        return platforms;
    }

    //will define what happens when the platform is hit
    //modifies itself and the guy
    public abstract void onCollision(ScribbleGuy guy);
    public abstract void update(float deltaTime);

    //basic action
    public void basicCollisionAction(ScribbleGuy guy)
    {
        guy.velocity = guy.getMV();
        if (Assets.theme.isPlaying())
            Assets.bounce.play(Assets.VOLUME);
    }

}
