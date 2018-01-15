/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greyjan.packageorganizer.project;

import java.io.Serializable;

/**
 *
 * @author Jan Fic
 */
public class Package implements Serializable {

    private final String projectName, userName;

    public Package(String pN, String uN) {
        projectName = pN;
        userName = uN;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getUserName() {
        return userName;
    }
}
