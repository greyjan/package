/**
 * ****************************************************
 * Copyright (C) 2015 Mirco Timmermann - All Rights Reserved
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Mirco Timmermann <mtimmermann@gmx.de>, December 2016
 * <p>
 * game file *****************************************************
 */
package com.greyjan.packageorganizer.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/*
 * singleton for assets on loading. 
 * (Can be used too instead of using the build in level assets loader of the 
 * levels itslelf. Often you dont want to use the level asset loader because its nicer 
 * to have one loading screen on the start on smaller games)
 */
public class Assets extends LevelAssets {

    private static Assets INSTANCE = null;

    private Assets() {

        this.addAsset("art/splashScreen/logo.png", Texture.class);
        this.addAsset("skin/packageSkin.json", Skin.class);
        /*
        Texture.class
		Sound.class
		Music.class
		TextureAtlas.class
		BitmapFont.class
		FreeTypeFontGenerator.class
		Skin.class
		TiledMap.class
		ShaderProgram.class
         */
    }

    public static Assets GetInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Assets();
        }
        return INSTANCE;
    }

    @Override
    public void dispose() {
        super.dispose();

        INSTANCE = null;
    }
}
