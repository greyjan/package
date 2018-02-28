/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greyjan.packageorganizer.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.greyjan.packageorganizer.project.Package;
import java.io.ObjectOutputStream;

/**
 *
 * @author Jan Fic
 */
public class PackageManager {
    
    private final static FileHandle PATH = Gdx.files.local("Package/");
    
    public static ArrayList<Package> packages = new ArrayList<Package>();
    
    public static final FileHandle getPATH() {
        return PATH;
    }
    
    public static void loadPackages() {
        FileHandle path = PATH.child("packages/");
        for (FileHandle file : path.list()) {
            try {
                ObjectInputStream is = new ObjectInputStream(file.read());
                Package p = (Package) is.readObject();
                packages.add(p);
            } catch (IOException ex) {
                Logger.getLogger(PackageManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PackageManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(Package p : packages) {
            System.out.println("Loaded Package : " + p.getProjectName());
            System.out.println(p.toString());
        }
    }
    
    public static void savePackages() {
        FileHandle path = PATH.child("packages/");
        for(Package p : packages) {
            try {
                FileHandle file = path.child(p.getProjectName() + ".ser");
                ObjectOutputStream os = new ObjectOutputStream(file.write(false));
                os.writeObject(p);
                System.out.println("Saved " + p.toString());
            } catch (IOException ex) {
                Logger.getLogger(PackageManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
