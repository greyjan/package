package com.greyjan.packageorganizer.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.greyjan.packageorganizer.theme.Theme;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jan Fic
 */
public class TaskList implements Serializable, PackagePart {

    private ArrayList<Task> tasks;
    private String title;
    private transient Table table;

    public TaskList(String t) {
        this.title = t;
        tasks = new ArrayList<Task>();
        table = new Table(Theme.getSkin());
        table.setBackground("tablePaper");
        makeActor();
    }

    private void initializeTransientFields() {
        table = new Table(Theme.getSkin());
        table.setBackground("tablePaper");
        makeActor();
    }
    
    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        initializeTransientFields();
    }

    private void makeActor() {
        table.reset();
        table.defaults().growX().pad(1);
        Label l = new Label(title, Theme.getSkin(), "subTitle");
        l.setAlignment(Align.center);
        table.add(l).center().row();
        for (Task task : tasks) {
            table.add(task.getActor()).row();
        }
        TextButton newTaskButton = new TextButton("New Task", Theme.getSkin());
        ClickListener listener = new ClickListener() {
            Window window;

            @Override
            public void clicked(InputEvent event, float x, float y) {
                window = new Window("New Package Part", Theme.getSkin());
                window.setKeepWithinStage(true);
                window.setResizable(false);
                window.setModal(true);
                window.top();
                window.defaults().pad(2);
                window.setBounds(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 200, 400, 400);
                Table table = new Table(Theme.getSkin());
                table.defaults().pad(2);
                table.setBackground("paper");
                Label title = new Label("Make a New Task", Theme.getSkin());
                table.add(title).colspan(2).row();
                final TextField taskTitleField = new TextField("", Theme.getSkin());
                taskTitleField.setMessageText("Task Name");
                table.add(taskTitleField).colspan(2).row();
                final TextArea descriptionField = new TextArea("", Theme.getSkin(), "textArea");
                descriptionField.setMessageText("Description...");
                table.add(descriptionField).colspan(2).growY().row();
                TextButton doneButton = new TextButton("Done", Theme.getSkin());
                TextButton cancelButton = new TextButton("Cancel", Theme.getSkin());
                cancelButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        window.remove();
                    }
                });
                doneButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Task task = new Task(taskTitleField.getText().trim(), descriptionField.getText().trim(), false);
                        TaskList.this.addTask(task);
                        window.remove();
                    }
                });
                table.add(doneButton, cancelButton);
                window.add(table).grow();
                TaskList.this.table.getStage().addActor(window);
            }
        };
        newTaskButton.addListener(listener);
        table.add(newTaskButton);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(40);
        sb.append("TaskList : ").append(title);
        sb.append("\n\tTitle : ").append(title);
        sb.append("\n\tTasks : ").append(tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n\t\t").append(tasks.get(i).toString());
        }
        return sb.toString();
    }

    @Override
    public Actor getActor() {
        return table;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        makeActor();
    }
}
