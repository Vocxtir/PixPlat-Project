package com.mygdx.game.Outils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PixPlat;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Théo on 11/03/2016.
 */
public class B2worldMaker {
    public B2worldMaker(PlayScreen playscreen){
        World world = playscreen.getWorld();
        TiledMap map = playscreen.getMap();

        //Pour gerer les objets du mondes
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //On recupere les "OBJETS" definis dans TiledMapEditor, index commence a 0 et part du bas des LAYERS, ici on veut le SOL
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){

            //On recup le rectangle du layer
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set( (rect.getX() + rect.getWidth()/2)/ PixPlat.PPM , (rect.getY() + rect.getHeight()/2)/ PixPlat.PPM);

            body = world.createBody(bdef);

            shape.setAsBox( (rect.getWidth()/2) / PixPlat.PPM, (rect.getHeight()/2) / PixPlat.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
        // A COMPLETER POUR LES PIXS SELON LE MEME PRINCIPE
        //POUR LES PIXS
        /*
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){

            //On recup le rectangle du layer
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Pix(world, map, rect);
        }
        */
    }
}
