package com.jordan.lemongame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.util.Log;

import com.jordan.framework.Screen;
import com.jordan.framework.implementation.AndroidGame;

//main activity
public class SampleGame extends AndroidGame {

	@Override
	public Screen getInitScreen() {

        Assets.fileLocation = getFilesDir();

		return new SplashLoadingScreen(this);
	}

	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

	@Override
	public void onResume() {
		super.onResume();
        if (Assets.loadedMusic)
		    Assets.theme.play();
	}

	@Override
	public void onPause() {
		super.onPause();
        if (Assets.theme != null)
		    Assets.theme.pause();
	}
	
	
}