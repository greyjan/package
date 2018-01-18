/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greyjan.packageorganizer.project;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.ArrayList;

/**
 *
 * @author Jan Fic
 */
public class ToDoList extends ArrayList<Task> implements PackagePart{
    public Table makeTable() {
        Table table = new Table();
        for(Task t : this) {
            table.add(t.makeLabel()).row();
        }
        return table;
    }
}
