package com.mygdx.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;



public class AndroidLauncher extends AndroidApplication {
	AndroidApplicationConfiguration config ;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		config  = new AndroidApplicationConfiguration();
		initialize(new PixPlat(), config);

	}

	public void helloGit(){
		//Something's wrong...
		return ;
	}
}
