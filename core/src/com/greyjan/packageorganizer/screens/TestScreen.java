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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.greyjan.packageorganizer.PackageOrganizer;
import com.greyjan.packageorganizer.theme.Theme;

/**
 *
 * @author Jan Fic
 */
public class TestScreen extends Stage implements Screen {

    private Table table;
    private final PackageOrganizer game;

    public TestScreen(PackageOrganizer g) {
        this.game = g;
        table = new Table();
        makeLayout();
    }

    private void makeLayout() {

        makeDragAndDrop();

        table.setFillParent(true);
        //this.setDebugAll(true);

        //this.addActor(table);
    }

    private void makeDragAndDrop() {
//        DragAndDrop dandd = new DragAndDrop();
//        dandd.addSource(new Source(newProjectButton) {
//            @Override
//            public Payload dragStart(InputEvent event, float x, float y, int pointer) {
//                Payload payload = new Payload();
//
//                payload.setObject("Payload");
//                payload.setDragActor(newProjectButton);
//
//                Label validLabel = new Label("Some payload!", Theme.getSkin());
//                validLabel.setColor(0, 1, 0, 1);
//                payload.setValidDragActor(newProjectButton);
//
//                Label invalidLabel = new Label("Some payload!", Theme.getSkin());
//                invalidLabel.setColor(1, 0, 0, 1);
//                payload.setInvalidDragActor(invalidLabel);
//
//                return payload;
//            }
//        });
//        dandd.addTarget(new Target(pt2) {
//            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
//                getActor().setColor(Color.GREEN);
//                return true;
//            }
//
//            public void reset(Source source, Payload payload) {
//                getActor().setColor(Color.WHITE);
//            }
//
//            public void drop(Source source, Payload payload, float x, float y, int pointer) {
//                System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
//                pt2.add(payload.getDragActor()).row();
//            }
//        });
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
