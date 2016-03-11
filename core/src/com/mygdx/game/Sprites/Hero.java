package com.mygdx.game.Sprites;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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
public class Hero extends Sprite{


    public enum State {STAND,JMP, FALL, RUN};
    public State currentState;
    public State previousState;

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
    public State getState(){
        if(b2body.getLinearVelocity().y>0){
            return State.JMP;
        }
        else if (b2body.getLinearVelocity().y<0){
            return State.FALL;
        }
        else if (b2body.getLinearVelocity().x!=0){
            return State.RUN;
        }
        else return State.STAND;
    }

    public TextureRegion getFrame(float dt){
        TextureRegion region = herostand;

        if((b2body.getLinearVelocity().x<0 || !runRight) && region.isFlipX()){
            region.flip(true, false);
            runRight = true;
        }
        else if ((b2body.getLinearVelocity().x>0 || runRight ) && !region.isFlipX()){
            region.flip(true, false);
            runRight = false;
        }

        stateTimer = currentState == previousState ? stateTimer+dt : 0;

        previousState=currentState;
        return region;
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
        shape.setRadius(15/PixPlat.PPM);

        fedf.shape = shape;
        b2body.createFixture(fedf);
    }

    public void update(float deltaTime){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void toRight(){
        b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), true);
        currentState=State.RUN;

    }

    public void toLeft(){
        b2body.applyLinearImpulse(new Vector2(-0.1f, 0), b2body.getWorldCenter(), true);
        currentState=State.RUN;
    }

    public boolean jump(){
        if (currentState==State.RUN || currentState==State.STAND || previousState!=State.JMP ) {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            currentState=State.JMP;
            previousState=State.JMP;
            return true;
        }
        else return false;
    }

}
