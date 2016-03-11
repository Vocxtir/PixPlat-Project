package com.mygdx.game.Sprites;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PixPlat;

/**
 * Created by Théo on 11/03/2016.
 */
public class BadGuy {
    private int life;


    public World world; //Monde dans lequel le perso vit
    public Body b2body;

    public BadGuy(World world){
        this.world = world;
        this.life = 100;
        defineBadGuy();
    }

    public int getLife(){
        return this.life;
    }

    public void setLife(int l){
        this.life = l;
    }

    public void defineBadGuy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ PixPlat.PPM, 32/ PixPlat.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody; //Box2D class pour les objets "dynamiques" = pouvant se mouvoir
        b2body = world.createBody(bdef);

        FixtureDef fedf = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(1);

        fedf.shape = shape;
        b2body.createFixture(fedf);
    }
}
