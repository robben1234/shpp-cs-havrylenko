package com.shpp.havrylenko.cs.task7.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    JTextField nameField = new JTextField(20);
    JButton graphButton = new JButton("Graph");
    JButton clearButton = new JButton("Clear");

    private NameSurferGraph graph = new NameSurferGraph();
    private NameSurferDataBase dataBase;

	/* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        // You fill this in, along with any helper methods //
        add(new JLabel("Name: "), NORTH);
        add(nameField, NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);
        add(graph);
        addActionListeners();

        try {
            NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        // You fill this in //
        if(e.getSource() == graphButton) {
            System.out.println("graph button clicked");
        } else if(e.getSource() == clearButton) {
            System.out.println("clear button clicked");
        }
    }
}
