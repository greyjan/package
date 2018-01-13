package com.greyjan.packageorganizer.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.greyjan.packageorganizer.PackageOrganizer;


public class DesktopLauncher {
	public static void main (String[] arg) {

		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PackageOrganizer.WIDTH;
		config.height = PackageOrganizer.HEIGHT;
		config.addIcon("icon.png", Files.FileType.Internal);
		config.title = "Package";
		config.resizable = false;

		new LwjglApplication(new PackageOrganizer(), config);
	}
}
