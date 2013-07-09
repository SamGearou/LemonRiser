package com.jordan.lemongame;

/**
 * Created by Owner on 6/29/13.
 */
import java.util.ArrayList;

public abstract class Platform {

    public Coord c;
    //j- makes the platform, stores coordinates
    public Platform(Coord c)
    {
        this.c = c;
    }

    public static ArrayList<Platform> spawn(int location)
    {
        ArrayList<Platform> a = new ArrayList<Platform>();

        int sec = 0;
        if (location >= 2500) sec = 1;
        if (location >= 5000) sec = 2;
        if (location >= 20000) sec = 3;
        if (location >= 40000) sec = 4;
        if (location >= 70000) sec = 5;
        if (location >= 100000) sec = 6;

        int[] diff = {25, 50, 100, 157, 209, 209, 250};
        int[][] percent = {
                {1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,4,4},
                {1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,4,4},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,4,4,4},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,4,4,4,4},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5},
                {1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,5,5},
                {1,1,1,2,2,2,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,5}};

        for(int y = 0; y < 2500; y += diff[sec])
        {
            int randt = (int)(Math.random() * percent[sec].length);
            int randx = (int)(Math.random() * 400 - 200);
            switch (percent[sec][randt])
            {
                case 1:
                    a.add(new SuperPlatform(new Coord(240 + randx,y + location)));
                    break;
                case 2:
                    a.add(new BasicPlatform(new Coord(240 + randx,y + location)));
                    break;
                case 3:
                    a.add(new MovingPlatform(new Coord(240 + (int)(randx * .5),y + location)));
                    break;
                case 4:
                    a.add(new VanishPlatform(new Coord(240 + randx,y + location)));
                    break;
                case 5:
                    a.add(new RisePlatform(new Coord(240 + randx*.2,y + location), diff[sec], location + 2500));
                    y += diff[sec];
            }
        }
        return a;
    }

    //j- will define what happens when the platform is hit
    //modifies itself and the guy
    public abstract void onCollision(ScribbleGuy guy);
    public abstract void update();

    //j- basic action
    public void basicCollisionAction(ScribbleGuy guy)
    {
        guy.velocity = guy.getMV();
        if (Assets.theme.isPlaying())
            Assets.bounce.play(0.85f);
    }

}
