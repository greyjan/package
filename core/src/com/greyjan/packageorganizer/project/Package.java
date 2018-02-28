package com.greyjan.packageorganizer.project;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jan Fic
 */
public class Package implements Serializable {

    private final String projectName, userName;
    private final ArrayList<PackagePart> projectParts;

    public Package(String pN, String uN) {
        projectName = pN;
        userName = uN;
        projectParts = new ArrayList<PackagePart>();
    }

    public void addProjectPart(PackagePart part) {
        projectParts.add(part);
    }

    public String getProjectName() {
        return projectName;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<PackagePart> getProjectParts() {
        return projectParts;
    }

    public PackagePart getProjectPart(int i) {
        return projectParts.get(i);
    }

    public Table getLayout() {
        Table table = new Table();
        for (PackagePart p : projectParts) {
            table.add(p.getActor()).row();
        }
        return table;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Package ").append(getProjectName()).append(" by ").append(getUserName()).append("\n");
        for (int i = 0; i < projectParts.size(); i++) {
            sb.append(projectParts.get(i).toString());
        }
        return sb.toString();
    }
}
