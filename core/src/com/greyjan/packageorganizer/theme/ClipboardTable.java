/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greyjan.packageorganizer.theme;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.greyjan.packageorganizer.utils.Assets;

/**
 *
 * @author Jan Fic
 */
public class ClipboardTable extends Table {

    Skin skin;
    Drawable clipBoard;
    NinePatch paper;

    public ClipboardTable() {
        super();
        top();
        skin = Assets.GetInstance().get("skin/packageSkin.json");
        clipBoard = skin.getDrawable("packageBackground");
        paper = skin.getPatch("paper");
        this.setBackground(clipBoard);
        
        ClipActor clip = new ClipActor();
        this.add(clip).row();
    }
    @Override
    protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
        super.drawBackground(batch, parentAlpha, x, y); //To change body of generated methods, choose Tools | Templates.
        paper.draw(batch, x + 10, y + 10, getWidth() - 20 , getHeight() - 30);
    }

    
    class ClipActor extends Actor {

        TextureRegion clipTexture;

        public ClipActor() {
            clipTexture = skin.getSprite("clip");
            this.setBounds(0, 0, clipTexture.getRegionWidth(), (float) (clipTexture.getRegionHeight() / 1.75 - 1));
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(clipTexture, getX(), getY(), getWidth(), clipTexture.getRegionHeight());
        }
    }
}
