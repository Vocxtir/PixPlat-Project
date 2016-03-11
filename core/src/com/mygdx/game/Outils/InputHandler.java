package com.mygdx.game.Outils;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Théo on 11/03/2016.
 */
public class InputHandler implements InputProcessor {
    private PlayScreen screen;

    public InputHandler(PlayScreen screen){
        this.screen = screen;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(screenX>850){
            screen.getPlayer().toRight();
            screen.getWorld().step(1 / 60f, 6, 2);
            screen.getGameCam().position.x = screen.getPlayer().b2body.getPosition().x;
            screen.getGameCam().update();
            screen.getRenderer().setView(screen.getGameCam());

        }
        else if(screenX<850){
            screen.getPlayer().toLeft();

            screen.getWorld().step(1 / 60f, 6, 2);//Comment deux corps réagissent quand ils se cognent

            screen.getGameCam().position.x = screen.getPlayer().b2body.getPosition().x;
            screen.getGameCam().update();
            screen.getRenderer().setView(screen.getGameCam());


        }
        if (screenY>1000){

            if(screen.getPlayer().jump()){

                screen.getWorld().step(1 / 60f, 6, 2);//Comment deux corps réagissent quand ils se cognent

                screen.getGameCam().position.x = screen.getPlayer().b2body.getPosition().x;
                screen.getGameCam().update();
                screen.getRenderer().setView(screen.getGameCam());
            }
        }
        return true;


    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
