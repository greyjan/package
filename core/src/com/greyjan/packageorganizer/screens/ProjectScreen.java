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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.greyjan.packageorganizer.PackageOrganizer;
import com.greyjan.packageorganizer.project.Package;
import com.greyjan.packageorganizer.theme.ClipboardTable;
import com.greyjan.packageorganizer.theme.PackageTable;
import com.greyjan.packageorganizer.theme.Theme;

/**
 *
 * @author Jan Fic
 */
public class ProjectScreen extends Stage implements Screen {

    PackageOrganizer game;
    Package project;

    Table table;

    public ProjectScreen(PackageOrganizer game, Package project) {
        super(Theme.getVP());
        this.game = game;
        this.project = project;
        table = new Table();
        makeStage();
    }

    private void makeStage() {
        table.setFillParent(true);
        table.pad(5);
        table.top();
        table.defaults().pad(5);
        
        PackageTable titleTable = new PackageTable();
        titleTable.pad(2);
        titleTable.defaults().pad(2);
        Label packageLabel = new Label(project.getProjectName() + " Package", Theme.getSkin(), "title");
        Label creatorLabel = new Label("Created by " + project.getUserName(), Theme.getSkin());
        titleTable.add(packageLabel).row();
        titleTable.add(creatorLabel);

        table.add(titleTable).colspan(2).row();

        ClipboardTable mainTable = new ClipboardTable();
        table.add(mainTable).grow();
        table.add().grow();
        
        
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
