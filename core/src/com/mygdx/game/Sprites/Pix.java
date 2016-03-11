package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.PixPlat;

import java.awt.Polygon;
import java.util.Random;

/**
 * Created by Théo on 11/03/2016.
 */
public class Pix extends InterractiveTileObj{
    private int value;
    private static final int minValue = 1;
    private static final int maxValue = 101;

    public Pix(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        Random r = new Random();
        this.value = minValue + r.nextInt(maxValue - minValue);
    }
}
