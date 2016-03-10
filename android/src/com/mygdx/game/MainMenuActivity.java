package com.mygdx.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MainMenuActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("PixSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int defaultValue = 0 ;
        int highScore = sharedPref.getInt(getString(R.string.saved_high_score), defaultValue);
        setContentView(R.layout.activity_main_menu);

        TextView lastScoreText = (TextView) findViewById(R.id.lastScoreText);
        String hScore = getResources().getString(R.string.lastScore)+ " " + Integer.toString(highScore) ;
        lastScoreText.setText(hScore);


    }

    public void androidLauncher(View v){
        startActivity(new Intent(this, AndroidLauncher.class));
    }
}
