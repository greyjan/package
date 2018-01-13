package com.greyjan.packageorganizer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.greyjan.packageorganizer.PackageOrganizer;
import com.greyjan.packageorganizer.utils.Assets;

/**
 * Created by Jan Fic on 1/10/2018.
 */
public final class SplashScreen extends Stage implements Screen {

    private PackageOrganizer game;
    private Table table;

    public SplashScreen(PackageOrganizer g) {
        super(new FitViewport(PackageOrganizer.WIDTH, PackageOrganizer.HEIGHT));
        game = g;
        table = new Table();
        makeLayout();
    }

    private void makeLayout() {
        table.setFillParent(true);
        final Texture texture = Assets.GetInstance().get("art/splashScreen/logo.png");
        Actor actor = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                Color c = getColor();
                batch.setColor(c);
                batch.draw(texture, getX(), getY());
            }
        };
        actor.setBounds(0, 0, texture.getWidth(), texture.getHeight());
        actor.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(0.5f), Actions.fadeIn(2), Actions.delay(0.5f), Actions.fadeOut(2), Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(new MainMenuScreen(game));
            }
        })));

        table.add(actor);

        table.center();
        this.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Color c = Color.GRAY;
        Gdx.gl.glClearColor(c.r, c.g, c.b, c.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        act();
        draw();
        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        this.getViewport().update(width, height);
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
}
