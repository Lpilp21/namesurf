/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program
		implements NameSurferConstants {
	JLabel nameLabel;
	JTextField nameField;
	JButton graphButton;
	JButton clearButton;
	NameSurferDataBase dataBase;

	private NameSurferEntry entry;
	NameSurferGraph graph;

	/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		nameLabel = new JLabel("Name");
		add(nameLabel, SOUTH);

		nameField = new JTextField(10);
		add(nameField, SOUTH);

		graphButton = new JButton("Graph");
		add(graphButton, SOUTH);

		clearButton = new JButton("Clear");
		add(clearButton, SOUTH);

		graph = new NameSurferGraph();
		add(graph);
		addActionListeners();


		// You fill this in, along with any helper methods //
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == graphButton) {
			dataBase = new NameSurferDataBase("names-data.txt");
			String inputName = nameField.getText();
			entry = dataBase.findEntry(inputName);
			graph.addEntry(entry);
			graph.update();
		}
		if (e.getSource() == clearButton){
			graph.clear();
			graph.update();

		}




	}
}
