package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PixPlat;
import com.mygdx.game.Scenes.HUD;

/**
 * Created by Théo on 25/02/2016.
 */
public class MainMenuScreen implements Screen {
    private OrthographicCamera camera;
    private Viewport gamePort;//ViewPort permet d'arranger l'affichage selon la taille de l'ecran
    private PixPlat game;
    private HUD hud;
    Texture texture;

    public MainMenuScreen(PixPlat PP){
        this.game = PP;
        texture = new Texture(Gdx.files.internal("data/startscreen.png"));

        camera   = new OrthographicCamera();
        gamePort = new FitViewport(PixPlat.V_WIDTH, PixPlat.V_HEIGHT, camera);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(this.texture,0, 0);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        }

        //Gdx.gl.glClearColor(0, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//Clean le screen
        /*
        game.batch.setProjectionMatrix(gameCam.combined);//Render seulement ce que la camera peut voir
        game.batch.begin();//Ouvre le batch
        game.batch.draw(texture, 0, 0);//Draw la texture
        game.batch.end();//Ferme le batch
        */

    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width, height) ;
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

    public void clearScreen(){
        Gdx.gl.glClearColor (1, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
    }

}
