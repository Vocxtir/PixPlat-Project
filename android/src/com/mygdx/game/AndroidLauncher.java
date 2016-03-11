package com.mygdx.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.mygdx.game.MainMenuActivity ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;



public class AndroidLauncher extends AndroidApplication {
	AndroidApplicationConfiguration config ;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences sharedPref = getSharedPreferences("PixSave", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();

		config  = new AndroidApplicationConfiguration();
		initialize(new PixPlat(), config);

	}

	@Override
	protected void onPause(){
		super.onPause();
		MainMenuActivity.getSong().pause() ;
	}

	@Override
	protected void onResume(){
		super.onResume();
		MainMenuActivity.getSong().start(); ;
	}

	public void helloGit(){
		//Something's wrong...
		return ;
	}
}
