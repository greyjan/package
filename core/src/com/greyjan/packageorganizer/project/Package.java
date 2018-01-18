/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greyjan.packageorganizer.project;

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
}
