/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greyjan.packageorganizer.project;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 *
 * @author Jan Fic
 */
public class Task implements PackagePart{

    private String title, description;
    private boolean completed;

    public Task(String title, String description, boolean isDone) {
        this.title = title;
        this.description = description;
        this.completed = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
    
    public Label makeLabel() {
       return null; 
    }
}
