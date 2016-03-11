package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.PixPlat;
import com.mygdx.game.Screens.PlayScreen;


/**
 * Created by Théo on 10/03/2016.
 */
public class Hero extends Sprite {
    public enum State {RUN, STAND, FALL, ATCK, JMP};
    public State currentState;
    public State previousState;

    private Animation heroRun;
    private Animation heroAtck;
    private Animation heroJmp;

    private boolean runRight;
    private float stateTimer;

    public World world; //Monde dans lequel le perso vit
    public Body b2body;
    private TextureRegion herostand;

    private int life;
    private int spellPoint;

    public Hero(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("klonoa_still"));
        this.world = world;

        currentState = State.STAND;
        previousState = State.STAND;
        stateTimer = 0;
        runRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i =1; i< 4; i++){
            frames.add(new TextureRegion(getTexture(), i * 48, 0, 48, 48));
        }
        heroRun = new Animation(0.1f, frames);
        frames.clear();

        for (int i =4; i<)

        this.life = 100;
        this.spellPoint = 100;
        defineHero();
        this.herostand = new TextureRegion(getTexture(), 15, 0, 64, 90);//DECOUPAGE DU PERSO
        setBounds(0, 0, 48/PixPlat.PPM, 48/PixPlat.PPM);
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
        shape.setRadius(20/PixPlat.PPM);

        fedf.shape = shape;
        b2body.createFixture(fedf);
    }

    public void update(float deltaTime){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight() / 2);
    }
}
