/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greyjan.packageorganizer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.greyjan.packageorganizer.PackageOrganizer;
import com.greyjan.packageorganizer.theme.ClipboardTable;
import com.greyjan.packageorganizer.theme.PackageTable;
import com.greyjan.packageorganizer.theme.Theme;
import com.greyjan.packageorganizer.project.Package;
import com.greyjan.packageorganizer.utils.PackageManager;

/**
 *
 * @author Jan Fic
 */
public class NewProjectScreen extends Stage implements Screen {

    private final PackageOrganizer game;
    private final Table table;
    private Package p;
    TextField projectName;
    TextField creatorName;

    public NewProjectScreen(PackageOrganizer game) {
        super(Theme.getVP());
        this.game = game;
        table = new Table();
        makeLayout();
    }

    private void makeLayout() {
        table.setFillParent(true);
        table.top();
        table.pad(32);
        table.defaults().space(5);

        Table infoTable = new Table();
        infoTable.defaults().growX().space(5).top();
        PackageTable titleTable = new PackageTable();
        titleTable.defaults().pad(3);
        Label title = new Label("New Package", Theme.getSkin(), "title");
        Label subTitle = new Label("Start a new project", Theme.getSkin());
        titleTable.add(title).row();
        titleTable.add(subTitle).row();
        infoTable.add(titleTable).row();

        PackageTable menuTable = new PackageTable();
        menuTable.defaults().expandX().fillX().pad(3);
        menuTable.pad(5);
        TextButton createButton = new TextButton("Create", Theme.getSkin());
        createButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                String pn = projectName.getText();
                String cn = creatorName.getText();

                if (!pn.isEmpty() && !cn.isEmpty()) {
                    p = new Package(projectName.getText(), creatorName.getText());
                    PackageManager.packages.add(p);
                    PackageManager.savePackages();
                    System.out.println("Created new package: " + p.getProjectName());
                    game.setScreen(new MainMenuScreen(game));
                    NewProjectScreen.this.dispose();
                }
            }

        });
        menuTable.add(createButton).row();
        TextButton resetButton = new TextButton("Reset Form", Theme.getSkin());
        resetButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                NewProjectScreen.this.dispose();
                game.setScreen(new NewProjectScreen(game));
            }
        });
        menuTable.add(resetButton).row();
        TextButton exitButton = new TextButton("Cancel", Theme.getSkin());
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
                NewProjectScreen.this.dispose();
            }

        });
        menuTable.add(exitButton).row();
        infoTable.add(menuTable).expandY().fillY();
        table.add(infoTable).growY().top();

        ClipboardTable form = new ClipboardTable();
        form.defaults().space(5);
        Label titleOfForm = new Label("New Package", Theme.getSkin(), "subTitle");
        form.add(titleOfForm).row();
        projectName = new TextField("", Theme.getSkin());
        projectName.setMessageText("Project Name");
        form.add(projectName).row();
        creatorName = new TextField("", Theme.getSkin());
        creatorName.setMessageText("Creator Name");
        form.add(creatorName).row();
        table.add(form).expand().fill();

        this.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Color c = Theme.BG_COLOR;
        Gdx.gl20.glClearColor(c.r, c.g, c.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        act();
        draw();
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
