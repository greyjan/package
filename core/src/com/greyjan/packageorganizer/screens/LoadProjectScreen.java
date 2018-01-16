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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.greyjan.packageorganizer.PackageOrganizer;
import com.greyjan.packageorganizer.theme.PackageTable;
import com.greyjan.packageorganizer.theme.Theme;
import com.greyjan.packageorganizer.utils.PackageManager;
import com.greyjan.packageorganizer.project.Package;

/**
 *
 * @author Jan Fic
 */
public class LoadProjectScreen extends Stage implements Screen{

    private PackageOrganizer game;
    Table table;

    public LoadProjectScreen(PackageOrganizer game) {
        super(Theme.getVP());
        this.game = game;
        this.table = new Table();
        makeLayout();
    }
    
    private void makeLayout() {
        table.setFillParent(true);
        table.center().top();
        table.defaults().prefWidth(200).pad(5);
        table.pad(16);
        
        Table infoTable = new Table();
        infoTable.defaults().growX().space(5).top();
        PackageTable titleTable = new PackageTable();
        titleTable.defaults().pad(3);
        Label title = new Label("Load Package", Theme.getSkin(), "title");
        Label subTitle = new Label("Continue working on a project", Theme.getSkin());
        titleTable.add(title).row();
        titleTable.add(subTitle).row();
        infoTable.add(titleTable).row();
        table.add(infoTable).row();
        

        PackageTable projects = new PackageTable();
        projects.top();
        projects.pad(5);
        projects.defaults().growX().prefHeight(50).pad(5);
        for(Package p : PackageManager.packages) {
            TextButton selectProject = new TextButton(p.getProjectName(),Theme.getSkin());
            projects.add(selectProject).row();
        }
        table.add(projects).growY().row();
        
        PackageTable footer = new PackageTable();
        TextButton cancelButton = new TextButton("Cancel",Theme.getSkin());
        cancelButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
                LoadProjectScreen.this.dispose();
            }
        
        });
        footer.add(cancelButton).pad(5);
        table.add(footer);
        
        addActor(table);
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
        getViewport().update(width, height);
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
