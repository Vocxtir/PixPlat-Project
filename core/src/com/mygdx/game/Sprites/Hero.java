package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PixPlat;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Théo on 10/03/2016.
 */
public class Hero extends Sprite {
    public World world; //Monde dans lequel le perso vit
    public Body b2body;
    private TextureRegion herostand;

    private int life;
    private int spellPoint;

    public Hero(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("klonoa_still"));
        this.world = world;
        this.life = 100;
        this.spellPoint = 100;
        defineHero();
        this.herostand = new TextureRegion(getTexture(), 23, 21, 29, 17);//23, 21, 29, 17
        setBounds(0, 0, 16/PixPlat.PPM, 16/PixPlat.PPM);
        setRegion(herostand);

    }

    public int getLife(){
        return this.life;
    }
    public int getSpellPoint(){
        return this.spellPoint;
    }

    public void setLife(int l){
        this.life = l;
    }
    public void setSpellPoint(int sp){
        this.spellPoint=sp;
    }

    public void defineHero(){
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
