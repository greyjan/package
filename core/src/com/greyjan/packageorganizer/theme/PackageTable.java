/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greyjan.packageorganizer.theme;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.greyjan.packageorganizer.utils.Assets;

/**
 *
 * @author Jan Fic
 */
public class PackageTable extends Table{
    
    Skin skin;
    
    NinePatch background;
    public PackageTable() {
        super();
        skin = Assets.GetInstance().get("skin/packageSkin.json");
        background = skin.getPatch("packageBackground");
    }

    @Override
    protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
        Color c = getColor();
        batch.setColor(c);
        super.drawBackground(batch, parentAlpha, x, y); //To change body of generated methods, choose Tools | Templates.
        background.draw(batch, x, y, getWidth(), getHeight());
    }
    
    
}
