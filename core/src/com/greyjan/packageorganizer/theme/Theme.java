/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greyjan.packageorganizer.theme;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.greyjan.packageorganizer.utils.Assets;

/**
 *
 * @author Jan Fic
 */
public class Theme {

    public static final Color BG_COLOR = Color.valueOf("e5dcc2");

    public final static Viewport getVP() {
        //return new ExtendViewport(PackageOrganizer.WIDTH / 2, PackageOrganizer.HEIGHT / 2);
//        return new ScalingViewport(Scaling.none,PackageOrganizer.WIDTH / 2, PackageOrganizer.HEIGHT / 2);
//        return new FitViewport(PackageOrganizer.WIDTH / 2, PackageOrganizer.HEIGHT / 2);
        return new ScreenViewport();
    }

    public final static Skin getSkin() {
        return Assets.GetInstance().get("skin/packageSkin.json");
    }

    public static final TextureAtlas getPack() {
        return null;
    }
}
