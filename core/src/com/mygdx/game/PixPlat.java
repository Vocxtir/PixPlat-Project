package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Screens.PlayScreen;
import com.sun.prism.image.ViewPort;

import java.awt.GraphicsDevice;


public class PixPlat extends Game {


	public SpriteBatch batch;//conteneur des images du jeu, très gourmand en mémoire, 1 public dans la main class de l'app
	public static final int V_WIDTH = 500 ;//TROUVER COMMENT AVOIR LES COORDONNEES
	public static final int V_HEIGHT = 280;
	public static final float PPM = 100;

	@Override
	public void create () {
		batch = new SpriteBatch();//Creer le batch
		setScreen(new PlayScreen(this));//Set l'image de départ
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}
	@Override
	public void render () {
		super.render();
		/*
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		*/

	}
}
