package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PixPlat;

/**
 * Created by rahman on 25/02/2016.
 */
public class PlayScreen implements Screen {
    public Texture texture ;
    private PixPlat game ;
    private OrthographicCamera camera ;
    private Viewport gamePort ;

    public PlayScreen(PixPlat g){
        this.game = g ;

        texture = new Texture("CAVESTORY-001.jpg");
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gamePort = new StretchViewport(800, 480, camera);

        camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        game.batch.begin();
        game.batch.draw(this.texture, 150, 150);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
