/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	public ArrayList<NameSurferEntry> entries = new ArrayList<>();
	Color[] colors = {Color.BLACK, Color.red, Color.BLUE, Color.darkGray};

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entries.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		entries.add(entry);
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		setup();
		System.out.println("update");
		graphEntries();

	}

	private void graphEntries() {
		for (int i = 0; i < entries.size(); i++) {
			int colIndex = 0;
			graphEntry(entries.get(i), colors[colIndex]);
			colIndex++;
			if(colIndex == colors.length)
				colIndex = 0;
		}
	}

	private void graphEntry(NameSurferEntry entry, Color color) {
		double lineSpacing = getWidth() / NDECADES;
		try{
			for (int i = 0; i <= NDECADES-1; i++) {
				double lineX = lineSpacing * i;
				double lineStartY;
				double lineEndY;
				if(entry.getRank(i)!= 0){
					lineStartY = GRAPH_MARGIN_SIZE +  (getHeight()-2*GRAPH_MARGIN_SIZE) - (getHeight()-2*GRAPH_MARGIN_SIZE)/(double)MAX_RANK *((double)MAX_RANK - (double) entry.getRank(i));
					GLabel nameLabel = new GLabel(entry.getName() + entry.getRank(i),lineX, lineStartY);
					nameLabel.setColor(color);
					add(nameLabel);
					if (entry.getRank(i+1)!= 0){
						lineEndY = GRAPH_MARGIN_SIZE + (getHeight()-2*GRAPH_MARGIN_SIZE) - (getHeight()-2*GRAPH_MARGIN_SIZE)/(double)MAX_RANK *((double) MAX_RANK - (double) entry.getRank(i+1)) ;
					}else {
						lineEndY = GRAPH_MARGIN_SIZE + ((getHeight()-2*GRAPH_MARGIN_SIZE));
					}
				}
				else {
					lineStartY = GRAPH_MARGIN_SIZE + ((getHeight() - 2 * GRAPH_MARGIN_SIZE));
					GLabel nameLabel = new GLabel(entry.getName() + "*",lineX, lineStartY);
					nameLabel.setColor(color);
					add(nameLabel);
					if (entry.getRank(i + 1) != 0) {
						lineEndY = GRAPH_MARGIN_SIZE + (getHeight() - 2 * GRAPH_MARGIN_SIZE) * (1 - (double)MAX_RANK - (double)entry.getRank(i + 1)/(double)MAX_RANK);
					} else {
						lineEndY = GRAPH_MARGIN_SIZE + ((getHeight() - 2 * GRAPH_MARGIN_SIZE));
					}
				}
				GLine line  = new GLine(lineX, lineStartY, lineX + lineSpacing, lineEndY);
				line.setColor(color);
				add(line);


			}


		}catch (Exception ArrayIndexOutOfBoundsException){

		}


	}

	private void setup() {
		double lineSpacing = getWidth() / NDECADES;
		GLine upperLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(upperLine);
		GLine lowerLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(lowerLine);
		for (int i = 0; i < NDECADES; i++) {
			double lineX = lineSpacing * i;
			GLine line = new GLine(lineX, 0, lineX, getHeight());
			add(line);
			String yearStr = "" + (START_DECADE + i*10);
			GLabel year = new GLabel(yearStr);
			add(year, lineX+3, getHeight());

		}
	}


	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {

	}
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
