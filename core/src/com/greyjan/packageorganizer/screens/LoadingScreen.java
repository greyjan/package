package com.greyjan.packageorganizer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.greyjan.packageorganizer.PackageOrganizer;
import com.greyjan.packageorganizer.theme.Theme;
import com.greyjan.packageorganizer.utils.Assets;
import com.greyjan.packageorganizer.utils.LevelAssets;
import com.greyjan.packageorganizer.utils.PackageManager;

public class LoadingScreen implements Screen {

    final PackageOrganizer game;

    protected BitmapFont font;
    protected SpriteBatch batch;
    private final Stage stage;

    public interface ILoadingListener {
        public void OnFinished();
    }

    private ILoadingListener loadingListener;
    private LevelAssets.ILoadListener loadListener;

    private LevelAssets assets = Assets.GetInstance();

    public LoadingScreen(PackageOrganizer game) {
        System.out.println("Making Loading Screen...");

        this.game = game;
        PackageManager.loadPackages();
        stage = new Stage(new ExtendViewport(PackageOrganizer.WIDTH / 2, PackageOrganizer.HEIGHT / 2));
        loadListener = new LevelAssets.ILoadListener() {

            @Override
            public void OnBegin() {

            }

            @Override
            public void OnLoading(float value) {

            }

            @Override
            public void OnFinished() {
                assets.removeLoadListener(loadListener);
                loadListener = null;

                if (loadingListener != null) {
                    loadingListener.OnFinished();
                }
                LoadingScreen.this.dispose();
            }

        };

        assets.addLoadListener(loadListener);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Color c = Theme.BG_COLOR;
        Gdx.gl20.glClearColor(c.r, c.g, c.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        assets.update();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void setILoadingListener(ILoadingListener loadingListener) {
        this.loadingListener = loadingListener;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
