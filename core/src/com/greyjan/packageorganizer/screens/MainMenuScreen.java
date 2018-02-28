package com.greyjan.packageorganizer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.greyjan.packageorganizer.PackageOrganizer;
import com.greyjan.packageorganizer.theme.PackageTable;
import com.greyjan.packageorganizer.theme.Theme;
import com.greyjan.packageorganizer.utils.Assets;

/**
 * Created by Jan Fic on 1/10/2018.
 */
public class MainMenuScreen extends Stage implements Screen {

    PackageOrganizer game;
    Skin skin;
    Table table;

    public MainMenuScreen(PackageOrganizer g) {
        super(Theme.getVP());
        game = g;
        skin = Assets.GetInstance().get("skin/packageSkin.json");
        table = new Table();
        makeLayout();
        
    }

    private void makeLayout() {
        table.setFillParent(true);
        table.center();

        PackageTable titleTable = new PackageTable();
        titleTable.pad(5);
        Label title = new Label("Package", skin, "title");
        Label subTitle = new Label("Project Organizer", skin);
        titleTable.add(title).row();
        titleTable.add(subTitle).row();
        table.add(titleTable).width(200).pad(10).row();

        PackageTable menuTable = new PackageTable();
        menuTable.defaults().expandX().fillX().pad(3);
        menuTable.pad(5);
        TextButton newProjectButton = new TextButton("New Project", skin);
        newProjectButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new NewProjectScreen(game));
                MainMenuScreen.this.dispose();
            }
        });
        menuTable.add(newProjectButton).row();
        TextButton loadProjectButton = new TextButton("Load Project", skin);
        loadProjectButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LoadProjectScreen(game));
                MainMenuScreen.this.dispose();
            }

        });
        menuTable.add(loadProjectButton).row();
        TextButton optionsButton = new TextButton("Options", skin);
        menuTable.add(optionsButton).row();
        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }

        });
        menuTable.add(exitButton).row();
        table.add(menuTable).width(200).row();

        this.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Color c = Theme.BG_COLOR;
        Gdx.gl.glClearColor(c.r, c.g, c.b, c.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        act();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
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
