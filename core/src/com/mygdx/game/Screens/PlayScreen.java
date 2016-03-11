package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Outils.B2worldMaker;
import com.mygdx.game.Outils.InputHandler;
import com.mygdx.game.PixPlat;
import com.mygdx.game.Scenes.HUD;
import com.mygdx.game.Sprites.Hero;

import javax.naming.Context;

/**
 * Created by Théo on 25/02/2016.
 */
public class PlayScreen implements Screen {
    private OrthographicCamera gameCam;
    private Viewport gamePort;//ViewPort permet d'arranger l'affichage selon la taille de l'ecran
    private PixPlat game;
    private HUD hud;
    private Hero player;
    private InputHandler input;

    private TextureAtlas atlas;

    //Tiled map
    private TmxMapLoader mapLoader;//le chargeur de map
    private TiledMap map;//la map
    private OrthogonalTiledMapRenderer renderer;

    //Box2d
    private World world;
    private Box2DDebugRenderer b2dr;


    public Hero getPlayer(){
        return this.player;
    }
    public PlayScreen(PixPlat PP){
        this.atlas = new TextureAtlas("Charac/PixPlat.pack");
        this.game = PP;
       // texture = new Texture("welcome.jpg");

        gameCam=new OrthographicCamera();
        //Permet de garder les ratios visuel (en cas de zoom/dezoom
        gamePort = new FitViewport(PixPlat.V_WIDTH / PixPlat.PPM, PixPlat.V_HEIGHT / PixPlat.PPM, gameCam);



        //Charge la map
        this.mapLoader = new TmxMapLoader();
        this. map = mapLoader.load("Level1.tmx");
        this. renderer = new OrthogonalTiledMapRenderer(map, 1 / PixPlat.PPM);

        //Permet de centrer la caméra au début du niveau
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);//Etre sur de commencer au DEBUT DE LA MAP
        //Et pas au centre X Y O,O

        this.world = new World(new Vector2(0, -10), true);
        this.b2dr = new Box2DDebugRenderer();




        //Le monde
        new B2worldMaker(this);

        //Le perso
        this.player = new Hero(world, this);
        //le HUD
        this.hud = new HUD(game.batch, this.player);

        this.input = new InputHandler(this);
        Gdx.input.setInputProcessor(this.input);

    }

    public TextureAtlas getAtlas(){
        return this.atlas;
    }

    public OrthographicCamera getGameCam(){
        return this.gameCam;
    }

    public OrthogonalTiledMapRenderer getRenderer(){
        return this.renderer;
    }

/*
    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2){
             player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);//Vector2(vitesse dans x, vitesse dans y), direction
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2){
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);//Vector2(vitesse dans x, vitesse dans y), direction
        }
    }
*/

    /*
    //Update le monde
    public void update(float dt){
        handleInput(dt);

        world.step(1 / 60f, 6, 2);//Comment deux corps réagissent quand ils se cognent

        player.update(dt);

        gameCam.position.x = player.b2body.getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);
    }
    */

    @Override
    public void show() {

    }

    public TiledMap getMap(){
        return this.map;
    }

    public World getWorld(){
        return this.world;
    }


    @Override
    public void render(float delta) {
     //   update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//Clean le screen

        renderer.render();

        b2dr.render(world, gameCam.combined);


        //Ce qui va etre montré
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    private void endScore(int score){

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);//Permet d'adapter le viewport dans le cas ou on resize l'ecran
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
