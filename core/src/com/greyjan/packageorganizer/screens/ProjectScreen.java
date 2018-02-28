package com.greyjan.packageorganizer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Align;
import com.greyjan.packageorganizer.PackageOrganizer;
import com.greyjan.packageorganizer.project.Package;
import com.greyjan.packageorganizer.project.Task;
import com.greyjan.packageorganizer.project.TaskList;
import com.greyjan.packageorganizer.theme.ClipboardTable;
import com.greyjan.packageorganizer.theme.PackageTable;
import com.greyjan.packageorganizer.theme.Theme;
import com.greyjan.packageorganizer.utils.PackageManager;

/**
 * @author Jan Fic
 */
public class ProjectScreen extends Stage implements Screen {

    PackageOrganizer game;
    Package project;

    Table table;
    ClipboardTable mainTable;
    PackageTable partTable;
    TextButton newTaskPartButton;
    TextButton newTaskListButton;

    public ProjectScreen(PackageOrganizer game, Package project) {
        super(Theme.getVP());
        this.game = game;
        this.project = project;
        table = new Table();
        makeStage();
        setDebugAll(false);
    }

    private void makeStage() {
        table.setFillParent(true);
        table.pad(5);
        table.top();
        table.defaults().pad(5);

        PackageTable titleTable = new PackageTable();
        titleTable.pad(2);
        titleTable.defaults().pad(2);
        Label packageLabel = new Label(project.getProjectName(), Theme.getSkin(), "title");
        Label creatorLabel = new Label("Package\nCreated by " + project.getUserName(), Theme.getSkin());
        creatorLabel.setAlignment(Align.center);
        TextButton saveButton = new TextButton("Save " + project.getProjectName(), Theme.getSkin());
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PackageManager.savePackages();
            }
        });
        titleTable.add(packageLabel).row();
        titleTable.add(creatorLabel).row();
        titleTable.add(saveButton);

        table.add(titleTable).left().colspan(3).growX().row();

        partTable = new PackageTable();
        partTable.defaults().pad(3).top().fill();
        partTable.top();
        Label sectionHeader = new Label("Project Parts", Theme.getSkin(), "subTitle");
        partTable.add(sectionHeader).pad(4).row();
        newTaskPartButton = new TextButton("New Task", Theme.getSkin());
        newTaskPartButton.addListener(new ClickListener() {

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
                        mainTable.add(task.getActor()).row();
                        project.addProjectPart(task);
                        window.remove();
                    }
                });
                table.add(doneButton, cancelButton);
                window.add(table).grow();
                ProjectScreen.this.addActor(window);
            }
        });
        partTable.add(newTaskPartButton).row();

        newTaskListButton = new TextButton("New Task List", Theme.getSkin());
        newTaskListButton.addListener(new ClickListener() {

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
                Label title = new Label("Make a New Task List", Theme.getSkin());
                table.add(title).colspan(2).row();
                final TextField taskListTitleField = new TextField("", Theme.getSkin());
                taskListTitleField.setMessageText("Task List Name");
                table.add(taskListTitleField).colspan(2).row();
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
                        TaskList taskList = new TaskList(taskListTitleField.getText().trim());
                        mainTable.add(taskList.getActor()).row();
                        project.addProjectPart(taskList);
                        window.remove();
                    }
                });
                table.add(doneButton, cancelButton);
                window.add(table).grow();
                ProjectScreen.this.addActor(window);
            }
        });
        partTable.add(newTaskListButton).row();
        //table.add(partTable).growY();

        mainTable = new ClipboardTable();
        mainTable.defaults().pad(2).padLeft(5).padRight(5).padBottom(5).growX().left();
        mainTable.columnDefaults(0).left();
        for (int i = 0; i < project.getProjectParts().size(); i++) {
            mainTable.add(project.getProjectPart(i).getActor());
        }
        table.add(mainTable).grow();
        table.add(partTable).growY().row();

        addActor(table);
    }

    private void makeDragAndDrop() {
        DragAndDrop dandd = new DragAndDrop();
        dandd.addSource(new Source(newTaskPartButton) {
            @Override
            public Payload dragStart(InputEvent event, float x, float y, int pointer) {
                Payload payload = new Payload();

                payload.setObject("Payload");
                payload.setDragActor(newTaskPartButton);

                return payload;
            }
        });
        dandd.addTarget(new Target(mainTable) {
            @Override
            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }

            @Override
            public void reset(Source source, Payload payload) {
                getActor().setColor(Color.WHITE);
            }

            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {
                System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
            }
        });
        dandd.addTarget(new Target(partTable) {
            @Override
            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }

            @Override
            public void reset(Source source, Payload payload) {
                getActor().setColor(Color.WHITE);
            }

            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {
                System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
                partTable.add(payload.getDragActor()).row();
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Color c = Theme.BG_COLOR;
        Gdx.gl.glClearColor(c.r, c.g, c.b, c.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        act();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

}
