package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
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
    private TmxMapLoader mapLoader;//le chargeur de map
    private TiledMap map;//la map
    private OrthogonalTiledMapRenderer renderer;
    //Texture texture;

    public PlayScreen(PixPlat PP){
        this.game = PP;
       // texture = new Texture("welcome.jpg");

        gameCam=new OrthographicCamera();
        gamePort = new FitViewport(PixPlat.V_WIDTH, PixPlat.V_HEIGHT, gameCam);
        this.hud = new HUD(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);//Etre sur de commencer au DEBUT DE LA MAP
        //Et pas au centre X Y O,O
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
