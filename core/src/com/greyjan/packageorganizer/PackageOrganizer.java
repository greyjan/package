package com.greyjan.packageorganizer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.greyjan.packageorganizer.screens.LoadingScreen;
import com.greyjan.packageorganizer.screens.SplashScreen;

public class PackageOrganizer extends Game {

    public static final int WIDTH = 1000, HEIGHT = 800;
    private SplashScreen splashScreen;
    private LoadingScreen loadingScreen;

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);

        loadingScreen.setILoadingListener(new LoadingScreen.ILoadingListener() {
            @Override
            public void OnFinished() {
                setScreen(new SplashScreen(PackageOrganizer.this));
            }
        });
        this.setScreen(loadingScreen);
    }

    @Override
    public void render() {
        super.render();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
    }
}
