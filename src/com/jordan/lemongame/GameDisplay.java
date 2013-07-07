package com.jordan.lemongame;

/**
 * Created by Owner on 6/29/13.
 */
import java.util.ArrayList;

public class GameDisplay {

    public static ScribbleGuy guy = new ScribbleGuy();
    public static ArrayList<Platform> platforms;
    public static ArrayList<Platform> platform0 = Platform.spawn(0);
    public static ArrayList<Platform> platform2500 = Platform.spawn(2500);
    public static ArrayList<Platform> platform5000 = Platform.spawn(5000);
    public static ArrayList<Platform> platform7500 = Platform.spawn(-2500);
    public static int spawnLocation = 0;
    public final static int DEATH = -13;

    public static Coord guyCoord = new Coord();

    public static void update(boolean right, boolean left)
    {
        guy.updatePosition(right, left);

        spawnPlatforms();

        doPlatforms();

        guy.checkCollision(platforms);
    }

    private static void spawnPlatforms() {
        if (guy.currentScore > spawnLocation + 2500)
        {
            spawnLocation += 2500;
            switch ((spawnLocation + 5000) % 10000)
            {
                case 0:
                    platform0 = Platform.spawn(spawnLocation + 5000);
                    break;
                case 2500:
                    platform2500 = Platform.spawn(spawnLocation + 5000);
                    break;
                case 5000:
                    platform5000 = Platform.spawn(spawnLocation + 5000);
                    break;
                case 7500:
                    platform7500 = Platform.spawn(spawnLocation + 5000);
                    break;
            }
        }
    }

    private static void doPlatforms() {

        platforms = new ArrayList<Platform>();

        if ( guy.deathHeight % 10000 < 3500 || guy.deathHeight % 10000 > 9000 )
            for (Platform p: platform0)
                if (800 - (p.c.y - guy.deathHeight) > 40 && 800 - (p.c.y - guy.deathHeight) < 800)
                    platforms.add(p);


        if ( guy.deathHeight % 10000 < 6000 && guy.deathHeight % 10000 > 1500 )
            for (Platform p: platform2500)
                if (800 - (p.c.y - guy.deathHeight) > 40 && 800 - (p.c.y - guy.deathHeight) < 800)
                    platforms.add(p);

        if ( guy.deathHeight % 10000 < 8500 && guy.deathHeight % 10000 > 4000 )
            for (Platform p: platform5000)
                if (800 - (p.c.y - guy.deathHeight) > 40 && 800 - (p.c.y - guy.deathHeight) < 800)
                    platforms.add(p);

        if ( guy.deathHeight % 10000 < 1000 || guy.deathHeight % 10000 > 6500 )
            for (Platform p: platform7500)
                if (800 - (p.c.y - guy.deathHeight) > 40 && 800 - (p.c.y - guy.deathHeight) < 800)
                    platforms.add(p);

        guyCoord = new Coord(guy.c.x, 800 - (guy.c.y - guy.deathHeight));

    }

    public static void updateCoords()
    {

    }

    public static void reset() {
        guy = new ScribbleGuy();
        platform0 = Platform.spawn(0);
        platform2500 = Platform.spawn(2500);
        platform5000 = Platform.spawn(5000);
        platform7500 = Platform.spawn(-2500);
        spawnLocation = 0;
        guyCoord = new Coord();

    }
}
