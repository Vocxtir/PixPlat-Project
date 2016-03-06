package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PixPlat;
import com.badlogic.gdx.graphics.Color;



/**
 * Created by Théo on 25/02/2016.
 */
public class HUD {
    public Stage stage;
    private Viewport viewport;//Viewport parce que quand le monde bouge on veut garder le HUD au meme endroit

    //private Integer worldTimer; //Si jamais on veut un timer
    //private float timeCount;
    private Integer money;
    private Integer life;
    private Integer spellPoint;
    private String worldLevelString;

    Label playerLife;
    Label playerSpellPoint;
    Label playerMoney;
    Label worldLevel;

    public HUD(SpriteBatch sb) {
        this.money=0;
        //Mettre Life au max de pdv du perso, 100 est arbitraire
        this.life=100;

        //Mettre spellPoint au max de spellpoint du perso, 100 est arbitraire
        this.spellPoint=100;

        //Mettre le bon nom de niveau evidemment
        this.worldLevelString = "1-1";

        this.viewport= new FitViewport(PixPlat.V_WIDTH,PixPlat.V_HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport,sb);
        Table table = new Table();
        table.top();//Sur le top du stage
        table.setFillParent(true);//La table fait la taille du stage

      //  String life = "Life +"
        //Formatage des labels
        this.playerLife = new Label("Life :"+String.format("%03d", this.life), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE ));
        this.playerSpellPoint = new Label("SpellPoints :"+String.format("%03d", this.spellPoint), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE ));
        this.playerMoney = this.playerSpellPoint = new Label("Pix :"+String.format("%05d", this.money), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE ));
        this.worldLevel = this.playerSpellPoint = new Label("Level :"+this.worldLevelString, new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE ));

        //repartion des labels sur la table
        //expandX() : etale le label sur toute la longueur de l'ecran, pad permet de subdivise
        table.add(playerLife).expandX().padTop(5);
        table.add(playerMoney).expandX().padTop(5);
        table.add(worldLevel).expandX().padTop(5);

        //ajout d'une nouvelle ligne dans la table, les labels sont donc en dessous des premiers
        table.row();

        table.add(playerSpellPoint).expandX();

        //Ajouter la table au stage
        stage.addActor(table);

    }

}
