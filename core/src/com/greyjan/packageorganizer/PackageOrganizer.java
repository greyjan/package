package com.greyjan.packageorganizer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.greyjan.packageorganizer.screens.LoadingScreen;
import com.greyjan.packageorganizer.screens.MainMenuScreen;
import com.greyjan.packageorganizer.screens.SplashScreen;
import com.greyjan.packageorganizer.utils.PackageManager;

public class PackageOrganizer extends Game {

    public static final int WIDTH = 1000, HEIGHT = 800;
    private SplashScreen splashScreen;
    private LoadingScreen loadingScreen;

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        splashScreen = new SplashScreen(this);
        loadingScreen.setILoadingListener(new LoadingScreen.ILoadingListener() {
            @Override
            public void OnFinished() {
                PackageManager.loadPackages();
                setScreen(new MainMenuScreen(PackageOrganizer.this));
            }
        });
        this.setScreen(loadingScreen);
        //System.out.println(PackageManager.getPATH().file().getAbsolutePath());
    }
    
    @Override
    public void render() {
        super.render();
        //System.out.println(Gdx.app.getJavaHeap()/1000/1000);
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

    }

    @Override
    public void dispose() {

    }

}
