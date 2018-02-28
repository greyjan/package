package com.greyjan.packageorganizer.project;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.greyjan.packageorganizer.theme.Theme;
import java.io.Serializable;

/**
 *
 * @author Jan Fic
 */
public class Task implements Serializable, PackagePart {

    private final String title, description;
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

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append("Task : ").append(title);
        r.append("\n\tDescription : ").append(description);
        r.append("\n\tCompleted : ").append(completed);
        return r.toString();
    }
    
    @Override
    public Actor getActor() {
        final CheckBox label = new CheckBox(title + " : \n\t" + description, Theme.getSkin());
        CheckBoxStyle style = new CheckBoxStyle(label.getStyle());
        style.up = Theme.getSkin().getDrawable("paperTextArea");
        label.setStyle(style);
        label.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                completed = label.isChecked();
            }
        });
        label.align(Align.left);
        label.setChecked(completed);
        label.pad(2);
        return label;
    }
}
