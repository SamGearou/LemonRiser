package com.jordan.lemongame;

//the yellow platform with basic action
public class BasicPlatform extends Platform {

    public BasicPlatform(Coord c) {
        super(c);
    }

    @Override
    public void onCollision(ScribbleGuy guy) {
        basicCollisionAction(guy);
    }

    @Override
    public void update(float deltaTime)
    {

    }

}
