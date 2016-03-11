package com.mygdx.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MainMenuActivity extends Activity {
    public static MediaPlayer startSong ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences("PixSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        startSong = MediaPlayer.create(this, R.raw.cavestory_mainmenu) ;
        int defaultValue = 0 ;
        int highScore = sharedPref.getInt(getString(R.string.saved_high_score), defaultValue);
        setContentView(R.layout.activity_main_menu);

        TextView lastScoreText = (TextView) findViewById(R.id.lastScoreText);
        String hScore = getResources().getString(R.string.lastScore)+ " " + Integer.toString(highScore) ;
        lastScoreText.setText(hScore);

        startSong.start();


    }

    @Override
    protected void onPause(){
        super.onPause();

    }

    /**
     *
     * @return MediaPlayer
     */
    public static MediaPlayer getSong(){
        return startSong ;
    }
    public void androidLauncher(View v){
        //log.i("Somewhat launching");
        startActivity(new Intent(this, AndroidLauncher.class));
    }
}
