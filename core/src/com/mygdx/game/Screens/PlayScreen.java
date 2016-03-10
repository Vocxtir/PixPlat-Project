package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.mygdx.game.PixPlat;
import com.mygdx.game.Scenes.HUD;

/**
 * Created by Théo on 25/02/2016.
 */
public class PlayScreen implements Screen {
    private OrthographicCamera gameCam;
    private Viewport gamePort;//ViewPort permet d'arranger l'affichage selon la taille de l'ecran
    private PixPlat game;
    private HUD hud;

    //Tiled map
    private TmxMapLoader mapLoader;//le chargeur de map
    private TiledMap map;//la map
    private OrthogonalTiledMapRenderer renderer;

    //Box2d
    private World world;
    private Box2DDebugRenderer b2dr;
    //Texture texture;

    public PlayScreen(PixPlat PP){
        this.game = PP;
       // texture = new Texture("welcome.jpg");

        gameCam=new OrthographicCamera();
        //Permet de garder les ratios visuel (en cas de zoom/dezoom
        gamePort = new FitViewport(PixPlat.V_WIDTH, PixPlat.V_HEIGHT, gameCam);

        //le HUD
        this.hud = new HUD(game.batch);

        //Charge la map
        this.mapLoader = new TmxMapLoader();
        this. map = mapLoader.load("Level1.tmx");
        this. renderer = new OrthogonalTiledMapRenderer(map);

        //Permet de centrer la caméra au début du niveau
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);//Etre sur de commencer au DEBUT DE LA MAP
        //Et pas au centre X Y O,O

        this.world = new World(new Vector2(0, 0), true);
        this.b2dr = new Box2DDebugRenderer();

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
            bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
        // A COMPLETER POUR LES PIXS SELON LE MEME PRINCIPE
        //POUR LES PIXS
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){

        }



    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            gameCam.position.x += 100 * dt;
        }
    }

    //Update le monde
    public void update(float dt){
        handleInput(dt);
        gameCam.update();
        renderer.setView(gameCam);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//Clean le screen

        renderer.render();

        b2dr.render(world, gameCam.combined);


        //Ce qui va etre montré
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        /*
        game.batch.setProjectionMatrix(gameCam.combined);//Render seulement ce que la camera peut voir
        game.batch.begin();//Ouvre le batch
        game.batch.draw(texture, 0, 0);//Draw la texture
        game.batch.end();//Ferme le batch
        */
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

    }
}
